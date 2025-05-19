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

// 파일과 관련된 공통 로직을 관리하는 유틸리티 클래스
// 예: 파일 업로드, 파일 이름 추출, 확장자 검사 등

@Component
public class FileUtil {

	// application.properties에 정의된 파일 저장 경로
	@Value("${filepath}")
	String filepath;

	// 업로드된 파일을 컴퓨터에 저장하는 메소드
	// 매개변수: 파일 스트림
	// 반환값: 저장된 파일 이름
	public String fileUpload(MultipartFile multipartFile) {
		Path copyOfLocation = null;
		
		// 전달받은 파일 스트림이 없으면 메소드 종료
		if(multipartFile.isEmpty()) {
			return null;
		}
		try {
			// 파일을 저장하기 위한 전체 폴더 경로
			copyOfLocation = Paths
					.get(filepath + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename())); //파일 전체 경로
			
			// 파일 저장
			// 파일 스트림, 저장 경로, (옵션)같은 이름이 있으면 덮어쓰기
			Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 저장된 파일 이름 반환
		return multipartFile.getOriginalFilename();
	}
}
