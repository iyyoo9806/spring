package com.wise.board.persistence;

import java.util.List;

import com.wise.board.domain.BoardVO;

public interface BoardDAO {

	// 게시물 목록
	public List<BoardVO> boardList() throws Exception;
	
	//게시글 작성
	public void write(BoardVO vo) throws Exception;
	
	//조회
	public BoardVO read(String idx) throws Exception;
	
	//수정
	public void modify(BoardVO vo) throws Exception;
	
	//삭제
	public void delete(BoardVO vo) throws Exception;
}
