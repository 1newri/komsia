package com.komsia.kom.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.mapper.FileMapper;
import com.komsia.kom.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileMapper fileMapper;

	@Value("${komsia.path}")
	private String filePath;
	
	@Override
	public void saveFile(FileVO fileVO) throws Exception{
		
		if(!ObjectUtils.isEmpty(fileVO.getFile())) {
			String oriFileNm = fileVO.getFile().getOriginalFilename();
			String extension = StringUtils.getFilenameExtension(oriFileNm);
			
			fileVO.setFileSeq(0);
			fileVO.setFileNm(oriFileNm);
			fileVO.setFileExt(extension);
			fileVO.setUseYn(CommonConstant.YN_Y);
			
			String fileNm = fileVO.getBoardType() + DateUtil.currentDate();
			String path = filePath + File.separator + fileNm + "." + extension;
			
			fileVO.setFileDir(path);
			
			File file = new File(path);
			fileVO.getFile().transferTo(file);
			
			fileMapper.insertFile(fileVO);
		}
	}

	@Override
	public FileVO selectFile(FileVO fileVO) {
		return fileMapper.selectFile(fileVO);
	}

	@Override
	public void saveFileActivity(FileVO fileVO) throws Exception{
		if(!ObjectUtils.isEmpty(fileVO.getFile())) {
			String oriFileNm = fileVO.getFile().getOriginalFilename();
			String extension = StringUtils.getFilenameExtension(oriFileNm);
			
			fileVO.setFileSeq(0);
			fileVO.setFileNm(oriFileNm);
			fileVO.setFileExt(extension);
			fileVO.setUseYn(CommonConstant.YN_Y);
			
			String fileNm = fileVO.getBoardType() + DateUtil.currentDate();
			String path = filePath + File.separator + fileNm + "." + extension;
			
			fileVO.setFileDir(path);
			
			File file = new File(path);
			fileVO.getFile().transferTo(file);
			
			fileMapper.insertFileActivity(fileVO);
		}
		
	}

	@Override
	public FileVO selectFileActivity(FileVO fileVO) {
		return fileMapper.selectFileActivity(fileVO);
	}
}
