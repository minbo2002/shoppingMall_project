package com.example.spring001.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring001.model.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void create(BoardDTO dto) throws Exception {
		
		sqlSession.insert("board.insert", dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		
		return sqlSession.selectOne("board.view", bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		

	}

	@Override
	public void delete(int bno) throws Exception {
		

	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		
		Map<String, Object> map = new HashMap<>();	// 데이터가 여러개이므로 hashmap으로 묶어서 한번에 보냄
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		System.out.println(map);
		
		return sqlSession.selectList("board.listAll", map);	// start, end는 페이징  search_option, keyword는 검색기능
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		
		sqlSession.update("board.increaseViewcnt", bno);
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("board.countArticle", map);
	}

}
