package com.pro.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;
/*�������� ���� ó�� ���
 * [1] @ExceptionHandler �� �̿��ϴ� ���
 * [2] @ControllerAdvice �� �̿��ϴ� ���
 * [3] @ResponseStatus �� �̿��� Http�����ڵ� ó��
 * */
//@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	//��� ���ܸ� ó���ϴ� ������ ��. @ControllerAdvice�� �� Ŭ������ ��ü��
	//��Ʈ�ѷ����� �߻��ϴ� ���ܸ� ���������� ó���ϴ� Ŭ�������� ����ϴ°�
	@ExceptionHandler(Exception.class)
	public String common(Exception ex) {
		log.error(ex.toString());
		return "login/errorAlert";
	}
}
