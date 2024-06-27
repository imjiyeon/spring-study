package com.example.demo.chatbot;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * embeddings api의 응답(답변)을 받기 위한 클래스
 * */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmbeddingResponse {
	String object;
	List<Dt> data;	
}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Dt {
	String object;
	int index;
	double[] embedding;	
}
