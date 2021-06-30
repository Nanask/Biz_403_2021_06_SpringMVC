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

import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverMovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverMovieServiceV1")
public class NaverMovieServiceImplV1 implements NaverMovieService{
	
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
	public String getJsonString(String queryURL) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		
		URL url = null;
		
		HttpURLConnection httpConn = null;
		
		url = new URL(queryURL);
		
		httpConn = (HttpURLConnection) url.openConnection();
		
		httpConn.setRequestMethod("GET");

		httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
		httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
		
		int httpStatusCode = httpConn.getResponseCode();
		
//		log.debug("httpStatusCode : {}", httpStatusCode);
		
		InputStreamReader is = null;
		
		if(httpStatusCode == 200) {
			is = new InputStreamReader(httpConn.getInputStream());
			
		}else {
			is = new InputStreamReader(httpConn.getErrorStream());
		}
		
		BufferedReader buffer = null;
		buffer = new BufferedReader(is);
		
		StringBuffer sBuffer = new StringBuffer();
		
		while(true) {
			String reader = buffer.readLine();
			if(reader == null) {
				break;
			}
			sBuffer.append(reader);
		}
		log.debug("sBuffer : {}",sBuffer.toString());
		log.debug("URL : {}", url);
		
		return sBuffer.toString();
		
		
	}

	@Override
	public List<BookDTO> getNaverList(String jsonString) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
