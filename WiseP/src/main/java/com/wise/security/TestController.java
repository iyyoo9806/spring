package com.wise.security;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wise.login.api.LoginApi;

@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(LoginApi.class);
	
	@PostMapping("/auth/t1")
	public Map<String, Object> te11(Principal principal){
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("okay", "OO");
		map.put("AUTH", principal);
		return map;
	}
}
