package ac.kr.kopo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.kr.kopo.dao.BoardItemDao;
import ac.kr.kopo.dao.BoardItemDaoImpl;
import ac.kr.kopo.domain.BoardItem;
import ac.kr.kopo.util.Helper;

public class BoardItemServiceImpl implements BoardItemService{
	private BoardItemDao boardItemDao;
	private static BoardItemServiceImpl instance;
	private Helper helper = new Helper();
	
	final private static int printVolume = 7; //한 페이지 당 출력 단위
	final private static int blockVol = 5; //한 블록에 출력할 페이지 수 단위
	
	public static BoardItemServiceImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new BoardItemServiceImpl();
		}
			return instance;
	}
	
	private BoardItemServiceImpl() throws ClassNotFoundException, SQLException {
		boardItemDao = BoardItemDaoImpl.getInstance();
	}

	@Override
	public void create(BoardItem boarditem) {
		boardItemDao.create(boarditem);
	}

	@Override
	public BoardItem selectOne(int id) {
		// TODO Auto-generated method stub
		return boardItemDao.selectOne(id);
	}

	@Override
	public List<BoardItem> selectAll() {
		// TODO Auto-generated method stub
		return boardItemDao.selectAll();
	}

	@Override
	public void update(BoardItem boarditem) {
		boardItemDao.update(boarditem);
		
	}

	@Override
	public void delete(BoardItem boarditem) {
		boardItemDao.delete(boarditem);
	}

	
	public int itemCount() {
		return boardItemDao.itemCount();
	}
	
	
	//조회수 올리는 메소드
	public void increaseCount(int id) {
		BoardItem boardItem = selectOne(id);
		boardItem.setCount(boardItem.getCount() + 1);
		
		boardItemDao.update(boardItem);
	}
	
	public boolean isNew(BoardItem boarditem) {
		String today = helper.today();
//		boolean isNew = false;
//		
//		if (boarditem.getDate().equals(today)) {
//			isNew = true;
//		}
//		
//		return isNew;
		return boarditem.getDate().equals(today);
	}
	
	public boolean isHot(BoardItem boarditem) {
		boolean isHot = false;
		
		if (boarditem.getCount() >= 50) {
			isHot = true;
		}
		
		return isHot;
	}
	
	public List<BoardItem> pagingItems(int pageNum) {
		List<BoardItem> allItems = boardItemDao.selectAll();
		List<BoardItem> boardItemsforPage = new ArrayList<>();
		
		int totalItems = itemCount();

		int totalPage = (totalItems / printVolume) + 1;
		int startNum = printVolume * (pageNum - 1);
		int endNum = printVolume * pageNum;
		
		//마지막 페이지의 경우에는
		if (pageNum == totalPage) {
			endNum = totalItems;
		}
		
		for(int i = startNum; i < endNum; i++) {
			boardItemsforPage.add(allItems.get(i));	
		}
		
		return boardItemsforPage;
	}
	
	public int[] pagingBlock (int pageNum) {
		int[] pageNumbers = new int[3 + blockVol];
		//페이지 넘버에는 차례로 이전 버튼, blockVol만큼의 페이지 버튼, 다음 버튼, 마지막 버튼에 대한 페이지 값이 들어감
		int totalItems = itemCount();
		int totalPage = (totalItems / printVolume) + 1;
		
		
		int totalPageBlock = (totalPage / blockVol) + 1; //총 페이징 블록 수
		
		if (totalPage % blockVol == 0) {
			totalPageBlock = (totalPage / blockVol);
		}
		
	    int pageBlockNum = ((pageNum - 1) / blockVol) + 1; //현재 페이지가 위치하는 블록의 번호
	    int startNum = blockVol * (pageBlockNum - 1) + 1; //현재 페이징 블록의 첫 페이지 번호 
	    int endNum = blockVol * pageBlockNum; //현재 페이징 블록의 마지막 번호 
		
	    if (pageBlockNum == totalPageBlock) {
	    	endNum = totalPage;
	    }
	    

	    //이전 버튼
	    if (pageNum == 1) {
	    	pageNumbers[0] = 1;
	    } else {
	    	pageNumbers[0] = pageNum-1;
	    }
		
	    //페이지 버튼
    	int index = 1;
	    for (int i = startNum; i <= endNum; i++) {
	    	pageNumbers[index] = i;
	    	index++;
	    }
	    
	    //다음버튼
	    if (pageNum == totalPage) {
	    	pageNumbers[6] = totalPage;
	    } else {
	    	pageNumbers[6] = pageNum + 1;
	    }
	    
	    //마지막 버튼
	    pageNumbers[7] = totalPage;
	    
		return pageNumbers;
	}


}
