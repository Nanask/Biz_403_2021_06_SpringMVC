package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.pesistance.CompanyDao;

import lombok.extern.slf4j.Slf4j;

/*
 * @component
 * 모든 component를 대표하는 Annotation 
 * 컴파일 과정에서 다소 비용이 많이 소요된다.
 * 
 * Component Annotation
 * @Controller, @Service, @Repository 이러한 Annotation을 사용한다.
 * Spring Container에게 이 표식이 부착된 클래스를 bean으로 생성하여 보관해 달라는 지시어
 * 
 * CompVO c = new ComVO()
 * Object o = new CompVO()
 * 
 * 모든 클래스는 object로 가능하나 용량을 많이 차지하기 때문에
 * 인터페이스를 사용함
 * 
 * CompServiceImplV1 cs = new CompServiceImplV1();
 * CompService cs1 = new ComplServiceImplV1();
 */
@Slf4j
@Repository("companyDaoV1")
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
	public CompanyVO findById(String cp_code) {
		// TODO Auto-generated method stub
		String sql =" SELECT FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		
		Object[] params = new Object[] { cp_code };
		
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
		
		// params에 배열로 넣을 수 없기 때문에 object로 해서 묶어줌
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

	//매개변수 이름은 변경해도 상관없다
	/*
	 * jdbcTemplate를 사용하여 QUERY를 실행할때 각 method에게 매개변수를 받아 QUERY에 전달할텐데
	 * 이때 매개변수는 primitive로 받으면 값을 변환시키는 어려움이 있다.
	 * 그래서 권장사항으로 매개변수는 wrapper class type으로 설정하는 것이 좋다.
	 * 즉, 숫자형일 경우 int,long 대신 Integer, Long 형으로 선언
	 * 
	 * vo에 담겨서 전달된 값은 Object[] 배열로 변환한 후 jdbcTemplate에 전달해 주어야 한다.
	 * 하지만, 1 ~ 2개 정도의 값을 전달할 때 Object[] 배열로 변환 후 전달을 하면
	 * Object 객체 저장소가 생성되고 메모리를 사용한다.
	 * 이때 전달되는 변수가 wrapper class type이면 Object[] 배열로 만들지 않고 바로 주입할 수 있다.
	 *  => Object[] params = new Object[] {cpcode,cptitle};등 여기에 추가해서 사용해도 문제없다
	 *  params(object생성)대신 pk를 사용해도 문제없다?
	 */
	@Override
	public int delete(String cpcode) {
		// TODO 출판사 정보 삭제
		
		String sql = " DELETE FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		/*
		 * cpcode가 String wrapper class type이므로
		 * Object[] 배열로 변환하지 않고 바로 전달이 가능하다.
		 */
//		Object[] params = new Object[] {cpcode};
		// cpcode가 class 타입이라서 그냥 사용해도 오류가 생기지 않는다.
		jdbcTemplate.update(sql,cpcode);
		
		
		return 0;
	}

	// 출판사 이름으로 검색하기
	@Override
	public List<CompanyVO> findByCName(String cname) {
		// TODO Auto-generated method stub
		
		String sql = " SELECT * FROM tbl_company ";
		//		WHERE cp_code LIKE '%' || '%' // oracle
		sql += " WHERE cp_name LIKE CONCAT('%', ? '%' ) "; // mysql

		// SELECT를 수행한 후 각각의 데이터를 CompVO에 담고
		// List에 add하여 return 한 후 
		// compList에 받기
//		List<CompanyVO> compList 
//		= jdbcTemplate.query(sql, new Object[] { cname },
//				new BeanPropertyRowMapper<CompVO>(CompVO.class));
//		
		
		List<CompanyVO> compList 
		= jdbcTemplate.query(sql, new Object[] {cname}, new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		
		return compList;
	}

	@Override
	public List<CompanyVO> findByTel(String tel) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_tel LIKE CONCAT('%', ? '%' ) "; // mysql
		List<CompanyVO> compList = jdbcTemplate.query(sql, new Object[] { tel },
				new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		return compList;
	}

	@Override
	public List<CompanyVO> findByCeo(String ceo) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_ceo LIKE CONCAT('%', ? '%' ) "; // mysql
		List<CompanyVO> compList = jdbcTemplate.query(sql, new Object[] { ceo },
				new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		return compList;
	}

	/*
	 * tbl_company table에서 cpcode(출판사코드) 중 가장 큰 값을 추출하기
	 */
	@Override
	public String findByMaxCode() {
		String sql = " SELECT MAX(cp_code) FROM tbl_company ";
		
		String cpCode = (String)jdbcTemplate.queryForObject(sql, String.class);
		return cpCode;
	}





}
