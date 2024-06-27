package com.example.demo.chatbot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.qa.ChatGPTRequestMsg;
import com.example.demo.qa.GPTRequest;
import com.example.demo.qa.GPTResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * ChatGPT의 API를 호출하는 클래스
 * 
 * 외부파일에서 key값을 가져오기 위해서, 해당 클래스는 컴포넌트로 만들었음
 * 스프링 컨테이너가 실행되는 시점에 값을 주입받음
 * */
@Component
public class ChatGPT {

	@Value("${apikey}")
	String secretKey;

	// 문자열을 백터로 변환하는 embeddings API를 호출하는 메소드
	public double[] CallEmbeddingApi(String text) throws JsonMappingException, JsonProcessingException {

		// http 통신을 위해 RestTemplate 생성
		RestTemplate restTemplate = new RestTemplate();

		// API 주소와 모델
		String model = "text-embedding-ada-002";
		String apiURL = "https://api.openai.com/v1/embeddings";

		// 메세지 헤더에 API키 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(secretKey);

		// 바디 데이터
		Map<String, Object> body = Map.of("model", model, "input", text);

		// 헤더와 바디 데이터 담기
		HttpEntity<Map> requestEntity = new HttpEntity<>(body, headers);

		// api 호출
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity,
				String.class);

		// json 문자열을 클래스로 변환해주는 매퍼 클래스 생성
		ObjectMapper mapper = new ObjectMapper();

		// 분석할 수 없는 구문을 무시하는 옵션 설정
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// JSON형식의 응답데이터를 객체로 변환
		EmbeddingResponse response = mapper.readValue(responseEntity.getBody(), EmbeddingResponse.class);

		// 백터 리스트를 꺼내서 반환
		return response.data.get(0).embedding;
	}

	// 질문과 유사도가 제일 높은 정책을 반환하는 메소드
	public String findPolicy(String question) throws Exception {

		// csv 파일에서 정책 목록 가져오기
		List<EmbeddingData> list = FileUtils.readEmbeddingCsv();

		// 질문을 백터로 변환하기
		double[] inputEmbeddings = CallEmbeddingApi(question);

		TreeMap<Integer, Double> map = new TreeMap<>();

		for (int i = 0; i < list.size(); i++) {

			EmbeddingData data = list.get(i);

			// 정책의 백터값 꺼내기
			double[] pageEmbeddings = data.embedding;

			// 질문과 정책의 유사도 계산하기
			double similarity = CosineSimilarityCalculator.cosineSimilarity(inputEmbeddings, pageEmbeddings);

//			System.out.println("질문과" + i + "문서 사이의 유사도: " + similarity);

			map.put(i, similarity);
		}

		// map에서 유사도를 기준으로 정렬하고, 유사도가 제일 높은 문서 2개를 선택
		List<Map.Entry<Integer, Double>> entry = map.entrySet().stream()
				.sorted(Map.Entry.<Integer, Double>comparingByValue(Comparator.reverseOrder())).limit(2)
				.collect(Collectors.toList());

		int index1 = entry.get(0).getKey();

		// 제일 유사도가 높은 정책을 반환
		return list.get(index1).getText();
	}

	// 챗봇의 답변을 만드는 메소드
	public String generateReponse(String question) throws Exception {

		// API 주소와 모델
		String model = "gpt-3.5-turbo";
		String apiURL = "https://api.openai.com/v1/chat/completions";

		// 필요한 파라미터(질문) 작성
		List<ChatGPTRequestMsg> msglist = new ArrayList<>();

		// 질문에 맞는 정책 찾기
		String policy = findPolicy(question);

		// 요청 메세지 생성
		msglist.add(new ChatGPTRequestMsg("system", policy)); // 정책에대한 정보
		msglist.add(new ChatGPTRequestMsg("user", question)); // 사용자 질문

		GPTRequest requestmsg = new GPTRequest(model, msglist, 500);

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

		// 챗봇의 답변을 반환
		return gptResponse.choices.get(0).message.getContent();
	}

}
