package com.ssafy.house.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.dto.MemberDto;

@Mapper
public interface UserMapper {

//	public MemberDto login(Map<String, String> map);
	public MemberDto login(MemberDto memberDto) throws Exception;
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
