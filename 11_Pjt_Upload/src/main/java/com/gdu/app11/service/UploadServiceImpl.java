package com.gdu.app11.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;
import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;
import com.gdu.app11.util.PageUtil;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@Service
@AllArgsConstructor // field의 @autowired 처리를 위해 
public class UploadServiceImpl implements UploadService {
	// field
	private UploadMapper uploadMapper;
	private MyFileUtil myFileUtil;
	private PageUtil pageUtil;

	
	@Override
	public void getUploadList(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int uploadCount = uploadMapper.getUploadCount();
		
		int recordPerPage = 10;
		
		pageUtil.setPageUtil(page, uploadCount, recordPerPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		List<UploadDTO> uploadList = uploadMapper.getUploadList(map);
		model.addAttribute("uploadList", uploadList);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/upload/list.do"));
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public int addUpload(MultipartHttpServletRequest multipartRequest) {
		// ~ upload 테이블에 uploadDTO 넣기 ~
		
		// 제목과 내용이 파라미터로 넘어옴
		String uploadTitle = multipartRequest.getParameter("uploadTitle");
		String uploadContent = multipartRequest.getParameter("uploadContent");
		
		// DB로 보낼 UploadDTO 만들기
		UploadDTO uploadDTO = new UploadDTO();
		uploadDTO.setUploadTitle(uploadTitle);
		uploadDTO.setUploadContent(uploadContent);
		
		// DB로 UploadDTO 보내기
		int uploadResult = uploadMapper.addUpload(uploadDTO);
		
		// ~ attach 테이블에 attachDTO 넣기 ~
		
		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files");
		
		// 첨부된 파일이 있는지 체크 5
		if(files != null && files.isEmpty() == false) {
			// 첨부된 파일 목록 순회
			for(MultipartFile multipartFile : files) {
				
				// 예외 처리
				try {
					// 부파일의 저장경로
					String path = myFileUtil.getPath();
					
					// 첨부파일의 저장경로가 없으면 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 첨부 파일의 원래 이름
					String originName = multipartFile.getOriginalFilename();
					originName = originName.substring(originName.lastIndexOf("\\") + 1); // IE는 전체 경로가 오기 때문에 마지막 역슬래시 뒤에 있는 파일명만 사용한다
					
					// 첨부 파일의 저장 이름
					String filesystemName = myFileUtil.getFileSystemName(originName);
					
					// 첨부파일의 File 객체
					File file = new File(dir, filesystemName);
					
					// 첨부 파일을 HDD에 저장
					multipartFile.transferTo(file); // 실제로 서버에 저장된다
					
					// 썸네일 (첨부파일이 이미지인 경우에만 썸네일이 가능)
					// 첨부파일의 Content-Type 확인
					String contentType = Files.probeContentType(file.toPath());
					
					// DB에 저장할 썸네일 유무 정보처
					boolean hasThumbnail = contentType != null && contentType.startsWith("image");
					// 첨부파일의 content-type이 이미지이면 썸네일을 생성하게 만들기
					if(hasThumbnail) {
						
						// HDD에 썸네일 저장하기 (thumbnailator 디펜던시)
						File thumbnail = new File(dir, "s_" + filesystemName);
						Thumbnails.of(file)
									.size(50, 50)
									.toFile(thumbnail);
						
					}
					
					// DB에 첨부파일 정보 저장하기
					// DB로 보낼 AttachDTO 만들
					AttachDTO attachDTO = new AttachDTO();
					attachDTO.setFilesystemName(filesystemName);
					attachDTO.setHasThumbnail(hasThumbnail? 1 : 0);
					attachDTO.setOriginName(originName);
					attachDTO.setPath(path);
					attachDTO.setUploadNo(uploadDTO.getUploadNo());
					
					// DB에 보내기
					uploadMapper.addAttach(attachDTO);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return uploadResult;
	}

	
	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.getUploadByNo(uploadNo));
		model.addAttribute("attachList", uploadMapper.getAttachList(uploadNo));
	}
	
	@Override
	public ResponseEntity<byte[]> display(int attachNo) {
		AttachDTO attachDTO = uploadMapper.getAttachbyNo(attachNo);
		
		ResponseEntity<byte[]> image = null;
		try {
			File thumbnail = new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName());
			image = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(thumbnail), HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	@Override
	public ResponseEntity<Resource> download(int attachNo, String userAgent) {
		// 다운로드 할 파일, 이름, 경로 불러오기
		AttachDTO attachDTO = uploadMapper.getAttachbyNo(attachNo);
		
		// 다운로드 할 첨부파일 file 객체 -> Resource 객체
		File file = new File(attachDTO.getPath(), attachDTO.getFilesystemName());
		Resource resource = new FileSystemResource(file);
		
		// 다운로드 할 첨부파일의 존재 여부 확인(다운로드 실패를 반환)
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
			
		}
		
		// 다운로드 횟수 증가
		uploadMapper.increaseDownloadCount(attachNo);
		
		// 다운로드 되는 파일명(원래 첨부파일 이름, Useragent(브라우저)에 따른 인코딩 세팅)
		String originName = attachDTO.getOriginName();
		try {
			// IE (UserAgent에 Trident가 포함되어있음)
			if(userAgent.contains("Trident")) {
				originName = URLEncoder.encode(originName, "UTF-8").replace("+", " ");
			}
			// Edge (UserAgent에 Edg가 포함됨)
			else if(userAgent.contains("Edg")) {
				originName = URLEncoder.encode(originName, "UTF-8");
			}
			// other
			else {
				originName = new String(originName.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 응답헤더 만들기
		MultiValueMap<String, String> responseHeader = new HttpHeaders();
		responseHeader.add("Content-Disposition", "attachment; filename=" + originName);
		responseHeader.add("Content-Length", file.length() + "");
		
		// 응답
		return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Resource> downloadAll(int uploadNo) {
		// 모든 첨부파일을 zip로 다운로드 하는 서비스
		// zip 파일이 저장될 경로
		String tempPath = myFileUtil.getTempPath();
		File dir = new File(tempPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// zip 파일의 이름
		String tempfileName = myFileUtil.getTempfileName();
				
		// zip 파일의 File 객체
		File zfile = new File(tempPath, tempfileName);
		// zip 파일을 생성하기 위한 Java IO Stream 선언
		BufferedInputStream bin = null;    // 각 첨부 파일을 읽어 들이는 스트림
		ZipOutputStream zout = null;  // zip 파일을 만드는 스트림
		
		// 다운로드 할 첨부 파일들의 정보(경로, 원래 이름, 저장된 이름) 가져오기
		List<AttachDTO> attachList = uploadMapper.getAttachList(uploadNo);		// zip 파일을 생성하기 위한 Java IO Stream 선언

		try {
			
			// 첨부파일들을 하나씩 순회하면서 읽어들인 후 zip 파일에 추가하기
			for(AttachDTO attachDTO : attachList) {
				// zip 파일에 첨부파일 이름 등록(첨부파일의 원래이름)
				ZipEntry zipEntry = new ZipEntry(attachDTO.getOriginName());
				zout.putNextEntry(zipEntry);
				zout.closeEntry();
				
				// zip 파일에 첨부파일 추가하기
				bin = new BufferedInputStream(new FileInputStream(new File(attachDTO.getPath(), attachDTO.getFilesystemName())));
				
				// bin -> zout 으로 복사하기
				byte[] b = new byte[1024]; // 1kb 단위로 복사하겠다
				int readByte = 0;
				while((readByte = bin.read(b)) != -1) {
					zout.write(b, 0, readByte);
				}
				bin.close();
				
				uploadMapper.increaseDownloadCount(attachDTO.getAttachNo());
			}
			zout.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 할 첨부 zip 파일의 File 객체 -> Resource 객체
		Resource resource = new FileSystemResource(zfile);
		
		// 다운로드 응답헤더 만들기
		MultiValueMap<String, String> responseHeader = new HttpHeaders();
		responseHeader.add("Content-Disposition", "attachment; filename=" + tempfileName);
		responseHeader.add("Content-Length", zfile.length() + "");

		return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
	}
	
	@Override
	public int removeUpload(int uploadNo) {
		
		// 삭제할 첨부 파일들의 정보
		List<AttachDTO> attachList = uploadMapper.getAttachList(uploadNo);
		
		// 첨부 파일이 있으면 삭제
		if(attachList != null && attachList.isEmpty() == false) {
			
			// 삭제할 첨부 파일들을 순회하면서 하나씩 삭제
			for(AttachDTO attachDTO : attachList) {
				
				// 삭제할 첨부 파일의 File 객체
				File file = new File(attachDTO.getPath(), attachDTO.getFilesystemName());
				
				// 첨부 파일 삭제
				if(file.exists()) {
					file.delete();
				}
				// 첨부 파일이 썸네일을 가지고 있다면 "s_"로 시작하는 썸네일이 함께 존재하므로 함께 제거해야 한다.
				if(attachDTO.getHasThumbnail() == 1) {
					
					// 삭제할 썸네일의 File 객체
					File thumbnail = new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName());
					
					// 썸네일 삭제
					if(thumbnail.exists()) {
						thumbnail.delete();
					}
					
				}
				
			}
			
		}
		
		// DB에서 uploadNo값을 가지는 데이터를 삭제
		// 외래키 제약조건에 의해서(ON DELETE CASCADE) UPLOAD 테이블의 데이터가 삭제되면
		// ATTACH 테이블의 데이터도 함께 삭제된다.
		int removeResult = uploadMapper.removeUpload(uploadNo);
		
		return removeResult;
		
	}
}
