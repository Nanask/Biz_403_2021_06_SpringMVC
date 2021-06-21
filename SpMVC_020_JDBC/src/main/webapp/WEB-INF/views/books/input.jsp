<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>
<style>
form#book_input input.search {
width: 30%;
}

</style>
<script>
	var rootPath = "${rootPath}"
</script>
<script src="${rootPath}/static/js/book_input.js?ver=2021-06-21-001"></script>

<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<section class="main_sec">
	<form action="POST">
		<fieldset>
			<legend>도서정보 등록</legend>
			<div>
				<label>ISBN</label> <input name="bk_isbn" id="bk_isbn"
					placeholder="">
			</div>
			<div>
				<label>도서명</label> <input name="bk_title" id="bk_isbn"
					placeholder="">
			</div>
			<div>
				<label>출판사</label> <input name="bk_ccode" id="bk_ccode"
					placeholder="">
			</div>
			<div>
				<label>저자</label> <input name="bk_acode" id="bk_acode"
					placeholder="">
			</div>
			<div>
				<label>출판년도</label> <input name="bk_date" id="bk_date"
					placeholder="">
			</div>
			<div>
				<label>가격</label> <input name="bk_price" id="bk_price"
					placeholder="">
			</div>
			<div>
				<label>페이지수</label> <input name="bk_pages" id="bk_pages"
					placeholder="">
			</div>
		</fieldset>
		<div class="btn_box">
			<div>
				<button type="button" class="btn_save_book">저장</button>
			</div>
			<div>
				<button type="reset" class="btn_reset_book">새로작성</button>
			</div>
			<div>
				<button type="button" class="btn_list_book">List</button>
			</div>
		</div>
	</form>
</section>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>

</body>
<script>

</script>
</html>