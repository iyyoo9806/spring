package com.wise.login.service;

import javax.inject.Inject;

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
	
	@Override
	public AccountVO idCheck(String id) throws Exception{
		return dao.idCheck(id);
	}
}
