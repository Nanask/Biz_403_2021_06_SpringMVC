package com.callor.jdbc.pesistance;

import java.util.List;

import com.callor.jdbc.model.CompanyVO;

public interface CompanyDao extends GenericDao<CompanyVO, String>{
	
	public String findByMaxCode();
	public List<CompanyVO> findByCName(String cname);
	public List<CompanyVO> findByTel(String tel);
	public List<CompanyVO> findByCeo(String ceo);
	public CompanyVO findById(String cp_code);

	
	

}
