package com.callor.book.service.impl.news;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.NaverParentDTO;
import com.callor.book.model.NewsDTO;

import lombok.extern.slf4j.Slf4j;

/*
 * spring framwork의 RestTemplate 클래스를 사용한 JSON parsing
 * 
 * json-simple, Gson 등을 사용할 때는
 * QpenAPI를 통하여 JsonString을 수신하고
 * parsing을 통해 DTO, List<DTO>형태의 데이터로 변환하는 과정을 수행했다.
 * 
 * RestTemplate를 사용하면 DTO(VO)의 겹치는 구조를 잘 설계하고
 * 
 * 요청주소를 URI로 생성하고 인증정보등을 HttpHeaders 클래스로 설정하고
 * HttpEntity로 변환하여 RestTemplate에 주입한다.
 * 
 * 또한, DTO(VO)의 Parent 클래스를 매개변수로 보내어 데이터를 parsing 하도록 한다.
 * 
 * 내부에서 요청정보를 Open API로 보내고 수신된 데이터를 DTO(VO)구조에 따라 자체 parsing을 하고 결과를 돌려준다.
 */

@Slf4j
@Service(NaverQualifier.NAVER_News_SERVICE_V2)
public class NaverNewsServiceImplV2 extends NaverNewsServiceImplV1 {

//	@Override
//	public String queryURL(String search) throws UnsupportedEncodingException {
//		String queryURL = NaverSecret.Nurl.NEWS;
//		queryURL += "?query=%s&display=10";
//
//		String searchUTF = URLEncoder.encode(search, "UTF-8");
//		queryURL = String.format(queryURL, searchUTF);
//		return queryURL;
//	}

	@Override
	public List<NewsDTO> getNaverList(String queryURL) throws Exception {
		
		//Spring framwork 3.2에서 처음 도입된 클래스
		RestTemplate resTemp = new RestTemplate();
		
		//RestTemplate 통해 수신된 데이터를 자동 Parsing하기 위한 클래스
		ResponseEntity<NaverParentDTO> resList = null;
		/*
		 * json-simple,Gson 등으로 parsing을 수행할 때는 URL 클래스를 사용하여 naver에 요청하고 결과를 jsonString으로 받았다.
		 * 
		 * URI 클래스를 사용하여 naver에 요청하는 정보를 생성하기
		 * 내부적으로 인코딩등의 문제를 없애기 위해 URL을 사용하지 않고 URI를 사용한다.
		 */
		
		URI restURI = new URI(queryURL);
		
		/*
		 * naver에 요청할 때 사용할 인증정보를 HTTP protocol의 header에 부착하기
		 */
		HttpHeaders header = new HttpHeaders();
		header.set("X-Naver-client-Id", NaverSecret.NAVER_CLIENT_ID);
		header.set("X-Naver-client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
		
		//선택값 기본값이 JSON일 경우에는 쓰지 않아도 된다.
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		// Header에 값을 넣었던 것을 entity에 다시 담는다 
		HttpEntity<String> entity = new HttpEntity<String>("parameter",header);
		
		
		resList = resTemp.exchange(restURI, // naver에 요청할 URL 정보 
				HttpMethod.GET, // GET method로 요청
				entity, // 인증정보등이 담긴 header
				NaverParentDTO.class); //내가 받을 데이터
		
		
		log.debug("업데이트날짜 {}", resList.getBody().lastBuildDate);
		log.debug("전체개수 {}", resList.getBody().total);
		log.debug("리스트 {}", resList.getBody().items.toString());
		
		List<NewsDTO> movies = resList.getBody().items;
		return movies;
	}

}