package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.UserVO;
import com.komsia.kom.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
	
	private UserService userService;
	
	@GetMapping(value = "/user/join")
	public String joinForm(HttpServletRequest request) {
		return "/user/join";
	}
	
	@PostMapping(value = "/user/join")
	@ResponseBody
	public Map<String, Object> join(HttpServletRequest request
			, @ModelAttribute UserVO userVO){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = userService.joinUser(userVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			map.put("resCode", ResponseCode.RESPONSE_FAIL);
			map.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return map;
	}
	
	@PostMapping(value = "/user/duplication")
	@ResponseBody
	public Map<String, Object> duplication(HttpServletRequest request
			, @RequestParam(value = "userId",required = true) String userId){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = userService.duplicationUser(userId);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			map.put("resCode", ResponseCode.RESPONSE_FAIL);
			map.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return map;
	}
	
	@GetMapping(value = "/user/login")
	public String loginForm(HttpServletRequest request) {
		String referrer = request.getHeader("Referer");
	    request.getSession().setAttribute("prevPage", referrer);
		return "/user/login";
	}
	
	@PostMapping(value = "/user/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "/user/login";
	}
	
	@PostMapping(value = "/user/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/user/denied")
	public String denied(HttpServletRequest request) {
		return "/user/denied";
	}
	
}
