package com.wise.board.api;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wise.board.domain.BoardVO;
import com.wise.board.service.BoardService;
import com.wise.login.service.AccountService;

@RestController
public class BoardApi {
	
	@Inject
	BoardService boardService;
	
	@Inject
	AccountService accountService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardApi.class);

	@GetMapping("/api/boardList")
	public List<BoardVO> getBoardList() throws Exception {
		List<BoardVO> boardList = boardService.boardList();
		return boardList;
	}
	
	
	@PostMapping("/api/write")
	public HashMap<String, String> apiWrite(Principal principal, @RequestBody Map<String,String> body) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String id = principal.getName();
		String hiddenId = body.get("hiddenId");
		
		if(id.equals(hiddenId)) {
			String title = body.get("title");
			String contents = body.get("contents");
			logger.info(title);
			logger.info(contents);
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setId(id);
			
			Date currentDate = new Date();
			vo.setCrdTime(currentDate);
			String name = accountService.findName(id);
			vo.setName(name);
			boardService.write(vo);
			//히스토리 질문 내일?
			map.put("status", "success");
			map.put("message", "글작성 성공.");
			logger.info("성공");
		}else {
			map.put("status", "fail");
			map.put("message", "잘못된 접속으로 작성을 시도했습니다.");
			logger.info("실패");
		}
		return map;
	}
	
	@GetMapping("/api/view")
	public HashMap<String, Object> apiView(@RequestParam(name = "idx", required = false) String idx) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		//idx 값에 해당하는 id, 제목 내용 가져오기
		BoardVO vo = new BoardVO();
		
		if(idx != null) {
			vo = boardService.read(idx);
			map.put("read", vo);
			map.put("status", "success");
			map.put("url", "/board/view?idx=" + idx);
		}else {
			map.put("status", "fail");
			map.put("message", "잘못된 접속으로 작성을 시도했습니다.");
		}
		return map;
	}
	
	
	@PutMapping("/api/modify")
	public HashMap<String, Object> apiModify(Principal principal, @RequestBody Map<String,String> body) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String id = principal.getName();
		String hiddenId = body.get("hiddenId");
		
		if(id.equals(hiddenId)){
			String title = body.get("title");
			String contents = body.get("contents");
			String idx = body.get("idx");
			
			BoardVO vo = new BoardVO();
			Date currentDate = new Date();
			vo.setIdx(idx);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setUpdTime(currentDate);
			boardService.modify(vo);
			map.put("status", "success");
			map.put("message", "글수정 성공.");
			logger.info("성공");
		}else {
			map.put("status", "fail");
			map.put("message", "잘못된 접속으로 수정을 시도했습니다.");
			logger.info("실패");
		}
		return map;
	}
	
	@DeleteMapping("/api/delete")
	public HashMap<String, Object> apiDelete(Principal principal, @RequestBody Map<String, String> body) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String id = principal.getName();
		String hiddenId = body.get("hiddenId");

		
		if(id.equals(hiddenId)) {
			String idx = body.get("idx");
			Date currentDate = new Date();
			BoardVO vo = new BoardVO();
			vo.setIdx(idx);
			vo.setDelTime(currentDate);
			boardService.delete(vo);
			map.put("status", "success");
			map.put("message", "글삭제 성공.");
			logger.info("성공");
		}else {
			map.put("status", "fail");
			map.put("message", "잘못된 접속으로 삭제를 시도했습니다.");
			logger.info("실패");
		}
		
		return map;
	}
}
