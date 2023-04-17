package com.gdu.app04.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app04.domain.BoardDTO;

@Repository
public class BoardDAO {
	//jdbc 방식
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	// 모든 메소드에서 사용할 Private method 설정해두기 1
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // ojdbc8.jar 메모리 로드 
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// 모든 메소드에서 사용할 Private method 설정해두기 2
	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// DAO 메소드 (BoardServiceImpl클래스에서 사용하게 됨)
	// 1. 목록
	public List<BoardDTO> selectBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	// 2. 상세
	public BoardDTO selectBoardByNo(int board_no){
		BoardDTO board = null;
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}
	// 3. 삽입
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	// 4. 수정
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	// 5. 삭제
	public int removeBoard(int board_no) {
		int result = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
}
