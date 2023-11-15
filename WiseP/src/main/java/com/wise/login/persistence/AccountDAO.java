package com.wise.login.persistence;

import com.wise.login.domain.AccountVO;

public interface AccountDAO {
	//가입
	public void register(AccountVO vo) throws Exception;
	
	//아이디 중복검사
	public AccountVO idCheck(String id) throws Exception;
}
