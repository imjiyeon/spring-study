package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {
	
	  @Select("SELECT SYSDATE FROM DUAL")
	  public String getTime1(); //어노테이션으로 SQL문 사용
	  
	  public String getTime2(); //mapper xml파일로 SQL문 사용

	  public String paramTest1(int empNo); //파라미터 1개 전달하기
	  
	  public String paramTest2(@Param("empNo") int empNo, @Param("empName") String empName); //파라미터 여러개 전달하기
	  
	  public String paramTest3(String columnNm); //상수값 전달하기
	  
	  public HashMap<String,String> returnTest1(int empNo); //결과값을 맵타입으로 리턴받기
	  
	  public ArrayList<HashMap<String,String>> returnTest2(); //결과값을 리스트로 리턴받기
	  
	  public ArrayList<HashMap<String,String>> ifTest(int empNo); //조건식 사용하기
	  
	  public ArrayList<HashMap<String,String>> chooseTest(@Param("empNo") int empNo, @Param("empName") String empName);
		
}

