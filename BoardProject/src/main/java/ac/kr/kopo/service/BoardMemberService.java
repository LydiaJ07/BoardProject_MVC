package ac.kr.kopo.service;

import java.util.List;

import ac.kr.kopo.domain.BoardMember;

public interface BoardMemberService {
	//C
	void create(BoardMember boardMember);						
	//R
	BoardMember selectOne(String id);
	List<BoardMember> selectAll();
	//U
	void update(BoardMember boardMember);
	//D
	void delete(BoardMember boardMember);
	
	boolean isExist(String id);
	public boolean pwCheck(String id, String pw);
}
