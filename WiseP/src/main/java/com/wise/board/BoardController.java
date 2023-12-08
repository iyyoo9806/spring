package com.wise.board;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wise.board.domain.BoardVO;
import com.wise.board.service.BoardService;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board() {
		return "board/board";
	}

	@GetMapping(value = "/board/write")
	public String write() {
		return "board/write";
	}
	
	@GetMapping(value = "/board/view")
	public String view() throws Exception {
		return "board/view";
	}
	
	@GetMapping(value = "/board/modify")
	public String modify() throws Exception {
		return "board/modify";
	}
	
    @GetMapping("/board/")
    public String redirectBoard() {
        return "redirect:/board";
    }
    
    @GetMapping("/board/write/")
    public String redirectWrite() {
        return "redirect:/board/write";
    }
    
    @GetMapping("/board/view/")
    public String redirectView() {
        return "redirect:/board/view";
    }
    
    @GetMapping("/board/modify/")
    public String redirectModify() {
        return "redirect:/board/modify";
    }
}
