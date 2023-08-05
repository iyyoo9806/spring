package com.wise.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wise.domain.BoardVO;
import com.wise.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	// 사용자 게시물 목록
//	@Override
//	public List<BoardVO> boardList(String id) throws Exception {
//		return dao.boardList(id);
//	}
	
	// 관리자 게시물 목록
	@Override
	public List<BoardVO> adminList(int displayPost, int postNum,
			String searchType, String keyword) throws Exception {
		return dao.adminList(displayPost, postNum, searchType, keyword);
	}
	
	// 관리자 게시물 엑셀
	@Override
	public List<BoardVO> excel(String searchType, String keyword) throws Exception {
		return dao.excel(searchType, keyword);
	}
	
	//작성
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
	public void delete(String idx) throws Exception {
		dao.delete(idx);
	}
	
	//게시물 총 갯수
	@Override
	public int countAdmin() throws Exception {
		return dao.countAdmin();
	}
	
	//게시물 목록 + 페이징
	@Override
	public List<BoardVO> listPage(String id, int displayPost, int postNum,
			String searchType, String keyword) throws Exception {
		
		return dao.listPage(id, displayPost, postNum, searchType, keyword);
	}
	
	//게시물 아이디별 갯수 + 검색
	@Override
	public int count(String id, String searchType, String keyword) throws Exception {
		
		return dao.count(id, searchType, keyword);
	}
	
	//최신 Idx
	@Override
	public String nextIdx() throws Exception {
		return dao.nextIdx();
	}
}
