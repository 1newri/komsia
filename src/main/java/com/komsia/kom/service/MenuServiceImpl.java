package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
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
	public List<MenuVO> getMenuList() {
		return menuMapper.selectMenuList();
	}

	@Override
	public int insertMenu(MenuVO menuVO) {
		 return menuMapper.insertMenu(menuVO);
	}


	@Override
	public List<MenuVO> getParentMenuList() {
		return menuMapper.getParentMenuList();
	}

	@Override
	@Cacheable(value = "menuCache", key="#pid")
	public List<MenuVO> getSideMenu(int pid) {
		return menuMapper.selectSideMenu(pid);
	}

	@Override
	public String getMenuTitle(String url) {
		return menuMapper.selectMenuTitle(url);
	}

	@Override
	public MenuVO getMenuIdByUrl(String url) {
		return menuMapper.getMenuIdByUrl(url);
	}


}
