package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;

public interface AdminService {

	Map<String, Object> getUserList();

	Map<String, Object> authUserRegist(String auth, List<String> userArr);

	List<MenuVO> getParentMenuList();

	Map<String, Object> getMenuList();

	Map<String, Object> insertMenu(MenuVO menuVO);

	List<Role> getRoleList();

	Map<String, Object> getAuthUserList(int roleId);

	Map<String, Object> getNotAuthUserList(String roleId, String userId);

	Map<String, Object> authUserDelete(String auth, String userNo);

	Map<String, Object> getMenuAuthList(int roleId);

	Map<String, Object> menuAuthRegist(int roleId, List<Integer> menuArr);

}
