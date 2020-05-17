package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.CommonVO;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.VideoVO;

@Repository
@Mapper
public interface CommonMapper {

	CommonVO selectCodeDetail(CommonVO commonVO);
	
}
