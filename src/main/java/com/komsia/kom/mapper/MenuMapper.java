package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.UserMenuVO;

@Repository
@Mapper
public interface MenuMapper {

	List<MenuVO> selectMenuList();

	int insertMenu(MenuVO menuVO);

	List<MenuVO> selectTopMenu();

	List<MenuVO> getParentMenuList();

	List<MenuVO> selectSideMenu(@Param(value = "pid") String pid, @Param(value = "roleId") String roleId);

	String selectMenuTitle(String url);

	MenuVO getMenuIdByUrl(String url);

	MenuVO selectMenuByMenuId(String menuId);
	
	List<MenuVO> selectMenuByPid(int pid);

	void insertMenuAuth(MenuVO menuVO);

	int updateMenu(MenuVO menuVO);
	
	int selectMenuAuth(@Param(value = "menuId") int menuId, @Param(value = "userId") String userId);

	List<UserMenuVO> selectMenuUserByMenuId(String menuId);

	void menuUserAuthDel(@Param(value = "menuId")int menuId, @Param(value = "userNo") int userNo);

}
