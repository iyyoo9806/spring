package com.wise.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wise.login.api.LoginApi;

@Controller
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(LoginApi.class);
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public void all() {
		logger.info("all");
	}
}
