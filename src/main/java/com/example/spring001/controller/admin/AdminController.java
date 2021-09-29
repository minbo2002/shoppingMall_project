package com.example.spring001.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring001.model.admin.dto.AdminDTO;
import com.example.spring001.model.member.dto.MemberDTO;
import com.example.spring001.service.admin.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Inject
	AdminService adminService;
	
	@RequestMapping("login.do")
	public String login() {
		
		return "admin/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(AdminDTO dto, MemberDTO dto2, HttpSession session, ModelAndView mav ) {
		
		String name = adminService.loginCheck(dto);	  // admin 테이블의 유저 name을 가져옴
		
		if(name != null ) {
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto2.getUserid());
			session.setAttribute("name", dto2.getName());
			
			mav.setViewName("admin/admin");
			mav.addObject("message", "success");
		
		}else {
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(ModelAndView mav, HttpSession session) {
		
		adminService.logout(session);	// 세션 초기화
		
		mav.setViewName("admin/login");
		mav.addObject("message", "logout");
		
		return mav;
	}
}
