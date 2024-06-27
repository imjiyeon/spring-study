package com.example.demo.chatbot;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class GPTTest {

	// 개인 인증키를 사용하여 API 인증
	@Value("${apikey}")
	String secretKey;

	@Autowired
	ChatGPT chatGPT;

	@Test
	public void 텍스트를백터로변환하기() throws IOException {

		// API 주소와 모델
		String model = "text-embedding-ada-002";
		String apiURL = "https://api.openai.com/v1/embeddings";

		// 텍스트 입력
		String text = "안녕하세요";

		// http 통신을 위해 RestTemplate 생성
		RestTemplate restTemplate = new RestTemplate();

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

		// 백터 리스트를 꺼내서 출력
		double[] embedding = response.data.get(0).embedding;

		System.out.println("텍스트: " + text);
		System.out.println("변환된 백터: " + Arrays.toString(embedding));

	}

	@Test
	public void 텍스트유사도계산하기() throws Exception {

		String text1 = "아무것도 안먹었더니 꼬르륵 소리가 나네";
		String text2 = "저기 배가 지나가네요";
		String text3 = "굶어서 허기가 지네요";
		String text4 = "허기라는 게임을 즐거워";

		// 텍스트를 백터로 변환하기
		double[] embedding1 = chatGPT.CallEmbeddingApi(text1);
		double[] embedding2 = chatGPT.CallEmbeddingApi(text2);
		double[] embedding3 = chatGPT.CallEmbeddingApi(text3);
		double[] embedding4 = chatGPT.CallEmbeddingApi(text4);

		// 텍스트 간의 유사도 구하기
		double similarity11 = CosineSimilarityCalculator.cosineSimilarity(embedding1, embedding2);
		double similarity22 = CosineSimilarityCalculator.cosineSimilarity(embedding1, embedding3);
		double similarity33 = CosineSimilarityCalculator.cosineSimilarity(embedding1, embedding4);

		System.out.println("백터1과 백터2의 유사도: " + similarity11);
		System.out.println("백터1과 백터3의 유사도: " + similarity22);
		System.out.println("백터1과 백터4의 유사도: " + similarity33);

	}

	@Test
	public void 유사도가높은정책찾기() throws Exception {

		// csv 파일에서 정책 목록 가져오기
		List<EmbeddingData> list = FileUtils.readEmbeddingCsv();

		// 질문 입력
		String question = "신혼 부부를 위한 서울 정책이 궁금해";

//		String question = "아르바이트를 위한 서울 정책이 궁금해";

		// 질문을 백터로 변환하기
		double[] inputEmbeddings = chatGPT.CallEmbeddingApi(question);

		TreeMap<Integer, Double> map = new TreeMap<>();

		for (int i = 0; i < list.size(); i++) {

			EmbeddingData data = list.get(i);

			// 정책의 백터값 꺼내기
			double[] pageEmbeddings = data.embedding;

			// 질문과 정책의 유사도 계산하기
			double similarity = CosineSimilarityCalculator.cosineSimilarity(inputEmbeddings, pageEmbeddings);

			System.out.println("질문과" + i + "문서 사이의 유사도: " + similarity);

			map.put(i, similarity);
		}

		// map에서 유사도를 기준으로 정렬하고, 유사도가 제일 높은 문서 3개를 선택
		List<Map.Entry<Integer, Double>> entry = map.entrySet().stream()
				.sorted(Map.Entry.<Integer, Double>comparingByValue(Comparator.reverseOrder())).limit(2)
				.collect(Collectors.toList());

		int index1 = entry.get(0).getKey();
		int index2 = entry.get(1).getKey();

		System.out.println(index1 + "번 문서");
		System.out.println(list.get(index1).getText());
		System.out.println();
		System.out.println(index2 + "번 문서");
		System.out.println(list.get(index2).getText());
		System.out.println();

	}

	@Test
	public void 챗봇에게질문하기() throws Exception {

		// 질문
		String question = "신혼 부부 임차보증금 지원정책의 지원 요건은?";
		
		// 답변 구하기
		String answer = chatGPT.generateReponse(question);
		
		System.out.println("사용자: " + question);
		
		System.out.println();
		
		System.out.println("답변: " + answer);
	}

}
