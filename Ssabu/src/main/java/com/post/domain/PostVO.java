package com.post.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostVO implements Serializable{
	private int pIdx;   
	private String paaaStr="java";  
	private int bIdx;
	private String subject;
	private String content;
	private int hitCount;
	private int pstate;
	private Timestamp pdate;
	private int likeCount;
	private int mIdx;
	
	private ImageFileVO imageFile;
	
/*	private List<ImageFileVO> imageList;
	private List<ReplyVO> replyList;
	private List<TagVO> tagList;*/
}
