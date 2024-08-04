package com.example.demo.stats.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.stats.dto.StatsDTO;
import com.example.demo.stats.entity.Stats;

public interface StatsService {

	LocalDate register(StatsDTO dto);

	List<StatsDTO> getList();

	StatsDTO read(LocalDate date); //상세 조회

	default Stats dtoToEntity(StatsDTO dto) {
		Stats entity = Stats.builder()
				.orderDt(dto.getOrderDt())
				.count(dto.getCount())
				.totalPrice(dto.getTotalPrice())
				.build();
		return entity;
	}

	default StatsDTO entityToDto(Stats entity) {
		StatsDTO dto = StatsDTO.builder()
				.orderDt(entity.getOrderDt())
				.count(entity.getCount())
				.totalPrice(entity.getTotalPrice())
				.modDate(entity.getModDate())
				.regDate(entity.getRegDate())
				.build();
		return dto;
	}

}
