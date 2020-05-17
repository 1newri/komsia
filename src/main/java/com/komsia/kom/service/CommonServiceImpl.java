package com.komsia.kom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komsia.kom.domain.CommonVO;
import com.komsia.kom.mapper.CommonMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonMapper commonMapper;

	@Override
	public CommonVO getCodeDetail(String cdId, String cdNo) {
		CommonVO commonVO = new CommonVO();
		commonVO.setCdId(cdId);
		commonVO.setCdNo(cdNo);
		return commonMapper.selectCodeDetail(commonVO);
	}

}
