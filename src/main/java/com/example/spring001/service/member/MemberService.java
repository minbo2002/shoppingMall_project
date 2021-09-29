package com.example.spring001.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring001.model.member.dto.MemberDTO;

public interface MemberService {
	
	public List<MemberDTO> memberList();	// List는 ArrayList를 담기위한것. MemberDTO를 저장하는 자료형
	
	public void insertMember(MemberDTO dto);	// insertMember 함수를 만들고 MemberDTO 객체를 전달한다.
	
	public MemberDTO viewMember(String userid);
	
	public void deleteMember(String userid);
	
	public void updateMember(MemberDTO dto);
	
	public boolean checkPw(String userid, String passwd);	// 비밀번호 체크
	
	public String loginCheck(MemberDTO dto, HttpSession session);
	
	public void logout(HttpSession session);
}
