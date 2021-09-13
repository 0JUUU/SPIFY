package com.ssafy.house.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.model.dto.MemberDto;
import com.ssafy.house.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public String userRegister(@RequestBody MemberDto memberDto) {
		System.out.println("<<< 입력폼의 데이터가 들어옵니다");
		
		try {
			userService.userRegister(memberDto);
			System.out.println(">>> 성공 : 데이터가 데이터 베이스에 저장되었습니다");
			
		} catch (Exception e) {
			System.out.println(">>> 실패 : 이메일이 중복됩니다.");
			return "FAIL";
		}
		return "SUCCESS";
	}

	@PutMapping("/user")
	public String modify(@RequestBody MemberDto memberDto) {
		System.out.println("<<< 수정폼의 데이터가 들어옵니다");
		try {
			if (userService.userModify(memberDto) > 0) {
				System.out.println(">>> 성공 : 데이터가 수정되었습니다");
				return "SUCCESS";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(">>> 실패 : 데이터가 잘 못 되었습니다.");
			return "FAIL";
		}
		return "FAIL";
	}

	@DeleteMapping("/user/{email}")
	public String remove(@PathVariable("email") String email) {
		System.out.println("<<< 서버에 삭제 할 이메일 주소가 들어옵니다");
		try {
			userService.userDelete(email);
			System.out.println(">>> 성공 : 유저 데이터가 데이터 베이스에서 삭제되었습니다");
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(">>> 실패 : 이메일이 없습니다.");
			return "FAIL";
		}
	}
	
	@GetMapping("/findpw")
	public void findPw() {
		
	}
	
	@PostMapping("/findpw/{email}")
	public void findPw(@PathVariable("email") String email) {
		try {
			userService.findPw(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

