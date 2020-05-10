package com.komsia.kom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InfoController {
	
	@GetMapping(value = "/info")
	public String info(HttpServletRequest request) {
		return "redirect:/info/hello";
	}
	@GetMapping(value = "/term")
	public String term(HttpServletRequest request) {
		return "/guide/term";
	}
	@GetMapping(value = "/person")
	public String person(HttpServletRequest request) {
		return "/guide/person";
	}
	
	@GetMapping(value = "/info/hello")
	public String hello(HttpServletRequest request) {
		return "/content/info/hello";
	}
	
	@GetMapping(value = "/info/directions")
	public String directions(HttpServletRequest request) {
		return "/content/info/directions";
	}
}
