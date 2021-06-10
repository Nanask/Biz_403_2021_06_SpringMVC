<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서정보 관리</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

section#main_sec {
	width: 100%;
	margin: 5px auto;
	border: 1px solid #999;
	display: flex;
	justify-content: space-around;
}

section#main_sec article {
	flex: 1;
	border: 1px solid green;
	margin: 6px 3px;
	padding: 5px
}

section#main_sec article ul {
	margin: 0;
	padding: 0;
}

section#main_sec article li {
	margin: 0 20px;
	padding: 0 10px;
}

section#ad_sec {
	display: flex;
	width: 100%;
	border: 1px soild #999;
}

section#ad_sec article {
	height: 300px;
	flex:1;
	width: 100%; 
	margin : 6px 3px;	
	background-size: 100% 100%;
	border-radius: 5px;
}
/*
어떤 요소의 순서대로 속성을 지정할 때
nth-child(순서), nth-of-type(순서)
다음과 같이 tag가 구성되어 있을 때
<div>
	<p>1
	<p>2
	<div>3
	<p>4
<div>

nth-child는 무조건 포함된 tag에 모두 일련번호를 부여한다
div p:nth-child(4) : <p>4 tag에 대하여	

nth-of-type은 포함된 tag중 지정한 tag만 일련번호를 부여한다. div속에 있는 p tag에만 일련번호를 붙인다.
div p:nth-of-type(3) : <p>4 tag에 대하여
*/
section#ad_sec article:nth-of-type(1) {
	background:url("${rootPath}/static/images/ad_01.png") no-repeat; 
	background-size: 100% 100%;
}
section#ad_sec article:nth-of-type(2) {
	background:url("${rootPath}/static/images/ad_02.jpg") no-repeat;
	background-size: 100% 100%; 
}
section#ad_sec article:nth-of-type(3) {
	background:url("${rootPath}/static/images/ad_03.jpg") no-repeat;
	background-size: 100% 100%; 
}
section#ad_sec article:nth-of-type(4) {
	background:url("${rootPath}/static/images/ad_04.jpg") no-repeat; 
	background-size: 100% 100%;
}
section#ad_sec article:nth-of-type(5) {
	background:url("${rootPath}/static/images/ad_05.jpg") no-repeat;
	background-size: 100% 100%;
}
</style>

</head>
<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<section id="main_sec">
		<article>
			<h3>출판사</h3>
			<ol>
				<li>출판사정보</li>
				<li>출판사정보</li>
				<li>출판사정보</li>
				<li>출판사정보</li>
				<li>출판사정보</li>
			</ol>
		</article>
		<article>
			<h3>도서정보</h3>
			<ul>
				<li>도서정보</li>
				<li>도서정보</li>
				<li>도서정보</li>
				<li>도서정보</li>
				<li>도서정보</li>
			</ul>
		</article>

		<article>
			<h3>저자정보</h3>
		</article>
	</section>
	<section id="ad_sec">
		<article></article>
		<article></article>
		<article></article>
		<article></article>
		<article></article>
	</section>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>


</body>
</html>