package ac.kr.kopo.dao;

import java.util.List;

import ac.kr.kopo.domain.BoardComment;

public interface BoardCommentDao {
	void create(BoardComment boardComment);
	BoardComment selectOne(int id);
	List<BoardComment> selectAll(int postId);
	void delete(BoardComment boardComment);
	
}
