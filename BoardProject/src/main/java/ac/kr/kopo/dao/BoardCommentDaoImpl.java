package ac.kr.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.kr.kopo.domain.BoardComment;
import ac.kr.kopo.domain.BoardItem;

public class BoardCommentDaoImpl implements BoardCommentDao{
	private static Connection conn;
	private static BoardCommentDaoImpl instance;
	
	public static BoardCommentDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new BoardCommentDaoImpl();
		return instance;
	}
	
	private BoardCommentDaoImpl() throws ClassNotFoundException, SQLException {
		conn = DBConn.getConnection();
	}
	
	//댓글 쓰기
	public void create(BoardComment boardComment) {
		String QueryTxt = "insert into boardcmt (postId, writer, content) values (?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setInt(1, boardComment.getPostId());
			pstmt.setString(2, boardComment.getWriter());
			pstmt.setString(3, boardComment.getContent());
			
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public BoardComment selectOne(int id) {
		String QueryTxt = "select * from boardcmt where id=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BoardComment boardComment = new BoardComment();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardComment.setId(rset.getInt(1));
				boardComment.setPostId(rset.getInt(2));
				boardComment.setWriter(rset.getString(3));
				boardComment.setContent(rset.getString(4));
				boardComment.setDate(rset.getString(5).substring(0, 11));
				
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return boardComment;
	}
	//해당 게시글의 댓글 출력하기
	public List<BoardComment> selectAll(int PostId) {
		String QueryTxt = "select * from boardcmt where postId=?";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardComment> boardCommentList = new ArrayList<BoardComment>();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setInt(1, PostId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardComment boardComment = new BoardComment();
				boardComment.setId(rset.getInt(1));
				boardComment.setPostId(rset.getInt(2));
				boardComment.setWriter(rset.getString(3));
				boardComment.setContent(rset.getString(4));
				boardComment.setDate(rset.getString(5).substring(0, 11));

				boardCommentList.add(boardComment);
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardCommentList;
	}
	
	//댓글 지우기
	public void delete(BoardComment boardComment) {
		String QueryTxt = "delete from boardcmt where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setInt(1, boardComment.getId());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
