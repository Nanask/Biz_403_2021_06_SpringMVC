<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${GALLERYS}" var="GA">
<style>
div.ga_box {
	display: flex;
}
div.ga_box div:first-of-type {
	flex:1;
}
div.ga_box div:last-of-type {
	flex:3;
}
</style>

<div class="ga_box">
	<div>
		<img src="${rootPath}/files/${GA.g_image}" width="300px">
	</div>
	<div>
		<h3>${GA.g_subject}</h3>
		<p>${GA.g_content}</p>										
	</div>
</div>

</c:forEach>