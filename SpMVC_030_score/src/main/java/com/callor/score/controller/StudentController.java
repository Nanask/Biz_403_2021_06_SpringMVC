package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.score.dao.ext.StudentDao;
import com.callor.score.model.ScoreinputVO;
import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/student")
public class StudentController {
	protected final StudentDao stDao;
	protected final StudentService stService;
//	protected final ScoreService scService;

	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String list(Model model) {

		List<StudentVO> stList = stDao.selectAll();

		// String BODY = "STUDENT_LIST";
		// view.rendering(BODY)와 같은의미
		model.addAttribute("BODY", "STUDENT_LIST");
		model.addAttribute("ST", stList);

		log.debug("stList[]", stList.toString());

		return "home";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		
		StudentVO stVO = new StudentVO();
		stVO.setSt_num(stService.makeStNum());
		
		model.addAttribute("ST",stVO);
		model.addAttribute("BODY", "STUDENT_INPUT");
		return "home";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(StudentVO studentVO, Model model) {
		
		log.debug("Req 학생정보 : {}", studentVO.toString());
		
		int ret = stService.insert(studentVO);
		
		model.addAttribute("BODY","STUDENT_INPUT");
		return "redirect:/student";
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(String st_num, Model model)  {
		
//		List<SubjectAndScoreDTO> ssList = scService.selectScore(st_num);
		// 리턴타입을 String으로 했기 때문에 오류가 발생함 => 그래서 변경해줌
		
		String ret = stService.detail(model,st_num);
		
//		StudentVO stVO = stService.find
		
//		model.addAttribute("SSLIST", ssList);
		model.addAttribute("BODY", "STUDENT_DETAIL");
		return "home";
		
	}
	@RequestMapping(value="/detail",method=RequestMethod.POST)
//	public String detail(@RequestParam(name="subject") List<String> subject,
//			@RequestParam(name="score") List<String> score) {
		// @RequestParam(name="subject") 을 넣지 않으면 데이터를 받을 수 없음
	
	public String detail(ScoreinputVO scInputVO) {
		
//		log.debug("Subject: {}",subject.toString());
//		log.debug("score: {}",score.toString());
		
		log.debug("Score Input {}", scInputVO.toString());
		
		String ret = stService.scoreInput(scInputVO);
 		
		return "home";
		
	}
}