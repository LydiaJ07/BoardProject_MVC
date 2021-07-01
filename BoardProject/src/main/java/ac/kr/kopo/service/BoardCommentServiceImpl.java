package ac.kr.kopo.service;

import java.sql.SQLException;
import java.util.List;

import ac.kr.kopo.dao.BoardCommentDao;
import ac.kr.kopo.dao.BoardCommentDaoImpl;
import ac.kr.kopo.dao.BoardItemDaoImpl;
import ac.kr.kopo.domain.BoardComment;

public class BoardCommentServiceImpl implements BoardCommentService {
	private BoardCommentDao boardCommentDao;
	private static BoardCommentServiceImpl instance;
	
	public static BoardCommentServiceImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new BoardCommentServiceImpl();
		}
			return instance;
	}
	
	private BoardCommentServiceImpl() throws ClassNotFoundException, SQLException {
		boardCommentDao = BoardCommentDaoImpl.getInstance();
	}
	
	public void create(BoardComment boardComment) {
		boardCommentDao.create(boardComment);
	}

	@Override
	public BoardComment selectOne(int id) {
		return boardCommentDao.selectOne(id);
	}
	
	public List<BoardComment> selectAll(int postId) {
		return boardCommentDao.selectAll(postId);
	}

	public void delete(BoardComment boardComment) {
		boardCommentDao.delete(boardComment);
	}


}
