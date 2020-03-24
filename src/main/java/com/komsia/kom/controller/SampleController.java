package com.komsia.kom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

	@GetMapping(value = "/")
	public String home() {
		return "index";
	}
	
	@GetMapping(value = "/menu")
	public String menu() {
		return "/sample/index";
	}
	
	@GetMapping(value = "/menu2")
	public String menu2() {
		return "/sample/index2";
	}
}
