package com.callor.gallery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO { //페이지네이션을 위해 필요한 변수를 선언
	
	//nav를 위한 데이터
	private int startPage;
	private int endPage;
	private int totalPages;
	
	//list에서 필요한 데이터를 추출하기 위한 변수
	private int offset;
	private int limit;

}
