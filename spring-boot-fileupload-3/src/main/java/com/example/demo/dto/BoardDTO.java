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
	MultipartFile uploadFile; // 파일 스트림
	String imgPath; // 파일 이름
}
