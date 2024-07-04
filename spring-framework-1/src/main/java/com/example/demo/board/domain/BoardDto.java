package com.example.demo.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private int no;
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private Date regDate; // 작성일
	private Date updateDate; // 수정일
}
