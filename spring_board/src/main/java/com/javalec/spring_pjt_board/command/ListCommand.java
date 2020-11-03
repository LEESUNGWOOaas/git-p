package com.javalec.spring_pjt_board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.Dao;
import com.javalec.spring_pjt_board.dto.Dto;

public class ListCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub
		
		//database�� ���� �� �����ͼ� ȭ�鿡 �ѷ��ֱ����� ��,jsp�������� dto��ü�� �Ѱܼ� jsp �������� dto��ü�� �ѷ��ְ���
		
		Dao dao=new Dao();
		ArrayList<Dto>dtos = dao.list();//dao�� list�� �۾��϶�� ��ɽ��� 

		model.addAttribute("list",dtos);//dto�� �޾Ƽ� model�� �־���,�̸��� list�� ���⶧���� Ű�� list�� ����(view ���� ������ list�� �������)
		
		
	}

}
