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

	void updateHitByNotice(NoticeVO noticeVO);
	
	@Options(useGeneratedKeys = true, keyProperty = "noticeVO.boardNo")
	int insertNotice(@Param("noticeVO")NoticeVO noticeVO);
	
	
	List<BoardVO> selectBoardList(BoardVO boardVO);

	int selectBoardListTotalCnt(BoardVO boardVO);

	BoardVO selectBoard(BoardVO boardVO);

	BoardVO selectPrevBoard(BoardVO boardVO);

	BoardVO selectNextBoard(BoardVO boardVO);
	
	void updateHitByBoard(BoardVO boardVO);
	
	@Options(useGeneratedKeys = true, keyProperty = "boardVO.boardNo")
	int insertBoardForum(BoardVO boardVO);
}
