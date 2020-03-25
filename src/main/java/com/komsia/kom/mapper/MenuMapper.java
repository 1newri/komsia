package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.MenuVO;

@Repository
@Mapper
public interface MenuMapper {

	List<MenuVO> selectMenuList();

	int insertMenu(MenuVO menuVO);

	List<MenuVO> selectTopMenu();

	List<MenuVO> getParentMenuList();

	List<MenuVO> selectSideMenu(String url);

	String selectMenuTitle(String url);

}
