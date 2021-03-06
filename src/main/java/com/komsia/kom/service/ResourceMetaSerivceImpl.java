package com.komsia.kom.service;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.komsia.kom.domain.AuthVO;
import com.komsia.kom.domain.MenuAuthVO;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;
import com.komsia.kom.mapper.ResourceMapper;

@Service
@AllArgsConstructor
public class ResourceMetaSerivceImpl implements ResourceMetaSerivce{

	private ResourceMapper resourceMapper;
	
	@Override
	public List<String> getRoleIds(List<String> roleNameList) {
		return resourceMapper.selectRoleIdList(roleNameList);
	}

	@Override
	@Cacheable(value = "menuCache")
	public List<MenuVO> selectTopMenu(List<String> roleNameList) {
		
		if("ROLE_ANONYMOUS".equals(roleNameList.get(0))) {
			roleNameList.clear();
		}
		
		return resourceMapper.selectTopMenu(roleNameList);
	}

	@Override
	public List<Role> getRoleList() {
		return resourceMapper.selectRoleList();
	}

	@Override
	public List<AuthVO> getAuthUserList(String roleId, String searchText) {
		
		List<AuthVO> list = resourceMapper.selectAuthUserList(roleId, searchText);
		
		return list;
	}

	@Override
	public List<AuthVO> getNotAuthUserList(String roleId, String searchText) {
		List<AuthVO> list = resourceMapper.selectNotAuthUserList(roleId, searchText);
		return list;
	}

	@Override
	public List<MenuAuthVO> getMenuAuthList(int roleId) {
		List<MenuAuthVO> list = resourceMapper.selectMenuAuthList(roleId);
		return list;
	}

	@Override
	public void deleteMenuAuth(int roleId) {
		resourceMapper.deleteMenuAuth(roleId);
	}

	@Override
	public void insertMenuAuth(MenuAuthVO menuAuthVO) {
		
		resourceMapper.insertMenuAuth(menuAuthVO);
	}
}
