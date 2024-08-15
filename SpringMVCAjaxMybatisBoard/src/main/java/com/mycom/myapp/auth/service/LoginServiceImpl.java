package com.mycom.myapp.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.auth.dao.LoginDao;
import com.mycom.myapp.user.dto.UserDto;

@Service
public class LoginServiceImpl implements LoginService{
	// LoginDao DI
	private final LoginDao loginDao;
	
	public LoginServiceImpl(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	@Override
	public Optional<UserDto> login(UserDto dto) { // Controller 에서 받은 UserDto 객체에는 userEmail, userPassword 가 포함
		
		UserDto userDto = loginDao.login(dto.getUserEmail()); // loginDao의 처리 결과에 따라 null, 정상적인 UserDto 객체
		
		if(userDto != null && dto.getUserPassword().equals(userDto.getUserPassword())){ // 로그인 성공
			userDto.setUserPassword(null); // password 삭제
			return Optional.of(userDto); 
		}
		return Optional.empty();
	}

}
