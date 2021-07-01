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
	
	//��ȸ��
	void increaseCount(int id); 
	
	//����¡
	List<BoardItem> pagingItems(int pageNum);
	int[] pagingBlock (int pageNum);
	
	//���� �� �̹���
	boolean isHot(BoardItem boarditem);
	boolean isNew(BoardItem boarditem);

}
