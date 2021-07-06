package com.callor.book.service.impl.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverAbstractService;

import lombok.extern.slf4j.Slf4j;

/*
 * NaverAbstractService 추상클래스를 상속받아 구현 클래스
 * 추상클래스에 사전 정의된 jsonString() method 코드는 직접 작성하지 않고, 사용할 수 있다.
 * 	jsonString()
 * 
 * 추상메서드는 반드시 구현해야 한다.
 * 	queryURL(), getNaverList()
 * 
 * 다음과 같은 형식으로 사용가능하다.
 * NaverAbstractService nService = new NaverServiceImplV1()
 * nService.queryURL()
 * nService.jsonString()
 * nService.getNaverList()
 */
@Slf4j
@Service(NaverQualifier.NAVER_Movie_SERVICE_V1)
public class NaverMovieServiceImplV1 extends NaverAbstractService<MovieDTO>{
	
	protected final String MovieURL ="https://openapi.naver.com/v1/search/movie.json";

	@Override
	public String queryURL(String search_text) {
		// TODO Auto-generated method stub
		
		String searchUTF8 = null;
		
		try {
			searchUTF8 = URLEncoder.encode(search_text,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(MovieURL);
		
		String queryString = String.format("?query=%S",searchUTF8);
		queryURL.append(queryString);
		
		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		
		log.debug("queryURL : {}", queryURL);
		
		return queryURL.toString();
	}

	@Override
	public List<MovieDTO> getNaverList(String jsonString) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
