package com.example.spring001.service.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring001.model.member.dao.MemberDAO;
import com.example.spring001.model.member.dto.MemberDTO;

@Service	// --> 서버가 실행되면 MemberServiceImpl 클래스를 자동으로 메모리에 올려줌  --> 우리가 호출할때 new 안써도됨. @Inject 써서 쉽게 처리
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDao;
	
	@Override
	public List<MemberDTO> memberList() {
		
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		
		memberDao.insertMember(dto);
	}

	@Override
	public MemberDTO viewMember(String userid) {
	
		return memberDao.viewMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
	
		memberDao.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		
		memberDao.updateMember(dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		
		return memberDao.checkPw(userid, passwd);
	}

	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) {
		
		String name = memberDao.loginCheck(dto);	// 사용자가 userid, passwd 입력하면 dto에 담아서 넘김
		
		if(name != null) {	// id,pw가 맞으면  name이 넘어가고 틀리면 null이 넘어감
						
			// 세션 등록
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
		}
		
		return name;
	}

	@Override
	public void logout(HttpSession session) {
		
		session.invalidate();	// 세션 초기화
	}


}
