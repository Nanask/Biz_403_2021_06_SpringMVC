<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<mata name="viewport" cotent="width=device-width, initial-scale:1">
<%-- cotent="width=device-width, initial-scale:1 모든 디바이스 크기를 1대 1로 하라 --%>
<title>나의 홈페이지</title>
</head>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	width: 100wv;
	display: flex;
	flex-direction: column;
	height: 100vh;
	overflow: auto;
}

header {
	/*height: 30vh; */
	background: url("${rootPath}/static/images/header_background.jpg")
		no-repeat;
	background-size: 100% 100%;
	background-position: top;
	background-attachment: fixed;
	text-align: center;
	text-shadow: 1px 1px 1px black;
	color: white;
	padding: 2rem;
	/*height: 30vh;가 없다면 2rem으로 표시했을 때 중앙으로 정렬되게 할 수 있음*/
}

section#main_sec {
	flex: 1;
	width: 100wv;
	display: flex;
	flex-direction: column; /*버튼이 아래로 배치될 수 있게 설정하는 부분*/
	background: linear-gradient(to top,#67B26F,#4ca2cd);
	background-size: 100% 100%;
	background-attachment: fixed;
}

form {
	width: 90%;
	margin: 0 auto 10px auto;
}

fieldset {
	background-color: #eee;
	border: 1px solid green;
	border-radius: 10px;
	padding: 0.7rem; 
}
form label, form input{
	display: inline-block;
	margin: 5px;
	padding: 5px;
}
form label {
	width: 30%;
	text-align: right;
	font-weight: boldl
}

form input {
	width: 60%;
	outline: 0;
	border: #aaa;
	border-radius: 30px;
}

form input:hover {
	background-color: #999; 
}

form button.save {
	background-color: blue;
	color: white;
	
}

form button.reset {
	background-color: olive;
	color: white;
}

form button.list {
	background-color: yellow;
	color: white;
}

h2 {
	width: 90%;
	color: white;
	margin: 10px auto 0 auto;
	padding: 1rem;
	border: 1px solid #aaa;
}

table {
	border: 0;
	width: 90%;
	border-collapse: collapse;
	border-spacing: 0;
	margin: 10px auto;
	border-top: 1px solid greean;
}

tr {
	border-top: 1px solid greean;
	text-align: center;
}

tr:last-child {
	border-bottom: 1px solid green;
}

tr:nth-of-type(odd) {
	background-color: #eee;
}

tr:nth-of-type(even) {
	background-color: #ccc;
}

tr:hover td {
	background-color: #aaa;
	cursor: pointer;
}

td, th {
	border-right: 1px solid green;
	padding: 8px 12px;
	text-align: center;
}

td.number {
	text-align: right;
}

td:last-child, th:last-child {
	border: none;
}

div.btn_box {
	width: 90%;
	/* 
	table의 margin이 10px auto로 설정되어 있기 때문에
	top margin은 0으로 bottom margin은 10px 좌우는 auto로 설정함
	*/
	margin: 0px auto 10px auto;
	padding: 5px;
	text-align: right;
}

div.btn_box button {
	border: 0;
	/*outline: 0; 버튼을 눌렀을 때 테두리가 남게 하지 않기 위함*/
	outline: 0;
	padding: 12px 16px;
	margin-left: 10px;
	border-radius: 5px;
}

button:hover {
	box-shadow: 1px 1px 1px 1px black;
	cursor: pointer;
}
</style>
<body>
	<header>
		<h1>대한고교 성적처리</h1>
		<p>대한고교 성적처리 시스템 2021</p>
	</header>
	<section id="main_sec">
		<c:choose>
			<c:when test="${BODY =='SCORE_VIEW' }">
				<%@ include file="/WEB-INF/views/score/list.jsp"%>
			</c:when>
			
			<c:when test="${BODY =='STUDENT_LIST' }">

				<%@ include file="/WEB-INF/views/student/list.jsp"%>
			</c:when>
			
			<c:when test="${BODY =='STUDENT_INPUT' }">
			
				<%@ include file="/WEB-INF/views/student/input.jsp"%>
			</c:when>
			<c:otherwise>
			<%@ include file="/WEB-INF/views/main.jsp"%>
		</c:otherwise>
	
		
		</c:choose>
	</section>
</body>
<script>
/*
 * js 코드에서 event를 등록할 때 현재 화면에 없는 DOM 요소에 addEvent를 설정하면
 * 없는 함수라는 오류가 발생함
 * 그 이유는 현재 화면에 없는 DOM 요소를 querySelector하면 그 결과값이 null 이기 때문에 발생하는 문제이다.
 * 
 * js 코드를 통합하여 모음으로 관리할때는
 * addEvent를 하려고 하는 요소가 있는지를 먼저 검사한 후 addEvent를 수행해 주어야 한다.
 */
	let st_list = document.querySelector("button.student.list");
	let st_insert = document.querySelector("button.student.insert");
	let home = document.querySelector("button.student.home")
	
	// st_list가 null이 아닌 값이 있다면
	if(st_list) {
		st_list.addEventListener("click", (e) => {
			location.href="${rootPath}/student"
		})
	}
	if(home) {
		home.addEventListener("click", (e) => {
			location.href="${rootPath}/"	// 홈으로 가도록 설정
		})
	}
	if(st_insert) {
		home.addEventListener("click", (e) => {
			location.href="${rootPath}/student/insert"	// 홈으로 가도록 설정
		})
	}
		
</script>
</html>