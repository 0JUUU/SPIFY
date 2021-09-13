package com.ssafy.house.model.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.jni.Mmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dao.UserMapper;
import com.ssafy.house.model.dto.MemberDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
//	@Override
//	public MemberDto login(Map<String, String> map) throws Exception {
//		System.out.println("email : " + map.get("email"));
//		System.out.println("userpwd : " + map.get("userpwd"));
//		if(map.get("email") == null || map.get("userpwd") == null)
//			return null;
//		return sqlSession.getMapper(UserMapper.class).login(map);
//	}

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).login(memberDto);
	}

	@Override 
	public List<MemberDto> userList() {
		return sqlSession.getMapper(UserMapper.class).userList();
	}

	@Override
	public MemberDto userInfo(String email) {
		return sqlSession.getMapper(UserMapper.class).userInfo(email);
	}

	@Override
	public int userRegister(MemberDto memberDto) {
		return sqlSession.getMapper(UserMapper.class).userRegister(memberDto);
	}

	@Override
	public int userModify(MemberDto memberDto) {
		return sqlSession.getMapper(UserMapper.class).userModify(memberDto);
	}

	@Override
	public int userDelete(String email) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userDelete(email);
	}

	@Override
	public void sendEmail(MemberDto memberDto, String div) throws Exception {
		// 메일 서버 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "5happyhouse2021@gmail.com";
		String hostSMTPpwd = "ssafy1234";
		
		// 보내는 사람 Email, 제목, 내용
		String fromEmail = "5happyhouse2021@gmail.com";
		String fromName = "HappyHouse 계정 관리팀";
		String subject = "";
		String msg = "";
		
		if (div.equals("findpw")) {
			subject = "<HappyHouse> 회원님의 비밀번호 안내 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += memberDto.getUsername() + "님 ( "+ memberDto.getEmail() +" ) 의 임시 비밀번호 입니다.</h3><br>";
			msg += "<p> 임시 비밀번호 : ";
			msg += memberDto.getUserpwd() + "</p><br>";
			msg += "<h1 style='color: red;'>반드시 로그인 후 비밀번호를 바꿔주세요!!!!</h2></div>";
		}
		
		String toMail = memberDto.getEmail();
		String toName = memberDto.getUsername();
		
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(toMail, toName, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch(Exception e) {
			
			System.out.println("메일 발송 실패 : " + e);
		}
		
	}

	@Override
	public void findPw(String email) throws Exception {
		MemberDto ck = sqlSession.getMapper(UserMapper.class).userInfo(email);
	
		System.out.println(ck.toString());
		if (ck == null) {
			System.out.println("데이터가 없다.");
		} else if (!email.equals(ck.getEmail())) {
			System.out.println("데이터가 없다.");
		} else {
			
			String pw = "";
			for (int i = 0 ; i <12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			
			ck.setUserpwd(pw); // 난수화 된 임시 비밀번호를 dto에 바꿔치기 한다.
			sqlSession.getMapper(UserMapper.class).userModify(ck); // 그리고 이 dto를 DB에 update!
			
			sendEmail(ck, "findpw");
		}	
	}
}
