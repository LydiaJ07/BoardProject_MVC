package ac.kr.kopo.dao;

import java.util.List;

import ac.kr.kopo.domain.BoardItem;

public interface BoardItemDao {
	
	//C
		void create(BoardItem boardItem);						
	//R
		BoardItem selectOne(int id);
		List<BoardItem> selectAll();

	//U
		void update(BoardItem boardItem);
	//D
		void delete(BoardItem boardItem);
		
	//count
		int itemCount();
}
