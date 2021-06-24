package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.dao.ext.StudentDao;
import com.callor.score.model.StudentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/student")
public class StudentController {
	private final StudentDao stDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
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

		model.addAttribute("BODY", "STUDENT_INPUT");
		return "home";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(StudentVO studentVO, Model model) {
		return null;
	}
}