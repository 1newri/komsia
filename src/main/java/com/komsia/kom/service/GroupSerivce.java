package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.GroupVO;
import com.komsia.kom.domain.ReplyVO;

public interface GroupSerivce {

	GroupVO getBoardGroup(GroupVO groupVO);

	List<String> getBoardDate(GroupVO groupVO);

	List<ReplyVO> getBoardGroupReplyList(GroupVO groupVO);

	Map<String, Object> boardGroupRegist(GroupVO groupVO) throws Exception ;

	List<GroupVO> getBoardListByBoardDate(GroupVO groupVO);

	Map<String, Object> boardGroupReplyRegist(ReplyVO replyVO);

}
