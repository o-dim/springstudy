package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;

@Mapper
public interface UploadMapper {
	// getUploadList
	public int getUploadCount();
	public List<UploadDTO> getUploadList(Map<String, Object> map);
	
	// addUpload
	public int addUpload(UploadDTO uploadDTO);
	public int addAttach(AttachDTO attachDTO);
	
	// getUploadByNo
	public UploadDTO getUploadByNo(int uploadNo);
	public List<AttachDTO> getAttachList(int uploadNo);
	
	// display, download›
	public AttachDTO getAttachbyNo(int attachNo);
	
	// increaseDownloadCount
	public int increaseDownloadCount(int attachNo);
	
	// delete
	public int removeUpload(int uploadNo);
}
