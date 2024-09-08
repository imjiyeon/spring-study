package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestaurantTest {

	@Autowired //컨테이너에서 빈을 찾아서 필드에 주입
	Restaurant restaurant;

	@Autowired
	Chef chef;

	@Test
	void 테스트() {

		System.out.println("restaurant: " + restaurant);
		
		System.out.println("chef: " + chef);
		
		System.out.println("getChef(): " + restaurant.getChef());

	}

}

//의존성 주입이 실패하는 원인
//1. 객체를 생성하지 않았을 때 (@Component가 없으면)
//2. 주입을 받지 않았을 때 (@Autowired가 없으면)
