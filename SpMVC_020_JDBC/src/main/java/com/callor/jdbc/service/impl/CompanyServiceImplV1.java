package com.callor.jdbc.service.impl;

import org.springframework.stereotype.Service;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.pesistance.CompanyDao;
import com.callor.jdbc.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

//Service를 생성하면 안됨, 오류발생할 수 있음
@Slf4j //로그 찍어보려고 만드는 것
@Service("companyServiceV1")
public class CompanyServiceImplV1 implements CompanyService {

	protected final CompanyDao compDao;

	
	public CompanyServiceImplV1(CompanyDao compDao) {
		// TODO Auto-generated constructor stub
		this.compDao = compDao;
	
	}
	@Override
	public int insert(CompanyVO vo) {
		// TODO Auto-generated method stub
		String cpCode = compDao.findByMaxCode();
		log.debug("cpCode {} ", cpCode);
		
		if(cpCode == null || cpCode.equals("")) {
			
			// C로 시작하는  4개의 자리를 만들어서 비어있는 자리에 0을 넣어서 자동으로 생성하라
			cpCode = String.format("C%04d", 1);
		}else {
			// 영문 접두사 c를 자르고 숫자만 추출
			String _code = cpCode.substring(1);
			Integer intCode = Integer.valueOf(_code) + 1;
			
			cpCode = String.format("C%04d", intCode);
		}
		vo.setCp_code(cpCode);
		compDao.insert(vo);
		
		return 0;
	}

}
