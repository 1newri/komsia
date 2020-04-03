package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.MenuVO;

public interface MenuService {
	
	public Map<String, Object> getMenuList();

	public List<MenuVO> getParentMenuList();
	
	public Map<String, Object> insertMenu(MenuVO menuVO);

	public List<MenuVO> getSideMenu(int pid);

	public String getMenuTitle(String url);

	public MenuVO getMenuIdByUrl(String url);

}
