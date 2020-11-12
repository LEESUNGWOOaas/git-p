package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;

public class WriteCommand implements Command {

	@Override
	public void excute(Model model) {
		//���� �����·� ����
		Map<String,Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
	
		String bName = request.getParameter("bName");//�ۼ��������� ������ ���� bName�� ��´� 
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
	
		Dao dao = new Dao();
		dao.write(bName,bTitle,bContent);
		
	}

}
