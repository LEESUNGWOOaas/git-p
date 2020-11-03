package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class ContentCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map=model.asMap();
		HttpServletRequest request =(HttpServletRequest)map.get("request");
		String Id = request.getParameter("Id");//Id에서 해당 데이터를 뽑아옴
	}

}
