package com.wise.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wise.login.domain.AccountVO;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		logger.info("auth : " + authentication);
		
		String id = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());
		
		logger.info("ID : " + id);
		
		AccountVO vo = (AccountVO) userDetailsService.loadUserByUsername(id);
		
		if( !matchPassword(password, vo.getPassword())) {
			logger.info("Not Matched");
			throw new BadCredentialsException(id);
		}
		return new UsernamePasswordAuthenticationToken(id, password, vo.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	private boolean matchPassword(String password, String originPassword) {
//		password = encoder.encode(password);
		logger.info("password : " + password);
		logger.info("org : " + originPassword);
		return encoder.matches(password, originPassword);
	}
}
