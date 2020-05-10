package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.MenuVO;

public interface MenuService {
	
	public List<MenuVO> getMenuList();

	public List<MenuVO> getParentMenuList();
	
	public int insertMenu(MenuVO menuVO);

	public List<MenuVO> getSideMenu(int pid, String roleId);

	public String getMenuTitle(String url);

	public MenuVO getMenuIdByUrl(String url);

	public MenuVO getMenuByMenuId(String menuId);
	
	public List<MenuVO> selectMenuByPid(int pid);

	public void insertMenuAuth(MenuVO menuVO);

	public int updateMenu(MenuVO menuVO);

	public int selectMenuAuth(int menuId, String userId);

}
