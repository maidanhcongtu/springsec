package com.mhdanh.springsec.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("public")
public class PublicResource {
	
	@RequestMapping("empty")
	public String helloYou() {
		return "empty string";
	}
	
}
