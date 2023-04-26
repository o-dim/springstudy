package com.gdu.app09.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PageUtil {
	private int page; 			// 현재 페이지 
	private int totalRecord;	// 전체 레코드 개수
	private int recordPerPage;  // 한 페이지에 표시할 레코드 개수
	private int begin;			// 한 페이지에 표시할 레코드 시작번호
	private int end;			// 한 페이지에 표시할 레코드 종료번호	
	
	private int pagePerBlock = 5; // 한 블록에 표시할 페이지 개수(임의로 정한다)
	private int totalPage; // 전페 페이지 개수(계산한다)
	private int beginPage; // 한 블록에 표시할 페이지의 시작번호(계산한다)
	private int endPage; // 한 블록에 표시할 페이지의 종료번호(계산한다)
	
	public void setPageUtil(int page, int totalRecord, int recordPerPage) {
		this.page = page;
		this.totalRecord = totalRecord;
		this.recordPerPage = recordPerPage;
		
		// begin, end 계산
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		// totalpage
		totalPage = totalRecord / recordPerPage ;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	public String getPagination(String path) {
		
		StringBuilder sb = new StringBuilder();
		
		if(beginPage == 1) {
			sb.append("<span class=\"hidden\">◀</span>");
		} else {
			sb.append("<a class=\"link\" href=\"" + path + "?page=" + (beginPage - 1) + ">◀</a>\n");
		}
		// 페이지 번호 : 현재 페이지는 링크가 없다
		for(int p = beginPage; p <= endPage; p++) {
			if(p == page) {
				sb.append("<span class=\"strong\">" + p + "</span>");
			} else {
				sb.append("<a class=\"link\" href = \"" + path + "?page=" + p + "\">" + p + "</a>");
			} 
		}
		
		// 다음 블록 : 마지막 블록은 다음 블록이 없고, 나머지 블록은 다음 블록이 있다
		if(endPage == totalPage) {
			sb.append("<span class=\"hidden\">►</span>");
		} else {
			sb.append("<a class=\"link\" href=\"" + path + "?page=" + (endPage + 1) + "\">►</a>");
		}
		
		return sb.toString();
	}
	
}

