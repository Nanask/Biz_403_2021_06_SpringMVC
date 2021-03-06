package com.callor.gallery.service.impl;

import org.springframework.stereotype.Service;

import com.callor.gallery.model.PageDTO;
import com.callor.gallery.service.PageService;

import lombok.Setter;

@Setter //값을 세팅할 수 있도록 표시
@Service
public class PageServiceImplV1 implements PageService{

	//한 페이지에 보여질 데이터 리스트의 개수
	protected int listPerPage = 10;
	//한 페이지에 보여질 페이지 nav의 개수
	protected int navsPerPage = 5;
	/*
	 * 전체 페이지 수와 현재 페이지 번호를 매개변수로 받아서 pagenation을 그리는데 필요한 데이터를 생성하기
	 */
	@Override // currentPage = 현재 페이지를 알려주는 것
	public PageDTO makePagination(int totalListSize, int currentPage) {
		// TODO Auto-generated method stub
		
		//전체 데이터가 1보다 작을경우(데이터가 없을 경우)페이지 나누지 말고 넘기기
		if(totalListSize < 1) return null;
		
		// 이 연산을 수행하면 전체 리스트를 표현하는데 몇 페이지가 필요한지를 계산
		// 이 연산은 int 형 데이터로 연산을 수행하므로 소수점이하를 무조건 자른다.
		// 마지막 페이지 개수가 listPerPage 보다 작으면 마지막 페이지는 무시하는 totalPage 값이 계산된다.
		
		// 그래서 실수형 값을 소수점 이하에서 반올림 하여 결과를 만드는 함수
		// Math.round() : 반올림
		// Math.floor() : 소수점이하 무조건 버림
		// Math.ceil() : 소수점이하 무조건 올림
		int totalPages = (int)Math.round((double) totalListSize / (double) this.listPerPage);
		
		int startPage = currentPage - (this.navsPerPage/2);
		
		// 네비 시작페이지를 계산하여 1보다 작으면 무조건 1부터 시작
		startPage = startPage < 1 ? 1 : startPage; // 여기까지는 네비게이션을 만들기 위한 코드들
		
		int endPage = startPage + this.navsPerPage-1;
		// 마지막 페이지가 전체 페이지 수보다 커지면 마지막 페이지로 세팅하기
		endPage = endPage > totalPages ? totalPages : endPage;
		
		// 데이터를 자르기 위한 변수 생성
		// 몇번 데이터부터 몇번째 까지 자를지 선택하기
		// 전체 데이터가 685개라고 가정하면
		// 마지막 페이지를 표현하기 위한 offset limit은
		// offset : 680
		// limit : 5가 되어야 하는데 아래 연산에서 limit 690이 되어버린다.
		// 그러면 데이터를 자르는 과정에서 오류가 발생함.
		int offset = (currentPage - 1) * this.listPerPage; // 전체 개수에서 값을 구할 때 정확히 나눠지지 않는 값을 구하기 위한 것 
//		int limit =  currentPage + this.listPerPage;
		int limit =  offset + this.listPerPage; // 전체 개수 offset으로 정확히 나눠지지 않는 값을 전체개수에 더해 limit를 만듦
		
//		offset + limit = 전체 데이터 수!!!!!!!!!!!!!!
		// 마지막 위치 값이 전체 리스트보다 크면 
		// 전체 리스트 끝 값으로 세팅하기
		limit = limit > totalListSize ? totalListSize : limit; // limit > totalListSize 면 totalList이고 아니라면 limit
		
		// offset : 680, limit : 685가 되도록 만들었음
		
//		offset < totalList =< limit
		
		//코드 줄일려고 썼대요
		PageDTO pageDTO = PageDTO.builder()
						.totalPages(totalPages)
						.startPage(startPage)
						.endPage(endPage)
						.offset(offset)
						.limit(limit).build();
		
		
		
		return pageDTO;
	}

}
