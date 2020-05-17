package com.komsia.kom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.VideoVO;

@Repository
@Mapper
public interface FileMapper {

	void insertFile(FileVO fileVO);

	FileVO selectFile(FileVO fileVO);

	void insertFileActivity(FileVO fileVO);
	
	void updateFileActivity(FileVO fileVO);

	FileVO selectFileActivity(FileVO fileVO);

	void videoUpload(VideoVO videoVO);

	List<VideoVO> selectVideoList(VideoVO videoVO);

	void videoUpdate(VideoVO videoVO);

	void updateFile(FileVO fileVO);

	FileVO selectFileGroup(FileVO fileVO);
	
	void insertFileGroup(FileVO fileVO);
	
	void updateFileGroup(FileVO fileVO);
	
	void videoUploadByGroup(VideoVO videoVO);
	
	void videoUpdateByGroup(VideoVO videoVO);
	
	List<VideoVO> selectVideoListByGroup(VideoVO videoVO);
	
}
