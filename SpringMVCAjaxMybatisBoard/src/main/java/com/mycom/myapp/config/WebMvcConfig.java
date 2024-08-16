package com.mycom.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myapp.common.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/",
            "/index.html",
            "/favicon.ico",
            "/assets/**", 
            "/pages/login",   // login 페이지 이동은 제외 
            "/pages/user",   // user 페이지 이동은 제외 
            "/auth/**",
            "/users/**"
        );
        
//		registry.addInterceptor(loginInterceptor)
//				.addPathPatterns("/pages/board/**");
	}
}
