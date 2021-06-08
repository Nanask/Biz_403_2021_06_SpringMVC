package com.callor.jdbc.pesistance;

import java.util.List;

import com.callor.jdbc.model.CompanyVO;

public interface CompanyDao extends GenericDao<CompanyVO, String>{
	
	public List<CompanyVO> findByCName(String cname);
	public List<CompanyVO> findByTel(String tel);
	public List<CompanyVO> findByCeo(String ceo);

	
	

}
