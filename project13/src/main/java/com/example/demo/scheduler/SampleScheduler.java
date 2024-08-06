package com.example.demo.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderService;

@Component
public class SampleScheduler {

	@Autowired
	OrderService orderService;

//	// cron: 주기 (매분 0초가 될때마다 실행)
//	@Scheduled(cron = "0 * * * * * ")
//	public void test() {
//		System.out.println("task run.......");
//	}	

	// 10초간격으로 실행
	@Scheduled(cron = "0/10 * * * * ? ")
	public void test() {
		System.out.println("task run.......");
	}

//	// 매일 1시가 될때마다 실행
//	@Scheduled(cron = "0 0 1 * * * ")
//	public void removeOrderHistory() {
//		System.out.println("task run.......");
//		List<OrderDTO> list = orderService.getList();
//		
//		LocalDate now = LocalDate.now(); // 현재 날짜를 구함
//		LocalDate yesterday = now.minusDays(1); // 전날을 구함
//		
//		// 전날 들어온 주문이력을 찾아서 삭제
//		list.stream().forEach(dto -> {
//			if (dto.getRegDate().toLocalDate().equals(yesterday)) {
//				System.out.println(dto);
//				orderService.remove(dto.getNo());
//				System.out.println(dto.getNo() + " remove..");
//			}
//		});
//	}

}
