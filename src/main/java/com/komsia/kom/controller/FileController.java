package com.komsia.kom.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.komsia.kom.constant.ResponseCode;
import com.komsia.kom.domain.FileVO;
import com.komsia.kom.domain.VideoVO;
import com.komsia.kom.service.FileService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class FileController {
	
	@Value("${komsia.ckeditor.filePath}")
	private String ckFilePath;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping(value =  "/file/ckImgUpload")
	public void ckImgUpload(HttpServletRequest request, HttpServletResponse response
			, MultipartHttpServletRequest multiFile
			, @RequestParam MultipartFile upload
			) throws IOException {
		
		// 랜덤 문자 생성
        UUID uid = UUID.randomUUID();

		OutputStream out = null;
		PrintWriter printWriter = null;
		
		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			// 이미지 경로
			String ckUploadPath = ckFilePath + File.separator + uid + "_" + fileName;
			
			File folder = new File(ckFilePath);
			
			if(!folder.exists()) {
				try {
					folder.mkdirs();
				} catch (Exception e) {
					log.error("CKEditor Image Upload Create Folder : {}", e);
				}
			}
			
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush();
			
			String callback = request.getParameter("CKEditorFuncNum");
			
			printWriter = response.getWriter();
			
			String fileUrl = "/file/ckImgSubmit?uid=" + uid + "&fileName=" + fileName;
			
			printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
			printWriter.flush();
			
		} catch (Exception e) {
			log.error("CKEditor Image Upload Error : {}", e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				log.error("CKEditor Image Upload Error : {}", e);
			}
		}
	}
	
	@GetMapping(value =  "/file/ckImgSubmit")
	public void ckImgSubmit(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value = "uid") String uid
			, @RequestParam(value = "fileName") String fileName
			) throws IOException {
		
		String dirPath = ckFilePath + File.separator + uid + "_" + fileName;
		
		File imageFile = new File(dirPath);
		
		if(imageFile.isFile()) {
			byte[] buf = new byte[1024];
			int readByte = 0;
			int length = 0;
			byte[] imgBuf = null;
			
			FileInputStream fileInputStream = null;
			ByteArrayOutputStream outputStream = null;
			ServletOutputStream out = null;
			
			try {
				fileInputStream = new FileInputStream(imageFile);
				outputStream = new ByteArrayOutputStream();
				out = response.getOutputStream();
				
				while((readByte = fileInputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, readByte);
				}
				
				imgBuf = outputStream.toByteArray();
				length = imgBuf.length;
				out.write(imgBuf, 0, length);
				out.flush();
			} catch (Exception e) {
				log.error("CKEditor Image File Submit Error : {}", e);
			} finally {
				outputStream.close();
				fileInputStream.close();
				out.close();
			}
		}
		
	}
	
	
	@PostMapping(value =  "/file/download")
	@ResponseBody
	public ResponseEntity<Resource> download(@ModelAttribute FileVO fileVO
			) throws IOException {
		
		fileVO = fileService.selectFile(fileVO);
		
		Path path = Paths.get(fileVO.getFileDir());
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileVO.getFileNm());
		log.debug("contentType : {}", contentType);
		log.debug("HttpHeaders : {}", headers);

		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping(value =  "/file/activity/download")
	@ResponseBody
	public ResponseEntity<Resource> activityFileDownload(
			@ModelAttribute FileVO fileVO
			) throws IOException {
		
		fileVO = fileService.selectFileActivity(fileVO);
		
		Path path = Paths.get(fileVO.getFileDir());
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileVO.getFileNm());
		log.debug("contentType : {}", contentType);
		log.debug("HttpHeaders : {}", headers);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@GetMapping(value =  "/video")
	public String videoUrl(HttpServletRequest request, HttpServletResponse response
			) throws IOException {
		return "/content/video";
	}
	
	@PostMapping(value =  "/video/upload")
	@ResponseBody
	public Map<String, String> videoUpload(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value = "videoUrl", required = true) String url
			, @RequestParam(value = "boardType", required = true) String boardType
			, @RequestParam(value = "boardSubType", required = true) String boardSubType
			) throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		
		String userId = (String) request.getSession().getAttribute("userId");
		
		VideoVO videoVO = new VideoVO();
		videoVO.setBoardType(boardType);
		videoVO.setBoardSubType(boardSubType);
		videoVO.setVideoUrl(url);
		videoVO.setRegId(userId);
		try {
			result = fileService.videoUpload(videoVO);
		} catch (Exception e) {
			log.error("video upload error : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}
	
	@PostMapping(value =  "/video/update")
	@ResponseBody
	public Map<String, String> videoUpload(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value = "videoNo", required = true) int videoNo
			, @RequestParam(value = "videoUrl", required = true) String url
			, @RequestParam(value = "boardType", required = true) String boardType
			, @RequestParam(value = "boardSubType", required = true) String boardSubType
			) throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		
		String userId = (String) request.getSession().getAttribute("userId");
		
		VideoVO videoVO = new VideoVO();
		videoVO.setVideoNo(videoNo);
		videoVO.setBoardType(boardType);
		videoVO.setBoardSubType(boardSubType);
		videoVO.setVideoUrl(url);
		videoVO.setModId(userId);
		try {
			result = fileService.videoUpdate(videoVO);
		} catch (Exception e) {
			log.error("video upload error : {}", e);
			result.put("resCode", ResponseCode.RESPONSE_FAIL);
			result.put("resMsg", ResponseCode.RESPONSE_FAIL_MSG);
		}
		
		return result;
	}

}
