package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.entity.Book;
import com.example.demo.entity.Gift;

/*
 * Gift엔티티클래스와 GiftReposiotry인터페이스를 이용하여 다음과 같이 테이블을 만드세요.
 * 다음과 같이 테이블에 데이터를 추가하세요.
 * 그리고 테이블에 데이터를 조회, 수정, 삭제 하세요.
 * */
@SpringBootTest
public class Quiz02 {
	@Autowired
	GiftRepository giftRepository;

	@Test
	public void 데이터일괄등록() {
		List<Gift> list = new ArrayList<>();
		
		// 선물번호는 자동으로 생략되므로 no는 생략 
		Gift gift1 = Gift.builder().name("참치세트").type("식품").price(10000).build();
		Gift gift2 = Gift.builder().name("햄세트").type("식품").price(20000).build();
		Gift gift3 = Gift.builder().name("샴푸세트").type("생활용품").price(30000).build();
		Gift gift4 = Gift.builder().name("세차용품").type("생활용품").price(40000).build();
		Gift gift5 = Gift.builder().name("주방용품").type("생활용품").price(50000).build();
		Gift gift6 = Gift.builder().name("노트북").type("가전제품").price(60000).build();
		Gift gift7 = Gift.builder().name("벽걸이TV").type("가전제품").price(70000).build();
		
		list.add(gift1);
		list.add(gift2);
		list.add(gift3);
		list.add(gift4);
		list.add(gift5);
		list.add(gift6);
		list.add(gift7);
		
		giftRepository.saveAll(list);
	}

	@Test
	public void 데이터단건조회() {
		Optional<Gift> result = giftRepository.findById(1);
		if (result.isPresent()) {
			Gift gift = result.get();
			System.out.println(gift);
		}
	}

	@Test
	public void 데이터전체조회() {
		List<Gift> list = giftRepository.findAll();
		for (Gift gift : list) {
			System.out.println(gift);
		}
	}

	@Test
	public void 데이터수정() {
		Optional<Gift> result = giftRepository.findById(1);
		Gift gift = result.get();
		gift.setPrice(100000);
		giftRepository.save(gift);
	}

	@Test
	public void 데이터삭제() {
		giftRepository.deleteById(1);
	}

	@Test
	public void 데이터전체삭제() {
		giftRepository.deleteAll();
	}
}
