package com.callor.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Builder 패턴을 사용할 수 있도록 선언하는 Annotation
 * Builder 패턴
 * 
 * GOF에서 제안하는 VO, DTO를 사용하는 중요한 개념
 */
@Builder

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

   private String title;// 검색 결과 문서의 제목. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
   private String link; //   검색 결과 문서의 하이퍼텍스트 link.
   private String image; // 썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타납난다.
   private String author; // 저자 정보.
   private String price; // 정가 정보. 가격이 없으면 나타나지 않는다.
   private String discount; // 할인 가격 정보. 가격이 없으면 나타나지 않는다.
   private String publisher; // 출판사 정보.
   private String isbn; // ISBN 넘버.
   private String description; // 검색 결과 문서의 내용을 요약한 패시지 정보. 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
   private String pubdate; // 출간일 정보.
   
}