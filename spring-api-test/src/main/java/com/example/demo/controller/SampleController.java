package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.util.S3FileUtil;

@RestController
@RequestMapping("/sample")
public class SampleController {

//	@Autowired
//	FileUtil fileUtil;
	
	@Autowired
	S3FileUtil s3FileUtil;

	@PostMapping("/file")
	public ResponseEntity<String> register(@RequestPart("uploadFile") MultipartFile uploadFile) {
		
		String imgPath = s3FileUtil.fileUpload(uploadFile);
		
		return new ResponseEntity<>(imgPath, HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<String> read(@RequestParam(name = "no") int no) {
//		BoardDTO dto = service.read(no);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
