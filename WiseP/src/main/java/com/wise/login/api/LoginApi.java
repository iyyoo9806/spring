package com.wise.login.api;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.login.domain.AccountVO;
import com.wise.login.service.AccountService;

@Controller
public class LoginApi {
	
	@Inject
	AccountService service;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginApi.class);
	@ResponseBody
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public HashMap<String, String> apiRegister(@RequestBody Map<String, String> body) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		AccountVO vo = new AccountVO();
		String id = body.get("id");
		String name = body.get("name");
		String password = body.get("password");
		logger.info(id);
		password = encoder.encode(password);
		vo.setId(id);
		vo.setName(name);
		vo.setPassword(password);
		AccountVO idCheck = service.idCheck(id);
		if(idCheck == null) {
			service.register(vo);
			map.put("status", "success");
			map.put("message", "회원가입 성공");
			logger.info("성공");
			
		}else {
			map.put("status", "fail");
			map.put("message", "중복된 아이디가 존재합니다.");
			logger.info("실패");
		}
		
		return map;
	}
}
