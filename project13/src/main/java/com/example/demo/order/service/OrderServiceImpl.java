package com.example.demo.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repository;

	@Override
	public int register(OrderDTO dto) {
		Order entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getNo();
	}

	@Override
	public List<OrderDTO> getList() {
		List<Order> entityList = repository.findAll();		
		List<OrderDTO> dtoList = entityList.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());

		return dtoList;
	}

	@Override
	public OrderDTO read(int no) {
        Optional<Order> result = repository.findById(no);
        if(result.isPresent()) {
        	Order order = result.get();
        	return entityToDto(order);
        } else {
        	return null;
        }
	}

	@Override
	public void modify(OrderDTO dto) {
        Optional<Order> result = repository.findById(dto.getNo());
        if(result.isPresent()){
        	Order entity = result.get();
            entity.setProductName(dto.getProductName());
            entity.setPrice(dto.getPrice());
            repository.save(entity);
        }
	}

	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}

}
