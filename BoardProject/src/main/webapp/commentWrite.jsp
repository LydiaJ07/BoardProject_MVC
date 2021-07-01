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
            alert("댓글이 등록되었습니다.");
            location.href = document.referrer;
        });
    </script> 
</head>
<body>

<%
int new_cmt_postId = Integer.parseInt(request.getParameter("cmt_postId"));
String new_cmt_writer = request.getParameter("cmt_writer");
String new_cmt_content = request.getParameter("cmt_content");

BoardCommentService boardCommentService = BoardCommentServiceImpl.getInstance();
BoardComment boardComment = new BoardComment();

boardComment.setPostId(new_cmt_postId);
boardComment.setWriter(new_cmt_writer);
boardComment.setContent(new_cmt_content);

boardCommentService.create(boardComment);
%>

</body>
</html>