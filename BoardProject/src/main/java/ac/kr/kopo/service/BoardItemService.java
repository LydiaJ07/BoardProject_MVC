package ac.kr.kopo.service;

import java.util.List;

import ac.kr.kopo.domain.BoardItem;

public interface BoardItemService {
	void create(BoardItem boarditem);
	BoardItem selectOne(int id);
	List<BoardItem> selectAll();
	void update(BoardItem boarditem);
	void delete(BoardItem boarditem);
	int itemCount();
	
	//조회수
	void increaseCount(int id); 
	
	//페이징
	List<BoardItem> pagingItems(int pageNum);
	int[] pagingBlock (int pageNum);
	
	//제목 옆 이미지
	boolean isHot(BoardItem boarditem);
	boolean isNew(BoardItem boarditem);

}
