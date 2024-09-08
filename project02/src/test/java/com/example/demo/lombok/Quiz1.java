package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//  1) 다음과 같이 도서(Book)클래스을 설계하세요
//  속성: 제목, 가격, 출판사, 페이지수
//  기능: 모든 멤버변수의 getter/setter, 디폴트생성자, 모든 멤버변수를 초기화하는 생성자, 도서정보를 반환하는 메소드
//  2) 도서 인스턴스를 3개 생성하세요 (디폴트생성자, 전체생성자, 빌더를 각각 사용하여 생성)
//  3) 모든 도서의 정보를 출력하세요

@SpringBootTest //스프링 컨테이너 환경
public class Quiz1 {

	@Test //단위테스트
	void 도서클래스생성테스트() {
		Book book1 = new Book();
		book1.setTitle("자바프로그래밍입문");
		book1.setPrice(20000);
		book1.setCompany("한빛미디어");
		book1.setPage(300);
		System.out.println(book1.getTitle());
		System.out.println(book1.getPrice());
		System.out.println(book1.getCompany());
		System.out.println(book1.getPage());
		
		Book book2 = new Book("스프링부트",30000,"남가락북스",400);
		System.out.println(book2.toString());
		
		Book book3 = Book.builder().title("엘라스틱서치").price(150).company("책만").page(350).build();
		System.out.println(book3.toString());
	}
}
