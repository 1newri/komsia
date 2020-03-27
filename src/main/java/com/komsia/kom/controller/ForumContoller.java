package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.domain.BoardVO;
import com.komsia.kom.domain.NoticeVO;
import com.komsia.kom.service.ForumService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@AllArgsConstructor
public class ForumContoller {
	
	private ForumService forumService;
	
	@GetMapping(value = "/forum")
	public String forum(HttpServletRequest request) {
		return "redirect:/forum/notice";
	}
	
	@GetMapping(value = "/forum/notice")
	public String notice(HttpServletRequest request) {
		return "/content/forum/notice";
	}
	
	@PostMapping(value = "/forum/notice/list")
	@ResponseBody
	public Map<String, Object> noticeList(HttpServletRequest request
			, @ModelAttribute NoticeVO noticeVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.getNoticeList(noticeVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/notice/detail")
	public String noticeDetail(HttpServletRequest request
			, @ModelAttribute NoticeVO noticeVO
			, ModelMap model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.getNotice(noticeVO);
			model.addAttribute("data", result);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return "/content/forum/notice_detail";
	}
	
	@GetMapping(value = "/forum/notice/regist")
	public String noticeRegist(HttpServletRequest request
			, ModelMap model) {
		
		return "/content/forum/notice_regist";
	}
	
	@PostMapping(value = "/forum/notice/regist")
	@ResponseBody
	public Map<String, Object> noticeRegist(HttpServletRequest request
			, @ModelAttribute NoticeVO noticeVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.noticeRegister(noticeVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@GetMapping(value = "/forum/press")
	public String press(HttpServletRequest request) {
		return "/content/forum/press";
	}
	
	@PostMapping(value = "/forum/press/list")
	@ResponseBody
	public Map<String, Object> pressList(HttpServletRequest request
			,@ModelAttribute BoardVO BoardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		BoardVO.setBoardType(CommonConstant.BOARD_TYPE_P);
		try {
			result = forumService.getBoardList(BoardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@GetMapping(value = "/forum/qna")
	public String qna(HttpServletRequest request) {
		return "/content/forum/qna";
	}
	
	@PostMapping(value = "/forum/qna/list")
	@ResponseBody
	public Map<String, Object> qnaList(HttpServletRequest request
			,@ModelAttribute BoardVO BoardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		BoardVO.setBoardType(CommonConstant.BOARD_TYPE_Q);
		try {
			result = forumService.getBoardList(BoardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
	
	@GetMapping(value = "/forum/reference")
	public String reference(HttpServletRequest request) {
		return "/content/forum/reference";
	}
	
	@PostMapping(value = "/forum/reference/list")
	@ResponseBody
	public Map<String, Object> referenceList(HttpServletRequest request
			,@ModelAttribute BoardVO BoardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		BoardVO.setBoardType(CommonConstant.BOARD_TYPE_R);
		try {
			result = forumService.getBoardList(BoardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return result;
	}
}
