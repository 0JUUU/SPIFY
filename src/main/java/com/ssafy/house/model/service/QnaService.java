package com.ssafy.house.model.service;

import java.util.List;

import com.ssafy.house.model.dto.QnaDto;

public interface QnaService {
	public QnaDto select(int no);
	public List<QnaDto> selectByUser(String writerName);
	public List<QnaDto> selectByTitle(String word);
	public List<QnaDto> selectByContent(String word);
	public List<QnaDto> selectAll();
	public int qnaModify(QnaDto qna);
	public int qnaDelete(int no);
	public int qnaRegister(QnaDto qna);
}
