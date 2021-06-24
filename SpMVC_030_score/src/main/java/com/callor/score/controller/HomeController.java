package com.callor.score.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.score.model.StudentVO;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HomeController {

	protected final StudentService stService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		
		List<StudentVO> stList = stService.selectAll();
		
		Map<String, Object> maps = stService.selectMaps();
		log.debug("Controller {}", stList.toString());
		
		
		
		return "redirect:/score";
		// redirect:/ localhost:/score  
//		홈을 열면 성적화면으로 바로 보여주기 위한 설정
	}
	/*
	 * @ResponseBody
	 * API server를 만들 때 stirng type의 데이터를 Response하라는 지시어
	 * 
	 * API controller
	 * view를 보여주는 것이 아닌 문자열 등 데이터를 보내주는것
	 * 
	 * 
	 * 서로 다른 서버와 서버, 서버와 클라이언트 간에 조건을 Request하고, 그 결과를 Data로 Response 서비스
	 * 
	 * 서로 다른 프로젝트로 서버와 클라이언트를 개발한다
	 * API service는 주고받은 데이터를 XML type, JSON type으로 만든다.
	 * 
	 * 리턴한 값을 문자열로 받아들임 원래는 jsp파일로 가는데 리턴 값을 문자열로 받아들임
	 */
	
	@ResponseBody
	@RequestMapping(value = "home", method=RequestMethod.GET)
	public String string(@RequestParam(name="HOME",required = false, defaultValue = "") String name) {
		
		if(name != null && name.equals("HOME")) {
			return "OK";
		}else {
			return "Fail";
		}
	}
	
}
