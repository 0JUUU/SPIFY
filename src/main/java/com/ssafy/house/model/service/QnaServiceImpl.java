package com.ssafy.house.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dao.QnaMapper;
import com.ssafy.house.model.dto.QnaDto;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	SqlSession sqlSession;
	@Override
	public QnaDto select(int no) {
		return sqlSession.getMapper(QnaMapper.class).select(no);
	}

	@Override
	public List<QnaDto> selectByUser(String word) {
		return sqlSession.getMapper(QnaMapper.class).selectByUser(word);
	}

	@Override
	public List<QnaDto> selectByTitle(String word) {
		return sqlSession.getMapper(QnaMapper.class).selectByTitle(word);
	}

	@Override
	public List<QnaDto> selectByContent(String word) {
		return sqlSession.getMapper(QnaMapper.class).selectByContent(word);
	}
	
	@Override
	public List<QnaDto> selectAll() {
		return sqlSession.getMapper(QnaMapper.class).selectAll();
	}

	@Override
	public int qnaModify(QnaDto qna) {
		return sqlSession.getMapper(QnaMapper.class).qnaModify(qna);
	}

	@Override
	public int qnaDelete(int no) {
		return sqlSession.getMapper(QnaMapper.class).qnaDelete(no);
	}

	@Override
	public int qnaRegister(QnaDto qna) {
		return sqlSession.getMapper(QnaMapper.class).qnaRegister(qna);
	}


}
