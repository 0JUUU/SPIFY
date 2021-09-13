package com.ssafy.house.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.house.model.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.house.model.dto.MemberDto;
import com.ssafy.house.model.service.UserService;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpSession session, HttpServletResponse response) throws Exception {
		System.out.println("<<< 로그인 폼에서 입력받은 이메일, 비밀번호 데이터 들어옵니다.");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginMember = userService.login(memberDto);
			if (loginMember != null) {
				// jwt.io에서 확인한다.
				String token = jwtService.create(loginMember); // 로그인에 성공했다면 토큰을 생성함
				logger.trace("로그인 정보 ; {}", token);

				resultMap.put("token", token); // 토큰 정보는 response 헤더에 보내고 나머지는 Map에 담는다.
				resultMap.put("email", loginMember.getEmail());
				resultMap.put("name", loginMember.getUsername());
				resultMap.put("status", "success");

				status = HttpStatus.ACCEPTED;
				System.out.println(">>> 로그인 정보로 생성된 토큰이 브라우저 세션 스토리지에 전송,저장됩니다.");
			} else {
				resultMap.put("message", "로그인 실패! 아이디와 비밀번호를 확인하세요.");
				System.out.println(">>> 로그인 실패! 아이디와 비밀번호를 확인하세요.");
				status = HttpStatus.NOT_FOUND;
			}
		}catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(">>> 로그인 실패! 아이디와 비밀번호를 확인하세요.");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/login") // 토큰을 Vue에서 받으면 로그인한 세부 개인 정보를 조회하여 Vue에 보내준다.
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest request) {
		System.out.println("<<< 세션 스토리지의 토큰 데이터가 들어옵니다");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			String info = "사용자에게 전달할 중요한 정보";
			resultMap.putAll(jwtService.get(request.getHeader("access_token"))); // 액세스 토큰을 받아서 resultMap에 put
			resultMap.put("status", true); // 받은 상태도 put
			resultMap.put("info", info); // 뭔가 중요하다는 정보도 put
			status = HttpStatus.ACCEPTED;
			System.out.println(">>> 토큰에 일치하는 유저 상세 정보가 전송, 세션 스토리지에 저장됩니다.");
			System.out.println("상세 정보 : " + resultMap);
		} catch (RuntimeException e) {
			logger.error("정보 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
