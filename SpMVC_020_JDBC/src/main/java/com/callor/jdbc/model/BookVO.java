package com.callor.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor // super생성자
@AllArgsConstructor //fild 생성자
public class BookVO {
	
	private String bk_isbn; //CHAR(13)
	private String bk_title; //VARCHAR(125)
	private String bk_ccode; //CHAR(5)
	private String bk_acode; //CHAR(5)
	private String bk_date; //VARCHAR(10)
	/*
	 * vo(DTO)를 설계할때 숫자형 변수는 PK나 특별히 조회할 때 사용하는 칼럼은
	 * class 형으로(Integer,Long,Float)으로 선언하는 것이 좋다.
	 * 이 칼럼이 null인 경우에 조건을 부여하여 연산하는데 유리하다.
	 * 
	 * 단, class 형으로 선언을 했을 때는 VO(DTO)에 해당변수를 0으로 선언해주는 것이 편리할 때가 있다.
	 * 일반적인 숫자형 변수는 primitive형으로 선언하는 것이 좋다.
	 * 
	 * null값을 취급할것이냐 말것이냐로 결정하자?
	 * 
	 * VO(DTO) 클래스의 변수를 primitive로 선언하면 자동으로 0으로 초기화가 된다.
	 * 
	 * DB에 insert를 수행할 때 해당 칼럼이 not null로 되어ㅣ 있을 때 별다른 처리를 하지 않아도
	 * SQL exception이 발생하지 않는다.
	 */
	private int bk_price; //INT
	private int bk_pages; //INT

}
