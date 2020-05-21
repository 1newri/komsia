package com.komsia.kom.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.ActivityVO;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.ReplyVO;
import com.komsia.kom.mapper.ActivityMapper;
import com.komsia.kom.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityServiceImpl implements ActivityService{
	
	private ActivityMapper activityMapper;

	private FileService fileSerivce;
	
	@Override
	public List<ActivityVO> selectActivityList(ActivityVO activityVO) {

		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		return activityMapper.selectActivityList(activityVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> regist(ActivityVO activityVO) throws Exception {
		
		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		activityVO.setBoardDate(DateUtil.currentDate());
		if(ObjectUtils.isEmpty(activityMapper.selectActivityStock(activityVO))) {
			log.debug("activityVO Insert : {} ", activityVO.toString());
			activityVO.setBoardOrder(0);
			activityMapper.insertActivity(activityVO);
		}else {
			log.debug("activityVO Update : {} ", activityVO.toString());
			activityVO.setModId(activityVO.getRegId());
			activityMapper.updateActivity(activityVO);
		}
		
		int boardNo = activityVO.getBoardNo();
		log.debug("boardNo : {}", boardNo);
		if(!ObjectUtils.isEmpty(activityVO.getFile())) {
			log.debug("file size : {}", activityVO.getFile().getSize());
			if(activityVO.getFile().getSize() > 0) {
				FileVO fileVO = new FileVO();
				fileVO.setFile(activityVO.getFile());
				fileVO.setBoardNo(boardNo);
				fileVO.setBoardOrder(0);
				fileVO.setBoardType(activityVO.getBoardType());
				fileVO.setBoardSubType(activityVO.getBoardSubType());
				fileVO.setRegId(activityVO.getRegId());
				
				fileSerivce.saveFileActivity(fileVO);	
			}
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> recommandRegist(ActivityVO activityVO) throws Exception {
		
		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		if("C".equals(activityVO.getCrud())) {
			log.debug("activityVO Insert : {} ", activityVO.toString());
			activityVO.setBoardDate(DateUtil.currentDate());
			activityVO.setBoardOrder(activityMapper.selectBoardOrder(activityVO));
			activityMapper.insertActivity(activityVO);
		}else {
			log.debug("activityVO Update : {} ", activityVO.toString());
			activityVO.setModId(activityVO.getRegId());
			activityMapper.updateActivity(activityVO);
		}
		
		int boardNo = activityVO.getBoardNo();
		int boardOrder = activityVO.getBoardOrder();
		log.debug("boardNo : {}", boardNo);
		if(!ObjectUtils.isEmpty(activityVO.getFile())) {
			log.debug("file size : {}", activityVO.getFile().getSize());
			if(activityVO.getFile().getSize() > 0) {
				FileVO fileVO = new FileVO();
				fileVO.setFile(activityVO.getFile());
				fileVO.setBoardNo(boardNo);
				fileVO.setBoardOrder(boardOrder);
				fileVO.setBoardType(activityVO.getBoardType());
				fileVO.setBoardSubType(activityVO.getBoardSubType());
				fileVO.setRegId(activityVO.getRegId());
				
				fileSerivce.saveFileActivity(fileVO);	
			}
		}
		
		if(CommonConstant.BOARD_SUB_TYPE_M.equals(activityVO.getBoardSubType())
				|| CommonConstant.BOARD_SUB_TYPE_A.equals(activityVO.getBoardSubType())) {
			
			ReplyVO replyVO = new ReplyVO(); 
			replyVO.setBoardNo(boardNo);
			replyVO.setBoardType(activityVO.getBoardType());
			replyVO.setBoardSubType(activityVO.getBoardSubType());
			activityMapper.updateBoardNoByReply(replyVO);
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	@Override
	public ActivityVO selectActivityStock(ActivityVO activityVO) {
		return activityMapper.selectActivityStock(activityVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> boardActivityReplyRegist(ReplyVO replyVO) {
		
		log.debug("board Type : {}", replyVO.getBoardType());
		log.debug("board Sub Type : {}", replyVO.getBoardSubType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
			
		log.debug("activityVO : {} ", replyVO.toString());
		activityMapper.boardActivityReplyRegist(replyVO);
		
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	@Override
	public List<ReplyVO> boardActivityReplyList(ActivityVO activityVO) {
		return activityMapper.boardActivityReplyList(activityVO);
	}

	@Override
	public List<ActivityVO> selectBoardListByBoardDate(ActivityVO activityVO) {
		return activityMapper.selectBoardListByBoardDate(activityVO);
	}

	@Override
	public List<String> selectBoardDate(ActivityVO activityVO) {
		return activityMapper.selectBoardDate(activityVO);
	}
}
