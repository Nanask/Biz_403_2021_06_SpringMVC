package com.callor.score.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.callor.score.model.ScoreinputVO;
import com.callor.score.model.StudentVO;

public interface StudentService {
	
	public List<StudentVO> selectAll();

	public Map<String, Object> selectMaps();
	
	public String makeStNum(String prefix);
	public String makeStNum();
	
	public int insert(StudentVO stVO);
	public int update(StudentVO stVO);

	public String detail(Model model, String st_num);

	public String scoreInput(ScoreinputVO scInputVO);
}
