<%@ page import="ac.kr.kopo.service.*" %>
<%@ page import="ac.kr.kopo.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.net.URL" %>
<%@ page contentType="text/html; charset=utf-8" %>
<% request.setCharacterEncoding("utf-8");%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <link rel="stylesheet" href="css/css.css">
    <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"></script>
    <script>
        function submitform() { 
            if (check() == true) {
                document.writeForm.submit(); 
            } else {
                return;
            }
        }

        function check() {
            if ($("[name='title']").val() ==""){
                alert("제목을 입력해주세요");
                $("[name='title']").focus();
            } else if($("[name='content']").val() ==""){
                alert("내용용 을 입력해주세요");
                $("[name='content']").focus();
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
<%
        //로그인 여부 판단해서 저장
   		String id = (String)session.getAttribute("id");
    	String nickname = (String)session.getAttribute("nickname");
   	 	request.setAttribute("id", id);
    	request.setAttribute("nickname", nickname);
%>
    <div class="board_wrap">
        <div class="board_title">
            <strong>자유게시판</strong>
            <p>여러분들의 생각을 자유롭게 남겨주세요.</p>
        </div>
        <form name="writeForm" method="post" action="writeNew.jsp">
         <input type="hidden" name="boardId" value="1">
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" name="title" placeholder="제목 입력"></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>글쓴이</dt>
                            <dd>
                            <input type="hidden" name="writer" value="${id}">
                            <b><c:out value="${id}" /></b>
                            </dd>
                        </dl>
 <!--                        <dl>
                            <dt>비밀번호</dt>
                            <dd><input type="password" name="pw" placeholder="비밀번호 입력"></dd>
                        </dl> -->
                    </div>
                    <div class="cont">
                        <textarea name="content" placeholder="내용 입력"></textarea>
                    </div>
                </div>
                <div class="bt_wrap">
                    <a href="javascript: submitform()" class="on">등록</a>
                    <a href="list.jsp">취소</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>