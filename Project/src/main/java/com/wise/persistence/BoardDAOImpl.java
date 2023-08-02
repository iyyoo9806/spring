package com.wise.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wise.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sql;
	
	//매퍼
	private static String namespace = "com.wise.mappers.boardMapper";
	
	//사용자 게시물 조회
//	@Override
//	public List<BoardVO> boardList(String id) throws Exception {
//		return sql.selectList(namespace + ".showList", id);
//	}
	
	//관리자 조회
	@Override
	public List<BoardVO> adminList(int displayPost, int postNum,
			String searchType, String keyword) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		return sql.selectList(namespace + ".adminList", data);
	}
	
	//작성
	@Override
	public void write(BoardVO vo) throws Exception {
		sql.insert(namespace + ".write", vo);
	}
	
	//조회
	@Override
	public BoardVO read(String idx) throws Exception {
		return sql.selectOne(namespace + ".read", idx);
	}
	
	//수정
	@Override
	public void modify(BoardVO vo) throws Exception {
		sql.update(namespace + ".modify", vo);
	}
	
	//삭제
	@Override
	public void delete(String idx) throws Exception {
		sql.delete(namespace + ".delete", idx);
	}
	
	//게시물 총 갯수
	@Override
	public int countAdmin() throws Exception {
		return sql.selectOne(namespace + ".countAdmin");
	}
	
	//게시물 목록 + 페이징
	@Override
	public List<BoardVO> listPage(String id, int displayPost, int postNum,
			String searchType, String keyword) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".listPage", data);
	}
	
	//게시물 아이디별 갯수
	@Override
	public int count(String id, String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace + ".count", data);
	}
}
