package com.wise.service;


import javax.servlet.http.HttpSession;

import com.wise.domain.UserVO;

public interface UserService {

	//회원가입
	public void signup(UserVO vo) throws Exception;
	
	//아이디 중복체크
	public UserVO idCheck(String id) throws Exception;
	
	//로그인
	public UserVO login(UserVO vo) throws Exception;
	
	//로그아웃
	public void logout(HttpSession session) throws Exception;
}
