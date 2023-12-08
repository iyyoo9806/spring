package com.wise.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogoutController {
	
	@RequestMapping("/board/loginOkay")
	public Map<String, Object> loginOkay() throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("Login", "Okay");
		return map;
	}
	
	@RequestMapping("/successLogout")
	public Map<String, Object> sucessLogout() throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("Logout", "Okay");
		return map;
	}
}
