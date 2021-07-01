package ac.kr.kopo.service;

import java.sql.SQLException;
import java.util.List;

import ac.kr.kopo.dao.BoardMemberDao;
import ac.kr.kopo.dao.BoardMemberDaoImpl;
import ac.kr.kopo.domain.BoardMember;

public class BoardMemberServiceImpl implements BoardMemberService{
	private BoardMemberDao boardMemberDao;
	private static BoardMemberServiceImpl instance; 
	
	public static BoardMemberServiceImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new BoardMemberServiceImpl();
		}
			return instance;
	}
	
	private BoardMemberServiceImpl() throws ClassNotFoundException, SQLException {
		boardMemberDao = BoardMemberDaoImpl.getInstance();
	}

	@Override
	public void create(BoardMember boardMember) {
		boardMemberDao.create(boardMember);		
	}

	@Override
	public BoardMember selectOne(String id) {
		return 	boardMemberDao.selectOne(id);
	}

	@Override
	public List<BoardMember> selectAll() {
		return 	boardMemberDao.selectAll();
	}

	@Override
	public void update(BoardMember boardMember) {
		boardMemberDao.update(boardMember);	
	}

	@Override
	public void delete(BoardMember boardMember) {
		boardMemberDao.delete(boardMember);
	}
	
	//아이디 존재 판단
	public boolean isExist(String id) {
		int index = boardMemberDao.countOne(id);
		boolean isExist = false;
		
		//0이면 false, 1이면 true
		if(index == 1) {
			isExist = true;
		}
		
		return isExist;
	}
	
	public boolean pwCheck(String id, String pw) {
		BoardMember boardMember = boardMemberDao.selectOne(id);
		boolean isCorrect = false;
		if (boardMember.getPw().equals(pw)) {
			isCorrect = true;
		}
		
		return isCorrect;
	}
}
