package com.example.spring001.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring001.model.board.dto.ReplyDTO;
import com.example.spring001.service.board.Pager;
import com.example.spring001.service.board.ReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Inject
	ReplyService replyService;
		
	@RequestMapping("insert.do")
	public void insert(ReplyDTO dto, HttpSession session) {
		
		System.out.println("insert.do 실행");
				
		String userid = (String) session.getAttribute("userid");
		dto.setReplyer(userid);	   // 댓글쓸 아이디
		replyService.create(dto);  // 댓글 쓰기	
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(int bno, @RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session) {
		
		int count = replyService.count(bno);  // 댓글 개수
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();	// 페이지 시작번호
		int end = pager.getPageEnd();		// 페이지 끝번호
		
		List<ReplyDTO> list = replyService.list(bno, start, end, session);
		
		mav.setViewName("board/reply_list");
		mav.addObject("list", list);
		mav.addObject("pager", pager);
		
		return mav;
	}
}
