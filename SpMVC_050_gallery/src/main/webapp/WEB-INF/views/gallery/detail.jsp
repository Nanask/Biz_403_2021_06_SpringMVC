<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<div id="gallery info">
	<h3>제목 : ${GFLIST[0].g_subject}</h3>
	<h3>작성일 : ${GFLIST[0].g_date}</h3>
	<h3>작성시각 : ${GFLIST[0].g_time}</h3>
	<h3>작성자 : ${GFLIST[0].g_writer}</h3>
	<h3>내용 : ${GFLIST[0].g_content}</h3>
</div>
<style>
div#gallery_files {
	display: flex;
	flex-wrap: wrap;
}
div#gallery_files img {
	margin: 2px;
}
</style>
<div id="gallery_files">
	<c:forEach items="${GFLIST}" var="FILE">
		<img src="${rootPath}/files/${FILE.f_upname}" height="100px">
	</c:forEach>
</div>