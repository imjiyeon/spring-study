package com.example.demo.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * mybatis를 사용하지 않고 순수한 자바코드로
 * 데이터베이스에 접속하고 SQL을 전달하여 처리결과 받기
 * */

@SpringBootTest
public class DatabaseTest {

	@Test
	public void mybatis없이sql사용하기() {
		Connection conn = null;
		PreparedStatement pstm = null; // SQL 문을 나타내는 객체
		ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

		try {
			
			String user = "test";
			String pw = "test1234";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			
			String quary = "SELECT * FROM EMP";

			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			while (rs.next()) {
			    int empNo = rs.getInt("EMP_NO");
			    String empName = rs.getString("EMP_NAME");
			    String deptNo = rs.getString("DEPT_NO");

			    System.out.println(empNo + " " + empName + " " + deptNo);
			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}

}
