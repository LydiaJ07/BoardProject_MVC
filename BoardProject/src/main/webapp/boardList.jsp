<%@ page import="ac.kr.kopo.service.*" %>
<%@ page import="ac.kr.kopo.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� ���</title>
</head>
<body>
	<b>�Խ��� ����Ʈ</b>
<%
	BoardService boardService = BoardServiceImpl.getInstance();
	List<Board> boards = boardService.selectAll();

 for (Board board: boards) {
 	out.println("<p>" + board.getBoardId() + " : " + board.getBoardName() + "</p>");
 	}
%>


</body>
</html>