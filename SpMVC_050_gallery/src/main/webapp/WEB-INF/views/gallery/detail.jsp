<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<div id="gallery info">
	<h3>제목 : ${GFLIST[0].g_subject}</h3>
	<h5>SEQ : ${GFLIST[0].g_seq}</h5>
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
<div>
	<button class="gallery update">수정</button>
	<button class="gallery update">삭제</button>
</div>

<script>
let update_button = document.querySelector("button.gallery.update")
let update_button = document.querySelector("button.gallery.delete")

update_button.addEventListener("click", () => {
	alert(일련번호"${GFLIST[0].g_seq}인 게시물을 수정")
	// 확인창만 있음
	location.href = "${rootPath}/gallery/update?g_seq=${GFLIST[0].g_seq}"
	// 변수로 직접 사용할 수 있음
	
delete_button.addEventListener("click", () => {
	if(confirm("일련번호 ${GFLIST[0].g_seq} 인 게시물 삭제??")) {
		// 확인, 취소 창이 같이 있음
		location.replace("${rootPath}/gallery/delete?g_seq=${GFLIST[0].g_seq}")
		// 뒤로가기가 되지 않음
	}
})
</script>