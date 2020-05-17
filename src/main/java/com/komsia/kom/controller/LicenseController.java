package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.ActivityVO;

@Controller
public class LicenseController {

	@GetMapping(value = "/license")
	public String license(HttpServletRequest request) {
		return "redirect:/license/info";
	}
	
	@GetMapping(value = "/license/info")
	public String info(HttpServletRequest request) {
		return "/content/license/info";
	}
	
	@GetMapping(value = "/license/schedule")
	public String schedule(HttpServletRequest request) {
		return "/content/license/schedule";
	}
	
}
