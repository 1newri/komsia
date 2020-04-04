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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.NoticeVO;
import com.komsia.kom.domain.SinmungoVO;
import com.komsia.kom.service.SinmungoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class SinmungoController {
	
	private SinmungoService sinmungoService;

	@GetMapping(value = "/sinmungo")
	public String sinmungo(HttpServletRequest request) {
		return "redirect:/sinmungo/complain";
	}
	
	@GetMapping(value = "/sinmungo/complain")
	public String complain(HttpServletRequest request) {
		return "/content/sinmungo/complain";
	}
	
	@PostMapping(value = "/sinmungo/complain/list")
	@ResponseBody
	public Map<String, Object> complainList(HttpServletRequest request
			, @ModelAttribute SinmungoVO sinmungoVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		sinmungoVO.setBoardType(CommonConstant.BOARD_TYPE_C);
		try {
			result = sinmungoService.getSinmungoList(sinmungoVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/sinmungo/complain/detail")
	public String complainDetail(HttpServletRequest request
			, @ModelAttribute SinmungoVO sinmungoVO
			, ModelMap model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		sinmungoVO.setBoardType(CommonConstant.BOARD_TYPE_C);
		try {
			result = sinmungoService.getSinmungo(sinmungoVO);
			model.addAttribute("data", result);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return "/content/sinmungo/complain_detail";
	}
	
	@GetMapping(value = "/sinmungo/complain/regist")
	public String complainRegist(HttpServletRequest request
			, ModelMap model) {
		
		return "/content/sinmungo/complain_regist";
	}
	
	@PostMapping(value = "/sinmungo/complain/regist")
	@ResponseBody
	public Map<String, Object> complainRegist(HttpServletRequest request
			, @ModelAttribute SinmungoVO sinmungoVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		sinmungoVO.setRegId(userId);
		sinmungoVO.setBoardType(CommonConstant.BOARD_TYPE_C);
		try {
			result = sinmungoService.sinmungoRegister(sinmungoVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@GetMapping(value = "/sinmungo/unfair")
	public String unfair(HttpServletRequest request) {
		return "/content/sinmungo/unfair";
	}
	
	@PostMapping(value = "/sinmungo/unfair/list")
	@ResponseBody
	public Map<String, Object> unfairList(HttpServletRequest request
			, @ModelAttribute SinmungoVO sinmungoVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		sinmungoVO.setBoardType(CommonConstant.BOARD_TYPE_U);
		try {
			result = sinmungoService.getSinmungoList(sinmungoVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/sinmungo/unfair/detail")
	public String unfairDetail(HttpServletRequest request
			, @ModelAttribute SinmungoVO sinmungoVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		sinmungoVO.setBoardType(CommonConstant.BOARD_TYPE_U);
		try {
			result = sinmungoService.getSinmungo(sinmungoVO);
			model.addAttribute("data", result);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return "/content/sinmungo/unfair_detail";
	}
	
	@GetMapping(value = "/sinmungo/unfair/regist")
	public String noticeRegist(HttpServletRequest request
			, ModelMap model) {
		
		return "/content/sinmungo/unfair_regist";
	}
	
	@PostMapping(value = "/sinmungo/unfair/regist")
	@ResponseBody
	public Map<String, Object> sinmungoRegist(HttpServletRequest request
			, @ModelAttribute SinmungoVO sinmungoVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		sinmungoVO.setRegId(userId);
		sinmungoVO.setBoardType(CommonConstant.BOARD_TYPE_U);
		try {
			result = sinmungoService.sinmungoRegister(sinmungoVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
}
