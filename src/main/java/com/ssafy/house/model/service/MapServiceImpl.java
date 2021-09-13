package com.ssafy.house.model.service;

import com.ssafy.house.model.dao.MapMapper;
import com.ssafy.house.model.dto.HouseDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MapServiceImpl implements MapService{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public String getDongCode(String dong) throws Exception {
        return sqlSession.getMapper(MapMapper.class).getDongCode(dong);
    }

    @Override
    public HouseDto getHouseDetail(int no) throws Exception {
        return sqlSession.getMapper(MapMapper.class).getHouseDetail(no);
    }

    @Override
    public HouseDto getHouseList(String dong) throws Exception {
            return sqlSession.getMapper(MapMapper.class).getHouseListDong(dong);
    }
}
