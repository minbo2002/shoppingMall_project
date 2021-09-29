package com.example.spring001.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring001.model.board.dto.BoardDTO;
import com.example.spring001.service.board.BoardService;
import com.example.spring001.service.board.Pager;

@Controller
@RequestMapping("/board/*")  // 공통적인 mapping
public class BoardController {

	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do")  // 세부적인 mapping
	public ModelAndView list(
			@RequestParam(defaultValue="1") int curPage, // defaultValue 지정안하면  NPE나 400 에러 발생할수있음
			@RequestParam(defaultValue="all") String search_option,
			@RequestParam(defaultValue="") String keyword ) throws Exception {
		
		int count = boardService.countArticle(search_option, keyword);  // 레코드 갯수
		
		Pager pager = new Pager(count, curPage);
		
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		
		List<BoardDTO> list = boardService.listAll(start, end, search_option, keyword);  // 목록 list(시작번호, 끝번호, 검색옵션, 검색옵션)
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/list");	// 이동할 페이지 지정
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);		// map에 자료 저장 (key value)
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("pager", pager);
		
		mav.addObject("map", map);  // 데이터 저장
		
		return mav;
	}
	
	@RequestMapping("write.do")
	public String write() {
		
		return "board/write";
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception {
		
		// 로그인한 사용자 아이디
		String writer=(String)session.getAttribute("userid");
		dto.setWriter(writer);
		
		if(writer==null) {
			
			return "redirect:/member/login.do";
			
		}else {
			
			// 레코드 저장됨
			boardService.create(dto);
			// 목록 갱신
			return "redirect:/board/list.do";
		}

	}
	
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno,
							 @RequestParam int curPage,
							 @RequestParam String search_option,
							 @RequestParam String keyword, HttpSession session) throws Exception {
		
		boardService.increaseViewcnt(bno);	// 조회수 증가처리
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("dto", boardService.read(bno));
		mav.addObject("curPage", curPage);
		mav.addObject("search_option", search_option);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
}
