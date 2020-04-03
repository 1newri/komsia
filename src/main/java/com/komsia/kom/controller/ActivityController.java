package com.komsia.kom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ActivityController {
	
	@GetMapping(value = "/activity")
	public String activity(HttpServletRequest request) {
		return "redirect:/activity/stock";
	}
	
	@GetMapping(value = "/activity/stock")
	public String stock(HttpServletRequest request) {
		return "/content/activity/stock/stock";
	}
	
	@GetMapping(value = "/activity/feature")
	public String feature(HttpServletRequest request) {
		return "/content/activity/feature/feature";
	}
	
	@GetMapping(value = "/activity/bond")
	public String bond(HttpServletRequest request) {
		return "/content/activity/bond/bond";
	}
	
	@GetMapping(value = "/activity/bio")
	public String bio(HttpServletRequest request) {
		return "/content/activity/bio/bio";
	}

}
