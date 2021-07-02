<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<style>
* {
	box-sizing: border-box;
	padding: 0;
	margin: 0;
}

body {
	display: flex;
	flex-direction: column;
	height: 100vh;
}

p b {
	color: blue;
}

nav#main_nav {
	background-color:#00C43B; 
	display: flex;
	justify-content: center;
	align-items: center;
	background-size: 100% 100%;
}

nav#main_nav form {
	margin: 0.6rem;
	width: 50%;
}

nav#main_nav input {
	width: 100%;
	padding: 8px;
	border: 0;
	outline: 0;
	border-radius: 10px;
}

nav#main_nav select {
	padding: 8px;
	width: 10%;
	border-radius: 3px;
	/*
	vertical-align: center;
	*/
}

section.content_box {
	border: 1px solid green;
	padding: 12px 16px;
	display: flex;
	flex-wrap: wrap;
	/*
	검색 결과가 표시되는 영역은 scroll 지정하고
	상단의 검색창(nav)는 화면에 고정하기
	
	1. body 에
		display : flex,
		flex-direction : column
		height:100vh
	2. 검색결과창에
		flex : 1
		overflow:auto
	*/
	flex: 1;
	overflow: auto;
}

section.content_box div.content {
	display: flex;
	border: 1px solid blue;
	margin: 5px auto;
	width: 30%;
	height: 30vh;
	overflow: auto;
}

section.content_box div.content img {
	flex: 1;
	width: 30%;
	/*height: 50%;*/
}

section.content_box div.content div {
	flex: 2;
	margin: 5px;
}

@media ( max-width :1000px) {
	section.content_box div.content {
		width: 20%;
	}
}

@media ( max-width :700px) {
	section.content_box div.content {
		width: 90%;
	}
}

a {
	text-decoration: none;
}

a:hover {
	color: green;
}

table {
	width: 95%;
	border-collapse: collapse;
	border-spacing: 0;
	margin:0 auto;
}

th, td {
	white-space: nowrap;
	padding: 16px 12px;
	border-top: 1px solid #ddd;
}
/* 가장 끝에 나타난 td에는 아래쪽에도 선을 만들기*/
tr:last-child td{
	border-bottom: 1px solid #ddd;
}
</style>
</head>
<body>
	<nav id="main_nav">
		<c:if test="${CAT == 'BOOK'}">
			<c:set var="pHolder" value="도서 검색" />
		</c:if>
		<c:if test="${CAT == 'MOVIE'}">
			<c:set var="pHolder" value="영화 검색" />
		</c:if>
		<c:if test="${CAT == 'NEWS'}">
			<c:set var="pHolder" value="뉴스 검색" />
		</c:if>
		<select name="category">
			<option value="BOOK"
				<c:if test="${CAT == 'BOOK'}">selected="selected"</c:if>>도서검색
			</option>
			<option value="MOVIE"
				<c:if test="${CAT == 'MOVIE'}">selected="selected"</c:if>>영화검색
			</option>
			<%-- selected="select" 선택된 것을 select 표시하라 --%>
			<option value="NEWS"
				<c:if test="${CAT == 'NEWS'}">selected="selected"</c:if>>뉴스검색
			</option>
		</select>
		<form>
			<input name="search" placeholder="${pHolder}를 입력하고 Enter치라구!">
		</form>
	</nav>
	<section class="content_box">
		<%@ include file="/WEB-INF/views/book/book_list.jsp"%>
		<%@ include file="/WEB-INF/views/movie_list.jsp"%>
		<%@ include file="/WEB-INF/views/news_list.jsp"%>
		<%-- if문을 쓰지 않는 이유는 각각의 item을 갖고 있기 때문에 일치 하지 않으면 바뀌지 않으므로 if문을 사용하지 않아도 된다. --%>
		
		<c:if test="${not empty MY_BOOKS}">
		<%@ include file ="/WEB-INF/views/book/list_view.jsp" %>
		</c:if>
	</section>

</body>
<script>

let category = document.querySelector("select[name='category']")
category.addEventListener("change",(e)=>{
	
	let value = category.value
	//alert(value)
	//location.href= "${rootPath}/?category=" +value;
	location.href= "${rootPath}/naver/" +value;
})
</script>
</html>