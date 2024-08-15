package com.mycom.myapp.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.auth.service.LoginService;
import com.mycom.myapp.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

// login, logout 처리
@Controller
@RequestMapping("/auth")
public class LoginController {
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	@ResponseBody // json 으로 성공, 실패 전달 (ResultDto 를 만들어서 처리 가능)
	public Map<String,String> login(UserDto dto, HttpSession session){
		// request.getParameter("userEmail");
		// request.getParameter("userPassword");
		Map<String,String> map = new HashMap<>();
		Optional<UserDto> optional = loginService.login(dto);
		
		// #1 isPresent() + get()
//		if(optional.isPresent()) {
//			
//			UserDto userDto = optional.get();
//			session.setAttribute("userDto", userDto);
//			
//			map.put("result", "success");
//			return map;
//		}	
//		
//		map.put("result", "fail");
//		return map;
		
		// #2 ifPresentOrElse
		optional.ifPresentOrElse(
			userDto ->{
				session.setAttribute("userDto", userDto);
				map.put("result", "success");
			},
			() -> {
				map.put("result", "fail");
			}
		);
		return map;
	}
	
	// logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 현재까지 유지하던 현 사용자의 session 종료, 삭제
		return "login";
	}
}
