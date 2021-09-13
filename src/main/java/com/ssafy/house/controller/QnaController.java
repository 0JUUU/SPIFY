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
import com.ssafy.house.model.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class QnaController {

	@Autowired
	private QnaService qnaservice;
	
	@GetMapping("/{no}")
	public QnaDto getQuestion(@PathVariable int no) {
		System.out.println(">>> no : " +no+"인 게시글 호출");
		return qnaservice.select(no);
	}
	
	@GetMapping("/list")
	public List<QnaDto> getAllQuestion(){
		System.out.println(">>> Q&A LIST 호출");
		return qnaservice.selectAll();
	}
	
	@GetMapping("/title/{word}")
	public List<QnaDto> getQuestionByTitle(@PathVariable String word) {
		System.out.println("<<< word : " + word +"를 포함하는 글 제목 찾기");
		return qnaservice.selectByTitle(word);
	}
	
	@GetMapping("/content/{word}")
	public List<QnaDto> getQuestionByContent(@PathVariable String word) {
		System.out.println("<<< word : " + word +"를 포함하는 글 내용 찾기");
		return qnaservice.selectByContent(word);
	}
	
	@GetMapping("/user/{word}")
	public List<QnaDto> getQuestionByUser(@PathVariable String word) {
		System.out.println("<<< word : " + word +"으로 작성한 글 찾기");
		return qnaservice.selectByUser(word);
	}
	
	@PostMapping("")
	public int registQuestion(@RequestBody QnaDto qna) {
		System.out.println(">>> Q&A Regist 호출" + qna);
		return qnaservice.qnaRegister(qna);
	}
	
	@PutMapping("/{no}")
	public int updateQuestion(@RequestBody QnaDto qna) {
		System.out.println(">>> Q&A Modify 호출 " + qna);
		return qnaservice.qnaModify(qna);
	}
	
	@DeleteMapping("/{no}")
	public int deleteQuestion(@PathVariable int no) {
		System.out.println(">>> " + no +"번 글 삭제 요청");
		return qnaservice.qnaDelete(no);
	}
}
