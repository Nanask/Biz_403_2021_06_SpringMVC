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
<h1>내 도서관</h1>
<input name="search">
<input name="st_name">
<button>전송</button>

<script>
document.querySelector("button").addEventListener("click", () =>{
	
	let search = document.querySelector("input[name='search']").value
	let st_name = document.querySelector("input[name='st_name']").value
	alert(search)
	
	// 서버로 fetch(ajax)로 전송하기
	// 1. JSON 데이터로 만들기
	
	let json = { search : search, st_name}
	
	// 2. json type의 데이터를 ajax로 전송하기 위한 문자열 화
	// Serialize 라고 한다.
	let jsonString = JSON.stringify(json)
	// 제이슨을 Serialize 한다??
	alert(jsonString)			
			
	//	3. fetch method를 이용하여 서버로 POST 방식으로 전송하기	
	fetch("${rootPath}/api",{
		//여기에 포스트로 보낼 옵션을 작성하자
		
		method:"POST",
		
		//문자열 화 하여 전달
		body : jsonString,
		
		//json에게 잘 받으라고 통보
		headers : {
			"content-Type" : "application/json" // 원래 UTF8을 전송해 줬지만 오류가 발생하면서 전송하지 않기로 함
		}
	})
})
</script>

</body>
</html>