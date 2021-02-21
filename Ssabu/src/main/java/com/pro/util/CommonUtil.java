package com.pro.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommonUtil {

	public String addMsgLoc(Model m, String msg, String loc) {
		m.addAttribute("message", msg);
		m.addAttribute("loc", loc);
		return "message"; // WEB-INF/views/memssage.jsp
	}
	public String addLoc(Model m,  String loc) {
		
		m.addAttribute("loc", loc);
		return "message"; // WEB-INF/views/memssage.jsp
	}

	public String addMsgBack(Model m, String msg) {
		m.addAttribute("message", msg);
		m.addAttribute("loc", "javascript:history.back()");
		return "message";
	}

}////////////////////////////////
