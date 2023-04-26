package com.gdu.app08.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app08.domain.BoardDTO;

// dao를 mapper의 board.xml의 인터페이스로 쓸거임 
@Mapper
public interface BoardMapper {

	public List<BoardDTO> selectBoardList();
	public BoardDTO selectBoardByNo(int boardNo);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int boardNo);
	public int deleteBoardList(List<String> boardNoList);
	public int selectBoardCount();

}