package com.wise.login.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.wise.login.domain.AccountVO;
import com.wise.login.persistence.AccountDAO;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Inject
	private AccountDAO dao;
	
	//회원가입
	@Override
	public void register(AccountVO vo) throws Exception{
		dao.register(vo);
	}
	
	//아이디 중복체크
	@Override
	public AccountVO idCheck(String id) throws Exception{
		return dao.idCheck(id);
	}
	
	//로그인
	@Override
	public AccountVO login(AccountVO vo) throws Exception{
		return dao.login(vo);
	}
	
	//로그아웃
	@Override
	public void logout(HttpSession session) throws Exception{
		session.invalidate();
	}
}
