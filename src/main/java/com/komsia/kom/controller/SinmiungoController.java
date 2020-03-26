package com.komsia.kom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class SinmiungoController {
	

	@GetMapping(value = "/sinmungo")
	public String sinmungo(HttpServletRequest request) {
		return "redirect:/sinmungo/complain";
	}
	
	@GetMapping(value = "/sinmungo/complain")
	public String complain(HttpServletRequest request) {
		return "/content/sinmungo/complain";
	}
	
	@GetMapping(value = "/sinmungo/unfair")
	public String unfair(HttpServletRequest request) {
		return "/content/sinmungo/unfair";
	}
}
