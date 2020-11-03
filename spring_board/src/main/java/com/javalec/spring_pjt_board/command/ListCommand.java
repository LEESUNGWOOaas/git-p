package com.javalec.spring_pjt_board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;
import com.javalec.spring_pjt_board.dto.Dto;

public class ListCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub
		
		//database에 값을 다 가져와서 화면에 뿌려주기위해 즉,jsp페이지에 dto객체를 넘겨서 jsp 페이지가 dto객체를 뿌려주게함
		
		Dao dao=new Dao();
		ArrayList<Dto>dtos = dao.list();//dao에 list를 작업하라는 명령실행 

		model.addAttribute("list",dtos);//dto를 받아서 model에 넣어줌,이름이 list로 오기때문에 키에 list로 설정(view 에서 받을때 list로 받으면됨)
		
		
	}

}
