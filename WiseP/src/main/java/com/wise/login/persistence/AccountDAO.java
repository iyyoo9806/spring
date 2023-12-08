package com.wise.login.persistence;

import com.wise.login.domain.AccountVO;

public interface AccountDAO {
	//가입
	public void register(AccountVO vo) throws Exception;
	
	//아이디 중복검사
	public AccountVO idCheck(String id) throws Exception;
	
	//로그인
	public AccountVO login(AccountVO vo) throws Exception;
	
	//이름 가져오기
	public String findName(String id) throws Exception;
	
	//회원 탈퇴
	public String withdrawal(String id) throws Exception;
}
