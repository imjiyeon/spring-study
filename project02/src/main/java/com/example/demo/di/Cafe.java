package com.example.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Spring이 Cafe 객체를 생성하고 컨테이너에 저장함
@Component
public class Cafe {

	// 컨테이너에 저장된 Manager 객체(빈)을 꺼내서 주입
	@Autowired
	Manager manager;

}
