package com.wise.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

	@GetMapping(value = "/")
	public String login() {
		return "home/login";
	}
	

	@GetMapping(value = "/register")
	public String register() {
		return "home/register";
	}
	
	@GetMapping(value = "/withdrawal")
	public String withdrawal() {
		return "home/withdrawal";
	}
	
	
    @GetMapping("/register/")
    public String redirectBoard() {
        return "redirect:/register";
    }
    
    @GetMapping("/withdrawal/")
    public String redirectWithdrawal() {
        return "redirect:/withdrawal";
    }
}
