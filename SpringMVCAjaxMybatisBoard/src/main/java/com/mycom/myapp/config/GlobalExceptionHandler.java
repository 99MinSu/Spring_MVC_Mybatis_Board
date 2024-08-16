package com.mycom.myapp.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 개별 Controller 에서 처리하지 않는 예외를 일괄 처리
// 에러 페이지 (jsp) 대신 예외가 발생했음을 json 으로 내려준다.
// 응답은 다른 jsp <-> backEnd dto (board.jsp 일 경우 BoardResultDto) 에 대응하도록 응답 구성 (result:success...)
// client 와 상호 약속된 예외 코드 체계를 통해서 보다 다양한 예외 대응 처리 가능하게 할 수 있다.
@ControllerAdvice
@RestController // json 응답
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public Map<String,String> handleException(Exception e) {
		Map<String,String> map = new HashMap<>();
		// e 객체를 이용해서 e의 타입, 종류에 따라 다른 메세지를 내려주고 jsp(html)에서는 그에 대응하는 화면 처리
		map.put("result", "exception");
		return map;
	}
}

// Global Exception Handler : 일반화 된 예외
// Local Exception Handler : 구체화 된 예외