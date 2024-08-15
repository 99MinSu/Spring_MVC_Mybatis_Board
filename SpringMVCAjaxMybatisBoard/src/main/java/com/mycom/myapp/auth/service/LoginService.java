package com.mycom.myapp.auth.service;

import java.util.Optional;

import com.mycom.myapp.user.dto.UserDto;

public interface LoginService {
	// Controller 에서 userEail, userPassword 가 포함된 UserDto 객체를 전달 받는다.
	// UserDto 의 userEmail 을 이용해서 LoginDao 의 login() 호출
	// -> LoginDao 의 login() 호출 결과가 null => userEmail 오류
	// -> LoginDao 의 login() 호출 결과가 UserDto => userEmail 존채, 전달받은 userPassword 의 dao 로부터 받은 userPassword 를 비교
	// LoginService 를 호출한 Controller 에서 Null check
	// Optional 적용 ( value 가 null 이 될수도 있는 경우 적용 ) 일종의 Wrapper Class
	Optional<UserDto> login(UserDto userDto);
}
