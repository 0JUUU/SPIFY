package com.ssafy.house.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dao.NoticeMapper;
import com.ssafy.house.model.dao.QnaMapper;
import com.ssafy.house.model.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public NoticeDto select(int no) {
		return sqlSession.getMapper(NoticeMapper.class).select(no);
	}

	@Override
	public List<NoticeDto> selectByTitle(String word) {
		return sqlSession.getMapper(NoticeMapper.class).selectByTitle(word);
	}

	@Override
	public List<NoticeDto> selectByContent(String word) {
		return sqlSession.getMapper(NoticeMapper.class).selectByContent(word);
	}
	
	@Override
	public List<NoticeDto> selectAll() {
		return sqlSession.getMapper(NoticeMapper.class).selectAll();
	}

	@Override
	public int noticeModify(NoticeDto notice) {
		return sqlSession.getMapper(NoticeMapper.class).noticeModify(notice);
	} 

	@Override
	public int noticeDelete(int no) {
		return sqlSession.getMapper(NoticeMapper.class).noticeDelete(no);
	}

	@Override
	public int noticeRegister(NoticeDto notice) {
		return sqlSession.getMapper(NoticeMapper.class).noticeRegister(notice);
	}

}
