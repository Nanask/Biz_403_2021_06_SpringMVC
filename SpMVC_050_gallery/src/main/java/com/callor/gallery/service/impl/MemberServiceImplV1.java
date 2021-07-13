package com.callor.gallery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.gallery.model.MemberVO;
import com.callor.gallery.persistance.ext.MemberDao;
import com.callor.gallery.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("memberServiceV1")
public class MemberServiceImplV1 implements MemberService{

	protected final MemberDao memDao;
	
	@Autowired
	public void create_member_table(MemberDao dummy) {
		
		//Map을 쓰는 이유가 뭔데 뭐냐고!!!!@!!!!!!!
		Map<String, String> maps = new HashMap<String,String>();
		memDao.create_table(maps);
	}
	
	/*
	 * 1.회원가입에서 최초로 가입된 member는 ADMIN이다.
	 * 		회원테이블에 데이터가 있는가? 없다면 최초의 가입자!
	 * 		selectAll() method를 사용하여 최초 가입된 member인지 파악
	 * 2. ADMIN 권한을 갖는 최초의 가입자는 level이 0이다.
	 * 3. ADMIN이 아닌 일반 가입자는 기본 level이 9이다.
	 * 4. level이 6보다 큰 member는 이미지 보기만 가능하다.
	 * 5. 이미지 등록을 하려면 level이 6보다 작아야 한다.
	 * 6. 최초 가입한 member가 가입승인이 되면 level을 6으로 설정한다.
	 * 7. 이미 가입된 member의 m_userid 정보가 JOIN을 통해서 전달되면 회원정보를 업데이트 한다.
	 */
	
	
	@Override
	public MemberVO join(MemberVO memberVO) {
		// TODO 회원가입
		
		List<MemberVO> mList = memDao.selectAll();
		log.debug("MList {}", mList.toString()); // 로그 찍어보면 아무것도 없다는 것이 확인됨
		
		// 아직 memeber table에 데이터가 하나도 없는 상태
		// 최초로 가입신청이 된 상태
		// 최초로 가입되는 member는 ADMIN
		// ADMIN은 level이 0이다.
		if(mList == null || mList.size() < 1) {
			memberVO.setM_level(0);
		}else {
			memberVO.setM_level(9);
			// 이렇게 세팅해줘야 하는 이유는 level이 int형이라서 자동으로 0을 세팅하기 때문에 
			// 새로 넣어줘야 하는 경우가 아니라면 9를 넣어줄 수 있게 따로 세팅해주어야 함
		}
		memDao.insertOrUpdate(memberVO);
		return memberVO; // memDao의 insertOrUpdate에 memberVO를 세팅했기 때문에 세팅된 memberVO를 리턴해줌
	}

	@Override
	public MemberVO update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findById(String m_userid) {
		// TODO Auto-generated method stub
		
		MemberVO memberVO = memDao.findById(m_userid.trim());
		//trim() 을 붙이는 이유는 빈칸때문에 오류가 발생할 수 있으므로 빈칸을 없애고 조회할 수 있도록 붙임
		
		
		if(memberVO == null) {
			//가입되지 않은 사용자 ID
			log.debug("가입되지 않은 사용자 정보 : {}",m_userid);
			// 가입되지 않은 사용자는 null값이 되기 때문에 tostring을 하면 500 오류가 발생함
			// 그래서 if문으로 묶어서 null값이여도 조회할 수 있도록 했음
			
		}else {
			log.debug("조회된 사용자 정보 : {}", memberVO.toString());
		}
		
		
		return memberVO;
	}

	@Override
	public MemberVO login(MemberVO memberVO, Model model) {
		// TODO Auto-generated method stub
		
		// 1. memberVO에서 m_userid를 getter한 후 
		// 2. findById()를 통해 id 조회
		// 3. 만약 결과가 null이라면 ? 가입되지 않은 ID
		// 4. 결과가 null이 아니라면 ? 가입된 ID
		// 4-1. 비밀번호 일치 조회
		// 4-2. 비밀번호가 일치하지 않으면 ? 비밀번호 오류 로그인 거부
		// 5. ID와 비밀번호가 일치한다면? 로그인 처리
		
		MemberVO findVO = memDao.findById(memberVO.getM_userid());
		if(findVO == null) { // memberVO에서 가져온 userid가 null이라면
			
			model.addAttribute("LOGIN_FAIL","NOT_USERID"); // HOME에 메세지를 띄움
//			"LOGIN_FAIL"는 변수
			//"LOGIN_FAIL" "NOT_USERID" 값이라서 텍스트로 표시할 수 있음
			
			return null;
		}
		
		// 비밀번호 비교
		if(findVO.getM_password().equals(memberVO.getM_password())) {
			return findVO;
		}
		model.addAttribute("LOGIN_FAIL","NEQ_PASS");	
		
		return null;
	}

}
