package com.callor.book.service.impl.books;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverAbstractService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQualifier.NAVER_BOOK_SERVICE_V1)
public class NaverBookServiceImpl extends NaverAbstractService<BookDTO>{
	/*
	 * naver에 요청하기
	 * BookURL + "?query=" + 검색문자열
	 */
	protected final String BookURL ="https://openapi.naver.com/v1/search/book.json";
	protected final String NewsURL ="https://openapi.naver.com/v1/search/news.json";
	protected final String MovieURL ="https://openapi.naver.com/v1/search/movie.json";
	
	public String queryURL(String search) {
		
		// 검색하고자 하는 문자열을 UTF-8로 인코딩
		// UTF-8로 인코딩 할 수 없어서 오류가 발생할 경우를 대비하는 try/catch
		String searchUTF8 = null;
		try {
			searchUTF8 = URLEncoder.encode(search,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(BookURL); // queryString += BookURL
		
		// query = 검색문자열 식으로 이루어지게 함
		String queryString = String.format("?query=%S", searchUTF8);
		queryURL.append(queryString);
		
		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		
		return queryURL.toString();
	}


	@Override
	public List<BookDTO> getNaverList(String jsonString) throws ParseException {
//		1. json Parsing 도구 선언
		
		JSONParser jParser = new JSONParser();
		
			// JsonString을 JSON 객체로 변환
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			
			// parsing된 JSON 객체에서 items 항목을 추출하여
			// JSON 배열 타입으로 변환하기(내부적으로는 List)
			JSONArray items = (JSONArray) jObject.get("items");
			
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			
			int nSize = items.size();
			for(int i=0 ; i < nSize ; i++) {
				
				JSONObject item = (JSONObject) items.get(i);
				
				//도서정보 항목을 문자열 변수에 저장
				 	String title = (String) item.get("title");
//				 	String title = item.get("").toString(); 이 방식으로도 처리할 수 있음 (String)을 하지 않고 .tostring으로 표시 
				   String link = (String) item.get("link"); 
				   String image = (String) item.get("image"); 
				   String author = (String) item.get("author");
				   String price = (String) item.get("price"); 
				   String discount = (String) item.get("discount");
				   String publisher = (String) item.get("publisher");
				   String isbn = (String) item.get("isbn"); 
				   String description = (String) item.get("description");
				   String pubdate = (String) item.get("pubdate");
				   
				   // BookDTO에 담기
				   BookDTO bookDTO = BookDTO.builder()
					.title(title) 
				   .link(link) 
				   .image(image)
				   .author(author)
				   .price(price)
				   .discount(discount)
				   .publisher(publisher)
				   .isbn(isbn)
				   .description(description) 
				   .pubdate(pubdate)
				   .build();
				   
				   //List에 add하기
				   bookList.add(bookDTO);
			}
			return bookList;
			
	}
	

}
