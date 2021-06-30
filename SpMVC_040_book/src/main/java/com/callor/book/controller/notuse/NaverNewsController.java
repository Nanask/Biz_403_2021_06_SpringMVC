package com.callor.book.controller.notuse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Controller
//@RequestMapping(value="/news")
public class NaverNewsController {
	
	@RequestMapping(value= {"/",""}, method = RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("CAT","NEWS");
//		model.addAttribute("pHolder", "뉴스 검색");
		return "home";
	}

}
