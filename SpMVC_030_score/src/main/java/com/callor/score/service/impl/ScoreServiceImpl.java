package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{
	protected final ScoreDao scDao;
	

	@Override
	public List<ScoreDTO> selectViewAll() {
		// TODO Auto-generated method stub
		
		List<ScoreDTO> stList = scDao.selectViewAll(); 
		
		return stList;
	}


	@Override
	public List<SubjectAndScoreDTO> selectScore(String st_num) {
		// TODO Auto-generated method stub
		
		List<SubjectAndScoreDTO> ssList = scDao.selectSubjectAndScore(st_num);
		return ssList;
	}
	

}
