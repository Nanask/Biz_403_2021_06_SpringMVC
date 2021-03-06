package com.callor.jdbc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String list(HttpSession hSession, Model model) {
		
		if(hSession.getAttribute("USERVO") == null) {
			model.addAttribute("MSG","LOGIN");
			return "redirect:/member/login";
		}
		
		List<CompanyVO> compList = compService.selectAll();
		log.debug("출판사 정보 가져오기: {} ", compList.toString());
		model.addAttribute("COMPS",compList);
		
		return "company/list";
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
		public String getList(
				@RequestParam(name="cp_title",required = false, defaultValue = "")
				String searchText, Model model) {

			List<CompanyVO> compList = null;
			if(searchText == null || searchText.trim().equals("")) {
				compList = compService.selectAll();
			} else {
				compList = compService.findByTitleAndCeoAndTel(searchText);
			}
		return "company/search";
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
