package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.SinmungoVO;

@Repository
@Mapper
public interface SinmungoMapper {

	List<SinmungoVO> selectSinmungoList(SinmungoVO sinmungoVO);

	int selectSinmungoListTotalCnt(SinmungoVO sinmungoVO);

	SinmungoVO selectSinmungo(SinmungoVO sinmungoVO);

	SinmungoVO selectPrevSinmungo(SinmungoVO sinmungoVO);

	SinmungoVO selectNextSinmungo(SinmungoVO sinmungoVO);

	void updateHitBySinmungo(SinmungoVO sinmungoVO);

	void insertSinmungo(SinmungoVO sinmungoVO);
	
}
