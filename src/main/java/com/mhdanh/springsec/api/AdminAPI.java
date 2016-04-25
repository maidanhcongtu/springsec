package com.mhdanh.springsec.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AdminAPI {
	
	@RequestMapping("admin-say")
	public String adminSay() {
		return "admin say";
	}
	
}
