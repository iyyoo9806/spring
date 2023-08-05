package com.wise.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

	  HttpSession session = request.getSession();
	  String id = (String)session.getAttribute("id");
	  if(id == null) {
		  response.sendRedirect("/");
		  return false;
	  }
	  
	  return true;


	}
}
