package com.example.demo.qa;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * GPT에게 요청을 보내기 위한 클래스
 * */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GPTRequest {
	
	String model; // 모델명(필수)
	List<ChatGPTRequestMsg> messages; //질문(필수)
	@JsonProperty("max_tokens")
	int maxToken; // 최대토큰수(선택)

}
