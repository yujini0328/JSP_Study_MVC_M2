package com.mysite.test;

import java.sql.Connection;

import com.mysite.common.JDBCUtil;

public class JDBCUtil_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// getConnection() : static     <== 객체화 후 호출, 클래스 이름으로 호출 
		//1. 클래스명으로 메소드 호출 : static 

		Connection conn = null; 
		
		 conn = JDBCUtil.getConnection();
		 
		//2. 객체 생성후 메소드 호출 
		 
		 Connection conn2 = null; 
		 
		 JDBCUtil jdbc  = new JDBCUtil(); 
		 conn2 = jdbc.getConnection(); 
		 
		 

	}

}
