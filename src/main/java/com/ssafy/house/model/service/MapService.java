package com.ssafy.house.model.service;


import com.ssafy.house.model.dto.HouseDto;

import java.util.List;
import java.util.Map;

public interface MapService {

    public String getDongCode(String dong) throws Exception;
    public HouseDto getHouseDetail(int no)throws Exception;
    public HouseDto getHouseList(String dong)throws Exception;

}
