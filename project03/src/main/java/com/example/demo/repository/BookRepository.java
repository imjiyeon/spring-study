package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	// 책의 제목이 '자바프로그래밍입문'인 데이터 검색
	// SELECT * FROM tbl_book WHERE title = '자바프로그래밍입문'; // 1.SQL을 그대로 작성한다
	@Query(value = "select * from tbl_book where title = :title", nativeQuery = true) // 3.파라미터 처리한다
	List<Book> get1(@Param("title") String title); // 2.파라미터를 매개변수로 선언한다

	// 책의 가격이 30000만원이상이고 출판사가 남가람북스인 데이터 검색
	// SELECT * FROM tbl_book WHERE price > 30000 AND publisher = '남가람북스'
	@Query(value = "SELECT * FROM tbl_book WHERE price > :price AND publisher = :publisher", nativeQuery = true)
	List<Book> get2(@Param("price") int price, @Param("publisher") String publisher);

	// 책의 출판사가 한빛출판사이거나 이지스퍼블리싱인 데이터 검색
	// SELECT * FROM tbl_book WHERE publisher IN ('한빛출판사','이지스퍼블리싱')
	@Query(value = "SELECT * FROM tbl_book WHERE publisher IN (:publisher1,:publisher2)", nativeQuery = true)
	List<Book> get3(@Param("publisher1") String publisher1, @Param("publisher2") String publisher2);

}
