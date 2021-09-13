package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.model.dao.HouseDealMapper;
import com.ssafy.house.model.dto.HouseAmountPerAreaDto;
import com.ssafy.house.model.dto.HouseDealDto;
import com.ssafy.house.model.dto.HouseDto;
import com.ssafy.house.model.dto.HouseInfoDto;
import com.ssafy.util.PageNavigation;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<HouseInfoDto> searchAll() throws ClassNotFoundException, SQLException {
		System.out.println(">>>>>>>HouseDealServiceImpl : selectAll()");
		return sqlSession.getMapper(HouseDealMapper.class).searchAll();
	}

	@Override
	public List<HouseInfoDto> searchAptName(String dong, String aptName) throws SQLException, ClassNotFoundException {
		return sqlSession.getMapper(HouseDealMapper.class).searchAptName(dong, aptName);
	}

	@Override
	public List<HouseInfoDto> searchDongName(String dong) throws SQLException, ClassNotFoundException {
		System.out.println(">>>>>>>HouseDealServiceImpl : searchDongName()");
		return sqlSession.getMapper(HouseDealMapper.class).searchDongName(dong);
	}

	@Override
	public HouseDealDto show(int no) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).show(no);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map)
			throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));	// 현재 페이지 번호
		int sizePerPage = Integer.parseInt(map.get("spp"));	// 페이지 글 갯수
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(HouseDealMapper.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		
		return pageNavigation;
	}

	@Override
	public List<HouseDealDto> aptList(String dong, String aptName) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).aptList(dong, aptName);
	}

	@Override
	public List<HouseAmountPerAreaDto> priceList(String dong) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).priceList(dong);
	}

}
