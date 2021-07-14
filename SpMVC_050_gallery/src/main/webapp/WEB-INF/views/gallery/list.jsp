<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

div.image {
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
	width: 90%;
	margin: 20px auto;
}
/*
div.out_box {
	width: 80%;
}
*/

div.ga_box {
	display: flex;
	width: 30%;
	border: 1px solid #F17F42;
	margin: 20px;
	height: 40%;
}

div.ga_image {
	margin: 10px auto;
	text-align: center;
}

img {
	margin: 10px auto;
}

div.ga_text {
	text-align: center;
	padding: 10px 0px 10px 0px;
}

div.title {
	background-color: #F17F42;
	padding: 10px 0px 10px 0px;
}

div.content {
	padding: 30px;
}
/*
div.ga_box div:first-of-type {
	flex: 1;
}

div.ga_box div:last-of-type {
	flex: 3;
}
*/
</style>
<div class="image">
	<c:forEach items="${GALLERYS}" var="GA">
		<%-- <div class="out_box">--%>
		<div class="ga_box">
			<div class="ga_image">
				<c:if test="${empty GA.g_image}">
					<img src="${rootPath}/files/noimage.png" width="90%">
				</c:if>
				<c:if test="${not empty GA.g_image}">
					<img src="${rootPath}/files/${GA.g_image}" width="90%">
				</c:if>
				<div class="ga_text">
					<div class="title">
						<a href="${rootPath}/gallery/detail2/${GA.g_seq}">${GA.g_subject}(${GA.g_seq})</a>
					</div>
					<div class="content">
						<p>${GA.g_content}</p>
					</div>
				</div>
			</div>
		<%-- </div>--%>
		</div>
	</c:forEach>
</div>

<%@include file="/WEB-INF/views/include/include_page.jspf" %>