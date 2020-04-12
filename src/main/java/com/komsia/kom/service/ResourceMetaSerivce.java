package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.AuthVO;
import com.komsia.kom.domain.MenuAuthVO;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;

public interface ResourceMetaSerivce {

	List<String> getRoleIds(List<String> roleNameList);

	List<MenuVO> selectTopMenu(List<String> roleNameList);

	List<Role> getRoleList();

	List<AuthVO> getAuthUserList(int roleId);

	List<AuthVO> getNotAuthUserList(String roleId, String userId);

	List<MenuAuthVO> getMenuAuthList(int roleId);

	void deleteMenuAuth(int roleId);

	void insertMenuAuth(MenuAuthVO menuAuthVO);

}
