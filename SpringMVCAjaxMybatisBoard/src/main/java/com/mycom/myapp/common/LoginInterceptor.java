package com.mycom.myapp.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycom.myapp.user.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component 
public class LoginInterceptor implements HandlerInterceptor{

	private final String jsonStr = "{\"result\":\"login\"}";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("LoginInterceptor >>> " + request.getRequestURI());
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		if(userDto == null) { // not logged in
			
			if("true".equals(request.getHeader("ajax"))){ // 비동기
				System.out.println("LoginInterceptor >>> ajax");
				response.getWriter().write(jsonStr);
			}else { // page
				System.out.println("LoginInterceptor >>> page");
				response.sendRedirect("/pages/login");
			}
			
			return false;
		}
		
		return true; // 이어서 계속 진행, false 이면 더 이상 진행하지 않는다.
	}
}

// server 에서 session timeout 인 상황에서 client 가 페이지 요청 <= login 페이지로 이동하라는 정상적인 html response => login 페이지로 이동
// server 에서 session timeout 인 상황에서 client 가 data(json) 요청 <= login 페이지로 이동하라는 정상적인 html response => javascript 오류
//	=> client 요청이 data 요청인지 페이지 요청인지 구분