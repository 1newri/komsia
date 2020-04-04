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
import com.komsia.kom.service.ActivityService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class ActivityController {
	
	private ActivityService activityService;
	
	/**
	 * 연구활동
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity")
	public String activity(HttpServletRequest request) {
		return "redirect:/activity/stock/recommend/type";
	}
	
	/**
	 * 주식
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock")
	public String stock(HttpServletRequest request) {
		return "redirect:/activity/stock/recommend/type";
	}
	
	/**
	 * 선물
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/futures")
	public String futures(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_F);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_F);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/futures/futures";
	}
	
	@GetMapping(value = "/activity/futures/regist")
	public String futuresRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_F);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_F);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/futures/futures_regist";
	}
	
	@PostMapping(value = "/activity/futures/regist")
	@ResponseBody
	public Map<String, Object> futuresRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_F);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_F);
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		try {
			result = activityService.recommandRegist(activityVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 채권
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/bond")
	public String bond(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_B);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_B);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/bond/bond";
	}
	
	@GetMapping(value = "/activity/bond/regist")
	public String bondRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_B);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_B);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/bond/bond_regist";
	}
	
	@PostMapping(value = "/activity/bond/regist")
	@ResponseBody
	public Map<String, Object> bondRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_B);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_B);
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		try {
			result = activityService.recommandRegist(activityVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 바이오 소액투자자 포럼
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/bio")
	public String bio(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_O);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_O);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/bio/bio";
	}
	
	@GetMapping(value = "/activity/bio/regist")
	public String bioRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_O);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_O);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/bio/bio_regist";
	}
	
	@PostMapping(value = "/activity/bio/regist")
	@ResponseBody
	public Map<String, Object> bioRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_O);
		activityVO.setBoardSubType(CommonConstant.BOARD_TYPE_O);
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		try {
			result = activityService.recommandRegist(activityVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}

}
