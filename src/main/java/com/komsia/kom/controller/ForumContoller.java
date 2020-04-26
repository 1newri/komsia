package com.komsia.kom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
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
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
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
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
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
		String userId = (String) request.getSession().getAttribute("userId");
		noticeVO.setRegId(userId);
		try {
			result = forumService.noticeRegister(noticeVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/notice/delete")
	@ResponseBody
	public Map<String, Object> deleteNotice(HttpServletRequest request
			, @ModelAttribute NoticeVO noticeVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		noticeVO.setModId(userId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.deleteNotice(noticeVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/forum/notice/update")
	public String updateNotice(HttpServletRequest request
			, @ModelAttribute NoticeVO noticeVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		noticeVO.setRegId(userId);
		
		Map<String, Object> result = forumService.getNotice(noticeVO);
		model.addAttribute("data", result);
		
		return "/content/forum/notice_regist";
	}
	
	@PostMapping(value = "/forum/notice/save")
	@ResponseBody
	public Map<String, Object> saveNotice(HttpServletRequest request
			, @ModelAttribute NoticeVO noticeVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		noticeVO.setModId(userId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.updateNotice(noticeVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
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
			,@ModelAttribute BoardVO boardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_P);
		try {
			result = forumService.getBoardList(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/press/detail")
	public String pressDetail(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_P);
		try {
			result = forumService.getBoard(boardVO);
			model.addAttribute("data", result);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return "/content/forum/press_detail";
	}
	
	@GetMapping(value = "/forum/press/regist")
	public String pressRegist(HttpServletRequest request
			, ModelMap model) {
		
		return "/content/forum/press_regist";
	}
	
	@PostMapping(value = "/forum/press/regist")
	@ResponseBody
	public Map<String, Object> pressRegist(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setRegId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_P);
		try {
			result = forumService.boardRegister(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/press/update")
	public String updatePress(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setRegId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_P);
		Map<String, Object> result = forumService.getBoard(boardVO);
		model.addAttribute("data", result);
		
		return "/content/forum/press_regist";
	}
	
	@PostMapping(value = "/forum/press/save")
	@ResponseBody
	public Map<String, Object> savePress(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setModId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_P);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.updateForum(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/forum/press/delete")
	@ResponseBody
	public Map<String, Object> deletePress(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setModId(userId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.deleteForum(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
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
			,@ModelAttribute BoardVO boardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_Q);
		try {
			result = forumService.getBoardList(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/qna/detail")
	public String qnaDetail(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_Q);
		try {
			result = forumService.getBoard(boardVO);
			model.addAttribute("data", result);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return "/content/forum/qna_detail";
	}
	
	@GetMapping(value = "/forum/qna/regist")
	public String qnaRegist(HttpServletRequest request
			, ModelMap model) {
		
		return "/content/forum/qna_regist";
	}
	
	@PostMapping(value = "/forum/qna/regist")
	@ResponseBody
	public Map<String, Object> qnaRegist(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setRegId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_Q);
		try {
			result = forumService.boardRegister(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/qna/update")
	public String updateQna(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setRegId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_Q);
		Map<String, Object> result = forumService.getBoard(boardVO);
		model.addAttribute("data", result);
		
		return "/content/forum/qna_regist";
	}
	
	@PostMapping(value = "/forum/qna/save")
	@ResponseBody
	public Map<String, Object> saveQna(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setModId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_Q);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.updateForum(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/forum/qna/delete")
	@ResponseBody
	public Map<String, Object> deleteQna(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setModId(userId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.deleteForum(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
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
			,@ModelAttribute BoardVO boardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_R);
		try {
			result = forumService.getBoardList(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/reference/detail")
	public String referenceDetail(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_R);
		try {
			result = forumService.getBoard(boardVO);
			model.addAttribute("data", result);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return "/content/forum/reference_detail";
	}
	
	@GetMapping(value = "/forum/reference/regist")
	public String referenceRegist(HttpServletRequest request
			, ModelMap model) {
		return "/content/forum/reference_regist";
	}
	
	@PostMapping(value = "/forum/reference/regist")
	@ResponseBody
	public Map<String, Object> referenceRegist(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setRegId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_R);
		try {
			result = forumService.boardRegister(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		return result;
	}
	
	@PostMapping(value = "/forum/reference/update")
	public String updateReference(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setRegId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_R);
		Map<String, Object> result = forumService.getBoard(boardVO);
		model.addAttribute("data", result);
		
		return "/content/forum/reference_regist";
	}
	
	@PostMapping(value = "/forum/reference/save")
	@ResponseBody
	public Map<String, Object> saveReference(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setModId(userId);
		boardVO.setBoardType(CommonConstant.BOARD_TYPE_R);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.updateForum(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value = "/forum/reference/delete")
	@ResponseBody
	public Map<String, Object> deleteReference(HttpServletRequest request
			, @ModelAttribute BoardVO boardVO
			, ModelMap model) {
		
		String userId = (String) request.getSession().getAttribute("userId");
		boardVO.setModId(userId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = forumService.deleteForum(boardVO);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
}

