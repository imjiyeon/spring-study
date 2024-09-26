package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //리스너 기능 활성화 시키기
public class Project07Application {

	public static void main(String[] args) {
		SpringApplication.run(Project07Application.class, args);
	}

}
