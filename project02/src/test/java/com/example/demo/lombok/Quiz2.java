package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//  1) 다음과 같이 자동차(Car) 클래스을 설계하세요
//  속성: 모델명, 제조사, 색
//  기능: 모든 멤버변수의 getter/setter, 디폴트생성자, 모든 멤버변수를 초기화하는 생성자, 자동차 정보를 반환하는 메소드
//  2) 자동차 인스턴스를 3개 생성하세요
//  3) 모든 자동차의 정보를 출력하세요

@SpringBootTest
public class Quiz2 {

	@Test //단위테스트
	void 자동차클래스생성테스트() {
		Car car1 = new Car();
		car1.setModel("소나타");
		car1.setCompany("현대");
		car1.setColor("블랙");
		System.out.println(car1.getModel());
		System.out.println(car1.getCompany());
		System.out.println(car1.getColor());
		
		Car car2 = new Car("베뉴","현대","그레이");
		System.out.println(car2);
				
		Car car3 = Car.builder().model("쏘렌토").company("기아").color("레드").build();
		System.out.println(car3);
	}
}
