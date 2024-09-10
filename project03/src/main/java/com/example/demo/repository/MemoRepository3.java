package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Memo;

import jakarta.transaction.Transactional;

/*
 * @Query 사용하기
 * */

@Transactional
public interface MemoRepository3 extends JpaRepository<Memo, Integer> {

	/* jpql 사용하기 */
	//메모의 번호가 3보다 작은 데이터 검색
	//select * from tbl_memo where no < ?
	@Query("select m from Memo m where m.no < :mno")
	List<Memo> get1(@Param("mno") int mno);
	
	//메모의 내용이 없는 데이터 검색
	//select * from tbl_memo where text is not null
	@Query("select m from Memo m where m.text is not null")
	List<Memo> get2();
	
	//메모의 번호가 2에서 3 사이인 데이터 검색
	//select * from tbl_memo where no between ? and ?
	@Query("select m from Memo m where m.no between :from and :to")
	List<Memo> get3(@Param("from") int from, @Param("to") int to);
	
	/* 순수한 쿼리 사용하기 */
	//메모의 번호를 기준으로 역정렬
	//select * from tbl_memo order by no desc
	@Query(value = "select * from tbl_memo order by no desc", nativeQuery = true)
	List<Memo> get4();
	
	@Query(value = "delete from tbl_memo where no = :mno", nativeQuery = true)
	void delete1(@Param("mno") int mno);
	
	// 객체 파라미터
	@Modifying
	@Query(value = "update tbl_memo set text = :#{#param.text} where no = :#{#param.no}", nativeQuery = true)
	int update1(@Param("param") Memo memo);
	
}

