<%@ page import="ac.kr.kopo.service.*" %>
<%@ page import="ac.kr.kopo.domain.*" %>
<%@page import="java.sql.*, javax.sql.*, java.io.*, java.net.URL" %>
<%@page contentType="text/html; charset=utf-8" %>
<% request.setCharacterEncoding("utf-8");%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 | 글 수정</title>
    <link rel="stylesheet" href="css/css.css">
    <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"></script>
        <script>
        function deleteBt (id) {
            var re = confirm("정말로 글을 삭제하시겠습니까?");

            if (re == true) {
                $("#formtochange").attr("action","deletePost.jsp");
                document.getElementById('formtochange').submit();
            } else {
                return;
            }

        }
    </script>
</head>
<body>
    <div class="board_wrap">
        <div class="board_title">
            <strong>자유게시판</strong>
            <p>여러분들의 생각을 자유롭게 남겨주세요.</p>
        </div>

        <%
		int targetID = Integer.parseInt(request.getParameter("id"));
        
        BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
        
      //게시글 정보 받아오기 
        BoardItem boardItem = boardItemService.selectOne(targetID);
        request.setAttribute("boardItem", boardItem);


        %>
        <form method="post" id="formtochange" action="editPost.jsp">
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" name="title" placeholder="제목 입력" value='${boardItem.title}'></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>글쓴이</dt>
                            <dd>
                                <!-- <input type="text" name="writer" placeholder="글쓴이 입력" value='${boardItem.writer}''> -->
                                ${boardItem.writer}
                            </dd>
                        </dl>
                        <dl>
                            <dt>작성된 날짜</dt>
                            <dd>
                                ${boardItem.date}
                            </dd>
                        </dl>
                    </div>
                    <div class="cont">
                        <textarea name="content">${boardItem.content}</textarea>
                    </div>
                    <input type="hidden" name="id" value='<%= targetID %>'>
                </div>
                <div class="bt_wrap">
                    <a href="javascript:{}" onclick="document.getElementById('formtochange').submit();">수정</a>
                    <a href="javascript: deleteBt('<%= targetID %>')">삭제</a>
                    <a href="view.jsp?id='<%= targetID %>'" class="on">취소</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>