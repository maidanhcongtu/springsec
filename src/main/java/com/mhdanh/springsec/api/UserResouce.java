package com.mhdanh.springsec.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserResouce {
	
	@RequestMapping("logout")
	public String logout(){
		SecurityContextHolder.clearContext();
		return "";
	}
	
	@RequestMapping("user/say-hello")
	public String userSay() {
		return "hay hello";
	}
	
}
