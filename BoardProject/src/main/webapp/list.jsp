<%@ page import="ac.kr.kopo.service.*" %>
<%@ page import="ac.kr.kopo.domain.*" %>
<%@ page import="ac.kr.kopo.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*, javax.sql.*, java.io.*, java.net.URL, java.util.*" %>
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
        function postClick (id) {
            location.href="view.jsp?id="+id;
        }
    </script>
</head>
<body>
    <%
    //서비스 객체
	BoardItemService boardItemSerivce = BoardItemServiceImpl.getInstance();
	int totalContents = boardItemSerivce.itemCount();
    
    //페이지 번호
    int pageNum = 1;
    
    //파라메터가 없을 경우 예외 처리
    if (request.getParameter("pageNum")==null) {
        pageNum = 1;
    } else {
    	pageNum = Integer.parseInt(request.getParameter("pageNum"));
    }
    
    request.setAttribute("pageNum", pageNum);
	
	
    //로그인 여부 판단해서 저장
    String id = (String)session.getAttribute("id");
    String nickname = (String)session.getAttribute("nickname");
    request.setAttribute("id", id);
    request.setAttribute("nickname", nickname);
    
    //검색어 가져오기
    String searchField = request.getParameter("searchIndex");
    String searchWord = request.getParameter("searchTarget");
    request.setAttribute("searchField", searchField);
    request.setAttribute("searchWord", searchWord);
    
    if(searchWord == null || searchWord.equals("")) {
    	//페이지 아이템
    	List<BoardItem> boardItems = boardItemSerivce.pagingItems(pageNum);
    	request.setAttribute("boardItems", boardItems);
    } else {
    	//페이지 아이템

    }
    
	//페이지 블록
	int[] pages = boardItemSerivce.pagingBlock(pageNum);
	request.setAttribute("pages", pages);
	
	//현재 시간
	Helper helper = new Helper();
	String today = helper.today();
	request.setAttribute("today", today);
	
    %>
    <header>
        <div class="nav_wrap">
            <a class="nav_board" href="list.jsp?board=1">공지사항</a>
            <a class="nav_board" href="list.jsp?board=2">자유게시판</a>
            <nav>
                <ul class="nav_menu">
	                <c:choose>
						<c:when test="${id == null}"> 
							<li><a href='login.html'>LOGIN</a></li>
							<li><a href="signup.html">SINGUP</a></li>
						</c:when> 
						<c:otherwise> 
							<li><a href='logoutAction.jsp'>LOGOUT</a></li>
						</c:otherwise> 
					</c:choose>		
                </ul>
            </nav>
        </div>
    </header>
    <div class="board_wrap">
        <div class="board_title">
            <strong>자유게시판</strong>
            <p>여러분들의 생각을 자유롭게 남겨주세요.</p>
        </div>
        <p style="padding:4px; font-size: 12px;">총 게시글 수 : <%= totalContents %></p>
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">글쓴이</div>
                    <div class="date">작성일</div>
                    <div class="count">조회수</div>
                </div>
	    <c:forEach var="boarditem" items="${boardItems}">
		    <div onclick="postClick('${boarditem.id}');" class="postDiv">
		   		<div class="num"><c:out value="${boarditem.id}" /></div>
		    	<div class="title"><c:out value="${boarditem.title}" />
		    	<c:if test="${boarditem.date eq today}">
		    		<img src="./css/new.png">
		    	</c:if>
		    	<c:if test="${boarditem.count >= 50}">
		    		<img src="./css/hot.png">
		    	</c:if>
		        </div>
		    	<div class="writer"><c:out value="${boarditem.writer}" /></div>
		        <div class="date"><c:out value="${boarditem.date}" /></div>
		        <div class="count"><c:out value="${boarditem.count}" /></div>
		    </div>
	    </c:forEach>
	</div>		
    <div class="search_wrap">
        <div class="searchBox">
            <form method="post" name="searchForm" action="list.jsp">
                <select name="searchIndex" id="Index" >
                    <option value="title">글 제목</option>
                    <option value="content">본문</option>
                    <option value="writer">작성자</option>
                </select>
                <input type="text" name="searchTarget" placeholder="검색어">
                <button id="searchBt" type="submit" form="searchForm">
                    <img src="./css/search.png" style="height: 12px; padding: 4px;">
                </button>
            </form>
        </div>
    </div>
    <c:if test="${id != null}">
    <div class="bt_wrap">
        <a href="write.jsp" class="on">글 작성</a>
    </div>
    </c:if>
    <!-- 페이징 블록 -->
    <div class="board_page">
    <a href='list.jsp?pageNum=1' class='bt prev'><<</a>
    <a href='list.jsp?pageNum=${pages[0]}' class='bt prev'><</a>
	
	<c:forEach var="i" begin="1" end="5">
		<c:choose>
			<c:when test="${pages[i] == pageNum}"> 
				<a href='list.jsp?pageNum=${pages[i]}' class='num on'>
				<c:out value="${pages[i]}" />
				</a>
			</c:when> 
			<c:otherwise> 
				<a href='list.jsp?pageNum=${pages[i]}' class='num'>
				<c:out value="${pages[i]}" />
				</a>
			</c:otherwise> 
		</c:choose>
	</c:forEach>
	
	<a href='list.jsp?pageNum=${pages[6]}' class='bt next'>></a>
	<a href='list.jsp?pageNum=${pages[7]}' class='bt next'>>></a>
    </div>
</body>
</html> 