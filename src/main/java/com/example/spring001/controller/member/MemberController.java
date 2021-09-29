package com.example.spring001.controller.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring001.model.member.dto.MemberDTO;
import com.example.spring001.service.member.MemberService;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	@RequestMapping("list.do")		// 회원리스트
	public String memberList(Model model) {
		
		List<MemberDTO> list = memberService.memberList();
		logger.info("회원 목록 : " + list);
		model.addAttribute("list", list);
		
		return "member/member_list";
	}
	
	@RequestMapping("write.do")	// 회원등록
	public String write() {
		
		return "member/write";
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		
		memberService.insertMember(dto);
		
		return "redirect:/member/list.do";
	}
	
	// <a href="/member/view.do?userid=minbo2002"> 이라면 @RequestParam String userid 변수가 minbo2002가 되는것
	@RequestMapping("view.do")
	public String view(@RequestParam String userid, Model model) {
		
		model.addAttribute("dto", memberService.viewMember(userid));
		
		return "member/view";
	}
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		
		logger.info("비밀번호 확인 : " + result);
		
		if(result) {
			memberService.updateMember(dto);
			return "redirect:/member/list.do";
		
		}else {
			MemberDTO dto2 = memberService.viewMember(dto.getUserid());	 // 비밀번호 틀리면 전체 dto 내용 다시 나오게한다.
			dto.setJoin_date(dto2.getJoin_date());	// 날짜가 없지지지않도록 한다.
			
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지않습니다.");
			return "member/view";
		}
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam String userid, @RequestParam String passwd, Model model) {
		 			// userid, passwd를 한번에 @ModelAttribute로 만들어도 되는데 dto 담는게 실패할때도 있기때문에 각각 사용
		//비밀번호 체크
		boolean result = memberService.checkPw(userid, passwd);
		if(result) {				// MemberDAOImpl에서 return = true이면 삭제
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		
		}else {  // 비밀번호 틀렸을때
			model.addAttribute("message", "비밀번호가 일치하지않습니다.");
			model.addAttribute("dto", memberService.viewMember(userid));	// model.addAttribute("변수명", "값");
			return "member/view";
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("login.do")	  
	public String login() {
		
		return "member/login";
	}
	
	@RequestMapping("login_check.do")	  
	public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session) {
		
		// 로그인 성공시 -> 이름이 넘어옴    /  로그인 실패시 -> null이 넘어옴
		
		String name=memberService.loginCheck(dto, session);
		logger.info("name : " + name);
		
		ModelAndView mav = new ModelAndView();
		
		if(name != null) {
			mav.setViewName("home");
		}else {
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(ModelAndView mav, HttpSession session) {
		
		memberService.logout(session);	// 세션 초기화
		
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		
		return mav;
	}
}
