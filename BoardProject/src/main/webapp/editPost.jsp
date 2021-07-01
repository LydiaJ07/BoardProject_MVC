<%@ page import="ac.kr.kopo.service.*" %>
<%@ page import="ac.kr.kopo.domain.*" %>
<%@page import="java.sql.*, javax.sql.*, java.io.*, java.net.URL" %>
<%@page contentType="text/html; charset=utf-8" %>
<% request.setCharacterEncoding("utf-8");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"></script>
    <title>Document</title>
    <script>
        $(document).ready(function() {
            // 다큐먼트가 다 불러와졌을 때 실행하고 싶은 내용 넣기
            alert("글 수정이 완료되었습니다.");
            location.href = "list.jsp"
        });
    </script>
</head>
<body>

<%
String new_title = request.getParameter("title");
String new_content = request.getParameter("content");
int targetID = Integer.parseInt(request.getParameter("id"));

BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
BoardItem boardItem = boardItemService.selectOne(targetID);

boardItem.setTitle(new_title);
boardItem.setContent(new_content);
boardItemService.update(boardItem);
%>

</body>
</html>

