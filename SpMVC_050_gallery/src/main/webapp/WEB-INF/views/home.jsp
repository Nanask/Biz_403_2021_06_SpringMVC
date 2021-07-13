<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style>
h1 {
	text-align: center;
	margin: 15px auto;
	padding: 10px;
}

form div {
	
}
</style>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<body>
	<h1>집에 갈래?</h1>
	<%@ include file="/WEB-INF/views/include/include_nav.jspf"%>
	<c:choose>
		<c:when test="${BODY eq 'GA-INPUT'}">
			<%@ include file="/WEB-INF/views/gallery/input.jsp"%>
		</c:when>
		<c:when test="${BODY eq 'GA-LIST'}">
			<%@ include file="/WEB-INF/views/gallery/list.jsp"%>
			<a href="${rootPath}/gallery/input">이미지등록</a>
		</c:when>
		<c:when test="${BODY eq 'GA-DETAIL'}">
			<%@ include file="/WEB-INF/views/gallery/detail.jsp"%>
			<a href="${rootPath}/gallery">리스트로</a>
		</c:when>
		<c:when test="${BODY eq 'GA-DETAIL-V2'}">
			<%@ include file="/WEB-INF/views/gallery/detail2.jsp"%>
		</c:when>
		<c:when test="${BODY eq 'JOIN'}">
			<%@ include file="/WEB-INF/views/member/join.jsp"%>
		</c:when>
		<c:when test="${BODY eq 'LOGIN'}">
			<%@ include file="/WEB-INF/views/member/login.jsp"%>
		</c:when>
		<c:otherwise>
			<a href="${rootPath}/gallery/input">이미지등록</a>
		</c:otherwise>
	</c:choose>

	<c:forEach items="${FILES}" var="FILE">
		<a href="${rootPath}/files/${FILE}" target="_NEW"></a>
		<img src="${rootPath}/files/${FILE}" width="100px" height="100px" />
	</c:forEach>
</body>
<script>
let main_nav = document.querySelector("nav#main_nav")

//스크립트 오류를 막기 위한 것
	if(main_nav) {
		//e를 넣고 안넣고의 차이가 뭐지
		main_nav.addEventListener("click", (e) => {
			let menu = e.target
			// LI에만 이벤트가 걸리도록 설정
			if(menu.tagName === "LI") {
				
				if(menu.id === "join") {
					//member라는 클래스를 만들고 거기에 join을 만들겠다?
					location.href = "${rootPath}/member/join"
				}else if(menu.id === "login") {
					location.href = "${rootPath}/member/login/nav"
				}else if(menu.id === "logout") {
					location.href = "${rootPath}/member/logout"
				}else if(menu.id === "image_create") {
					location.href = "${rootPath}/gallery/input"
				}else if(menu.id === "home") {
					location.href = "${rootPath}/"
				}
			}
		})
	}

</script>
</html>