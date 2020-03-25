package com.komsia.kom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.MenuVO;
import com.komsia.kom.service.MenuService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class MenuController {
	
	private MenuService menuService;

	@GetMapping(value = "/menu")
	public String menu() {
		
		return "/admin/content/menu";
	}
	
	@PostMapping(value = "/menu/pid")
	@ResponseBody
	public List<MenuVO> menuPid(HttpServletRequest request){
		List<MenuVO> list = menuService.getParentMenuList();
		return list;
	}
	
	@PostMapping(value = "/menu/list")
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
	
	@PostMapping(value = "/menu/sidebar")
	@ResponseBody
	public Map<String, Object> getSidebarMenu(HttpServletRequest request
			, @RequestParam(value = "url", required = true) String url){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = menuService.getSideMenu(url);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@PostMapping(value = "/menu/save")
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
}
