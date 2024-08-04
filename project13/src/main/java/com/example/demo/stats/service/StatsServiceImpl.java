package com.example.demo.stats.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.stats.dto.StatsDTO;
import com.example.demo.stats.entity.Stats;
import com.example.demo.stats.repository.StatsRepository;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	StatsRepository repository;

	@Override
	public LocalDate register(StatsDTO dto) {
		Stats entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getOrderDt();
	}

	@Override
	public List<StatsDTO> getList() {
		List<Stats> entityList = repository.findAll();		
		List<StatsDTO> dtoList = entityList.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());

		return dtoList;
	}

	@Override
	public StatsDTO read(LocalDate date) {
        Optional<Stats> result = repository.findById(date);
        if(result.isPresent()) {
        	Stats order = result.get();
        	return entityToDto(order);
        } else {
        	return null;
        }
	}

}
