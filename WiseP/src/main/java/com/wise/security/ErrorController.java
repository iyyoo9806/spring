package com.wise.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	@RequestMapping("/error")
	public Map<String, Object> handle404() throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("Error", "YES");
		map.put("msg", "Check Your URL");
		return map;
	}
	
	@RequestMapping("/err1")
	public Map<String, Object> err1() throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("Main Msg", "Login Fail");
		map.put("Error Bool", true);
		return map;
	}
	
	@RequestMapping("/err2")
	public Map<String, Object> err2() throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("Main Msg", "No AUTH");
		map.put("Error Bool", true);
		return map;
	}
}
