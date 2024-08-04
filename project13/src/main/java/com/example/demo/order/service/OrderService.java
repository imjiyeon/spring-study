package com.example.demo.order.service;

import java.util.List;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.entity.Order;

public interface OrderService {

	int register(OrderDTO dto);

	List<OrderDTO> getList();

	OrderDTO read(int no); //상세 조회

	void modify(OrderDTO dto); //수정

	void remove(int no); //삭제

	default Order dtoToEntity(OrderDTO dto) {
		Order entity = Order.builder()
				.no(dto.getNo())
				.productName(dto.getProductName())
				.price(dto.getPrice())
				.build();
		return entity;
	}

	default OrderDTO entityToDto(Order entity) {
		OrderDTO dto = OrderDTO.builder()
				.no(entity.getNo())
				.productName(entity.getProductName())
				.price(entity.getPrice())
				.modDate(entity.getModDate())
				.regDate(entity.getRegDate())
				.build();
		return dto;
	}

}
