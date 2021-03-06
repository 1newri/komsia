package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;
import com.komsia.kom.domain.UserVO;

public interface AdminService {

	Map<String, Object> getUserList(UserVO userVO);

	Map<String, Object> authUserRegist(String auth, List<String> userArr);

	List<MenuVO> getParentMenuList();

	Map<String, Object> getMenuList();

	Map<String, Object> insertMenu(MenuVO menuVO);

	List<Role> getRoleList();

	Map<String, Object> getAuthUserList(String roleId, String searchText);

	Map<String, Object> getNotAuthUserList(String roleId, String searchText);

	Map<String, Object> authUserDelete(String auth, String userNo);

	Map<String, Object> getMenuAuthList(int roleId);

	Map<String, Object> menuAuthRegist(int roleId, List<Integer> menuArr);

	Map<String, Object> passwordInit(UserVO userVO);

	List<UserVO> getAdminUserList();

	Map<String, Object> getMenuByMenuId(String menuId);

	Map<String, Object> menuUserAuthDel(int menuId, int userNo);

}
