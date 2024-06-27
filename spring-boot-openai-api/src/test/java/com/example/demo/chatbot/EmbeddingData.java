package com.example.demo.chatbot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 텍스트를 백터로 변환한 데이터를 저장하기 위한 클래스
 * */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddingData {
	
	String text; // 실제 텍스트

	double[] embedding; // 변환된 백터 리스트
}
