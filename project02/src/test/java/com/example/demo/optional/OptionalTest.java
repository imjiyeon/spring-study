package com.example.demo.optional;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Optional: 값을 감싸고 있는 wrapper 클래스
// 객체에 값이 있는지 없는지를 확인하기 위해 사용
// null을 다룰때 발생하는 오류를 방지할 수 있다

@SpringBootTest
public class OptionalTest {
	
	@Test
	void Optional사용하기() {

		String str = null;
//		String str = "apple";
		Optional<String> opt = Optional.of(str); //값 저장

		System.out.println("Optional에서 값 꺼내기: " + opt.get());
		System.out.println("Optional 안에 값이 있는지? " + opt.isEmpty());
		System.out.println("Optional 안에 값이 없는지? " + opt.isPresent());

		//Optional에 값이 없으면, 바나나로 대체
		String str2 = null;
		System.out.println(Optional.ofNullable(str2).orElse("banana"));
	}

	@Test 
	void of와ofNullable의차이(){
		//of: Optional을 생성하는 함수. null 입력 불가
		//ofNullable: Optional을 생성하는 함수. null 입력 가능
		
		String str = null;
		Optional<String> opt1 = Optional.of(str); //of메소드로 null값을 입력받으면 에러남
//		Optional<String> opt2 = Optional.ofNullable(str);
	}
	
	@Test
	void 빈객체를사용하는경우() {
		Optional<String> opt = Optional.ofNullable(null);
		System.out.println(opt.get());//에러남

		//if를사용하여null값체크하기
		String str = "banana";
		if(str != null) {
			System.out.println("값이 존재합니다");
		}

		//optinal을사용하여null값체크하기
		Optional<String> opt2 = Optional.ofNullable(str);
		opt2.ifPresent(name -> System.out.println("값이 존재합니다")); //람다식 사용
	}

}
