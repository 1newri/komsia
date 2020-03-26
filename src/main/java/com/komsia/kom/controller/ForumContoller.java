package com.komsia.kom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@AllArgsConstructor
public class ForumContoller {
	
	@GetMapping(value = "/forum")
	public String forum(HttpServletRequest request) {
		return "redirect:/forum/notice";
	}
	
	@GetMapping(value = "/forum/notice")
	public String notice(HttpServletRequest request) {
		return "/content/forum/notice";
	}
	
	@GetMapping(value = "/forum/press")
	public String press(HttpServletRequest request) {
		return "/content/forum/press";
	}
	
	@GetMapping(value = "/forum/qna")
	public String qna(HttpServletRequest request) {
		return "/content/forum/qna";
	}
	
	@GetMapping(value = "/forum/reference")
	public String reference(HttpServletRequest request) {
		return "/content/forum/reference";
	}
}
