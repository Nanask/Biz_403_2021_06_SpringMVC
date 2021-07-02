<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<c:set
	var="rootPath"
	value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<style>
	td.book_title {
		width: 20%;
		max-width: 0;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
	
	td img {
		padding: 0.5rem;
		border:1px solid green;
		width: 50px;
		height: 50%;
		
	}
</style>
<body>
	<h1>나의 서재</h1>
	<table id="my_books">
		<tr>
			<th>ISBN</th>
			<th>도서명</th>
			<th>이미지</th>
			<th>출판사</th>
			<th>저자</th>
			<th>출판일</th>
		</tr>
		<c:choose>
			<c:when test="${empty MY_BOOKS}">
				<tr><td colspan="6">데이터없음</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${MY_BOOKS}" var="BOOK" varStatus="i">
					<tr data-isbn="${BOOK.isbn}">
						<td>${BOOK.isbn}</td>
						<td class="book_title">${BOOK.title}</td>
						<td><img width="50px" src="${BOOK.image}"alt="${BOOK.title}"/></td>
						<%-- alt="${BOOK.title} 이미지가 없다면 이미지대신 타이틀을 보이게 하기 --%>
						<td>${BOOK.publisher}</td>
						<td>${BOOK.author}</td>
						<td>${BOOK.pubdate}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<%-- 
		<c:forEach
			items="${BLIST}"
			var="BList">
			<tr>
				<td>${BList.title}</td>
				<td><a href="${BList.link}">${BList.link}</a></td>
				<td><a href="${BList.image}">${BList.image}</a></td>
				<td>${BList.author}</td>
				<td>${BList.price}</td>
				<td>${BList.discount}</td>
				<td>${BList.publisher}</td>
				<td>${BList.isbn}</td>
				<td>${BList.description}</td>
				<td>${BList.pubdate}</td>
			</tr>
		</c:forEach>
		--%>
	</table>
</body>
<script>
//scirpt 오류를 
// table#my_books DOM 요소가 현재 화면에 있으면
let my_books = document.querySelector("table#my_books")
if(my_books) {
	my_books.addEventListener("click", (e) => {
		let td = e.target
		if(td.tagName === "TD") {
            let tr = td.closest("TR")
            let isbn = tr.dataset.isbn
            alert(isbn)
         }
		
	})
}
</script>
</html>