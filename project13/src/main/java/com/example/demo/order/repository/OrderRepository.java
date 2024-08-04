package com.example.demo.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
