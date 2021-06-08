package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.pesistance.CompanyDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("compDaoV1")
public class CompanyDaoImplV1 implements CompanyDao{
	
	protected JdbcTemplate jdbcTemplate;
	
	public CompanyDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CompanyVO> selectAll() {
		// TODO 여기는 출판사 전체 조회
		
		String sql = " SELECT * FROM tbl_company ";
		List<CompanyVO> compList =
		jdbcTemplate.query(sql, new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		
		log.debug("Comp Select {}", compList.toString());
		return compList;
	}

	@Override
	public CompanyVO findById(String pk) {
		// TODO Auto-generated method stub
		String sql =" SELECT FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		
		Object[] params = new Object[] {pk};
		
		CompanyVO compVO = (CompanyVO) jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		
		log.debug(compVO.toString());
		
		return compVO;
	}

	@Override
	public int insert(CompanyVO vo) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO tbl_company ";
		sql += " ( cp_code, cp_title, cp_ceo, cp_tel, cp_addr ) ";
		sql += " VALUES( ?,?,?,?,? ) ";
		
		Object[] params = new Object[] {
				vo.getCp_code(),
				vo.getCp_title(),
				vo.getCp_ceo(),
				vo.getCp_tel(),
				vo.getCp_addr()
		};
		
		return jdbcTemplate.update(sql,params);
		
	}
	@Override
	public int update(CompanyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CompanyVO> findByCName(String cname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyVO> findByTel(String tel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyVO> findByCeo(String ceo) {
		// TODO Auto-generated method stub
		return null;
	}





}
