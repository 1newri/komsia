package com.komsia.kom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.NoticeVO;
import com.komsia.kom.domain.SinmungoVO;
import com.komsia.kom.mapper.SinmungoMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SinmungoServiceImpl implements SinmungoService{
	
	private SinmungoMapper sinmungoMapper;
	private FileService fileSerivce;
	
	@Override
	public Map<String, Object> getSinmungoList(SinmungoVO sinmungoVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// notice List 조회
		List<SinmungoVO> list = sinmungoMapper.selectSinmungoList(sinmungoVO);
		result.put("data", list);
		log.debug("Sinmungo list : {}", list.toString());
		
		// total Cnt 조회
		int total = sinmungoMapper.selectSinmungoListTotalCnt(sinmungoVO);
		log.debug("Sinmungo total : {}", total);
		result.put("recordsTotal",total);
		result.put("recordsFiltered",total);
		
		return result;
	}
	
	@Override
	public Map<String, Object> getSinmungo(SinmungoVO sinmungoVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// sinmungoVO 조회
		sinmungoVO = sinmungoMapper.selectSinmungo(sinmungoVO);
		result.put("vo", sinmungoVO);
		log.debug("sinmungoVO : {} ", sinmungoVO.toString());
		
		// 이전글 조회
		SinmungoVO prev = sinmungoMapper.selectPrevSinmungo(sinmungoVO);
		if(ObjectUtils.isEmpty(prev)) {
			prev = new SinmungoVO();
			prev.setPrevTitle("이전글이 없습니다.");
		}
		result.put("prev", prev);
		log.debug("prev : {} ", prev.toString());
		
		// 다음글 조회
		SinmungoVO next = sinmungoMapper.selectNextSinmungo(sinmungoVO);
		if(ObjectUtils.isEmpty(next)) {
			next = new SinmungoVO();
			next.setNextTitle("다음글이 없습니다.");
		}
		result.put("next", next);
		log.debug("next : {} ", next.toString());
		
		// update hit
		sinmungoMapper.updateHitBySinmungo(sinmungoVO);
		
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> sinmungoRegister(SinmungoVO sinmungoVO) throws Exception{

		log.debug("boardType : {} ", sinmungoVO.getBoardType());
		Map<String, Object> result = new HashMap<String, Object>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		sinmungoMapper.insertSinmungo(sinmungoVO);
		int boardNo = sinmungoVO.getBoardNo();
		log.debug("boardNo : {}", boardNo);
		
		if(!ObjectUtils.isEmpty(sinmungoVO.getFile())) {
			if(sinmungoVO.getFile().getSize() > 0) {
				FileVO fileVO = new FileVO();
				fileVO.setFile(sinmungoVO.getFile());
				fileVO.setBoardNo(boardNo);
				fileVO.setBoardType(sinmungoVO.getBoardType());
				
				fileSerivce.saveFile(fileVO);	
			}
		}
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

}
