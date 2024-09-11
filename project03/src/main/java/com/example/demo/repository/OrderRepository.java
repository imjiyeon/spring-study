package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	// 순수SQL
	// 주소지가 '인천'인 데이터 검색
	// select * from tbl_order where ship_address like '인천%';
	@Query(value = "select * from tbl_order where ship_address like :address%", nativeQuery = true)
	List<Order> get1(@Param("address") String address);

	// 주문일이 '7월3일'인 데이터 검색
	// select * from tbl_order where order_date = '2023-07-03';
	@Query(value = "select * from tbl_order where order_date = :orderDate", nativeQuery = true)
	List<Order> get2(@Param("orderDate") LocalDate orderDate);
	
}
