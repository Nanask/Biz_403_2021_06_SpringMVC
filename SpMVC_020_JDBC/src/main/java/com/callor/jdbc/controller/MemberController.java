package com.callor.jdbc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.jdbc.model.UserVO;
import com.callor.jdbc.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	protected final MemberService mService;

	public MemberController(MemberService mService) {
		// TODO Auto-generated constructor stub
		this.mService = mService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(name = "MSG", required = false) String msg,Model model) {
		
		if(msg == null) {
			model.addAttribute("MSG","NONE");
		}else if(msg.equals("LOGIN")) {
			model.addAttribute("MSG","권한없음 로그인 수행");
		}else if(msg.equals("LOGIN_FAIL")) {
			model.addAttribute("MSG","로그인 실패");
		}
		
		return "member/login";
	}
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpSession hSession) {
		
		hSession.removeAttribute("USERVO");
		hSession = null;
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/join";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("m_username") String username, @RequestParam("m_password") String password,
			HttpSession hSession, Model model) {
		/*
		 * String m_username, String m_password 의 변수명은 form에 인풋박스에 정해져있는 클래스 네임으로 해야한다.
		 */
		log.debug("사용자ID {}", username);
		log.debug("비밀번호 {}", password);

		UserVO userVO = mService.login(username, password);
		
		// 로그인에 실패했을 경우
		if(userVO == null) {
			model.addAttribute("MSG","LOGIN_FAIL");
			return "redirect:/member/login";
			
		}

		/*
		 * HttpSession 객체는 한 번 생성되면 유효기간 동안 서버의 메모리에 상주한다.
		 * 
		 * Session은 꼭 필요한 경우에만 최소한으로 생성하는 것이 좋다.
		 */
		hSession.setAttribute("USERVO", userVO);

		return "redirect:/";
	}



}
