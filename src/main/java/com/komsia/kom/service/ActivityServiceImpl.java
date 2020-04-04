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
import com.komsia.kom.mapper.ActivityMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityServiceImpl implements ActivityService{
	
	private ActivityMapper activityMapper;

	private FileService fileSerivce;
	
	@Override
	public List<ActivityVO> selectRecommandList(ActivityVO activityVO) {

		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		return activityMapper.selectActivityList(activityVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> recommandRegist(ActivityVO activityVO) throws Exception {
		
		log.debug("board Type : {}", activityVO.getBoardType());
		log.debug("board Sub Type : {}", activityVO.getBoardSubType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
			
		log.debug("activityVO : {} ", activityVO.toString());
		activityMapper.insertActivity(activityVO);
		
		int boardNo = activityVO.getBoardNo();
		log.debug("boardNo : {}", boardNo);
		if(!ObjectUtils.isEmpty(activityVO.getFile())) {
			log.debug("file size : {}", activityVO.getFile().getSize());
			if(activityVO.getFile().getSize() > 0) {
				FileVO fileVO = new FileVO();
				fileVO.setFile(activityVO.getFile());
				fileVO.setBoardNo(boardNo);
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
	public ActivityVO selectActivityStock(ActivityVO activityVO) {
		return activityMapper.selectActivityStock(activityVO);
	}

}
