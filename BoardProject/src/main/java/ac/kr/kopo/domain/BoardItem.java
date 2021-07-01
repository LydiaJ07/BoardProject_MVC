package ac.kr.kopo.domain;

import java.util.Date;

public class BoardItem {
	private int boardId;
	private int id;
	private String title;
	private String writer;
	private String pw;
	private String date;
	private String content;
	private int count;
	
	@Override
	public String toString() {
		return "BoardItem [boardId=" + boardId + ", id=" + id + ", title=" + title + ", writer=" + writer + ", pw=" + pw
				+ ", date=" + date + ", content=" + content + ", count=" + count + "]";
	}
	
	
	public int getBoardId() {
		return boardId;
	}
	
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}	


}
