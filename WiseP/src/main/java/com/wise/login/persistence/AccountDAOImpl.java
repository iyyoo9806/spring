package com.wise.login.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wise.login.domain.AccountVO;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Inject
	private SqlSession sql;
	
	//매퍼
	private static String namespace = "com.wise.mappers.accountMapper";
	
	//회원가입
	@Override
	public void register(AccountVO vo) throws Exception {
		sql.insert(namespace + ".register", vo);
	}
	
	//아이디 중복검사
	@Override
	public AccountVO idCheck(String id) throws Exception {
		return sql.selectOne(namespace + ".idCheck", id);
	}
	
	//로그인
	@Override
	public AccountVO login(AccountVO vo) throws Exception {
		return sql.selectOne(namespace + ".login", vo);
	}
}
