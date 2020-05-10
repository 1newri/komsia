package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.ActivityVO;
import com.komsia.kom.domain.ReplyVO;
import com.komsia.kom.domain.VideoVO;
import com.komsia.kom.service.ActivityService;
import com.komsia.kom.service.FileService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class StockController {
	
	private ActivityService activityService;
	
	private FileService fileService;
	
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
	@GetMapping(value = "/activity/stock/analyst/info")
	public String analystInfo(HttpServletRequest request) {
		return "/content/activity/stock/analyst/info";
	}
	
	/**
	 * 애널리스트 채팅
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analyst/chat")
	public String analystChat(HttpServletRequest request) {
		return "redirect:/activity/stock/analyst/chat/manager";
	}
	
	/**
	 * 애널리스트 채팅 > 연구소소장
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analyst/chat/manager")
	public String managerChat(HttpServletRequest request
				, @RequestParam(value = "boardDate", required = false) String boardDate
				, @RequestParam(value = "boardOrder", required = false) String boardOrder
				, ModelMap model) {
		
		model.addAttribute("boardType", CommonConstant.BOARD_TYPE_S);
		model.addAttribute("boardSubType", CommonConstant.BOARD_SUB_TYPE_M);
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_M);
		try {
			if(StringUtils.isEmpty(boardOrder)) {
				activityVO.setBoardOrder(0);
			}else {
				activityVO.setBoardOrder(Integer.parseInt(boardOrder));
			}
			
			activityVO.setBoardDate(boardDate);
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
			List<String> list = activityService.selectBoardDate(activityVO);
			model.addAttribute("list", list);
			
			List<ReplyVO> reply = activityService.boardActivityReplyList(activityVO);
			model.addAttribute("reply", reply);
			
			VideoVO videoVO = new VideoVO();
			videoVO.setBoardType(CommonConstant.BOARD_TYPE_S);
			videoVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_M);
			
			List<VideoVO> video = fileService.selectVideoList(videoVO);
			model.addAttribute("video", video);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/stock/analyst/manager";
	}
	
	@GetMapping(value = "/activity/stock/analyst/chat/manager/modify")
	public String managerChatModify(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_M);
		activityVO = activityService.selectActivityStock(activityVO);
		
		model.addAttribute("data", activityVO);
		model.addAttribute("crud","U");
		
		return "/content/activity/stock/analyst/manager_regist";
	}
	
	@GetMapping(value = "/activity/stock/analyst/chat/manager/regist")
	public String managerChatRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_M);
		
		model.addAttribute("crud","C");
		return "/content/activity/stock/analyst/manager_regist";
	}
	
	@PostMapping(value = "/activity/stock/analyst/chat/manager/boardDate")
	@ResponseBody
	public Map<String, Object> boardListByBoardDate(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO
			, ModelMap model){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			List<ActivityVO> list = activityService.selectBoardListByBoardDate(activityVO);
			result.put("list", list);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/activity/stock/analyst/chat/manager/regist")
	@ResponseBody
	public Map<String, Object> managerChatRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_M);
		try {
			result = activityService.recommandRegist(activityVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/activity/stock/analyst/chat/manager/reply/regist")
	@ResponseBody
	public Map<String, Object> managerReplyRegist(HttpServletRequest request
			, @ModelAttribute ReplyVO replyVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		replyVO.setReplyRegId(userId);
		try {
			result = activityService.boardActivityReplyRegist(replyVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	/**
	 * 애널리스트 채팅 > 애널리스트
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/activity/stock/analyst/chat/analyst")
	public String analystChat(HttpServletRequest request
			, @RequestParam(value = "boardDate", required = false) String boardDate
			, @RequestParam(value = "boardOrder", required = false) String boardOrder
			, ModelMap model) {
		
		model.addAttribute("boardType", CommonConstant.BOARD_TYPE_S);
		model.addAttribute("boardSubType", CommonConstant.BOARD_SUB_TYPE_A);
		
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_A);
		try {
			
			activityVO.setBoardDate(boardDate);
			if(StringUtils.isEmpty(boardOrder)) {
				activityVO.setBoardOrder(0);
			}else {
				activityVO.setBoardOrder(Integer.parseInt(boardOrder));
			}
			
			activityVO = activityService.selectActivityStock(activityVO);
			model.addAttribute("data", activityVO);
			
			List<String> list = activityService.selectBoardDate(activityVO);
			model.addAttribute("list", list);
			
			
			List<ReplyVO> reply = activityService.boardActivityReplyList(activityVO);
			model.addAttribute("reply", reply);
			
			VideoVO videoVO = new VideoVO();
			videoVO.setBoardType(CommonConstant.BOARD_TYPE_S);
			videoVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_A);
			
			List<VideoVO> video = fileService.selectVideoList(videoVO);
			model.addAttribute("video", video);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/activity/stock/analyst/analyst";
	}
	
	@GetMapping(value = "/activity/stock/analyst/chat/analyst/modify")
	public String analystChatModify(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_A);
		activityVO = activityService.selectActivityStock(activityVO);
		
		model.addAttribute("data", activityVO);
		model.addAttribute("crud","U");
		
		return "/content/activity/stock/analyst/analyst_regist";
	}
	
	@GetMapping(value = "/activity/stock/analyst/chat/analyst/regist")
	public String analystChatRegist(HttpServletRequest request
			, ModelMap model) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_A);
		activityVO = activityService.selectActivityStock(activityVO);
		model.addAttribute("data", activityVO);
		model.addAttribute("crud","C");
		return "/content/activity/stock/analyst/analyst_regist";
	}
	
	@PostMapping(value = "/activity/stock/analyst/chat/analyst/regist")
	@ResponseBody
	public Map<String, Object> analystChatRegist(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		activityVO.setRegId(userId);
		activityVO.setBoardType(CommonConstant.BOARD_TYPE_S);
		activityVO.setBoardSubType(CommonConstant.BOARD_SUB_TYPE_A);
		try {
			result = activityService.recommandRegist(activityVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/activity/stock/analyst/chat/analyst/boardDate")
	@ResponseBody
	public Map<String, Object> analystBoardListByBoardDate(HttpServletRequest request
			, @ModelAttribute ActivityVO activityVO
			, ModelMap model){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			List<ActivityVO> list = activityService.selectBoardListByBoardDate(activityVO);
			result.put("list", list);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/activity/stock/analyst/chat/analyst/reply/regist")
	@ResponseBody
	public Map<String, Object> analystReplyRegist(HttpServletRequest request
			, @ModelAttribute ReplyVO replyVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		replyVO.setReplyRegId(userId);
		try {
			result = activityService.boardActivityReplyRegist(replyVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
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
