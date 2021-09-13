package com.ssafy.house.model.service;

import java.util.List;

import com.ssafy.house.model.dto.NoticeDto;

public interface NoticeService {
	public NoticeDto select(int no);
	public List<NoticeDto> selectByTitle(String word);
	public List<NoticeDto> selectByContent(String word);
	public List<NoticeDto> selectAll();
	public int noticeModify(NoticeDto notice);
	public int noticeDelete(int no);
	public int noticeRegister(NoticeDto notice);
}
