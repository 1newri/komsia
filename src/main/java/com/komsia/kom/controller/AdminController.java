package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.AuthVO;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.domain.Role;
import com.komsia.kom.domain.UserVO;
import com.komsia.kom.service.AdminService;
import com.komsia.kom.service.MenuService;
import com.komsia.kom.service.ResourceMetaSerivce;
import com.komsia.kom.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class AdminController {
	
	private AdminService adminService;
	
	private ResourceMetaSerivce resourceService;

	private MenuService menuService;
	
	private UserService userService;
	
	@GetMapping(value = "/admin")
	public String admin(HttpServletRequest request) {
		log.debug("================== admin");
		return "redirect:/admin/menu";
	}
	
	@GetMapping(value = "/admin/menu")
	public String menu(HttpServletRequest request,
			ModelMap model) {
		List<MenuVO> list = menuService.getParentMenuList();
		model.put("list", list);
		return "/admin/content/menu";
	}
	
	@PostMapping(value = "/menu/pid")
	@ResponseBody
	public List<MenuVO> menuPid(HttpServletRequest request){
		List<MenuVO> list = menuService.getParentMenuList();
		return list;
	}
	
	@PostMapping(value = "/admin/menu/list")
	@ResponseBody
	public Map<String, Object> menuList(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = menuService.getMenuList();
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@PostMapping(value = "/admin/menu/save")
	@ResponseBody
	public Map<String, Object> insertMenu(HttpServletRequest request
			, @ModelAttribute MenuVO menuVO){
		log.debug("menuVO : {}", menuVO);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = menuService.insertMenu(menuVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@GetMapping(value = "/admin/user")
	public String user(HttpServletRequest request,
			ModelMap model) {
		List<UserVO> list = userService.getUserList();
		log.debug("user List Size : {}", list.size());
		model.put("data", list);
		return "/admin/content/user";
	}
	

	@PostMapping(value = "/admin/user/list")
	@ResponseBody
	public Map<String, Object> userList(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = adminService.getUserList();
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@GetMapping(value = "/admin/auth")
	public String auth(HttpServletRequest request,
			ModelMap model) {
		
		List<Role> list = resourceService.getRoleList();
		log.debug("Role List : {}", list);
		model.put("data", list);
		
		return "/admin/content/auth";
	}
	
	@PostMapping(value = "/admin/auth/list")
	@ResponseBody
	public Map<String, Object> authList(HttpServletRequest request
			, @RequestParam(value = "roleId") int roleId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = resourceService.getAuthUserList(roleId);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
}
