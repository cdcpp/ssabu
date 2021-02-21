package com.member.domain;
/*1. Exception을 상속받는다.
 *2. 생성자를 만들어 예외 메시지를 super()를 통해 등록한다 
 * */

public class NotUserException extends Exception{
	
	public NotUserException() {
		super("회원이 아닙니다");
	}
	public NotUserException(String msg) {
		super(msg);
	}

}
