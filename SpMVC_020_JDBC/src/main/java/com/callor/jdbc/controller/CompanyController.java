package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.pesistance.CompanyDao;
import com.callor.jdbc.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/company")//company라고 적으면 뒷부분이 인식되서 연결된다.
public class CompanyController {
			
		protected final CompanyDao compDao;
		protected final CompanyService compService;
		
		public CompanyController(CompanyDao compDao, CompanyService compService) {
			this.compDao = compDao;
			this.compService = compService;
}	
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String list() {
		return "company/list";
	}
		
	// localhost:8080/jdbc/company/insert로 호출되는 함수
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert() {
		
		// WEB-INF/views/comp/input.jsp를 열어라
		return "company/input";
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	// wep에서 사용한 변수명이 아닌 다른 변수명을 사용하고 싶을 때 @RequestParam
	public String delete(@RequestParam("cpcode")String cpCode) {
//public String delete(@RequestParam("cpcode")String cpCode) {
		compDao.delete(cpCode);
		return "redirect:/";
		
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(CompanyVO compVO) {
		
		//CompanyVO compVO를 매개변수로 설정하면 VO 전체를 인식!
		
		log.debug("Company VO {}",compVO.toString());
		
		compService.insert(compVO);
		
		return "redirect:/"; //rootPath로 redirect 하라!
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update() {
		
		return "company/input";
	}
	
	

}
