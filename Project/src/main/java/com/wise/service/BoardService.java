package com.wise.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wise.domain.BoardVO;

public interface BoardService {
	
	// 사용자 게시물 목록
//	public List<BoardVO> boardList(String id) throws Exception;
	
	// 관리자 게시물 목록 + 페이징 + 검색
	public List<BoardVO> adminList(int displayPost, int postNum, String searchType, String keyword) throws Exception;
	
	// 관리자 게시물 목록 + 검색 + 엑셀
	public List<BoardVO> excel(String searchType, String keyword) throws Exception;
	
	//작성
	public void write(BoardVO vo) throws Exception;
	
	//조회
	public BoardVO read(String idx) throws Exception;
	
	//수정
	public void modify(BoardVO vo) throws Exception;
	
	//삭제
	public void delete(String idx) throws Exception;
	
	//게시물 총 갯수
	public int countAdmin() throws Exception;
	
	//게시물 목록 + 페이징 + 검색
	public List<BoardVO> listPage(String id, int displayPost, int postNum,
			String searchType, String keyword) throws Exception;
	
	//게시물 아이디별 갯수 + 검색
	public int count(String id, String searchType, String keyword) throws Exception;

	//최신 idx
	public String nextIdx() throws Exception;
}
