package com.example.demo.qa;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * GPT의 응답(답변)을 받기 위한 클래스
 * */

// 토큰 사용량
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
class GPTResponseUsage {
	@JsonProperty("prompt_tokens")
	public int promptTokens;
	@JsonProperty("completion_tokens")
	public int completionTokens;
	@JsonProperty("total_tokens") // JSON문자열과 필드를 매칭시키기 위한 텍스트
	public int totalTokens; // 전체사용량
}
