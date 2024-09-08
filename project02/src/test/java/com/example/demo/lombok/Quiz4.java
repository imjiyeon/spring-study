package com.example.demo.lombok;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//  1) 다음과 같이 영화(Movie) 클래스을 설계하세요
//  속성: 제목, 감독, 개봉일
//  기능: 모든 멤버변수의 getter/setter, 디폴트생성자, 모든 멤버변수를 초기화하는 생성자, 영화 정보를 반환하는 메소드
//  2) 영화 인스턴스를 3개 생성하세요
//  3) 모든 영화의 정보를 출력하세요
 
@SpringBootTest
public class Quiz4 {

	@Test
	void 영화클래스생성테스트() {
		Movie movie = new Movie();
		movie.setTitle("서울의 봄");
		movie.setDirector("김성수");
		movie.setDate(LocalDate.of(2023, 11, 22)); // 시간 생성
		System.out.println(movie.getTitle());
		System.out.println(movie.getDirector());
		System.out.println(movie.getDate());

		// 개봉일 미정
		Movie movie2 = new Movie("3일의 휴가", "육상효", null);
		System.out.println(movie2);

		// builder는 필요한 정보만 입력할 수 있음
		Movie movie3 = Movie.builder().title("리틀 포레스트").director("임순례").build();
		System.out.println(movie3);
	}
}
