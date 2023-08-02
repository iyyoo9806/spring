package com.wise.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wise.domain.UserVO;
import com.wise.service.UserService;

@Controller
public class UserController {
	
	@Inject
	UserService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	//회원가입
	@RequestMapping(value = "/signup")
	public String signup(UserVO vo, @RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("degree") String degree, @RequestParam("password") String password, ModelMap model) throws Exception {
		System.out.println("name : " + name);
		System.out.println("id : " + id);
		System.out.println("password : " + password);
		System.out.println("degree : " + degree);
		password = passEncoder.encode(password);
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(password);
		vo.setDegree(degree);
		UserVO idCheck = service.idCheck(id);
		
		if(idCheck == null) {
			model.addAttribute("message", "회원가입 성공");
			model.addAttribute("url", "/");
			service.signup(vo);
			System.out.println("성공");
			return "/alert";
		} else {	
			model.addAttribute("message", "아이디가 중복되었습니다.");
			model.addAttribute("url", "/sign");
			System.out.println("실패");
		}
		
		return "/alert";
	}
	
	//로그인
	@RequestMapping(value = "/logining")
	public String logining( UserVO vo, @RequestParam("id") String id, @RequestParam("password") String password,
			ModelMap model, HttpServletRequest request) throws Exception {
		System.out.println("id : " + id);
		System.out.println("password : " + password);
		vo.setId(id);
		vo.setPassword(password);
		UserVO idCheck = service.idCheck(id);
		UserVO login = service.login(vo);
		if(idCheck !=null) {
			boolean passMatch = passEncoder.matches(vo.getPassword(), login.getPassword());
			
			if(passMatch) {
				String name = login.getName();
				String degree = login.getDegree();
				System.out.println("성공");
				System.out.println("직급" + degree);
				request.getSession().setAttribute("id", id);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("degree", degree);
				return "redirect:/board?num=1";
			}else {
				System.out.println("실패");
				request.getSession().setAttribute("id", "");
				request.getSession().setAttribute("name", "");
				request.getSession().setAttribute("verify", "");
				model.addAttribute("message", "비밀번호가 올바르지 않습니다.");
				model.addAttribute("url", "/");
				return "/alert";
			}
		}else {
			System.out.println("실패");
			request.getSession().setAttribute("id", "");
			request.getSession().setAttribute("name", "");
			request.getSession().setAttribute("verify", "");
			model.addAttribute("message", "아이디가 존재하지 않습니다.");
			model.addAttribute("url", "/");
			return "/alert";
		}
	}
}
