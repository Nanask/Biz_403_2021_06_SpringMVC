package com.callor.jdbc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.jdbc.pesistance.BookDao;
import com.callor.jdbc.service.HomeService;
import com.callor.jdbc.service.RentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	//string.properties 파일에 설정된 속성값 가져와서 변수에 setting
	@Value("${user.name}")
	protected String user_name;
	
	@Value("${user.email}")
	protected String user_email;

	/*
	 * 보편적인 Spring에서 bean을 사용하는 코드
	 * @Autowired
	 * privatre BooDao bookdao
	 * 이 코드에서 메모리 leak(누수)현상이 보고되어
	 * 다음의 코드를 권장한다.
	 */
	// protected final BookDao bookDao;
	protected final RentService rentService;
	protected final HomeService homeService;
	public HomeController(RentService rentService, HomeService homeService ) {
		this.rentService = rentService;
		this.homeService = homeService;
	}
	/*
	 * 사용자에게 Response 할때 forward방법과 redirect방법이 있다.
	 * 
	 * forwarding은 service등등에서 생성한(조회한) 데이터를
	 * *.jsp 파일과 Rendering하여 사용자에게 HTML 코드로 전송한다.
	 * 
	 * service 등등에서 생성한 데이터는 Model 객체에 addAttribute() method를 사용하여 데이터를 만들고
	 * 
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		log.debug("user_name : {}", user_name);
		log.debug("user_email : {}", user_email);
		
		homeService.dashBoard(model);
		
		/*
		 * class Spring__ {
		 * 		main() {
		 * 			HomController hController = new HomeController();
		 * 			Locale locale = new Locale();
		 * 			Model model = new Model();
		 * 			hController.home()
		 * 		}
		 * 
		 *  }	
		 *  
		 * 매개변수로 전달받은 Model class type 변수인 model에 속성을 하나 추가한다.
		 * 속성의 이름은 user이며, 값은 user_name에 담긴 값이다.  
		 *  
		 */
		
//		모델에서 user라는 변수를 만들고 username을 세팅해서 home으로 보내라
		
		// 물어봅시다 테스트코드인가?
		model.addAttribute("user",user_name);
		
		
		rentService.viewBookAndComp();
		

		return "home";
		
	}
	
}
