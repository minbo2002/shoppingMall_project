package com.example.spring001.model.member.dao;

import java.util.List;

import com.example.spring001.model.member.dto.MemberDTO;

public interface MemberDAO {

	public List<MemberDTO> memberList();	// List는 ArrayList를 담기위한것. MemberDTO를 저장하는 자료형

	public void insertMember(MemberDTO vo);	// insertMember 함수를 만들고 MemberDTO 객체를 전달한다.
	
	public MemberDTO viewMember(String userid);
	
	public void deleteMember(String userid);
	
	public void updateMember(MemberDTO vo);
	
	public boolean checkPw(String userid, String passwd);
	
	public String loginCheck(MemberDTO dto);
	
	
}
