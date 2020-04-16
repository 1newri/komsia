package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
	@GetMapping(value = "/user/find")
	public String findUser(HttpServletRequest request) {
		return "/user/find";
	}
	
	@GetMapping(value = "/user/mypage")
	public String mypage(HttpServletRequest request) {
		return "redirect:/user/mypage/myinfo";
	}
	
	@GetMapping(value = "/user/mypage/myinfo")
	public String myinfo(HttpServletRequest request, ModelMap modelMap) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		log.debug("userId : {}", userId );
		
		UserVO userVO = userService.selectUserByUserId(userId);
		if(!ObjectUtils.isEmpty(userVO)) {
			modelMap.put("user", userVO);
		}
		return "/user/mypage/myinfo";
	}
	
	@PostMapping(value = "/user/mypage/myinfo")
	@ResponseBody
	public Map<String, Object> myinfo(HttpServletRequest request
			, @ModelAttribute UserVO userVO) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		userVO.setUserId(userId);
		userVO.setModId(userId);
		try {
			map = userService.changeMyinfo(userVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			map.put("resCode", ResponseCode.RESPONSE_FAIL);
			map.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return map;
	}
	
	@GetMapping(value = "/user/mypage/myuser")
	public String myuser(HttpServletRequest request) {
		return "/user/mypage/myuser";
	}
	
	@GetMapping(value = "/user/mypage/password")
	public String password(HttpServletRequest request) {
		return "/user/mypage/password";
	}
	
	@PostMapping(value = "/user/mypage/password")
	@ResponseBody
	public Map<String, Object> password(HttpServletRequest request
			, @RequestParam(value = "password") String password
			, @RequestParam(value = "newPassword") String newPassword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		UserVO userVO = new UserVO();
		String userId = (String) request.getSession().getAttribute("userId");
		
		userVO.setUserId(userId);
		userVO.setPassword(password);
		userVO.setNewPassword(newPassword);
		
		try {
			map = userService.changePassword(userVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			map.put("resCode", ResponseCode.RESPONSE_FAIL);
			map.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return map;
	}
	
}
