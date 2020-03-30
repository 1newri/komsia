package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.BoardVO;
import com.komsia.kom.domain.NoticeVO;
import com.komsia.kom.mapper.ForumMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ForumServiceImpl implements ForumService{

	private ForumMapper forumMapper;
	
	@Override
	public Map<String, Object> getNoticeList(NoticeVO noticeVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// notice List 조회
		List<NoticeVO> list = forumMapper.selectNoticeList(noticeVO);
		result.put("data", list);
		log.debug("Notice list : {}", list.toString());
		
		// total Cnt 조회
		int total = forumMapper.selectNoticeListTotalCnt(noticeVO);
		log.debug("Notice total : {}", total);
		result.put("recordsTotal",total);
		result.put("recordsFiltered",total);
		
		return result;
	}
	

	@Override
	public Map<String, Object> getNotice(NoticeVO noticeVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// noticeVO 조회
		noticeVO = forumMapper.selectNotice(noticeVO);
		result.put("vo", noticeVO);
		log.debug("noticeVO : {} ", noticeVO.toString());
		
		// 이전글 조회
		NoticeVO prev = forumMapper.selectPrevNotice(noticeVO);
		if(ObjectUtils.isEmpty(prev)) {
			prev = new NoticeVO();
			prev.setPrevNoticeTitle("이전글이 없습니다.");
		}
		result.put("prev", prev);
		log.debug("prev : {} ", prev.toString());
		
		// 다음글 조회
		NoticeVO next = forumMapper.selectNextNotice(noticeVO);
		if(ObjectUtils.isEmpty(next)) {
			next = new NoticeVO();
			next.setNextNoticeTitle("다음글이 없습니다.");
		}
		result.put("next", next);
		log.debug("next : {} ", next.toString());
		
		// update hit
		forumMapper.updateHitByNotice(noticeVO);
		
		return result;
	}

	@Override
	public Map<String, Object> getBoardList(BoardVO boardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		log.debug("Board Type : {}", boardVO.getBoardType());
		
		List<BoardVO> list = forumMapper.selectBoardList(boardVO);
		result.put("data", list);
		
		int total = forumMapper.selectBoardListTotalCnt(boardVO);
		result.put("recordsTotal",total);
		result.put("recordsFiltered",total);
		
		return result;
	}


	@Override
	public Map<String, Object> noticeRegister(NoticeVO noticeVO) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		// forumMapper.saveNotice(noticeVO);
		int boardNo = noticeVO.getBoardNo();
		
		log.debug("boardNo : {}", boardNo);
		
		result.put("boardNo", boardNo);
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}


}
