package com.wise.login.service;

import com.wise.login.domain.AccountVO;

public interface AccountService {

	//회원가입
	public void register(AccountVO vo) throws Exception;
	
	//아이디 중복체크
	public AccountVO idCheck(String id) throws Exception;
}
