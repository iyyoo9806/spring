package com.wise.login.api;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wise.login.domain.AccountVO;
import com.wise.login.service.AccountService;

@RestController
public class LoginApi {
	
	@Inject
	AccountService service;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginApi.class);
	
	@PostMapping(value = "/api/register")
	public HashMap<String, String> apiRegister(@RequestBody Map<String, String> body) throws Exception {
		logger.info("okay");
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
	
	@PutMapping(value = "/api/withdrawal")
	public HashMap<String,Object> apiWithdrawal(Principal principal, @RequestBody Map<String,String> body) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String id = principal.getName();
		String hiddenId = body.get("hiddenId");
		
		if(id.equals(hiddenId)) {
			service.withdrawal(id);
			SecurityContextHolder.getContext().setAuthentication(null);
			map.put("status", "success");
			map.put("message", "성공적으로 탈퇴하셨습니다.");
		}else {
			map.put("status", "fail");
			map.put("message", "에러 발생");
			logger.info("실패");
		}
		return map;
	}
	
}
