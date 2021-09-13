package com.ssafy.house.model.dao;

import com.ssafy.house.model.dto.HouseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {
    public String getDongCode(String dong) throws Exception;
    public HouseDto getHouseDetail(int no) throws Exception;
    public HouseDto getHouseListDong(String dong) throws Exception;
}
