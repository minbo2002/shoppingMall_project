package com.example.spring001.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring001.model.board.dao.BoardDAO;
import com.example.spring001.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;
	
	@Override
	public void create(BoardDTO dto) throws Exception {
		
		boardDao.create(dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		
		return boardDao.read(bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		
		return boardDao.listAll(start, end, search_option, keyword);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		
		boardDao.increaseViewcnt(bno);
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		
		return boardDao.countArticle(search_option, keyword);
	}

}
