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
		String Id = request.getParameter("Id");//Id에서 해당 데이터를 뽑아옴
			//dao에 작업 지시 내림
		Dao dao = new Dao();
		//dao작업후 컨텐츠에 내용응 Dto에서 뿌려줌
		Dto dto = dao.contentView();
		
		//model에 dto로 온걸 content_view에서 뿌려줘야대서 가져온다 
		model.addAttribute("content_view",dto);
	}

}
