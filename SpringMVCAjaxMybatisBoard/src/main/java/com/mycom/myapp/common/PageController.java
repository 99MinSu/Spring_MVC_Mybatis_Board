package com.mycom.myapp.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/pages/user")
	private String register() {
		return "/register";
	}
	
	@GetMapping("/pages/login")
	private String login() {
		return "/login";
	}
	
	@GetMapping("/pages/board")
	private String board() {
		return "/board";
	}
}
