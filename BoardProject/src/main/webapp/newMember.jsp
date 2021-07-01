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
</head>
<body>

<%
String new_id = request.getParameter("id");
String new_pw = request.getParameter("pswd1");
String new_nickname = request.getParameter("nickname");
String new_birth = request.getParameter("yy") + "-" + request.getParameter("mm") + "-" + request.getParameter("dd");
String new_gender = request.getParameter("gender");

BoardMemberService boardMemberService = BoardMemberServiceImpl.getInstance();

boolean checkId = boardMemberService.isExist(new_id);
if (checkId == true) {
	
%>
        <script>
            alert("동일한 아이디가 이미 존재합니다.");
            window.history.back(); 
        </script>
<%
    } else {

    	BoardMember boardMember = new BoardMember();
    	
    	boardMember.setId(new_id);
    	boardMember.setPw(new_id);
    	boardMember.setNickName(new_nickname);
    	boardMember.setBirth(new_birth);
    	boardMember.setGender(new_gender);
    	
    	boardMemberService.create(boardMember);
    	
        %>
        <script>
            alert("회원 등록이 완료되었습니다.");
            location.href = "login.html";
        </script>
<%
    }

%>

</body>
</html>

