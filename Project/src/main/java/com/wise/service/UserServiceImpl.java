package com.wise.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.wise.domain.UserVO;
import com.wise.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	//회원 가입
	@Override
	public void signup(UserVO vo) throws Exception {
		dao.signup(vo);
	}
	
	//아이디 중복체크
	@Override
	public UserVO idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}
	
	//로그인
	@Override
	public UserVO login(UserVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//로그아웃
	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
	}
}
