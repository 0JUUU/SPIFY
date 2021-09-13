package com.ssafy.house.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ssafy.house.model.dto.MemberDto;

public interface UserService {
//	public MemberDto login(Map<String, String> map) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
//	REST 
	public List<MemberDto> userList();
	public MemberDto userInfo(String email);
	public int userRegister(MemberDto memberDto);
	public int userModify(MemberDto memberDto);
	public int userDelete(String email) throws Exception;
	
	// 이메일 발송
	public void sendEmail(MemberDto memberDto, String div) throws Exception;
	
	// 비밀번호 찾기
	public void findPw(String email) throws Exception;
}
