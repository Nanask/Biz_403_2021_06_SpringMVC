<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<body>
	<h1>너무 당황했어요</h1>
	<a href="${rootPath}/company/insert">출판사 추가</a>
	<a href="${rootPath}/author/insert">저자 추가</a>
	<a href="${rootPath}/books/insert">도서 추가</a>

</body>
</html>