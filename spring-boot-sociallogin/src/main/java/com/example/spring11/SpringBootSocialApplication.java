package com.example.spring11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSocialApplication.class, args);
	}

}
