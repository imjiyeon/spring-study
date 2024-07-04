package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class TestMapperTest {

	@Autowired
	private TestMapper mapper;
	
	@Test
	public void 현재시간함수_사용하기1() { //데이터베이스 접속 확인
		String date = mapper.getTime1(); //어노테이션으로 SQL문 사용
		log.info(date);
	}
	
	@Test
	public void 현재시간함수_사용하기2() {
		String date = mapper.getTime2(); //XML파일을 생성해서 SQL문 사용
		log.info(date);
	}
	
	@Test
	public void 파라미터_1개_전달하기() {
		String empName = mapper.paramTest1(1001);
		log.info(empName);
	}
	
	@Test
	public void 파라미터_여러개_전달하기() { 
		String empName = mapper.paramTest2(1001, "정소화");
		log.info(empName);
	}
	
	@Test
	public void 파라미터로_상수값_전달하기() {
		//#{} ${}의 차이
		//String result = mapper.paramTest3("EMP_NAME"); //값이 상수로 넘어가서 select 문의 컬럼으로 사용됨
		String result = mapper.paramTest3("EMP_NO");
		log.info(result);
	}
	
	@Test
	public void 리턴타입으로_MAP반환받기() {
		HashMap<String,String> map = mapper.returnTest1(1001);
		log.info(map.toString());
	}
	
	@Test
	public void 리턴타입으로_리스트반환받기() {
		ArrayList<HashMap<String,String>> list = mapper.returnTest2();
		log.info(list.toString());
	}
	
	@Test
	public void IF문_테스트() { //조건식 결과에따라 쿼리가 동적으로 생성됨
		ArrayList<HashMap<String,String>> list = mapper.ifTest(0); //거짓
//		ArrayList<HashMap<String,String>> list = mapper.ifTest(1001); //참
		log.info(list.toString());
	}
	
	@Test
	public void CHOOSE문_테스트() { //조건식 결과에따라 쿼리가 동적으로 생성됨
		ArrayList<HashMap<String,String>> list = mapper.chooseTest(0, null);
//		ArrayList<HashMap<String,String>> list = mapper.chooseTest(1001, null);
//		ArrayList<HashMap<String,String>> list = mapper.chooseTest(0, "정소화");
		log.info(list.toString());
	}
}
