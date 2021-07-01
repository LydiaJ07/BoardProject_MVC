package ac.kr.kopo.service;


import java.sql.SQLException;
import java.util.List;

import ac.kr.kopo.dao.BoardCommentDao;
import ac.kr.kopo.dao.BoardCommentDaoImpl;
import ac.kr.kopo.dao.BoardDao;
import ac.kr.kopo.dao.BoardDaoImpl;
import ac.kr.kopo.domain.Board;

public class BoardServiceImpl implements BoardService{
	private BoardDao boardDao;
	private static BoardServiceImpl instance;
	
	public static BoardServiceImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new BoardServiceImpl();
		}
			return instance;
	}
	
	private BoardServiceImpl() throws ClassNotFoundException, SQLException {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	@Override
	public void create(Board board) {
		boardDao.create(board);
	}

	@Override
	public Board selectOne(int id) {
		// TODO Auto-generated method stub
		return boardDao.selectOne(id);
	}

	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		return boardDao.selectAll();
	}

	@Override
	public void update(Board board) {
		boardDao.update(board);
		
	}

	@Override
	public void delete(Board board) {
		boardDao.delete(board);
		
	}


}
