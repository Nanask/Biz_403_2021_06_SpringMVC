<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<body>
	<form id="student_input" method="POST">
		<fieldset>
			<div>
				<label>학번</label><input name="st_num" id="st_num"
					placeholder="학번을 입력하세요" />
			</div>
			<div>
				<label>이름</label><input name="st_name" id="st_name"
					placeholder="학생이름을 입력하세요" />
			</div>
			<div>
				<label>학번</label><input name="st_dept" id="st_dept"
					placeholder="학과를 입력하세요" />
			</div>
			<div>
				<label>학번</label><input name="st_grade" id="st_grade"
					placeholder="학년을 입력하세요" />
			</div>
			<div>
				<label>학번</label><input name="st_tel" id="st_tel"
					placeholder="전화번호를 입력하세요" />
			</div>
			<div>
				<label>주소</label><input name="st_addr" id="st_addr"
					placeholder="주소를 입력하세요" />
			</div>
			<div class="btn_box">
				<button type="button" class="save">저장</button>
				<button type="reset" class="reset">초기화</button>
				<button type="button" class="list">리스트로</button>
			</div>
		</fieldset>
	</form>
<script>
	let doc = document
	
	doc.querySelector("button.save").addEventListener("click", (e) =>{
		
		let st_num = doc.querySelector("input#st_num")
		let st_name = doc.querySelector("input#st_name")
		let st_dept = doc.querySelector("input#st_dept")
		let st_grade = doc.querySelector("input#st_grade")
		let st_tel = doc.querySelector("input#st_tel")
		let st_addr = doc.querySelector("input#st_addr")
		
		if(st_num.value === "") {
			alert("학번은 반드시 입력하세요")
			st_num.focus()
			//return; event 핸들링 코드에서 코드 진행을 멈출때는 반드시 return false를 한다
			return false;
		}
		if(st_num.value.length !==8) {
			alert("학번은 8자리로 입력해야 합니다")
			st_num.focus()
			return false;
		}
		if(st_name.value === "") {
			alert("이름은 반드시 입력하세요")
			st_name.focus()
			return false
		}
		
		doc.querySelector("form#student_input").submit()
		
		
	})
	
	
	
	
</script>