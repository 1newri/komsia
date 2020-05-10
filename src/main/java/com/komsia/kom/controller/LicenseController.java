package com.komsia.kom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
