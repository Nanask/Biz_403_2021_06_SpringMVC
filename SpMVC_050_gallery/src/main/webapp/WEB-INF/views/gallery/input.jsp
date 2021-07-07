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
	<style>
	@font-face {
    font-family: 'HangeulNuri-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_three@1.0/HangeulNuri-Bold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

	*{
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	}
	
	body {
	font-family: 'HangeulNuri-Bold';
	}
	
	form.input div{
	margin: 10px;
	padding: 10px;
	text-align: center;
	}
	form.input label {
		display: inline-block;
		text-align: center;
		width: 20%;
	}
	
	form.input input {
		width: 15%;
		padding: 10px;
		border: 0 solid black;
		border-bottom: 1px solid black;
		font-family: 'HangeulNuri-Bold';
	}
	form.input input:focus {
		outline: none;
	}
	
	form.input div.image  input{
		font-family: 'HangeulNuri-Bold';
	}
	
	div.btn button {
		padding: 10px 25px;
		font-family: 'HangeulNuri-Bold';
		background-color: #F17F42;
		border: 0px solid #F17F42;
		
	}
	textarea {
	resize: none;
	}
	
	</style>
<form class="input"
	method="POST"
	enctype="multipart/form-data">
	<%-- enctype 파일을 받아올 때 필요한 것? --%>
	<div>
		<label>작성자</label> <input
			name="g_writer"
			value="${CMD.g_writer}">
	</div>
	<div>
		<label>작성일자</label> <input
			type="date"
			name="g_date"
			value="${CMD.g_date}">
	</div>
	<div>
		<label>작성시각</label> <input
			type="time"
			name="g_time"
			value="${CMD.g_time}">
	</div>
	<div>
		<label>작성제목</label> <input
			name="g_subject"
			value="${CMD.g_writer}">
	</div>
	<div>
		<label></label>
		<textarea cols="40" rows="10" name="g_content"></textarea>
	</div>
	<div class="image">
		<label>대표이미지</label> <input
			type="file"
			name="one_file" />
		</div>	
		<div class="image">	
		<label>갤러리</label> <input 
			type="file"
			multiple="multiple"
			name="m_file" />
			
	</div>
	<div class="btn">
	<button>전송</button>
	</div>
</form>