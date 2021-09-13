package com.ssafy.house.controller;

import com.ssafy.house.model.dto.HouseDto;
import com.ssafy.house.model.service.MapService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class MapController {
    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    @Autowired
    private MapService mapService;

    @GetMapping("/map/{search}")
    public String envirList(@PathVariable("search") String search) throws Exception {
        return mapService.getDongCode(search);
    }

    @GetMapping(value = "/map/detail/{no}")
    public ResponseEntity<HouseDto> detailhouse(@PathVariable("no") int no) throws Exception {
        return new ResponseEntity<HouseDto>(mapService.getHouseDetail(no), HttpStatus.OK);
    }

    @GetMapping(value = "/map/searchHouse/{dong}")
    public ResponseEntity<HouseDto> houseList(@PathVariable("dong") String dong) throws Exception {

        return new ResponseEntity<HouseDto>(mapService.getHouseList(dong),HttpStatus.OK);
    }

}
