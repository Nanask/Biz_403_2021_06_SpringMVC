package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.model.ScoreDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/score")
public class ScoreController {
	
	protected final ScoreDao scDao;
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET )
	public String list(Model model) {

		System.out.println("TEST ##########################");
		List<ScoreDTO> scList = scDao.selectViewAll();
		
		model.addAttribute("SC", scList);
		
		model.addAttribute("BODY","SCORE_VIEW");
		
		
//		log.debug("scList : {}" + scList);
		
		
		return "home";
	}

}
