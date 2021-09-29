package com.example.spring001.model.board.dao;

import java.util.List;

import com.example.spring001.model.board.dto.BoardDTO;

public interface BoardDAO {

	public void create(BoardDTO dto) throws Exception; // 글쓰기
	public BoardDTO read(int bno) throws Exception; // 글읽기
	public void update(BoardDTO dto) throws Exception; // 글수정
	public void delete(int bno) throws Exception; // 글삭제
	
	// 목록(페이지 나누기, 검색기능 포함)
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception;
	
	// 조회수 증가처리
	public void increaseViewcnt(int bno) throws Exception;
	
	// 레코드 갯수 계산
	public int countArticle(String search_option, String keyword) throws Exception;
}
