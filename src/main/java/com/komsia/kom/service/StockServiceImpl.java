package com.komsia.kom.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.ActivityVO;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.mapper.ActivityMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService{
	
	private ActivityMapper activityMapper;

	@Override
	public List<ActivityVO> selectRecommandList(ActivityVO activityVO) {

		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		return activityMapper.selectActivityList(activityVO);
	}

	@Override
	public Map<String, Object> recommandRegist(ActivityVO activityVO) {
		
		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		activityVO.setRegId("SYSTEM");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		log.debug("activityVO : {} ", activityVO.toString());
		activityMapper.insertActivity(activityVO);
		
		int boardNo = activityVO.getBoardNo();
		log.debug("boardNo : {}", boardNo);
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

}
