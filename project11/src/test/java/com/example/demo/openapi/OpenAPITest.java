package com.example.demo.openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class OpenAPITest {

	String serviceKey = "EHoOALnbGNikccSKc4nAdZmHeHC%2FmKtbBxnDLAsfTeb1ehsiB01tiQpb8t1nqnTIiz43e1tCk5EHGNo%2FY2rhZw%3D%3D";
	String dataType = "JSON";
	String code = "11B20201";

	//날씨 예보 API를 테스트하는 메소드
	@Test
	public void getWeather() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8"));
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());

//		return sb.toString();
	}

	/*//json문자열을 클래스로 변환하는 메소드 만들기
	@Test
	public void jsonToDto() throws IOException {

		//json 문자열을 클래스로 변환해주는 매퍼 클래스 생성
		ObjectMapper mapper = new ObjectMapper();

		//분석할 수 없는 구문을 무시하는 옵션 설정
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		//날씨 데이터 가져오기
		String weather = getWeather();

		Root root = null;

		//JSON 문자열을 클래스로 변환
		root = mapper.readValue(weather, Root.class);

		// 결과 코드만 추출
		System.out.println(root.response.header.resultCode);

		// 결과 메세지만 추출
		System.out.println(root.response.header.resultMsg);
	}*/

}
