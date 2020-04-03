package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;

public interface ResourceMetaSerivce {

	List<String> getRoleIds(List<String> roleNameList);

	List<MenuVO> selectTopMenu(List<String> roleNameList);

	List<Role> getRoleList();

	Map<String, Object> getAuthUserList(int roleId);

}
