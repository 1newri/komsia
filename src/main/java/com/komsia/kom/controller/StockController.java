package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.List;
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
import com.komsia.kom.service.StockService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class StockController {
	
	private StockService stockService;
	
	@GetMapping(value = "/activity/stock/recommend")
	public String recommand(HttpServletRequest request) {
		return "/content/activity/stock/recommend/recommend";
	}
	
	@GetMapping(value = "/activity/stock/analist/info")
	public String analistInfo(HttpServletRequest request) {
		return "/content/activity/stock/analist/info";
	}
	
	@GetMapping(value = "/activity/stock/analist/chat")
	public String analistChat(HttpServletRequest request) {
		return "/content/activity/stock/analist/chat";
	}
	
	@GetMapping(value = "/activity/stock/analist/chat/manager")
	public String analistChatManager(HttpServletRequest request) {
		return "/content/activity/stock/analist/manager";
	}
	
	@GetMapping(value = "/activity/stock/analist/chat/analyst")
	public String analistChatAnalyst(HttpServletRequest request) {
		return "/content/activity/stock/analist/analyst";
	}
	
	@GetMapping(value = "/activity/stock/recommend/type")
	public String recommandType(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_T);
		try {
			
			List<ActivityVO> list = stockService.selectRecommandList(activityVO);
			log.debug("list size : {}, list : {}", list.size(), list);
			model.addAttribute("data", list);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/stock/recommend/type";
	}
	
	
	@GetMapping(value = "/activity/stock/recommend/type/regist")
	public String recommandTypeRegist(HttpServletRequest request) {
		
		return "/content/activity/stock/recommend/type_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/type/regist")
	@ResponseBody
	public Map<String, Object> recommandTypeRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_T);
		try {
			result = stockService.recommandRegist(activityVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@GetMapping(value = "/activity/stock/recommend/trading")
	public String recommandTrading(HttpServletRequest request) {
		return "/content/activity/stock/recommend/trading";
	}
	
	@GetMapping(value = "/activity/stock/recommend/financial")
	public String recommandFinancial(HttpServletRequest request) {
		return "/content/activity/stock/recommend/financial";
	}
	
	@GetMapping(value = "/activity/stock/recommend/supply")
	public String recommandSupply(HttpServletRequest request) {
		return "/content/activity/stock/recommend/supply";
	}
	
	@GetMapping(value = "/activity/stock/recommend/items")
	public String recommandItems(HttpServletRequest request) {
		return "/content/activity/stock/recommend/items";
	}
	
	@GetMapping(value = "/activity/stock/recommend/business")
	public String recommandBusiness(HttpServletRequest request) {
		return "/content/activity/stock/recommend/business";
	}
	
	@GetMapping(value = "/activity/stock/recommend/theme")
	public String recommandTheme(HttpServletRequest request) {
		return "/content/activity/stock/recommend/theme";
	}
	
	@GetMapping(value = "/activity/stock/recommend/study")
	public String recommandStudy(HttpServletRequest request) {
		return "/content/activity/stock/recommend/study";
	}
}
