package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.UserMenuVO;
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
	@Cacheable(value = "menuCache", key="{#pid, #roleId}")
	public List<MenuVO> getSideMenu(int pid, String roleId) {
		return menuMapper.selectSideMenu(String.valueOf(pid) , roleId);
	}

	@Override
	public String getMenuTitle(String url) {
		return menuMapper.selectMenuTitle(url);
	}

	@Override
	public MenuVO getMenuIdByUrl(String url) {
		return menuMapper.getMenuIdByUrl(url);
	}

	@Override
	public MenuVO getMenuByMenuId(String menuId) {
		return menuMapper.selectMenuByMenuId(menuId);
	}
	
	@Override
	public List<MenuVO> selectMenuByPid(int pid) {
		return menuMapper.selectMenuByPid(pid);
	}

	@Override
	public void insertMenuAuth(MenuVO menuVO) {
		menuMapper.insertMenuAuth(menuVO);
	}

	@Override
	public int updateMenu(MenuVO menuVO) {
		return menuMapper.updateMenu(menuVO);
	}

	@Override
	public int selectMenuAuth(int menuId, String userId) {
		return menuMapper.selectMenuAuth(menuId, userId);
	}

	@Override
	public List<UserMenuVO> getMenuUserByMenuId(String menuId) {
		return menuMapper.selectMenuUserByMenuId(menuId);
	}

	@Override
	public void menuUserAuthDel(int menuId, int userNo) {
		menuMapper.menuUserAuthDel(menuId, userNo);
	}


}
