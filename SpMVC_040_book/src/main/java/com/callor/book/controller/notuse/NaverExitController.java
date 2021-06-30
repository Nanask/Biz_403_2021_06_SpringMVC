package com.callor.book.controller.notuse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverBookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/naver")
public class NaverExitController {
	
	@Qualifier("naverBookServiceV1")
	protected final NaverBookService nBookService;
	
	public NaverExitController(@Qualifier("naverBookServiceV1") NaverBookService nBookService) {
		// TODO Auto-generated constructor stub
		this.nBookService = nBookService;
	}
	
	@ResponseBody // 문자열을 그대로 출력시켜라
	@RequestMapping(value="/book", method = RequestMethod.GET) //, produces="application/json;char=UTF8")
	//produces="application/json;char-UTF8" 리턴할 때 이렇게 보내겠다.
	public List<BookDTO> book(String search) throws MalformedURLException, IOException, ParseException {
		
//		nBookService.queryURL("자바");
		String queryURL = nBookService.queryURL(search);
		String jsonString = nBookService.getJsonString(queryURL);
		
		List<BookDTO> bookList = nBookService.getNaverList(jsonString);
		
//throws MalformedURLException 스프링에서 처리하게 throws 처리!		
		return bookList;
		
	}

}
