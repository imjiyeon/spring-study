package com.example.demo.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService service;

	@PostMapping("/register")
	public ResponseEntity<Integer> register(@RequestBody OrderDTO dto) {
		int no = service.register(dto);
		return new ResponseEntity<>(no, HttpStatus.CREATED); //201성공코드와 새로운 번호를 반환한다
	}

	//localhost:8080/board/
	@GetMapping("/list")
	public ResponseEntity<List<OrderDTO>> getList() {
		List<OrderDTO> list = service.getList();
		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 목록을 반환한다
	}

	//localhost:8080/board?no=1
	@GetMapping("/read")
	public ResponseEntity<OrderDTO> read(@RequestParam(name = "no") int no) {
		OrderDTO dto = service.read(no);
		return new ResponseEntity<>(dto, HttpStatus.OK); //200성공코드와 정보를 반환한다
	}

	@PutMapping("/modify")
	public ResponseEntity modify(@RequestBody OrderDTO dto) {
		 service.modify(dto);
		 return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
	}

	@DeleteMapping("/remove")
	public ResponseEntity remove(@RequestParam(name = "no") int no) {
		service.remove(no);
		return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
	}

}
