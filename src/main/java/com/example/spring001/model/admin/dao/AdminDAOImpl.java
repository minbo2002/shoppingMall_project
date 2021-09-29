package com.example.spring001.model.admin.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring001.model.admin.dto.AdminDTO;


@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public String loginCheck(AdminDTO dto) {
		
		return sqlSession.selectOne("admin.login_Check", dto);
	}

}
