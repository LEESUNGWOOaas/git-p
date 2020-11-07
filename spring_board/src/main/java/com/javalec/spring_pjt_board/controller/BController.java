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

@Controller // 오토스캔에 걸리게 해주는 어노테이션

public class BController {
	
	Command command;//필드화
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");//list메소드가 실행됫다고 명시
		//ListCommand의 일을 시킴
		command = new ListCommand();
		command.excute(model);//model에 있는 값을 실행한다
		
		return "list";//어떤 view페이지로 찾아가라 명시(list.jsp에 리턴)
		
	}
	@RequestMapping("/write_view")
	public String write_view(Model model) { //write뷰 작성하는 화면
		System.out.println("write_view()");
		
		return"write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request,Model model){//위 코드에있는 화면에 작성한 데이터를 받기위해 HttpServletRequest로 데이터를 받아옴
		System.out.println("write()");
	
		model.addAttribute("request",request);//Controller에서는 작업을 안하니 request에 정보를 담아서 날린다(service와 dao) 
		command = new WriteCommand();
		command.excute(model);
		
		return"redirect:list";//다시 list로 갈수있게 리다이렉트이동페이지로 생성 
	}
	//클릭시 작성내용 컨텐츠 보는 메소드
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("content_view()");
		model.addAttribute("request",request);//데이터를 가져오기위해 요청을 함
		command = new ContentCommand();//데이터를 가져옴 
		command.excute(model);//실행
	
		return"content_view";
		
	}
	
	
	@RequestMapping(value= "/modify" , method=RequestMethod.POST)//수정이 목적이기 때문에 method값을 post방식 value를 추가해줌''으로
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
