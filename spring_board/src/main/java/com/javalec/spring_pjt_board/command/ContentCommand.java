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
		String bId = request.getParameter("bId");//Id���� �ش� �����͸� �̾ƿ�
	
		//dao�� �۾� ���� ����
		Dao dao = new Dao();
		//dao�۾��� �������� ������ Dto���� �ѷ���
		Dto dto = dao.contentView(bId);
		//model�� dto�� �°� content_view���� �ѷ���ߴ뼭 �����´� 
		model.addAttribute("content_view", dto);
		
	}

}