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
		 //받아올떄 dto이기 떄문에 dto로 받는다
		 Dto dto = dao.reply_view(bId);
		 //reply할떄 view로 던저주기 위해 model로 설정
		 
		 model.addAttribute("reply_viw", dto);
		 
	}

}
