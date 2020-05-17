package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.CommonVO;
import com.komsia.kom.domain.GroupVO;
import com.komsia.kom.domain.ReplyVO;
import com.komsia.kom.domain.VideoVO;
import com.komsia.kom.service.CommonService;
import com.komsia.kom.service.FileService;
import com.komsia.kom.service.GroupSerivce;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class GroupController {

	private GroupSerivce groupService;
	
	private CommonService commonService;
	
	private FileService fileService;
	
	@GetMapping(value = "/group")
	public String group(HttpServletRequest request) {
		return "redirect:/group/G01";
	}
	
	/**
	 * 성창기업
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/group/G01")
	public String group1(HttpServletRequest request
			, @RequestParam(value = "boardDate", required = false) String boardDate
			, @RequestParam(value = "boardOrder", required = false) String boardOrder
			, ModelMap model) {
		
		String boardType = CommonConstant.BOARD_TYPE_G01;
		model.addAttribute("boardType", boardType);
		CommonVO commonVO = commonService.getCodeDetail(boardType, CommonConstant.CODE_NO_GROUP);
		model.addAttribute("menuTitle", commonVO.getCdNm());
		
		GroupVO groupVO = new GroupVO();
		groupVO.setBoardType(boardType);
		try {
			
			if(StringUtils.isEmpty(boardOrder)) {
				groupVO.setBoardOrder(0);
			}else {
				groupVO.setBoardOrder(Integer.parseInt(boardOrder));
			}
			groupVO.setBoardDate(boardDate);
			
			groupVO = groupService.getBoardGroup(groupVO);
			model.addAttribute("data", groupVO);
			
			List<String> list = groupService.getBoardDate(groupVO);
			model.addAttribute("list", list);
			
			List<ReplyVO> reply = groupService.getBoardGroupReplyList(groupVO);
			model.addAttribute("reply", reply);
			
			VideoVO videoVO = new VideoVO();
			videoVO.setBoardType(boardType);
			
			List<VideoVO> video = fileService.selectVideoList(videoVO);
			model.addAttribute("video", video);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/group/group1";
	}
	
	/**
	 * 메이슨캐피탈
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/group/G02")
	public String group2(HttpServletRequest request
			, @RequestParam(value = "boardDate", required = false) String boardDate
			, @RequestParam(value = "boardOrder", required = false) String boardOrder
			, ModelMap model) {
		
		String boardType = CommonConstant.BOARD_TYPE_G02;
		model.addAttribute("boardType", boardType);
		CommonVO commonVO = commonService.getCodeDetail(boardType, CommonConstant.CODE_NO_GROUP);
		model.addAttribute("menuTitle", commonVO.getCdNm());
		
		GroupVO groupVO = new GroupVO();
		groupVO.setBoardType(boardType);
		try {
			
			if(StringUtils.isEmpty(boardOrder)) {
				groupVO.setBoardOrder(0);
			}else {
				groupVO.setBoardOrder(Integer.parseInt(boardOrder));
			}
			groupVO.setBoardDate(boardDate);
			
			groupVO = groupService.getBoardGroup(groupVO);
			model.addAttribute("data", groupVO);
			
			List<String> list = groupService.getBoardDate(groupVO);
			model.addAttribute("list", list);
			
			List<ReplyVO> reply = groupService.getBoardGroupReplyList(groupVO);
			model.addAttribute("reply", reply);
			
			VideoVO videoVO = new VideoVO();
			videoVO.setBoardType(boardType);
			
			List<VideoVO> video = fileService.selectVideoList(videoVO);
			model.addAttribute("video", video);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		
		return "/content/group/group2";
	}
	
	/**
	 * 삼성전자
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/group/G03")
	public String group3(HttpServletRequest request
			, @RequestParam(value = "boardDate", required = false) String boardDate
			, @RequestParam(value = "boardOrder", required = false) String boardOrder
			, ModelMap model) {
		String boardType = CommonConstant.BOARD_TYPE_G03;
		model.addAttribute("boardType", boardType);
		CommonVO commonVO = commonService.getCodeDetail(boardType, CommonConstant.CODE_NO_GROUP);
		model.addAttribute("menuTitle", commonVO.getCdNm());
		
		GroupVO groupVO = new GroupVO();
		groupVO.setBoardType(boardType);
		try {
			
			if(StringUtils.isEmpty(boardOrder)) {
				groupVO.setBoardOrder(0);
			}else {
				groupVO.setBoardOrder(Integer.parseInt(boardOrder));
			}
			groupVO.setBoardDate(boardDate);
			
			groupVO = groupService.getBoardGroup(groupVO);
			model.addAttribute("data", groupVO);
			
			List<String> list = groupService.getBoardDate(groupVO);
			model.addAttribute("list", list);
			
			List<ReplyVO> reply = groupService.getBoardGroupReplyList(groupVO);
			model.addAttribute("reply", reply);
			
			VideoVO videoVO = new VideoVO();
			videoVO.setBoardType(boardType);
			
			List<VideoVO> video = fileService.selectVideoList(videoVO);
			model.addAttribute("video", video);
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/group/group3";
	}
	
	
	@GetMapping(value = "/group/modify/{boardType}")
	public String futuresModify(HttpServletRequest request
			, @RequestParam(value = "boardDate", required = true) String boardDate
			, @RequestParam(value = "boardOrder", required = true) int boardOrder
			, @PathVariable(value = "boardType", required = true) String boardType
			, ModelMap model) {
		GroupVO groupVO = new GroupVO();
		groupVO.setBoardType(boardType);
		groupVO.setBoardDate(boardDate);
		groupVO.setBoardOrder(boardOrder);
		groupVO = groupService.getBoardGroup(groupVO);
		
		CommonVO commonVO = commonService.getCodeDetail(boardType, CommonConstant.CODE_NO_GROUP);
		model.addAttribute("menuTitle", commonVO.getCdNm());
		
		model.addAttribute("data", groupVO);
		model.addAttribute("crud","U");
		return "/content/group/group_regist";
	}
	
	@GetMapping(value = "/group/regist/{boardType}")
	public String futuresRegist(HttpServletRequest request
			, @PathVariable(value = "boardType", required = true) String boardType
			, ModelMap model) {
		model.addAttribute("crud","C");
		
		CommonVO commonVO = commonService.getCodeDetail(boardType, CommonConstant.CODE_NO_GROUP);
		model.addAttribute("menuTitle", commonVO.getCdNm());
		
		return "/content/group/group_regist";
	}
	
	@PostMapping(value = "/group/regist/{boardType}")
	@ResponseBody
	public Map<String, Object> futuresRegist(HttpServletRequest request
			, @PathVariable(value = "boardType", required = true) String boardType
			, @ModelAttribute GroupVO groupVO) {
		
		groupVO.setBoardType(boardType);
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		groupVO.setRegId(userId);
		try {
			result = groupService.boardGroupRegist(groupVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/group/boardDate")
	@ResponseBody
	public Map<String, Object> futuresByBoardDate(HttpServletRequest request
			, @ModelAttribute GroupVO groupVO
			, ModelMap model){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			List<GroupVO> list = groupService.getBoardListByBoardDate(groupVO);
			result.put("list", list);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/group/reply/regist")
	@ResponseBody
	public Map<String, Object> futuresReplyRegist(HttpServletRequest request
			, @ModelAttribute ReplyVO replyVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		replyVO.setReplyRegId(userId);
		try {
			result = groupService.boardGroupReplyRegist(replyVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
}
