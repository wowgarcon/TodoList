package com.wowgarcon.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wowgarcon.todo.domain.CommonDTO;

import lombok.extern.java.Log;

@Controller
@Log
public class RouteController {
	
	@GetMapping("/")
	public String index(Model model, CommonDTO utils) {

		model.addAttribute("page", utils.getPage());
		return "index";
	}
	
	@RequestMapping("/signUp")
	public String signUp(Model model, CommonDTO utils) {
		utils.setPage("signUp");
		
		model.addAttribute("page", utils.getPage());
		return "index";
	}
	
	@RequestMapping("/logIn")
	public String logIn(Model model, CommonDTO utils) {
		utils.setPage("logIn");
		
		model.addAttribute("page", utils.getPage());
		return "index";
	}
}
