package com.example.demo.chatbot;

/*
 * 코사인 유사도를 구하는 클래스
 * */
public class CosineSimilarityCalculator {

	// 두 백터간의 유사도를 구하는 메소드
	public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;

		for (int i = 0; i < vectorA.length; i++) {
			dotProduct += vectorA[i] * vectorB[i];
			normA += Math.pow(vectorA[i], 2);
			normB += Math.pow(vectorB[i], 2);
		}

		normA = Math.sqrt(normA);
		normB = Math.sqrt(normB);

		return dotProduct / (normA * normB);
	}
	
}
