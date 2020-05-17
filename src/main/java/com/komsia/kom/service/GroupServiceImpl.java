package com.komsia.kom.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.GroupVO;
import com.komsia.kom.domain.ReplyVO;
import com.komsia.kom.domain.VideoVO;
import com.komsia.kom.mapper.ActivityMapper;
import com.komsia.kom.mapper.FileMapper;
import com.komsia.kom.mapper.GroupMapper;
import com.komsia.kom.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupSerivce{
	
	private GroupMapper groupMapper;
	
	private FileService fileSerivce;

	@Override
	public GroupVO getBoardGroup(GroupVO groupVO) {
		return groupMapper.selectBoardGroup(groupVO);
	}

	@Override
	public List<String> getBoardDate(GroupVO groupVO) {
		return groupMapper.selectBoardDate(groupVO);
	}

	@Override
	public List<ReplyVO> getBoardGroupReplyList(GroupVO groupVO) {
		return groupMapper.selectBoardGroupReplyList(groupVO);
	}

	@Override
	public Map<String, Object> boardGroupRegist(GroupVO groupVO) throws Exception {
		
		log.debug("board Type : {}", groupVO.getBoardType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		if("C".equals(groupVO.getCrud())) {
			log.debug("activityVO Insert : {} ", groupVO.toString());
			groupVO.setBoardDate(DateUtil.currentDate());
			groupVO.setBoardOrder(groupMapper.selectBoardOrder(groupVO));
			groupMapper.insertBoardGroup(groupVO);
		}else {
			log.debug("activityVO Update : {} ", groupVO.toString());
			groupVO.setModId(groupVO.getRegId());
			groupMapper.updateBoardGroup(groupVO);
		}
		
		int boardNo = groupVO.getBoardNo();
		int boardOrder = groupVO.getBoardOrder();
		log.debug("boardNo : {}", boardNo);
		if(!ObjectUtils.isEmpty(groupVO.getFile())) {
			log.debug("file size : {}", groupVO.getFile().getSize());
			if(groupVO.getFile().getSize() > 0) {
				FileVO fileVO = new FileVO();
				fileVO.setFile(groupVO.getFile());
				fileVO.setBoardNo(boardNo);
				fileVO.setBoardOrder(boardOrder);
				fileVO.setBoardType(groupVO.getBoardType());
				fileVO.setRegId(groupVO.getRegId());
				
				fileSerivce.saveFileBoardGroup(fileVO);	
			}
		}
		
		
		ReplyVO replyVO = new ReplyVO(); 
		replyVO.setBoardNo(boardNo);
		replyVO.setBoardType(groupVO.getBoardType());
		groupMapper.updateBoardNoByReply(replyVO);
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	@Override
	public List<GroupVO> getBoardListByBoardDate(GroupVO groupVO) {
		return groupMapper.selectBoardListByBoardDate(groupVO);
	}

	@Override
	public Map<String, Object> boardGroupReplyRegist(ReplyVO replyVO) {
		
		log.debug("board Type : {}", replyVO.getBoardType());
		log.debug("board Sub Type : {}", replyVO.getBoardSubType());
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
			
		log.debug("activityVO : {} ", replyVO.toString());
		groupMapper.boardGroupReplyRegist(replyVO);
		
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
		
	}

	
}
