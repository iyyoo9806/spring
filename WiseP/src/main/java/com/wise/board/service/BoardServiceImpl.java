package com.wise.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.wise.board.domain.BoardVO;
import com.wise.board.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	//목록 조회
	@Override
	public List<BoardVO> boardList() throws Exception {
		return dao.boardList();
	}
	
	//게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		dao.write(vo);
	}
	
	//조회
	@Override
	public BoardVO read(String idx) throws Exception {
		return dao.read(idx);
	}
	
	//수정
	@Override
	public void modify(BoardVO vo) throws Exception {
		dao.modify(vo);
	}
	
	//삭제
	@Override
	public void delete(BoardVO vo) throws Exception {
		dao.delete(vo);
	}
}
