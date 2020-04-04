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
import com.komsia.kom.service.ActivityService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class StockController {
	
	private ActivityService activityService;
	
	/**
	 * 연구회추천주
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend")
	public String recommand(HttpServletRequest request) {
		return "redirect:/activity/stock/recommend/type";
	}
	
	/**
	 * 애널리스트 소개
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analist/info")
	public String analistInfo(HttpServletRequest request) {
		return "/content/activity/stock/analist/info";
	}
	
	/**
	 * 애널리스트 채팅
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analist/chat")
	public String analistChat(HttpServletRequest request) {
		return "redirect:/activity/stock/analist/chat/manager";
	}
	
	/**
	 * 애널리스트 채팅 > 연구소소장
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analist/chat/manager")
	public String analistChatManager(HttpServletRequest request) {
		return "/content/activity/stock/analist/manager";
	}
	
	/**
	 * 애널리스트 채팅 > 애널리스트
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analist/chat/analyst")
	public String analistChatAnalyst(HttpServletRequest request) {
		return "/content/activity/stock/analist/analyst";
	}
	
	/**
	 * 연구회 관심종목
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/type")
	public String type(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_T);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/stock/recommend/type";
	}
	
	@GetMapping(value = "/activity/stock/recommend/type/regist")
	public String typeRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_T);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/type_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/type/regist")
	@ResponseBody
	public Map<String, Object> typeRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_T);
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
	 * 데이트레이딩
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/trading")
	public String trading(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_D);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/activity/stock/recommend/trading";
	}
	
	@GetMapping(value = "/activity/stock/recommend/trading/regist")
	public String tradingRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_D);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/trading_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/trading/regist")
	@ResponseBody
	public Map<String, Object> tradingRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_D);
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
	 * 기관추천주
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/financial")
	public String financial(HttpServletRequest request
			, ModelMap model) {
			
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_F);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/activity/stock/recommend/financial";
	}
	
	@GetMapping(value = "/activity/stock/recommend/financial/regist")
	public String financialRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_F);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/financial_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/financial/regist")
	@ResponseBody
	public Map<String, Object> financialRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_F);
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
	 * 수급동향
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/supply")
	public String supply(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_S);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/activity/stock/recommend/supply";
	}
	
	@GetMapping(value = "/activity/stock/recommend/supply/regist")
	public String supplyRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_S);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/supply_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/supply/regist")
	@ResponseBody
	public Map<String, Object> supplyRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_S);
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
	 * 종목별이슈
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/items")
	public String items(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_I);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/stock/recommend/items";
	}
	
	@GetMapping(value = "/activity/stock/recommend/items/regist")
	public String itemsRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_I);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/items_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/items/regist")
	@ResponseBody
	public Map<String, Object> itemsRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_I);
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
	 * 업종이슈
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/business")
	public String business(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_B);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/activity/stock/recommend/business";
	}
	
	@GetMapping(value = "/activity/stock/recommend/business/regist")
	public String businessRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_B);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/business_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/business/regist")
	@ResponseBody
	public Map<String, Object> businessRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_B);
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
	 * 테마동향
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/theme")
	public String theme(HttpServletRequest request
			, ModelMap model) {
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_H);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/activity/stock/recommend/theme";
	}
	
	@GetMapping(value = "/activity/stock/recommend/theme/regist")
	public String themeRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_H);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		return "/content/activity/stock/recommend/theme_regist";
	}
	
	@PostMapping(value = "/activity/stock/recommend/theme/regist")
	@ResponseBody
	public Map<String, Object> themeRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_H);
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
	 * 공부방
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/activity/stock/recommend/study")
	public String study(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_Y);
		try {
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/stock/recommend/study";
	}
}
