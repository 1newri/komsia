package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.AuthVO;
import com.komsia.kom.domain.MenuAuthVO;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;
import com.komsia.kom.domain.UserVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService{
	
	
	private ResourceMetaSerivce resourceService;

	private MenuService menuService;
	
	private UserService userService;

	@Override
	public Map<String, Object> getUserList() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<UserVO> list = userService.getUserList();
		
		result.put("data", list);
		return result;
	}


	@Override
	public List<MenuVO> getParentMenuList() {
		return menuService.getParentMenuList();
	}

	@Override
	public Map<String, Object> getMenuList() {
		Map<String, Object> result = new HashMap<String, Object>();

		// 메뉴리스트 조회
		List<MenuVO> list = menuService.getMenuList();
		result.put("data",list);
		
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertMenu(MenuVO menuVO) {
		String resCode = ResponseCode.RESPONSE_FAIL;
		String resMsg = ResponseCode.RESPONSE_FAIL_MSG;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		menuVO.setLevel(menuVO.getLevel() + 1);
		menuVO.setRegId(CommonConstant.SYSTEM_ID);
		
		if(menuService.insertMenu(menuVO) > 0) {
			resCode = ResponseCode.RESPONSE_OK;
			resMsg = ResponseCode.RESPONSE_OK_MSG;
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	@Override
	public List<Role> getRoleList() {
		return resourceService.getRoleList();
	}

	@Override
	public Map<String, Object> getAuthUserList(int roleId) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<AuthVO> list = resourceService.getAuthUserList(roleId);
		
		result.put("data", list);
		return result;
	}

	@Override
	public Map<String, Object> getNotAuthUserList(String roleId, String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<AuthVO> list = resourceService.getNotAuthUserList(roleId, userId);
		
		result.put("data", list);
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> authUserRegist(String auth, List<String> userArr) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		try {
			for(String userNo : userArr) {
				userService.insertAuthUser(userNo, auth);
			}
		} catch (Exception e) {
			log.error("Exception : {}", e);
			resCode = ResponseCode.RESPONSE_FAIL;
			resMsg = ResponseCode.RESPONSE_FAIL_MSG;
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		return result;
	}


	@Override
	public Map<String, Object> authUserDelete(String auth, String userNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		try {
			userService.deleteAuthUser(userNo, auth);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			resCode = ResponseCode.RESPONSE_FAIL;
			resMsg = ResponseCode.RESPONSE_FAIL_MSG;
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		return result;
	}


	@Override
	public Map<String, Object> getMenuAuthList(int roleId) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<MenuAuthVO> menuAuthList = resourceService.getMenuAuthList(roleId);
		result.put("data", menuAuthList);
		return result;
	}


	@Override
	public Map<String, Object> menuAuthRegist(int roleId, List<Integer> menuArr) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		try {
			
			resourceService.deleteMenuAuth(roleId);
			
			MenuAuthVO menuAuthVO = null;
			for(int menuId : menuArr) {
				menuAuthVO = new MenuAuthVO();
				menuAuthVO.setRoleId(roleId);
				menuAuthVO.setMenuId(menuId);
				
				resourceService.insertMenuAuth(menuAuthVO);
			}
		} catch (Exception e) {
			log.error("Exception : {}", e);
			resCode = ResponseCode.RESPONSE_FAIL;
			resMsg = ResponseCode.RESPONSE_FAIL_MSG;
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		return result;
	}

}
