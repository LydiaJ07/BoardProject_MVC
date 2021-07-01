package ac.kr.kopo.service;

import java.util.List;

import ac.kr.kopo.domain.Board;
import ac.kr.kopo.domain.BoardItem;

public interface BoardService {
	
	void create(Board board);
	Board selectOne(int id);
	List<Board> selectAll();
	void update(Board board);
	void delete(Board board);
}
