package com.example.demo.job;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.order.repository.OrderRepository;

@Component
public class SampleScheduler {

	// cron: 주기 (매분 0초가 될때마다 실행)
	@Scheduled(cron = "0 * * * * * ")
	public void test() {
		System.out.println("task run......." + LocalDateTime.now());
	}

//	// 10초간격으로 실행
//	@Scheduled(cron = "0/10 * * * * * ")
//	public void test() {
//		System.out.println("task run......." + LocalDateTime.now());
//	}

}