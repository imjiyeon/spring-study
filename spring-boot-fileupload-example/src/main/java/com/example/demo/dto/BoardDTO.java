package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    int no;
    
    String title;
    
    String content;
    
    String writer;
    
    LocalDateTime regDate;
    
    LocalDateTime modDate;
     
    // 사용자가 업로드한 파일을 담는 객체
    // 파일의 내용과 메타데이터(파일명, 크기, 타입(예:img, txt, dir))을 포함
	MultipartFile uploadFile; 
	
	// 이미지 파일 이름
	String imgFileName; 

}

