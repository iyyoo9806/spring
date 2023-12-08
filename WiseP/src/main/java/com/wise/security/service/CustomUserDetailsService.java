package com.wise.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wise.login.domain.AccountVO;
import com.wise.login.persistence.AccountDAOImpl;

public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private AccountDAOImpl accountDAOImpl;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		logger.info("ID : " + name);
	    try {
	        AccountVO vo = accountDAOImpl.idCheck(name);
	        
	        if (vo == null) {
	            logger.info("NULL");
	            throw new UsernameNotFoundException(name);
	        }
	        logger.info("NOT NULL");
	        logger.info(vo.getId());

	        return vo;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new UsernameNotFoundException("Error while loading user by username: " + name, e);
	    }
	}
}
