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
String input_id = request.getParameter("id");
String input_pw = request.getParameter("pswd");

BoardMemberService boardMemberService = BoardMemberServiceImpl.getInstance();

boolean checkId = boardMemberService.isExist(input_id);
if (checkId == false) {

%>
        <script>
            alert("존재하지 않는 아이디 입니다.");
            window.history.back(); 
        </script>
<%
    } 


boolean checkPw = boardMemberService.pwCheck(input_id, input_pw);
if (checkId == false) { 
%>
        <script>
            alert("비밀번호가 일치하지 않습니다.");
            window.history.back(); 
        </script>
<%
    } else {
    	BoardMember boardMember = boardMemberService.selectOne(input_id);

        session.setAttribute("id", boardMember.getId());
        session.setAttribute("nickname", boardMember.getNickName());
%>
        <script>
            alert("<%= boardMember.getNickName() %>님, 반갑습니다.");
            window.location.href = "list.jsp";
        </script>
<%
    }

%>

</body>
</html>

