package com.pro.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;
/*스프링의 예외 처리 방법
 * [1] @ExceptionHandler 를 이용하는 방법
 * [2] @ControllerAdvice 를 이용하는 방법
 * [3] @ResponseStatus 를 이용한 Http상태코드 처리
 * */
//@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	//모든 예외를 처리하는 역할을 함. @ControllerAdvice는 이 클래스의 객체가
	//컨트롤러에서 발생하는 예외를 전문적으로 처리하는 클래스임을 명시하는것
	@ExceptionHandler(Exception.class)
	public String common(Exception ex) {
		log.error(ex.toString());
		return "login/errorAlert";
	}
}
