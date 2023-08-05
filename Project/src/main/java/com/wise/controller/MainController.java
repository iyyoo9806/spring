package com.wise.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wise.domain.BoardVO;
import com.wise.domain.Page;
import com.wise.service.BoardService;

@Controller
public class MainController {
	@Inject
	BoardService service;
	
	//로그인창
	@RequestMapping(value = "/")
	public String login(HttpServletRequest request) {
		String id = (String)request.getSession().getAttribute("id");
		String degree = (String)request.getSession().getAttribute("degree");
		if(id == null) {
			return "account/login";
		}
		
		if(degree == null) {
			return "account/login";
		}
		
		return "redirect:/board?num=1";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	//회원가입
	@RequestMapping(value = "/sign")
	public String signUping() {
		
		return "account/sign";
	}
	
	//게시판 페이지(페이징 처리)
	@RequestMapping(value = "/board")
	public String listPage(ModelMap model, @RequestParam("num") int num,  HttpServletRequest request,
			@RequestParam(value = "searchType",required = false, defaultValue = "title") String searchType,
			   @RequestParam(value = "keyword",required = false, defaultValue = "") String keyword) throws Exception {
		
		Page page = new Page();
		
		String id = (String)request.getSession().getAttribute("id");
		String degree = (String)request.getSession().getAttribute("degree");
		System.out.println("확인 : " + degree);
		
		if(degree.equals("admin")) {
			page.setNum(num);
			page.setCount(service.countAdmin());
			page.setSearchType(searchType);
			page.setKeyword(keyword);
			List<BoardVO> adminList = service.adminList(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
			model.addAttribute("adminList", adminList);   
			model.addAttribute("page", page);
			model.addAttribute("select", num);
			return "board/admin/adminboard";
		}
		System.out.println("searchType " + searchType);
		System.out.println("keyword " + keyword);
		page.setNum(num);
		page.setCount(service.count(id,searchType,keyword));
		//검색 타입과 검색어
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		List<BoardVO> boardList = service.listPage(id, page.getDisplayPost(), page.getPostNum(), searchType, keyword);

		model.addAttribute("boardList", boardList);   
		model.addAttribute("page", page);
		model.addAttribute("select", num);
		
		return "board/user/board";
	}
	
	//게시물 작성 페이지
	@RequestMapping(value = "/write")
	public String write() {
		
		return "board/user/write";
	}
	
	//게시물 상세 페이지
	@RequestMapping(value ="/view")
	public String view(HttpServletRequest request,@RequestParam("idx") String idx, Model model) throws Exception {
//		String degree = (String)request.getSession().getAttribute("degree");
		Object degreeObj = request.getSession().getAttribute("degree");
	    String degree = degreeObj != null ? degreeObj.toString() : "default";
	    if(degree.equals("default")) {
	    	return "redirect:/";
	    }
	    
		
		BoardVO vo = service.read(idx);
		String filePath = vo.getFilepath();
		
		String[] fileNames = new String[0];
		
		if(filePath != null) {
			File dir = new File(filePath);
			fileNames = dir.list();
		}
//		File dir = new File(filePath);
		
//		String[] filenames = dir.list();
		
		model.addAttribute("read", vo);
		model.addAttribute("fileNames", fileNames);// 파일 이름들 모델에 담아 화면에 전송
		
		if(degree.equals("admin")) {
			return "board/admin/adminview";
		}
		
		return "board/user/view";
	}
	
	
	//게시물 수정 페이지
	@RequestMapping(value ="/modify")
	public String modify(@RequestParam("idx") String idx, Model model) throws Exception {
		BoardVO vo = service.read(idx);
		model.addAttribute("read", vo);
		
		return "board/user/modify";
	}
}
