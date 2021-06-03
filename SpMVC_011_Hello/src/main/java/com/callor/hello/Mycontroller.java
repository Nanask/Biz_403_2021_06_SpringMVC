package com.callor.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Mycontroller {
	/*
	 * RequestMethod
	 * 일반적인 HTTP Server에서
	 * 	Get : 조회(SELECT) - a tag의 link 클릭
	 * 	POST : 추가(INSERT) - form에 값을 입력하여 서버로 보낼때
	 * 
	 * RESTfull HTTp Server에서 추가하여 사용
	 * 	PUT : 업데이트(update)
	 * 	DELETE : 삭제(delete)
	 * 
	 * RequestMapping이 같고, RequestMethod가 같은 경우는 절대 허용하지 않는다
	 * 
	 * RequestMapping이 같더라도 RequestMethod가 다르면 같이 사용할 수 있다.
	 * 
	 * GET method 방식으로 form에 담긴 데이터를 전송할때
	 * http://localhost:8080/hello/insert?name=홍길동&tel=전화번호
	 * - 전송하는 데이터가 주소에 포함되어서 전송되기 때문에 데이터가 쉽게 노출될 수 있다.
	 * - 전체 URI(주소,데이터 등등)의 길이가 256byte를 넘어가면 데이터가 잘리는 현상이 발생한다.
	 * 
	 * POST method 방식으로 form에 담긴 데이터를 전송할때
	 * http://localhost:8080/hello/insert
	 * - 전송하는 데이터가 HTTP 프로토콜의 BODY에 담겨서 전송되기 때문에 일단 주소창 등에 노출이 되지 않는다.
	 * - 이론상으로는 전송하는 데이터의 크기에 제한이 없다
	 * 	다만, 이미지와 같이 큰 데이터를 전송하게 되면 네트워크에 문제가 발생할 수 있어서 설정에서 일부 크기를
	 * 	제한하기도 한다.
	 * 
	 * 서버입장에서
	 * GET: http://localhost:8080/hello/insert?name=홍길동으로 요청을 받았으면
	 * @RequestMapping(value="/insert",method=GET)을 처리하는 method가 있어야 한다.
	 * 
	 * Mapping(value="/insert)가 있는데
	 * method=POST만 있을 경우 서버는 405 http 상태코드(status code)를 응답한다.
	 * 
	 * 주소에 직접 Mapping을 입력하는 방식은 GET 방식!
	 * 
	 * 
	 */
	@RequestMapping(value = "/", method=RequestMethod.POST)

	public void my() {
		
	}
	// insert URI mapping으로 POST 방식의 요청을 처리하겠다.
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public void insert() {
		
	}
	
	// insert URI mapping으로 GET 방식의 요청을 처리하겠다.
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public void insert(String dummy, int num) {
		
		Integer.valueOf(dummy);
		
		// Numberformat Exception 발생
//		String strNum = "300A";
//		Integer intNum = Integer.valueOf(strNum);
		
	}
 
 
}
