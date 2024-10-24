package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
	//s3 키
	@Value("${s3.access-key}")
	private String accessKey;
	@Value("${s3.secret-key}")
	private String secretKey;
	//s3 지역 (한국)
	@Value("${s3.region}")
	private String region;

	//인증정보를 담아서 AmazonS3 객체 생성
	@Bean
	public AmazonS3 amazonS3() {
	    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

	    return AmazonS3ClientBuilder
	        .standard()
	        .withCredentials(new AWSStaticCredentialsProvider(credentials))
	        .withRegion(region)
	        .build();
	}
}
