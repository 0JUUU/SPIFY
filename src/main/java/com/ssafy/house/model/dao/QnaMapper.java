package com.ssafy.house.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.dto.QnaDto;

@Mapper
public interface QnaMapper {
	public QnaDto select(int no);
	public List<QnaDto> selectByUser(String word);
	public List<QnaDto> selectByTitle(String word);
	public List<QnaDto> selectByContent(String word);
	public List<QnaDto> selectAll();
	public int qnaModify(QnaDto qna);
	public int qnaDelete(int no);
	public int qnaRegister(QnaDto qna);
}
