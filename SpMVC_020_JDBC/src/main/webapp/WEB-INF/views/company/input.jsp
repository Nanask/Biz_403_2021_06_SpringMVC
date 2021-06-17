<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include_head.jspf"%>

<body>
	<%@ include file="/WEB-INF/views/include/include_header.jspf"%>
	<section class="main_sec">
	<form action="POST">
		<fieldset>
			<legend>도서정보 등록</legend>

			<!--<div><label>출판사코드</label><input name="cp_code"></div>  -->
			<!-- companyService에서 자동으로 코드를 완성하도록 만들었기 때문에 필요없어짐-->
			<div>
				<label>출판사명</label><input name="cp_title" id="cp_title">
			</div>
			<div>
				<label>대표자명</label><input name="cp_ceo" id="cp_ceo">
			</div>
			<div>
				<label>전화번호</label><input name="cp_tel" id="cp_tel" type="tel">
			</div>
			<div>
				<label>주소</label><input name="cp_addr" id="cp_addr">
			</div>
			<div>
				<label>주요장르</label><input name="cp_genre" id="cp_genre">
			</div>
			<div class="btn_box">
				<button type="button" class="btn_save company">저장</button>
			</div>
			<div class="btn_box">
				<button type="button" class="btn_reset company">새로작성</button>
			</div>
			<div class="btn_box">
				<button type="button" class="btn_list company">list로</button>
			</div>
		</fieldset>

	</form>
</section>
	<%@ include file="/WEB-INF/views/include/include_footer.jspf"%>
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