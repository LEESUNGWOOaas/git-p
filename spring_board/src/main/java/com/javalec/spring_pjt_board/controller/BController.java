package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board.command.Command;
import com.javalec.spring_pjt_board.command.ContentCommand;
import com.javalec.spring_pjt_board.command.DeleteCommand;
import com.javalec.spring_pjt_board.command.ListCommand;
import com.javalec.spring_pjt_board.command.ModifyCommand;
import com.javalec.spring_pjt_board.command.ReplyCommand;
import com.javalec.spring_pjt_board.command.ReplyViewCommand;
import com.javalec.spring_pjt_board.command.WriteCommand;

@Controller // ���佺ĵ�� �ɸ��� ���ִ� ������̼�

public class BController {
	
	Command command;//�ʵ�ȭ
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");//list�޼ҵ尡 ����̴ٰ� ���
		//ListCommand�� ���� ��Ŵ
		command = new ListCommand();
		command.excute(model);//model�� �ִ� ���� �����Ѵ�
		
		return "list";//� view�������� ã�ư��� ���(list.jsp�� ����)
		
	}
	@RequestMapping("/write_view")
	public String write_view(Model model) { //write�� �ۼ��ϴ� ȭ��
		System.out.println("write_view()");
		
		return"write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request,Model model){//�� �ڵ忡�ִ� ȭ�鿡 �ۼ��� �����͸� �ޱ����� HttpServletRequest�� �����͸� �޾ƿ�
		System.out.println("write()");
	
		model.addAttribute("request",request);//Controller������ �۾��� ���ϴ� request�� ������ ��Ƽ� ������(service�� dao) 
		command = new WriteCommand();
		command.excute(model);
		
		return"redirect:list";//�ٽ� list�� �����ְ� �����̷�Ʈ�̵��������� ���� 
	}
	//Ŭ���� �ۼ����� ������ ���� �޼ҵ�
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("content_view()");
		model.addAttribute("request",request);//�����͸� ������������ ��û�� ��
		command = new ContentCommand();//�����͸� ������ 
		command.excute(model);//����
	
		return"content_view";
		
	}
	
	
	@RequestMapping(value= "/modify" , method=RequestMethod.POST)//������ �����̱� ������ method���� post��� value�� �߰�����''����
	public String modify(HttpServletRequest request,Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request",request);
		command = new ModifyCommand();
		command.excute(model);
		
		
		return"redirect:list";
	}
	
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request",request);
		command = new ReplyViewCommand();
		command.excute(model);
		
		return"reply_view";
	}
	
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("reply()");
		model.addAttribute("requset",request);
		command = new ReplyCommand();
		command.excute(model);
		
		return"redirect:list";
	}
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,Model model) {
		
		System.out.println("delete()");
		model.addAttribute("request",request);
		command = new DeleteCommand();
		command.excute(model);
		
		return"redirect:list";
	}
}
