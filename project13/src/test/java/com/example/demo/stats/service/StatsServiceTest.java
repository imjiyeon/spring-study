package com.example.demo.stats.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stats.dto.StatsDTO;

@SpringBootTest
public class StatsServiceTest {

	@Autowired
	StatsService service;

	@Test
	public void 데이터추가() {
		LocalDate date = LocalDate.of(2024, 8, 4);
		StatsDTO dto = StatsDTO.builder().orderDt(date).count(10).totalPrice(1000).build();	
		service.register(dto);
	}
	
	@Test
	public void 목록조회() {
		List<StatsDTO> list = service.getList();
		for(StatsDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 단건조회() {
		LocalDate date = LocalDate.of(2024, 8, 4);
		StatsDTO dto = service.read(date);
		System.out.println(dto);
	}

}
