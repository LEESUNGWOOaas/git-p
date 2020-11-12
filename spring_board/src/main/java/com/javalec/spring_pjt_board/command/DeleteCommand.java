package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;

public class DeleteCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String bid = request.getParameter("request");
		Dao dao = new Dao();
		dao.delete(bid);

	}

}