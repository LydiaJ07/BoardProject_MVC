package ac.kr.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.kr.kopo.domain.Board;
import ac.kr.kopo.domain.BoardItem;

public class BoardItemDaoImpl implements BoardItemDao {
	private static Connection conn;
	private static BoardItemDaoImpl instance;

	public static BoardItemDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null)
			instance = new BoardItemDaoImpl();
		return instance;
	}

	private BoardItemDaoImpl() throws ClassNotFoundException, SQLException {
		// 생성자에서 커넥션 얻기
		conn = DBConn.getConnection();
	}
	
	@Override
	public void create(BoardItem boardItem) {
		String QueryTxt = "insert into boardItem (boardId, title, writer, content) "
							+ "values (?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setInt(1, boardItem.getBoardId());
			pstmt.setString(2, boardItem.getTitle());
			pstmt.setString(3, boardItem.getWriter());
			pstmt.setString(4, boardItem.getContent());
			
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public BoardItem selectOne(int id) {
		String QueryTxt = "select * from boardItem where id=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BoardItem boardItem = new BoardItem();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardItem.setBoardId(rset.getInt(1));
				boardItem.setId(rset.getInt(2));
				boardItem.setTitle(rset.getString(3));
				boardItem.setWriter(rset.getString(4));
				boardItem.setPw(rset.getString(5));
				boardItem.setDate(rset.getString(6).substring(0,10));
				boardItem.setContent(rset.getString(7));
				boardItem.setCount(rset.getInt(8));
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return boardItem;
	}

	@Override
	public List<BoardItem> selectAll() {
		String QueryTxt = "select * from boardItem order by id desc";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardItem> boardItemList = new ArrayList<BoardItem>();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardItem boardItem = new BoardItem();
				
				boardItem.setBoardId(rset.getInt(1));
				boardItem.setId(rset.getInt(2));
				boardItem.setTitle(rset.getString(3));
				boardItem.setWriter(rset.getString(4));
				boardItem.setPw(rset.getString(5));
				boardItem.setDate(rset.getString(6).substring(0,10));
				boardItem.setContent(rset.getString(7));
				boardItem.setCount(rset.getInt(8));
				
				boardItemList.add(boardItem);
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardItemList;
	}
	
	//오버로딩(검색 기능)
	public List<BoardItem> selectAll(String searchWord, String searchField) {
		String QueryTxt = "select count(*) from board where ? like ?;";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardItem> boardItemList = new ArrayList<BoardItem>();
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			pstmt.setString(1, searchField);
			pstmt.setString(2, "%" + searchWord + "%");
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardItem boardItem = new BoardItem();
				
				boardItem.setBoardId(rset.getInt(1));
				boardItem.setId(rset.getInt(2));
				boardItem.setTitle(rset.getString(3));
				boardItem.setWriter(rset.getString(4));
				boardItem.setPw(rset.getString(5));
				boardItem.setDate(rset.getString(6).substring(0,10));
				boardItem.setContent(rset.getString(7));
				boardItem.setCount(rset.getInt(8));
				
				boardItemList.add(boardItem);
			}
			
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardItemList;
	}

	@Override
	public void update(BoardItem boardItem) {
		String QueryTxt = "update boardItem set title=?, content=?, count=? where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setString(1, boardItem.getTitle());
			pstmt.setString(2, boardItem.getContent());
			pstmt.setInt(3, boardItem.getCount());
			pstmt.setInt(4, boardItem.getId());
			
			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(BoardItem boardItem) {
		String QueryTxt = "delete from boardItem where id=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
			
			pstmt.setInt(1, boardItem.getId());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public int itemCount() {
		String QueryTxt = "select count(*) from boardItem";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(QueryTxt);
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
