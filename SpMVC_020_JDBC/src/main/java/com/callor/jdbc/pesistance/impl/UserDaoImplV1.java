package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.UserVO;
import com.callor.jdbc.pesistance.UserDao;

@Repository
public class UserDaoImplV1 implements UserDao{

	protected final JdbcTemplate jdbcTemplate;
	
	// 而댄띁�윴�듃濡� 留뚮뱾�뼱�졇 �엳�뒗 �깮�꽦�옄留� 媛��뒫
	//�깮�꽦�옄�뿉寃� 二쇱엯諛쏅뒗 媛앹껜
//	�깮�꽦�옄�뿉寃� 二쇱엯諛쏆븘 珥덇린�솕 �븯�뒗 媛앹껜�뒗 @Component濡� �꽑�뼵�맂 �겢�옒�뒪留� 媛��뒫
	public UserDaoImplV1(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findById(String username) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_member ";
		sql = " WHERE mb_username = ? ";
		
		Object[] prams = new Object[] {username};
		
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
		
		return null;
	}

	@Override
	public int insert(UserVO vo) {
		// TODO Auto-generated method stub
		
		String sql = " INSERT INTO tbl_member (username, password)";
		sql = " VALUE( ? , ? ) ";
		
		// JdbcTemplate�쑝濡� query瑜� �쟾�넚�븷 �븣 �쟾�떖�븷 媛믪씠 紐뉕컻 �븞�맆�븣�뒗 Object[] 諛곗뿴濡� 留뚮뱾吏� �븡�븘�룄 �맂�떎.
		
		return jdbcTemplate.update(sql, vo.getUsername(), vo.getPassword());
		
	}

	@Override
	public int update(UserVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
