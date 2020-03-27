package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.BoardVO;
import com.komsia.kom.domain.NoticeVO;

import groovy.cli.Option;

@Repository
@Mapper
public interface ForumMapper {

	List<NoticeVO> selectNoticeList(NoticeVO noticeVO);

	int selectNoticeListTotalCnt(NoticeVO noticeVO);
	
	NoticeVO selectNotice(NoticeVO noticeVO);
	
	NoticeVO selectPrevNotice(NoticeVO noticeVO);
	
	NoticeVO selectNextNotice(NoticeVO noticeVO);

	List<BoardVO> selectBoardList(BoardVO boardVO);

	int selectBoardListTotalCnt(BoardVO boardVO);

	BoardVO selectBoard(String boardNo);
	
	BoardVO selectPrevBoard(String boardNo);
	
	BoardVO selectNextBoard(String boardNo);

	void updateHitByNotice(NoticeVO noticeVO);
	
	void updateHitByBoard(BoardVO boardVO);
	
	@Options(useGeneratedKeys = true, keyProperty = "noticeVO.boardNo")
	int saveNotice(@Param("noticeVO")NoticeVO noticeVO);
	
}
