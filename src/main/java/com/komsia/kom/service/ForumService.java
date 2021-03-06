package com.komsia.kom.service;

import java.util.Map;

import com.komsia.kom.domain.BoardVO;
import com.komsia.kom.domain.NoticeVO;

public interface ForumService {

	Map<String, Object> getNoticeList(NoticeVO noticeVO);

	Map<String, Object> getBoardList(BoardVO boardVO);

	Map<String, Object> getNotice(NoticeVO noticeVO);

	Map<String, Object> noticeRegister(NoticeVO noticeVO) throws Exception;

	Map<String, Object> getBoard(BoardVO boardVO);

	Map<String, Object> boardRegister(BoardVO boardVO) throws Exception;

	Map<String, Object> updateNotice(NoticeVO noticeVO);

	Map<String, Object> deleteNotice(NoticeVO noticeVO);

	Map<String, Object> deleteForum(BoardVO boardVO);

	Map<String, Object> updateForum(BoardVO boardVO);

}
