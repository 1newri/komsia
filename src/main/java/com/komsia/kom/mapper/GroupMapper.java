package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.GroupVO;
import com.komsia.kom.domain.ReplyVO;

@Repository
@Mapper
public interface GroupMapper {

	GroupVO selectBoardGroup(GroupVO groupVO);

	List<String> selectBoardDate(GroupVO groupVO);

	List<ReplyVO> selectBoardGroupReplyList(GroupVO groupVO);

	int selectBoardOrder(GroupVO groupVO);

	void insertBoardGroup(GroupVO groupVO);

	void updateBoardGroup(GroupVO groupVO);

	void updateBoardNoByReply(ReplyVO replyVO);

	void boardGroupReplyRegist(ReplyVO replyVO);

	List<GroupVO> selectBoardListByBoardDate(GroupVO groupVO);

}
