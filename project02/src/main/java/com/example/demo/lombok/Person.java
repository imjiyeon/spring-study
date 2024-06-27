package com.example.demo.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@ToString
@NoArgsConstructor //생성자
@AllArgsConstructor //생성자
@Builder //생성자
public class Person {
	
	String name;
	int age;
	
}

/*
 * 1.어노테이션을 사용할 때 에러가 발생한다면 플러그인을 다시 설치해야 한다
 * 2.@Data는 @Getter, @Setter, @ToString을 자동으로 생성해준다
 *   기능이 명시적으로 보이지 않기 때문에 사용하지 않는 게 좋다
 *   @Data보다는 필요한 어노테이션을 각각 선언하는 것을 권장한다
 * */
