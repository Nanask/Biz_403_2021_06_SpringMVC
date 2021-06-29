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

p b {
	color: blue;
}

nav#main_nav {
	background-color: #2DB400;
	display: flex;
	justify-content: center;
	align-items: center;
}

nav#main_nav form {
	margin:0.6rem;
	width: 50%;

}
nav#main_nav input {
	width: 100%;
	padding: 8px;
	border: 0;
	outline: 0;
	border-radius: 10px;
}

section.content_box {
	border: 1px solid green;
	padding: 12px 16px;
	display: flex;
	flex-wrap: wrap;
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

@media (max-width:1000px) {
	section.content_box div.content {
		width: 20%;
		
	}
}

@media (max-width:700px) {
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


</style>
</head>
<body>
	<nav id="main_nav">
		<form>
			<input name="search" placeholder="도서명을 입력하고 Enter치라구!">
		</form>
	</nav>
	<section class="content_box">
		<c:forEach items="${BOOKS}" var="BOOK">
			<div class="content">
				<img src="${BOOK.image}" />
				<div>
					<p class="title">
						<a href="${BOOK.link}" target="_NEW"> <!-- target 새로운창열기 NEW -->
							${BOOK.title}
						</a>
					</p>
					<!-- <p class="desc">${BOOK.description}</p> -->
					<p class="author">
						<strong>저자</strong>${BOOK.author}</p>
					<p class="publisher">
						<strong>출판사 : </strong>${BOOK.publisher}</p>
						<button class="insert">내 서재등록</button>
				</div>
			</div>
		</c:forEach>
	</section>

</body>
</html>