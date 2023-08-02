package com.wise.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wise.domain.BoardVO;
import com.wise.service.BoardService;

@Controller
public class BoardController {
	
	@Inject
	BoardService service;
	
	//게시물 작성
	@RequestMapping(value = "/writing")
	public String writing(BoardVO vo, @RequestParam("title") String title,
			@RequestParam("contents") String contents, HttpServletRequest request,
			ModelMap model) throws Exception {
		
		try {
			String id = request.getSession().getAttribute("id").toString();
			String name = request.getSession().getAttribute("name").toString();
			String degree = request.getSession().getAttribute("degree").toString();
			
			vo.setId(id);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setName(name);
			vo.setDegree(degree);
			service.write(vo);
			model.addAttribute("message", "글작성 성공");
			model.addAttribute("url", "/board?num=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "실패");
			model.addAttribute("url", "/board?num=1");
		}
		
		return "/alert";
	}
		
	//게시물 수정
	@RequestMapping(value = "/modifying", method = RequestMethod.POST)
	public String postModify(BoardVO vo, @RequestParam("title") String title,
			@RequestParam("contents") String contents, @RequestParam("idx") String idx
			,HttpServletRequest request, ModelMap model) throws Exception {
		try {
			vo.setIdx(idx);
			vo.setTitle(title);
			vo.setContents(contents);
			System.out.println(title);
			System.out.println(idx);
			service.modify(vo);
			model.addAttribute("message", "수정 성공");
			model.addAttribute("url", "/board?num=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "실패");
			model.addAttribute("url", "/board?num=1");
		}
		return "/alert";
	}
	
	//게시물 삭제
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("idx") String idx, ModelMap model) throws Exception {
		try {
			service.delete(idx);
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("url", "/board?num=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "실패");
			model.addAttribute("url", "/board?num=1");
		}
		return "/alert";		
	}
}
