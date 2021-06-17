package com.callor.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	
	private String username;
	private String password;
	private String name;
	private String nname;
	private String email;
	private String tel;
	private String addr;
	
//	탈퇴한 회원이 다시 로그인이 불가하도록 하는 것
	public Boolean expire;

}
