package com.example.demo.qa;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * GPT의 응답(답변)을 받기 위한 클래스
 * */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPTResponse {

	public String id;
	public String object;
	public long created; // 답변 생성 시간
	public String model; // 사용한 모델
	public GPTResponseUsage usage; // 토큰 사용량
	public List<GPTResponseChoice> choices; // 답변 목록
}
