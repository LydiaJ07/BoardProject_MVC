package ac.kr.kopo.dao;

import java.util.List;

import ac.kr.kopo.domain.BoardMember;

public interface BoardMemberDao {
	//C
	void create(BoardMember boardMember);						
	//R
	BoardMember selectOne(String id);
	List<BoardMember> selectAll();
	//U
	void update(BoardMember boardMember);
	//D
	void delete(BoardMember boardMember);
	
	//count
	int countOne(String id);
}
