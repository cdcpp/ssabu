package com.post.domain;

import java.util.Date;
import java.util.List;

import com.member.domain.MemberVO;

import lombok.Data;

@Data
public class ReplyVO {
	private int pIdx;
	private int rIdx;
	private int rmIdx;
	private String rContent;
	private String emoji;
	private Date rDate;
	private int refer;
	private int lev;
	private int sunbun;
	private int rState; 
	
	private List<MemberVO> mvo;
	
	
}
