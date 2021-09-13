package com.ssafy.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.model.dto.NoticeDto;
import com.ssafy.house.model.dto.QnaDto;
import com.ssafy.house.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NoticeController {

	@Autowired
	private NoticeService noticeservice;
	
	@GetMapping("/{no}")
	public NoticeDto getQuestion(@PathVariable int no) {
		System.out.println(">>> no : " +no+"인 공지사항 호출");
		return noticeservice.select(no);
	}
	
	@GetMapping("/title/{word}")
	public List<NoticeDto> getQuestionByTitle(@PathVariable String word) {
		System.out.println("<<< word : " + word +"를 포함하는 공지사항 제목 찾기");
		return noticeservice.selectByTitle(word);
	}
	
	@GetMapping("/content/{word}")
	public List<NoticeDto> getQuestionByContent(@PathVariable String word) {
		System.out.println("<<< word : " + word +"를 포함하는 공지사항 내용 찾기");
		return noticeservice.selectByContent(word);
	}
	
	@GetMapping("/list")
	public List<NoticeDto> getAllQuestion(){
		System.out.println(">>> Notice LIST 호출");
		return noticeservice.selectAll();
	}
	
	@PostMapping("")
	public int registNotice(@RequestBody NoticeDto notice) {
		System.out.println(">>> Notice Regist 호출" + notice);
		return noticeservice.noticeRegister(notice);
	}
	
	@PutMapping("/{no}")
	public int updateNotice(@RequestBody NoticeDto notice) {
		System.out.println(">>> Notice Modify 호출 " + notice);
		return noticeservice.noticeModify(notice);
	}
	
	@DeleteMapping("/{no}")
	public int deleteNotice(@PathVariable int no) {
		System.out.println(">>> " + no +"번 공지사항 삭제 요청");
		return noticeservice.noticeDelete(no);
	}
}
