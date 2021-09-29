package com.example.spring001.model.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;
import com.example.spring001.model.member.dto.MemberDTO;

@Repository	  // @Repository를 사용해야 Model 클래스로써 스프링이 관리를 해줌  
			  // --> 서버가 실행되면 MemberDAOImpl 클래스를 자동으로 메모리에 올려줌  --> 우리가 호출할때 new 안써도됨. @Inject 써서 쉽게 처리
public class MemberDAOImpl implements MemberDAO {
	
		
	@Inject		// SqlSession 객체를 직접만들지않고 스프링이 연결시켜줌 (DI) , [[ sqlSession.commit(); sqlSession.close(); 자동으로해줌 ]]
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList() {
		
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberDTO vo) {
	
		sqlSession.insert("member.insertMember", vo);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		
		return sqlSession.selectOne("member.viewMember", userid);
	}

	@Override
	public void deleteMember(String userid) {
	
		sqlSession.delete("member.deleteMember", userid);
	}

	@Override
	public void updateMember(MemberDTO vo) {
		
		sqlSession.update("member.updateMember", vo);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {	//회원정보 수정 및 삭제할때 pw체크
		
		boolean result = false;
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		int count = sqlSession.selectOne("member.checkPw", map);	// map에 userid, passwd 를 묶어서 넣음
		
		if(count==1) result=true;	// return값 1이면true ,  0이면 false
		
		return result;
	}
	
	@Override
	public String loginCheck(MemberDTO dto) {
	
		return sqlSession.selectOne("member.login_check", dto);
	}


}
