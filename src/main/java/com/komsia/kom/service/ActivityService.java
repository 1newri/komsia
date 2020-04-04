package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.ActivityVO;

public interface ActivityService {

	List<ActivityVO> selectRecommandList(ActivityVO activityVO);

	Map<String, Object> recommandRegist(ActivityVO activityVO);

	ActivityVO selectActivityStock(ActivityVO activityVO);

}
