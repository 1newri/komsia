package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.mapper.AdminMapper;
import com.komsia.kom.mapper.MenuMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService{

	private MenuMapper menuMapper;
	
	@Override
	public Map<String, Object> getMenuList() {
		
		Map<String, Object> result = new HashMap<String, Object>();

		// 메뉴리스트 조회
		List<MenuVO> list = menuMapper.selectMenuList();
		result.put("data",list);
		
		return result;
	}

	@Override
	public Map<String, Object> insertMenu(MenuVO menuVO) {
		
		String resCode = ResponseCode.RESPONSE_FAIL;
		String resMsg = ResponseCode.RESPONSE_FAIL_MSG;
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		menuVO.setLevel(menuVO.getLevel() + 1);
		menuVO.setRegId(CommonConstant.SYSTEM_ID);
		
		if(menuMapper.insertMenu(menuVO) > 0) {
			resCode = ResponseCode.RESPONSE_OK;
			resMsg = ResponseCode.RESPONSE_OK_MSG;
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	@Override
	public List<MenuVO> getTopMenu() {
		return menuMapper.selectTopMenu();
	}

	@Override
	public List<MenuVO> getParentMenuList() {
		return menuMapper.getParentMenuList();
	}

	@Override
	public Map<String, Object> getSideMenu(String url) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// sideMenu
		List<MenuVO> list = menuMapper.selectSideMenu(url);
		result.put("side", list);
		
		String title = menuMapper.selectMenuTitle(url);
		result.put("title", title);
		
		return result;
	}


}
