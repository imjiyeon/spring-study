package com.example.demo.stats.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.stats.entity.Stats;


@SpringBootTest
public class StatsRepositoryTest {

	@Autowired
	StatsRepository repository;

	@Test
	public void 데이터추가() {
		
		LocalDate date = LocalDate.of(2024, 8, 4);
		Stats stats = Stats.builder().orderDt(date).count(10).totalPrice(1000).build();	
		repository.save(stats);
		
	}
	
	@Test
	public void 목록조회() {
		List<Stats> list = repository.findAll();
		for(Stats entity : list) {
			System.out.println(entity);
		}
	}
	
	@Test
	public void 단건조회() {
		LocalDate date = LocalDate.of(2024, 8, 4);
		Optional<Stats> optional = repository.findById(date);
		System.out.println(optional.get());
	}
	
}
