package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA의 리스너 기능을 활성화
// 데이터가 생성 및 수정되는 것을 추적함
// 이 어노테이션이 메인 클래스와 함께 있어야 @CreatedDate의 기능이 수행됨
@EnableJpaAuditing
@SpringBootApplication
public class Project03Application {

	public static void main(String[] args) {
		SpringApplication.run(Project03Application.class, args);
	}

}
