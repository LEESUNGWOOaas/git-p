package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;

public class WriteCommand implements Command {

	@Override
	public void excute(Model model) {
		//모델을 맵형태로 변형
		Map<String,Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
	
		String bName = request.getParameter("bName");//작성자정보를 가져옴 그후 bName에 담는다 
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
	
		Dao dao = new Dao();
		dao.write(bName,bTitle,bContent);
		
	}

}
