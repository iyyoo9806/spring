package com.wise.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wise.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject
	private SqlSession sql;
	
	//매퍼
	private static String namespace = "com.wise.mappers.userMapper";
	
	//회원가입
	@Override
	public void signup(UserVO vo) throws Exception {
		sql.insert(namespace + ".signup", vo);
	}
	
	//아이디 중복검사
	@Override
	public UserVO idCheck(String id) throws Exception {
		return sql.selectOne(namespace + ".idCheck", id);
	}
	
	//로그인
	@Override
	public UserVO login(UserVO vo) throws Exception {
		return sql.selectOne(namespace + ".login", vo);
	}
}
