package com.komsia.kom.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.FileVO;

@Repository
@Mapper
public interface FileMapper {

	void insertFile(FileVO fileVO);

	FileVO selectFile(FileVO fileVO);
	
}
