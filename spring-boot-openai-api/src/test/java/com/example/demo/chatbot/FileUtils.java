package com.example.demo.chatbot;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

public class FileUtils {

	// csv 파일 경로
	static String csvFilepath = "D:\\0.tool\\spring-workspace\\spring-study\\spring-boot-openai-api\\data\\embedding.csv";

	// 엑셀 파일을 읽어오는 메소드
	public static List<EmbeddingData> readEmbeddingCsv() throws Exception {

		// csv 리더 생성
		CSVReader csvReader = new CSVReader(new FileReader(csvFilepath));

		String[] line;

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 리스트에 정책내용과 백터값 담기
		List<EmbeddingData> list = new ArrayList<>();

		while ((line = csvReader.readNext()) != null) {
			double[] embedding = mapper.readValue(line[1], double[].class);
			list.add(new EmbeddingData(line[0], embedding));
		}

		// 리스트 반환
		return list;
	}

}
