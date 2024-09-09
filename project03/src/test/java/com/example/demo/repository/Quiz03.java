package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;

/*
 * Order엔티티클래스와 OrderReposiotry인터페이스를 이용하여 다음과 같이 테이블을 만드세요.
 * 다음과 같이 테이블에 데이터를 추가하세요.
 * 그리고 테이블에 데이터를 조회, 수정, 삭제 하세요.
 * */
@SpringBootTest
public class Quiz03 {
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void 데이터일괄등록() {

		List<Order> list = new ArrayList<>();
		//of함수: 생성자 대신 LocalDate의 객체를 생성하는 함수

		LocalDate localDate1 = LocalDate.of(2023,7,1);
		LocalDate localDate2 = LocalDate.of(2023,7,2);
		LocalDate localDate3 = LocalDate.of(2023,7,3);

		Order order1 = new Order(0,"둘리","인천 구월동", localDate1);
		Order order2 = new Order(0,"또치","인천 연수동",localDate2);
		Order order3 = new Order(0,"도우너","부산 동래동",localDate3);
		Order order4 = new Order(0,"마이콜",null,localDate1);
		Order order5 = new Order(0,"고길동",null,localDate2);

		list.add(order1);
		list.add(order2);
		list.add(order3);
		list.add(order4);
		list.add(order5);

		orderRepository.saveAll(list);
	}

	@Test
	public void 데이터단건조회() {
		Optional<Order> result = orderRepository.findById(1);
		if(result.isPresent()) {
			Order order = result.get();
			System.out.println(order);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<Order> list = orderRepository.findAll();
		for(Order order : list) {
			System.out.println(order);
		}
	}

	@Test
	public void 데이터수정() {
		Optional<Order> result = orderRepository.findById(1);
		Order order = result.get();
		order.setShipAddress("서울 신림동");
		orderRepository.save(order);
	}

	@Test
	public void 데이터삭제() {
		orderRepository.deleteById(1);
	}

}
