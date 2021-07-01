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
            alert("댓글이 삭제되었습니다.");
            location.href = document.referrer;
        });
    </script>
</head>
<body>

<%
int targetID = Integer.parseInt(request.getParameter("id"));

BoardCommentService boardCommentService = BoardCommentServiceImpl.getInstance();
BoardComment boardComment = boardCommentService.selectOne(targetID);

boardCommentService.delete(boardComment);
%>

</body>
</html>

