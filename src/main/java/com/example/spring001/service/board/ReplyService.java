package com.example.spring001.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring001.model.board.dto.ReplyDTO;


public interface ReplyService {

	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session);
	public int count(int bno);
	public void create(ReplyDTO dto);
	public void update(ReplyDTO dto);
	public void delete(Integer bno);
	public ReplyDTO detail(int rno);
	
}
