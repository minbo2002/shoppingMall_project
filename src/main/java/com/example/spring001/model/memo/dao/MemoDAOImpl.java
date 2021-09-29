package com.example.spring001.model.memo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring001.model.memo.dto.MemoDTO;

@Repository
public class MemoDAOImpl implements MemoDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemoDTO> list() {

		return sqlSession.selectList("memo.memoList");
	}

	@Override
	public void insert(String writer, String memo) {
		
		Map<String, String> map = new HashMap<>();
		map.put("writer", writer);
		map.put("memo", memo);
		
		sqlSession.insert("memo.memoInsert", map);
	}

	@Override
	public MemoDTO memo_view(int idx) {
	
		return sqlSession.selectOne("memo.memoView", idx);
	}

	@Override
	public void memo_update(MemoDTO dto) {
		
		sqlSession.update("memo.memoUpdate", dto);
	}

	@Override
	public void memo_delete(int idx) {
	
		sqlSession.delete("memo.memoDelete", idx);
	}

}
