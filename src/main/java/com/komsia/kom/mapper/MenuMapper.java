package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.MenuVO;

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

}
