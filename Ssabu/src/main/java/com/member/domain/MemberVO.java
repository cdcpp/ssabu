package com.member.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int midx;
	private String name;
	private String email;
	private String pwd;
	private String hp;

	private String sex;
	private String mImage;
	private String pInfo;
	private String bgm;
	private String emailYN;

	private String birth;
	private Date date;

}