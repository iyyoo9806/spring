package com.wise.persistence;

import com.wise.domain.UserVO;

public interface UserDAO {

	//가입
	public void signup(UserVO vo) throws Exception;
	
	//아이디 중복검사
	public UserVO idCheck(String id) throws Exception;
	
	//로그인
	public UserVO login(UserVO vo) throws Exception;
}
