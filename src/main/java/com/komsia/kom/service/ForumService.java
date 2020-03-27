package com.komsia.kom.service;

import java.util.Map;

import com.komsia.kom.domain.BoardVO;
import com.komsia.kom.domain.NoticeVO;

public interface ForumService {

	Map<String, Object> getNoticeList(NoticeVO noticeVO);

	Map<String, Object> getBoardList(BoardVO boardVO);

	Map<String, Object> getNotice(NoticeVO noticeVO);

	Map<String, Object> noticeRegister(NoticeVO noticeVO);

}
