<%@ page import="ac.kr.kopo.service.*" %>
<%@ page import="ac.kr.kopo.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*, java.util.*, javax.sql.*, java.io.*, java.net.URL" %>
<%@page contentType="text/html; charset=utf-8" %>
<% request.setCharacterEncoding("utf-8");%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="css/css.css">
    <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"></script>

    <script>
        function editClick(id, password) {
            var pw = prompt("비밀번호를 입력해주세요");

            if(pw == "") {
                editClick();
            } else if(pw == null) {
                return;
            } else if(pw != password) {
                alert("비밀번호가 일치하지 않습니다.");
                editClick();
            } else {
                location.href = "edit.jsp?id=" + id;
            } 
        } 

        $(document).ready(function () {
                $(".toggle").click(function () {
                    $(".under_toggle").toggle();
                });
            });
    </script>

</head>
<body>
    <div class="board_wrap">
        <div class="board_title">
            <strong>자유게시판</strong>
            <p>여러분들의 생각을 자유롭게 남겨주세요.</p>
        </div>

        <%
        //로그인 여부 판단해서 저장
   		String id = (String)session.getAttribute("id");
    	String nickname = (String)session.getAttribute("nickname");
   	 	request.setAttribute("id", id);
    	request.setAttribute("nickname", nickname);
        
        //글 아이디
        int targetID = Integer.parseInt(request.getParameter("id"));
        
        BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
        
        //조회수 올리기
        boardItemService.increaseCount(targetID);
        
        //게시글 정보 받아오기 
        BoardItem boardItem = boardItemService.selectOne(targetID);
        request.setAttribute("boardItem", boardItem);
        
        //게시글에 해당하는 정보 받아오기
        BoardCommentService boardCommentService = BoardCommentServiceImpl.getInstance();
        List<BoardComment> boardComments = boardCommentService.selectAll(targetID);
        
        request.setAttribute("boardComments", boardComments);
        %>

        <div class="board_view_wrap">
            <div class="board_view">
                <div class="title">
                    ${boardItem.title}
                </div>
                <div class="info">
                    <dl>
                        <dt>번호</dt>
                        <dd>${boardItem.id}</dd>
                    </dl>
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>${boardItem.writer}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>${boardItem.date}</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>${boardItem.count}</dd>
                    </dl>
                </div>
                <div class="cont">
                    <pre>${boardItem.content}</pre>
                </div>
            </div>

            <!-- 댓글 -->
            <div class="comment_wrap">
                <div class="toggle">
                    <img src="./css/chat.png" width="16px">
                    <span id="cmtspan">댓글 목록▼</span>
                </div>
                <div class="under_toggle">
            		<c:forEach var="boardComment" items="${boardComments}">
                    <div class="comment_show">
                        <div class="comment_header">
                            <div class="writer"><b><c:out value="${boardComment.writer}" /></b></div>
                            <div class="time"><c:out value="${boardComment.date}" /></div>
                            <c:if test="${id eq boardComment.writer}">
                            <div class="delete"><a href="deleteCmt.jsp?id=${boardComment.id}"><b>삭제</b></a></div>
                            </c:if>
                            <!-- <div class="edit"><b>수정</b></div> -->
                        </div>
                        <div class="comment_body"><c:out value="${boardComment.content}" /></div>
                    </div>
                	</c:forEach>
                	<c:if test="${id != null}">
	                    <div class="comment_write">
	                        <form method="post">
	                            <input type="hidden" name="cmt_postId" value="<%= targetID %>">
	                            <input type="hidden" name="cmt_writer" value="${id}">
	                            <span id="cmt_writer" name="cmt_writer" type="text"><b><c:out value="${id}" /></b></span>
	                            <input id="cmt_content" name="cmt_content" type="text" placeholder="댓글 작성">
	                            <button id="cmt_submit" formaction="commentWrite.jsp">등록</button>
	                    </form>
	                    </div>
	                </c:if>
                </div>
            </div>
            <div class="bt_wrap">
                <a href="list.jsp" class="on">목록</a>
                    <c:if test="${id eq boardItem.writer}">
		                <a id="editButton" href="edit.jsp?id=${boardItem.id}">수정/삭제</a>
				    </c:if>
            </div>
        </div>
    </div>
</body>
</html>