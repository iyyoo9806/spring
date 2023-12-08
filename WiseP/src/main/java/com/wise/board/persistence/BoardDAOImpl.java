package com.wise.board.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wise.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;

	// 매퍼
	private static String namespace = "com.wise.mappers.boardMapper";

	// 게시물 목록
	@Override
	public List<BoardVO> boardList() throws Exception {
		return sql.selectList(namespace + ".listPage");
	}
	
	//게시물 작성
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
	public void delete(BoardVO vo) throws Exception {
		sql.delete(namespace + ".delete", vo);
	}
}
