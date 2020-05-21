package com.komsia.kom.service;

import java.util.List;
import java.util.Map;

import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.VideoVO;

public interface FileService {

	void saveFile(FileVO fileVO) throws Exception;

	FileVO selectFile(FileVO fileVO);

	void saveFileActivity(FileVO fileVO) throws Exception;

	FileVO selectFileActivity(FileVO fileVO);

	Map<String, String> videoUpload(VideoVO videoVO);

	List<VideoVO> selectVideoList(VideoVO videoVO);

	Map<String, String> videoUpdate(VideoVO videoVO);

	void updateFile(FileVO fileVO) throws Exception;
	
	void updateFileActivity(FileVO fileVO) throws Exception;
	
	void updateFileGroup(FileVO fileVO) throws Exception;

	Map<String, String> videoUploadByGroup(VideoVO videoVO);

	Map<String, String> videoUpdateByGroup(VideoVO videoVO);

	void saveFileBoardGroup(FileVO fileVO) throws Exception;

	List<VideoVO> selectVideoListByGroup(VideoVO videoVO);

}
