package com.callor.score.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.dao.ext.StudentDao;
import com.callor.score.dao.ext.SubjectDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectVO;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("studentServiceV1")
public class StudentServiceImpl implements StudentService{
	protected final ScoreDao scDao;
	protected final StudentDao stDao;
	protected final SubjectDao sbDao;
	@Override
	public List<StudentVO> selectAll() {
		List<StudentVO> stList = stDao.selectAll();
		List<ScoreVO> scList = scDao.selectAll();
		List<SubjectVO> sbList = sbDao.selectAll();
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		log.debug("service {}",stList.toString());
		log.debug("service {}",scList.toString());
		
		
		return stList;
	}
	@Override
	   public Map<String, Object> selectMaps() {
	      // TODO Auto-generated method stub
	      
	      List<StudentVO> stList = stDao.selectAll();
	      List<ScoreVO> scList = scDao.selectAll();
	      List<SubjectVO> sbList = sbDao.selectAll();
	      List<ScoreDTO> scViewList = scDao.selectViewAll();
	      
	      Map<String, Object> maps = new HashMap<String, Object>();
	      
	      maps.put("학생", stList);
	      maps.put("점수", scList);
	      maps.put("과목", sbList);
	      maps.put("View", scViewList);
	      
	      return maps;
	   }
	}

