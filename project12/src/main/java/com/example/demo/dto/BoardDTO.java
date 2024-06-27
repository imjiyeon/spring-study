package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

	int no; //게시물번호

	String title; //제목

	String content; //내용

	String writer; //작성자

	LocalDateTime regDate; //등록일

	LocalDateTime modDate; //수정일
}
