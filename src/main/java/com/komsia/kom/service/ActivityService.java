package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.ActivityVO;
import com.komsia.kom.domain.ReplyVO;

public interface ActivityService {

	List<ActivityVO> selectActivityList(ActivityVO activityVO);

	Map<String, Object> regist(ActivityVO activityVO) throws Exception;
	
	Map<String, Object> recommandRegist(ActivityVO activityVO) throws Exception;

	ActivityVO selectActivityStock(ActivityVO activityVO);

	Map<String, Object> boardActivityReplyRegist(ReplyVO replyVO);

	List<ReplyVO> boardActivityReplyList(ActivityVO activityVO);

	List<ActivityVO> selectBoardListByBoardDate(ActivityVO activityVO);

	List<String> selectBoardDate(ActivityVO activityVO);


}
