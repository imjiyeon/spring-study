package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.stats.entity.Stats;
import com.example.demo.stats.repository.StatsRepository;

@SpringBootTest
public class StatsRepositoryTest {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	StatsRepository statsRepository;

	@Test
	public void 오늘_총주문건수와금액_구하기() {
		
		// 오늘 날짜
		LocalDate now = LocalDate.of(2024, 10, 11);
//		LocalDate now = LocalDate.now();

		// 주문 목록 조회
		List<Order> list = orderRepository.findAll();

		//
		List<Order> filterList = list.stream().filter(entity -> {
			LocalDate orderDt = entity.getOrderDate();
			if (orderDt.equals(now)) {
				return true;
			} else {
				return false;
			}
		}).collect(Collectors.toList());

		for (Order order : filterList) {
			System.out.println(order);
		}

		// 전체 건수와 총금액 구하기
		long count = filterList.stream().count();
		int totalPrice = filterList.stream().mapToInt(e -> e.getPrice()).sum();

		
		Stats stats = Stats.builder()
							.orderDt(now)
							.count(count)
							.totalPrice(totalPrice)
							.build();
		
		statsRepository.save(stats);
	}

	@Test
	public void 목록조회() {
		List<Stats> list = statsRepository.findAll();
		for (Stats entity : list) {
			System.out.println(entity);
		}
	}

	@Test
	public void 단건조회() {
		LocalDate date = LocalDate.of(2024, 10, 11);
		Optional<Stats> optional = statsRepository.findById(date);
		System.out.println(optional.get());
	}

}
