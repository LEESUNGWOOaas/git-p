package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;
import com.javalec.spring_pjt_board.dto.Dto;

public class ReplyViewCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request= (HttpServletRequest) map.get("request");
		 String bId=request.getParameter("bId");
		 
		 Dao dao = new Dao();
		 //�޾ƿË� dto�̱� ������ dto�� �޴´�
		 Dto dto = dao.reply_view(bId);
		 //reply�ҋ� view�� �����ֱ� ���� model�� ����
		 
		 model.addAttribute("reply_viw", dto);
		 
	}

}
