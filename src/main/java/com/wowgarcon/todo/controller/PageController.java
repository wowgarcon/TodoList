package com.wowgarcon.todo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wowgarcon.todo.config.auth.PrincipalDetails;
import com.wowgarcon.todo.domain.PageDTO;

import lombok.extern.java.Log;

@Controller
@Log
public class PageController {
	
	@GetMapping("/")
	public String index(Model model, PageDTO utils) {
		
		model.addAttribute("page", utils.getPage());
		return "index";
	}
	
	@GetMapping("/join")
	public String signUp(Model model, PageDTO utils) {
		utils.setPage("join");
		
		model.addAttribute("page", utils.getPage());
		return "index";
	}
	
	@GetMapping("/login")
	public String logIn(Model model, PageDTO utils, HttpSession session) {
		utils.setPage("login");
		
		if(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
			System.out.println(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));
		}
		
		
		model.addAttribute("page", utils.getPage());
		return "index";
	}
	
	@GetMapping("/todo")
	public String todo(Model model, PageDTO utils) {
		utils.setPage("todolist");
		
		model.addAttribute("page", utils.getPage());
		return "index";
	}
}
