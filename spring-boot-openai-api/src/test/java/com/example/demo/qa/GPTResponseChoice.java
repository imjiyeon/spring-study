package com.example.demo.qa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * GPT의 응답(답변)을 받기 위한 클래스
 * */

// 답변
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPTResponseChoice {
	public int index;
	public ChatGPTRequestMsg message;
}
