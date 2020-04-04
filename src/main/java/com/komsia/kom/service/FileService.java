package com.komsia.kom.service;

import com.komsia.kom.domain.FileVO;

public interface FileService {

	void saveFile(FileVO fileVO) throws Exception;

	FileVO selectFile(FileVO fileVO);

	void saveFileActivity(FileVO fileVO) throws Exception;

	FileVO selectFileActivity(FileVO fileVO);
}
