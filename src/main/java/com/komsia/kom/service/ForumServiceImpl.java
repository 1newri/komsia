package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.BoardVO;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.NoticeVO;
import com.komsia.kom.mapper.ForumMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ForumServiceImpl implements ForumService{

	private ForumMapper forumMapper;
	
	private FileService fileSerivce;
	
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
	public Map<String, Object> getBoard(BoardVO boardVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// noticeVO 조회
		boardVO = forumMapper.selectBoard(boardVO);
		result.put("vo", boardVO);
		log.debug("boardVO : {} ", boardVO.toString());
		
		// 이전글 조회
		BoardVO prev = forumMapper.selectPrevBoard(boardVO);
		if(ObjectUtils.isEmpty(prev)) {
			prev = new BoardVO();
			prev.setPrevTitle("이전글이 없습니다.");
		}
		result.put("prev", prev);
		log.debug("prev : {} ", prev.toString());
		
		// 다음글 조회
		BoardVO next = forumMapper.selectNextBoard(boardVO);
		if(ObjectUtils.isEmpty(next)) {
			next = new BoardVO();
			next.setNextTitle("다음글이 없습니다.");
		}
		result.put("next", next);
		log.debug("next : {} ", next.toString());
		
		// update hit
		forumMapper.updateHitByBoard(boardVO);
		
		return result;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> noticeRegister(NoticeVO noticeVO) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		
		forumMapper.insertNotice(noticeVO);
		
		int boardNo = noticeVO.getBoardNo();
		log.debug("boardNo : {}", boardNo);
		
		if(!ObjectUtils.isEmpty(noticeVO.getFile())) {
			log.debug("file size : {}", noticeVO.getFile().getSize());
			if(noticeVO.getFile().getSize() > 0) {
				FileVO fileVO = new FileVO();
				fileVO.setFile(noticeVO.getFile());
				fileVO.setBoardNo(boardNo);
				fileVO.setBoardType(CommonConstant.BOARD_TYPE_N);
				fileVO.setRegId(noticeVO.getRegId());
				
				fileSerivce.saveFile(fileVO);	
			}
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> boardRegister(BoardVO boardVO) throws Exception{
		log.debug("boardType : {} ", boardVO.getBoardType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		try {
			log.debug("boardVO : {} ", boardVO.toString());
			forumMapper.insertBoardForum(boardVO);
			
			int boardNo = boardVO.getBoardNo();
			log.debug("boardNo : {}", boardNo);
			if(!ObjectUtils.isEmpty(boardVO.getFile())) {
				if(boardVO.getFile().getSize() > 0) {
					FileVO fileVO = new FileVO();
					fileVO.setFile(boardVO.getFile());
					fileVO.setBoardNo(boardNo);
					fileVO.setBoardType(boardVO.getBoardType());
					fileVO.setRegId(boardVO.getRegId());
					fileSerivce.saveFile(fileVO);	
				}
			}
			
		} catch (Exception e) {
			log.error("Exception : {}", e);
			resCode = ResponseCode.RESPONSE_FAIL;
			resMsg = ResponseCode.RESPONSE_FAIL_MSG;
		}
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}


}
