package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.jdbc.model.CompVO1;
import com.callor.jdbc.pesistance.CompDao1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/comp")
public class CompController1 {
	
	protected final CompDao1 compDao;
	public CompController1(CompDao1 compDao) {
		this.compDao = compDao;
	}
	
	
	// localhost:8080/jdbc/comp/insert로 호출되는 함수
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		// WEB-INF/views/comp/input.jsp 열어라
		return "comp/input";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(CompVO1 cmVO) {
		
		log.debug("Company VO {}",cmVO.toString());
		compDao.insert(cmVO);
		
		return "redirect:/";
	
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update() {
		
		return "comp/input";
	}
	

}
