package com.callor.book.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.callor.book.dao.ext.BookDao;
import com.callor.book.model.BookDTO;
import com.callor.book.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/book")
@Controller
public class BookController {


	protected final BookService bookService;
	protected final BookDao bookDao;

	@RequestMapping(value="/insert/{isbn}",method=RequestMethod.GET)
	public String insert(
			@PathVariable("isbn") String isbn, Model mode) throws Exception {
//@PathVariable("isbn") 이걸로 받아 온 값을 value에 있는 {}안에 넣어주는 것? 
		
		log.debug("ISBN : {}",isbn);

		int ret = bookService.insert(isbn);
		return "redirect:/book/list";
	}
	/*
	 * 통상적인 Spring Framwork의 method에서는 view 파일의 이름을 String 형으로 return하고
	 * view에서 랜더링 할 데이터는 Model 객체에 attribute로 담아서 보내는 방식이다.
	 * 
	 * ModelAndView 객체를 별도로 생성하여 view는 setViewName() method를 이용하여 setting하고
	 * 랜더링 할 데이터는 addObject() method를 이용하여 추가하고
	 * ModelAndView 객체를 return하는 방식도 사용한다. 
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public String list(Model model) {
	public ModelAndView list() {
		//모델이라는 변수명을 쓰지 않을 때 이렇게 사용했었음 지금은 많이 사용하지는 않으나
		// 한번 보기 위해 표시해놓음
		
//		List<BookDTO> bookList = bookDao.selectAll(); 
		List<BookDTO> bookList = bookService.selectAll();
		
		ModelAndView mv= new ModelAndView();
//		model.addAttribute("BLIST", bookList);
		mv.setViewName("home");
		mv.addObject("MY_BOOKS",bookList);
		
//		return "book/list_view";
		return mv;
	}
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(String isbn) {
		//isbn을 받아서 도서 정보를 findById() 하고 자세히 보이기 화면 구현
		return "home";
	}

}
