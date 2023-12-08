package com.wise.login.service;

import javax.servlet.http.HttpSession;

import com.wise.login.domain.AccountVO;

public interface AccountService {

	//회원가입
	public void register(AccountVO vo) throws Exception;
	
	//아이디 중복체크
	public AccountVO idCheck(String id) throws Exception;
	
	//로그인
	public AccountVO login(AccountVO vo) throws Exception;
	
	//로그아웃
	public void logout(HttpSession session) throws Exception;
	
	//이름
	public String findName(String id) throws Exception;
	
	//탈퇴
	public String withdrawal(String id) throws Exception;
}
