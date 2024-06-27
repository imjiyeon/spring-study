package com.example.demo.qa;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * GPT API 호출 테스트 클래스
 * */

@SpringBootTest
public class GPTTest {

	// API 주소와 모델
	String apiURL = "https://api.openai.com/v1/chat/completions";
	
	String model = "gpt-3.5-turbo";

	// 개인 인증키를 사용하여 API 인증
	@Value("${apikey}")
	String secretKey;
	
	@Test
	public void GPTAPI호출() throws JsonMappingException, JsonProcessingException {

		// 필요한 파라미터(질문) 작성
		List<ChatGPTRequestMsg> msglist = new ArrayList<>();
//		msglist.add(new ChatGPTRequestMsg("user","자바가 뭐야?"));
		msglist.add(new ChatGPTRequestMsg("user", "안녕"));

		// 요청 메세지 생성
		GPTRequest requestmsg = new GPTRequest(model, msglist, 300);

		// http 메세지 헤더에 API키와 미디어타입 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(secretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// http 통신을 위해 RestTemplate 생성 및 설정
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<GPTRequest> requestEntity = new HttpEntity<>(requestmsg, headers);

		// API 호출
		ResponseEntity<String> response = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity, String.class);

		// API 응답 처리
		// json 문자열을 클래스로 변환해주는 매퍼 클래스 생성
		ObjectMapper mapper = new ObjectMapper();

		// 분석할 수 없는 구문을 무시하는 옵션 설정
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// JSON 문자열을 클래스로 변환
		GPTResponse gptResponse = mapper.readValue(response.getBody(), GPTResponse.class);
		
		// GPT 답변 출력
		for(GPTResponseChoice choice : gptResponse.choices) {
			System.out.println(choice.message);
		}
		
		// 토큰 사용량 출력
		System.out.println("사용한 토큰량:" + gptResponse.usage.totalTokens);

	}

}
