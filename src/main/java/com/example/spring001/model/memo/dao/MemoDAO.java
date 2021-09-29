package com.example.spring001.model.memo.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring001.model.memo.dto.MemoDTO;


public interface MemoDAO {

	public List<MemoDTO> list();
	
	public void insert(@RequestParam("writer") String writer, @RequestParam("memo") String memo);
	
	public MemoDTO memo_view(@RequestParam("idx") int idx);
	
	public void memo_update(MemoDTO dto);
	
	public void memo_delete(@RequestParam("idx") int idx);
}
