package com.wise.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "home/login";
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet() {
		return "home/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost() {
		return "home/register";
	}

}
