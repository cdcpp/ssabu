package com.pro.ssabu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/main")
	public String mainPageView(Model m) {
		return "main";
	}
	
	@RequestMapping("/top")
	public void showTop() {
		
	}
	
	@RequestMapping("boardWrite")
	public String showWrite() {
		return "board/boardWrite";
	}
	@RequestMapping("front")
	public String showFront() {
		return "board/front";
	}
	
	@RequestMapping("article")
	public String showArticle() {
		return "board/Article";
	}
}
