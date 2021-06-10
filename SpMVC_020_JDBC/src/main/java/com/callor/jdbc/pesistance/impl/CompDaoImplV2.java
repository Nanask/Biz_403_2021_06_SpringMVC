package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompVO1;
import com.callor.jdbc.pesistance.CompDao1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("compDaoV1")
public class CompDaoImplV2 implements CompDao1{

	protected final JdbcTemplate jdbcTemplate;
	public CompDaoImplV2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<CompVO1> selectAll() {
		// TODO 여기는 출판사 전체 조회
		String sql = " SELECT * FROM tbl_company ";
		
		List<CompVO1> compList 
		= jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<CompVO1>(CompVO1.class));
		
		log.debug("Comp Select {} ", compList.toString());
		return compList;
	}

	@Override
	public CompVO1 findById(String pk) {

		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		Object[] params = new Object[] { pk };
		
		CompVO1 vo = (CompVO1) jdbcTemplate.query(sql, 
				params,
				new BeanPropertyRowMapper<CompVO1>(CompVO1.class));
		return vo;
	
	}

	@Override
	public int insert(CompVO1 vo) {

		String sql =  " INSERT INTO tbl_company " ;
		sql += " ( cp_code, cp_title, cp_ceo, cp_addr, cp_tel ) ";
		sql += " VALUES( ?,?,?,?,? ) " ;
		
		Object[] params = new Object[] {
				vo.getCp_code(),
				vo.getCp_title(),
				vo.getCp_ceo(),
				vo.getCp_addr(),
				vo.getCp_tel() 
		};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(CompVO1 vo) {
		// TODO Auto-generated method stub
		
		String sql = " UPDATE tbl_company SET ";
		sql += " cp_title = ?, ";
		sql += " cp_ceo = ?, ";
		sql += " cp_addr = ?, ";
		sql += " cp_tel = ?, ";
		sql += " WHERE cp_code = ? ";
		
		Object[] params = new Object[] {
				vo.getCp_title(),
				vo.getCp_ceo(),
				vo.getCp_addr(),
				vo.getCp_tel(),
				vo.getCp_code()
			
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CompVO1> findByCName(String cname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompVO1> findByTel(String tel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompVO1> findByCeo(String ceo) {
		// TODO Auto-generated method stub
		return null;
	}

}
