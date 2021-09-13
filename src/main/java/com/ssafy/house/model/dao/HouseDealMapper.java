package com.ssafy.house.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.house.model.dto.HouseAmountPerAreaDto;
import com.ssafy.house.model.dto.HouseDealDto;
import com.ssafy.house.model.dto.HouseDto;
import com.ssafy.house.model.dto.HouseInfoDto;

public interface HouseDealMapper {
	public List<HouseInfoDto> searchAll() throws ClassNotFoundException, SQLException;
	public List<HouseInfoDto> searchAptName(String dong, String aptName) throws SQLException, ClassNotFoundException;
	public List<HouseInfoDto> searchDongName(String dong) throws SQLException, ClassNotFoundException;

	public HouseDealDto show(int no) throws SQLException;
	public List<HouseDealDto> aptList(String dong, String aptName) throws SQLException;
	public List<HouseAmountPerAreaDto> priceList(String dong) throws SQLException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
}
