package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.komsia.kom.domain.AuthVO;
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
	public Map<String, Object> getAuthUserList(int roleId) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		List<AuthVO> list = resourceMapper.selectAuthUserList(roleId);
		
		result.put("data", list);
		return result;
	}
}
