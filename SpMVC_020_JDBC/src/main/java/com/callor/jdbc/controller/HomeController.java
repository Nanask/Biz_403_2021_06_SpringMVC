package com.callor.jdbc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.jdbc.pesistance.BookDao;
import com.callor.jdbc.service.RentService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	/*
	 * 보편적인 Spring에서 bean을 사용하는 코드
	 * @Autowired
	 * private BookDao bookDao
	 * 이 코드에서 메모리 leak(누수)현상이 보고되어 다음의 코드를 권장한다
	 * 
	 */
	protected final RentService rentService;
//	protected final BookDao bookDao; // bean을 사용할 때 메모리 사용을 최소화 하기 위함
	public HomeController(RentService rentService) {
		this.rentService = rentService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		rentService.viewBookAndComp();
		
		return "home";
	}
	
}
