package com.callor.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		// logger의 method 들
		// 이 method 들을 logger에서는 level
		
		// 예를 들어logback-test.xml의 level을 INFO로 설정을 하면
		// 코드 곳곳에 logger.trace(), logger.debug()로 출력한 곳은 모두 코드가 무시된다.
		// 인포로 설정을 한다면 그 위 단계들은 무시되고 밑으로 출력한다.
		logger.trace("트레이스");
		logger.debug("디버그");
		logger.info("인포 Welcome home! The client locale is {}.", locale);
		logger.warn("워닝");
		logger.error("에러");
		
		logger.debug(String.valueOf(300 *400));
		logger.debug("여기는 HOME Controller return 바로위");
		

		
		return "home";
	}
	
}
