package com.ssafy.house.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.house.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	public NoticeDto select(int no);
	public List<NoticeDto> selectByTitle(String word);
	public List<NoticeDto> selectByContent(String word);
	public List<NoticeDto> selectAll();
	public int noticeModify(NoticeDto notice);
	public int noticeDelete(int no);
	public int noticeRegister(NoticeDto notice);
}
