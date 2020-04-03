package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.ActivityVO;

public interface StockService {

	List<ActivityVO> selectRecommandList(ActivityVO activityVO);

	Map<String, Object> recommandRegist(ActivityVO activityVO);

}
