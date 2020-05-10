package com.komsia.kom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

	@GetMapping(value = "/group")
	public String group(HttpServletRequest request) {
		return "redirect:/group/group1";
	}
	
	/**
	 * 성창기업
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/group/group1")
	public String group1(HttpServletRequest request) {
		return "/content/group/group1";
	}
	
	/**
	 * 메이슨캐피탈
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/group/group2")
	public String group2(HttpServletRequest request) {
		return "/content/group/group2";
	}
	
	/**
	 * 삼성전자
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/group/group3")
	public String group3(HttpServletRequest request) {
		return "/content/group/group3";
	}
}
