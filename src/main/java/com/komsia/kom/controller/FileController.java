package com.komsia.kom.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.komsia.kom.domain.FileVO;
import com.komsia.kom.service.FileService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@PostMapping(value =  "/file/download")
	@ResponseBody
	public ResponseEntity<Resource> download(
			@ModelAttribute FileVO fileVO
			) throws IOException {
		
		fileVO = fileService.selectFile(fileVO);
		
		Path path = Paths.get(fileVO.getFileDir());
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
		log.debug("contentType : {}", contentType);
		log.debug("HttpHeaders : {}", headers);

		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

}
