package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
/*
 * 전달받은 파일스트림을 특정 폴더에 저장하고 파일이름을 반환한다
 * 만약 파일스트림이 없다면 메소드를 바로 종료한다
 * */
@Component
public class FileUtil {

	//이미지 파일을 저장할 경로
	@Value("${filepath}")
	String filepath;

	public String fileUpload(MultipartFile multipartFile) {
		Path copyOfLocation = null;
		
		if(multipartFile.isEmpty()) { //파일스트림이 없으면 메소드를 종료한다
			return null;
		}
		try {
			copyOfLocation = Paths
					.get(filepath + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename())); //파일 전체 경로
			
			Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING); //파일을 폴더에 저장
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//파일이름을 반환한다
		return multipartFile.getOriginalFilename();
	}
}
