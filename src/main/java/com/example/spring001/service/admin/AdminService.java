package com.example.spring001.service.admin;

import javax.servlet.http.HttpSession;

import com.example.spring001.model.admin.dto.AdminDTO;

public interface AdminService {

	public String loginCheck(AdminDTO dto);
	
	public void logout(HttpSession session);
}
