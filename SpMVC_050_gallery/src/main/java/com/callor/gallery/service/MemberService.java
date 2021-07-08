package com.callor.gallery.service;

import org.springframework.ui.Model;

import com.callor.gallery.model.MemberVO;

public interface MemberService {
	
	// join을 하고 나서 다시 vo로 리턴받는 타입으로 만듦
	public MemberVO join(MemberVO memberVO);
	
	public MemberVO update(MemberVO memberVO);
	
	//id값을 이용해서 회원정보 찾기
	public MemberVO findById(String m_userid);

	public MemberVO login(MemberVO memberVO, Model model);
}
