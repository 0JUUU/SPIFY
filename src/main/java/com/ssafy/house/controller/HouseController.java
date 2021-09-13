package com.ssafy.house.controller;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.model.dto.HouseAmountPerAreaDto;
import com.ssafy.house.model.dto.HouseDealDto;
import com.ssafy.house.model.dto.HouseDto;
import com.ssafy.house.model.dto.HouseInfoDto;
import com.ssafy.house.model.service.HouseDealService;

@RestController 
@RequestMapping("/house")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class HouseController {

	@Autowired
	private HouseDealService houseDealService;
	
	@RequestMapping(value = "/searchDong/{dong}", method = RequestMethod.GET)
	public List<HouseInfoDto> searchDong(@PathVariable String dong) throws ClassNotFoundException, SQLException {
		System.out.println("dong = "+dong);
		return houseDealService.searchDongName(dong);
	}
	
//	@RequestMapping(value="/searchApt", method=RequestMethod.GET)
//	public List<HouseDealDto> searchApt(@PathVariable String dong, @PathVariable String apt) throws ClassNotFoundException, SQLException {
//		System.out.println("dong = "+dong +", apt : " + apt);
//
//		if(dong.equals("") && apt.equals("")) {
//			return houseDealService.searchAll();
//		} 
//		else {
//			System.out.println("실행함수 : searchAptName");
//			return houseDealService.searchAptName(dong, apt);
//		}
//	}
	
	@RequestMapping(value="/searchApt", method=RequestMethod.GET) 
	public List<HouseInfoDto> searchApt(@RequestParam Map<String, String> map) throws ClassNotFoundException, SQLException {
		System.out.println("map : "+ map.toString());
		String dong = map.get("dong");
		String aptName = map.get("aptName");
		if(dong.equals("") && aptName.equals("")) {
			return houseDealService.searchAll();
		} 
		else {
			System.out.println("실행함수 : searchAptName");
			System.out.println(houseDealService.searchAptName(dong, aptName));
			return houseDealService.searchAptName(dong, aptName);
		}
	}
	
	@RequestMapping(value="/show/{no}", method=RequestMethod.GET)
	public HouseDealDto show(@PathVariable int no) throws SQLException {
		System.out.println(">>> show() " + no);
		return houseDealService.show(no); 
	}
	
	@RequestMapping(value="/aptList/{dong}/{aptName}", method=RequestMethod.GET)
	public List<HouseDealDto> aptList(@PathVariable String dong, @PathVariable String aptName) throws SQLException {
		System.out.println("<<< aptList() dong : "+dong +", aptName : "+aptName);
		return houseDealService.aptList(dong, aptName);
	}
	
	@RequestMapping(value="/priceList/{dong}", method=RequestMethod.GET)
	public List<HouseAmountPerAreaDto> priceList(@PathVariable String dong) throws SQLException {
		System.out.println("<<< priceList() dong : " + dong);
		return houseDealService.priceList(dong);
	}
}
