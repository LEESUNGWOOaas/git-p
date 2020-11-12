package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;
import com.javalec.spring_pjt_board.dto.Dto;

public class ContentCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map=model.asMap();
		HttpServletRequest request =(HttpServletRequest)map.get("request");
		String bId = request.getParameter("bId");//Id에서 해당 데이터를 뽑아옴
		
		Dao dao = new Dao();
		Dto dto = dao.contentView(bId);
		
		model.addAttribute("content_view", dto);
		
	}

}