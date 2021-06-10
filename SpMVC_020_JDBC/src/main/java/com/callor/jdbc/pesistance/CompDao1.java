package com.callor.jdbc.pesistance;

import java.util.List;

import com.callor.jdbc.model.CompVO1;

public interface CompDao1 extends GenericDao<CompVO1, String>{
	
	public List<CompVO1> findByCName(String cname);
	public List<CompVO1> findByTel(String tel);
	public List<CompVO1> findByCeo(String ceo);
	
}