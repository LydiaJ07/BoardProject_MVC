package ac.kr.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.kr.kopo.domain.Board;

public class BoardDaoImpl implements BoardDao {
	private static Connection conn;
	private static BoardDaoImpl instance;
	
	public static BoardDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null)
			instance = new BoardDaoImpl();
		return instance;
	}
	
	private BoardDaoImpl() throws ClassNotFoundException, SQLException {
		// 생성자에서 커넥션 얻기
		conn = DBConn.getConnection();
	}

	@Override
	//새로운 게시판 생성하기
	public void create(Board board) {
		String QueryTxt = "insert into board values (?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setInt(1, board.getBoardId());
			pstmt.setString(2, board.getBoardName());
			
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	//게시판에서 하나만 불러오기
	public Board selectOne(int id) {
		String QueryTxt = "select * from board where id=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Board board = new Board();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				board.setBoardId(rset.getInt(1));
				board.setBoardName(rset.getString(2));
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return board;
	}

	@Override
	public List<Board> selectAll() {
		String QueryTxt = "select * from board";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBoardId(rset.getInt(1));
				board.setBoardName(rset.getString(2));
				boardList.add(board);
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	public void update(Board board) {
		String QueryTxt = "update board set boardName=? where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setString(1, board.getBoardName());
			pstmt.setInt(2, board.getBoardId());
			
			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Board board) {
		String QueryTxt = "delete from board where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setInt(1, board.getBoardId());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
