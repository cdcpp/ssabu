package com.post.domain;

import java.util.List;

import lombok.Data;

@Data
public class TagVO {
	private int pIdx;
	private int tIdx;
	private String tName;
	
	private List<TagVO> tvo;
}
