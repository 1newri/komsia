package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.ActivityVO;

@Mapper
@Repository
public interface ActivityMapper {

	List<ActivityVO> selectActivityList(ActivityVO activityVO);

	@Options(useGeneratedKeys = true, keyProperty = "activityVO.boardNo")
	void insertActivity(ActivityVO activityVO);

	ActivityVO selectActivityStock(ActivityVO activityVO);

}