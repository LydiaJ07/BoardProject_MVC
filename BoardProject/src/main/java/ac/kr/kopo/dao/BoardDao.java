package ac.kr.kopo.dao;

import java.util.List;

import ac.kr.kopo.domain.Board;

public interface BoardDao {
	//C
	void create(Board board);						
	//R
	Board selectOne(int id);
	List<Board> selectAll();
	
	//U
	void update(Board board);
	//D
	void delete(Board board);
}
