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
	<h1>출판사 정보 등록</h1>
	<form method="POST">
<!--<div><label>출판사코드</label><input name="cp_code"></div>  --> 	<!-- companyService에서 자동으로 코드를 완성하도록 만들었기 때문에 필요없어짐-->
	<div><label>출판사명</label><input name="cp_title"></div>
	<div><label>대표자명</label><input name="cp_ceo"></div>
	<div><label>전화번호</label><input name="cp_tel"></div>
	<div><label>주소</label><input name="cp_addr"></div>
	<div><button>저장</button></div>
	</form>
	<div>
	<lable>삭제할 코드</lable>
	<input id="cpcode" name ="cpcode">
	<button class="btn_delete">삭제</button>
	
	</div>
	<script>
	// const : 상수를 선언하는 키워드
	// 코드가 진행되는 동안 값이 변형되면 안되는 것
	const doc = document
		doc.querySelector("button.btn_delete").addEventListener("click", (e)=>{
			doc.querySelector("input[name='cpcode']")
			//document.querySelector("input#cpcode") = document.querySelector("input[name='cpcode']
					// let 는 변형되지 않기 때문에 const로 작성해도 괜찮음
			const cpcodeObj = doc.querySelector("input#cpcode")
			let cpcode = cpcodeObj.value
			if(confirm(cpcode + " 를 삭제합니다!!")) {
				location.replace("${rootPath}/company/delete?cpcode=" + cpcode)
			}
			alert("삭제버튼 클릭" + cpcode)
		})
	</script>
</body>
</html>