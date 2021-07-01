package ac.kr.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.kr.kopo.domain.Board;
import ac.kr.kopo.domain.BoardItem;
import ac.kr.kopo.domain.BoardMember;

public class BoardMemberDaoImpl implements BoardMemberDao {
	private static Connection conn;
	private static BoardMemberDaoImpl instance;

	public static BoardMemberDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardMemberDaoImpl();
		}
		return instance;
	}

	private BoardMemberDaoImpl() throws ClassNotFoundException, SQLException {
		conn = DBConn.getConnection();
	}

	@Override
	public void create(BoardMember boardMember) {
		String QueryTxt = "insert into boardMember values (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(QueryTxt);

			pstmt.setString(1, boardMember.getId());
			pstmt.setString(2, boardMember.getPw());
			pstmt.setString(3, boardMember.getNickName());
			pstmt.setString(4, boardMember.getBirth());
			pstmt.setString(5, boardMember.getGender());

			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BoardMember selectOne(String id) {
		String QueryTxt = "select * from boardMember where id=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BoardMember boardMember = new BoardMember();

		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardMember.setId(rset.getString(1));
				boardMember.setPw(rset.getString(2));
				boardMember.setNickName(rset.getString(3));
				boardMember.setBirth(rset.getString(4));
				boardMember.setGender(rset.getString(5));
			}
			
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardMember;
	}

	@Override
	public List<BoardMember> selectAll() {
		String QueryTxt = "select * from boardMember";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<BoardMember> boardMembers = new ArrayList<BoardMember>();

		try {
			pstmt = conn.prepareStatement(QueryTxt);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardMember boardMember = new BoardMember();
				
				boardMember.setId(rset.getString(1));
				boardMember.setPw(rset.getString(2));
				boardMember.setNickName(rset.getString(3));
				boardMember.setBirth(rset.getString(4));
				boardMember.setGender(rset.getString(5));
				
				boardMembers.add(boardMember);
			}
			
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardMembers;
	}

	@Override
	public void update(BoardMember boardMember) {
		String QueryTxt = "update boardItem set pw=?, nickname=?, birth=?, gender=? where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setString(1, boardMember.getPw());
			pstmt.setString(2, boardMember.getNickName());
			pstmt.setString(3, boardMember.getBirth());
			pstmt.setString(4, boardMember.getGender());
			pstmt.setString(5, boardMember.getId());
			
			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(BoardMember boardMember) {
		String QueryTxt = "delete from boardMember where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setString(1, boardMember.getId());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int countOne(String id) {
		String QueryTxt = "select count(*) from boardMember where id=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;

		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				count = rset.getInt(1);
			}
			
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}



}
