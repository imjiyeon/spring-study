package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;


@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository repository;

	@Test
	public void 어제날짜로_주문() {
		LocalDate date = LocalDate.of(2024, 10, 10);
		Order order = Order.builder()
							.productName("무선 이어폰")
							.price(10000)
							.orderDate(date)
							.build();
		Order order2 = Order.builder()
							.productName("블루투스 스피커")
							.price(20000)
							.orderDate(date)
							.build();
		repository.save(order);
		repository.save(order2);
	}
	
	@Test
	public void 오늘날짜로_주문() {
		LocalDate date = LocalDate.of(2024, 10, 11);
		Order order = Order.builder()
							.productName("스마트 워치")
							.price(30000)
							.orderDate(date)
							.build();
		Order order2 = Order.builder()
							.productName("노트북")
							.price(40000)
							.orderDate(date)
							.build();
		Order order3 = Order.builder()
							.productName("게이밍 마우스")
							.price(50000)
							.orderDate(date)
							.build();
		repository.save(order);
		repository.save(order2);
		repository.save(order3);
	}
	
	@Test
	public void 어제들어온_주문삭제() {
		
		// 현재 날짜 구하기
		LocalDate now = LocalDate.of(2024, 10, 11);
//		LocalDate now = LocalDate.now(); 
		// 어제 날짜 구하기
		LocalDate yesterday = now.minusDays(1);
		
//		// 주문 목록 조회
//		List<Order> list = repository.findAll();
//
//		// 전날 들어온 주문이력을 찾아서 삭제
//		list.stream().forEach(entity -> {
//			int no = entity.getNo();
//			LocalDate orderDate = entity.getOrderDate();
//
//			if (orderDate.equals(yesterday)) {
//				repository.deleteById(no);
//				System.out.println(no + " remove..");
//			}
//		});

		// 전날 들어온 주문이력을 찾아서 삭제
		repository.removeByOrderDate(yesterday);
		
	}
	
}
