package com.komsia.kom.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.komsia.kom.constant.CommonConstant;
import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.VideoVO;
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


	@Override
	public Map<String, String> videoUpload(VideoVO videoVO) {
		Map<String, String> result = new HashMap<String, String>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		if(fileMapper.selectVideoList(videoVO).size() >= 5) {
			resCode = ResponseCode.NOT_PROCEED_LENGTH;
			resMsg = ResponseCode.NOT_PROCEED_LENGTH_MSG;
		}else {
			if(!StringUtils.isEmpty(videoVO.getVideoUrl())){
				String videoUrl = youtubeRegex(videoVO.getVideoUrl());
				videoVO.setVideoUrl(videoUrl);
				
				String thumbnailUrl = thumbnailRegex(videoVO.getVideoUrl());
				videoVO.setThumbnailUrl(thumbnailUrl);
			}
			
			fileMapper.videoUpload(videoVO);
		}
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}

	@Override
	public List<VideoVO> selectVideoList(VideoVO videoVO) {
		return fileMapper.selectVideoList(videoVO);
	}
	
	private String youtubeRegex(String videoUrl) {
		String url = "";
		Pattern pattern = Pattern.compile("(?:http|https|)(?::\\/\\/|)(?:www.|)(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11})[a-z0-9;:@#?&%=+\\/\\$_.-]*");
		Matcher result = pattern.matcher(videoUrl);
	    if (result.find()) {
	         String id = result.group(1);
	         log.debug("youtube url id : {} ", id);
	         url = CommonConstant.YOUTUBE_URL + "/" + id;
	    }
	    return url;
	}
	
	private String thumbnailRegex(String videoUrl) {
		String url = "";
		Pattern pattern = Pattern.compile("(?:http|https|)(?::\\/\\/|)(?:www.|)(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11})[a-z0-9;:@#?&%=+\\/\\$_.-]*");
		Matcher result = pattern.matcher(videoUrl);
		if (result.find()) {
			String id = result.group(1);
			log.debug("youtube url id : {} ", id);
			url = CommonConstant.THUMBNAIL_URL + "/" + id + "/mqdefault.jpg";
		}
		return url;
	}

	@Override
	public Map<String, String> videoUpdate(VideoVO videoVO) {
		Map<String, String> result = new HashMap<String, String>();
		
		String resCode = ResponseCode.RESPONSE_OK;
		String resMsg = ResponseCode.RESPONSE_OK_MSG;
		
		if(!StringUtils.isEmpty(videoVO.getVideoUrl())){
			String videoUrl = youtubeRegex(videoVO.getVideoUrl());
			videoVO.setVideoUrl(videoUrl);
			
			String thumbnailUrl = thumbnailRegex(videoVO.getVideoUrl());
			videoVO.setThumbnailUrl(thumbnailUrl);
		}
		
		fileMapper.videoUpdate(videoVO);
		
		result.put("resCode", resCode);
		result.put("resMsg", resMsg);
		
		return result;
	}
}
