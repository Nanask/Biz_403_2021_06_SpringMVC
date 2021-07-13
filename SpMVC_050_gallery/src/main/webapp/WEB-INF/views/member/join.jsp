<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
	div.msg {
		font-size:10px;
		color: red;
	}
</style>
<form method ="POST">
	<div>
		<label>사용자 ID(Email)</label>
		<input name="m_userid" type="email">
		<div class="msg join id">	
		</div>
	</div>
	<div>
		<label>사용자 password</label>
		<input name="m_password" type="password">
	</div>
	<div>
		<label>비밀번호 확인</label>
		<input name="re_password" type="password">
	</div>
	<div>
		<label>닉네임</label>
		<input name="m_nick">
	</div>
	<div>
		<label>전화번호</label>
		<input name="m_tel" type="tel">
	</div>
	<div>
	<%-- 스크립트에 별도 처리를 하지 않고 버튼에도 처리를 하지 않으면 기본값이 submit 버튼만 눌러서 전송할 수 있게 해놓은 것 --%>
		<button>가입신청</button>
	</div>
</form>

<script>

	//id가 있다면 document.querySelector("input#user_id") 로 
	//id를 따로 설정하지 않았기 때문에 name값을 가져오기 위해 이렇게 표시했다.
	let user_id = document.querySelector("input[name='m_userid']")
	let msg_user_id = document.querySelector("div.join.id")
	
	if(user_id) {
		// "blur",focusout => lost focus
		// focusout
		// input tag에 입력하는 도중 다른 tag로 focus가 이동되는 경우
		// "blur",focusout event 코드에서
		// alert를 사용하면 lost focus와 alert 사이에서 무한반복이 일어나는 현상이 발생한다.
		// lost focus가 되었을 때 메세지를 사용자에게 보이고 싶을 때는 alert를 사용하지 않고 강구해야 한다.
		// 비어있는 div box를 하나 만들고 그곳에 메세지를 표시하는 방법을 사용한 것
		user_id.addEventListener("blur",(e) => {
			
			//문제가 발생할 경우에만 사용자 ID는 반드시 입력하세요를 표시할 수 있도록 한다.
			// 문제가 발생하지 않을 경우에는 이벤트가 발생하나 ""로 내용이 뜨지 않는다.
			msg_user_id.innerText=""
			msg_user_id.style.padding="0";
			
			let m_userid = e.currentTarget.value
			
			//m_userid box에 사용자 ID를 입력한 상태로 tab키를 누르거나,
			// 다른 값을 입력하기 위하여 focus를 이동하면
			// m_userid box에 입력된 값으로 ID중복검사를 수행하기
			if(m_userid === "") {
				msg_user_id.innerText = "*사용자 ID는 반드시 입력하세요"
				msg_user_id.style.padding ="5px";
				user_id.focus()
				return false;
			}
			
			fetch("${rootPath}/member/id_check?m_userid=" + m_userid)
			
			//.then((response) => {
				//return response.text()
			//})
			// in_check에서 이루어진 결과를.text()값으로 받아와서 then으로 받는다.
			//  
			.then(response=>response.text())
			.then(result=> {
				if(result === "USE_ID") {
					msg_user_id.innerText = "*이미 가입된 아이디 입니다."
					user_id.focus()
					return false
				}else if(result === "NOT_USE_ID") {
					msg_user_id.innerText = "*가입가능한 ID입니다."
					msg_user_id.style.color = "blue"
					//가입가능한 아이디라면 password를 focus하여 입력할 수 있게 표시해주기
					document.querySelector("input[name='m_password']").focus()
				}else {
					msg_user_id.innerText = "*알수없는 결과를 수신합니다."
				}
			})
		})
	}
	
	
</script>