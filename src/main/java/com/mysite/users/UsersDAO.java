package com.mysite.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysite.common.JDBCUtil;

public class UsersDAO {
	
	private Connection conn = null ; 
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null; 
	
	//SQL 쿼리를 상수로 정의후에 각각 필요한 메소드에서 사용  
	private final String USERS_INSERT = 
			"insert into users (id, password, name, role) values (?,?,?,?)"; 
	private final String USERS_UPDATE = ""; 
	private final String USERS_DELETE = ""; 
	private final String USERS_GET = ""; 
	private final String USERS_LIST = "select * from users order by id asc"; 
	private final String USERS_LOGIN = "select * from users where id = ? and password = ?"; 
	
	//1. users 테이블의 값을 넣는 메소드 
	public void insertUsers(UsersDTO dto) {
		System.out.println("=====insertUsers () 메소드 호출");
	
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(USERS_INSERT); 
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getRole());
			
			pstmt.executeLargeUpdate(); 
			
			System.out.println("insertUsers () - 성공 ");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertUsers () - 실패 ");
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	//2. users 테이블의 모든 레코드를 출력 메소드 
	public List<UsersDTO> getUsersList(UsersDTO dto){
		System.out.println("=====getUsersList () 메소드 호출");
		List<UsersDTO> userList = new ArrayList<UsersDTO>(); 
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(USERS_LIST); 
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				UsersDTO users = new UsersDTO();
				users.setId(rs.getString("ID"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setName(rs.getString("NAME"));
				users.setRole(rs.getString("ROLE"));
				
				userList.add(users); 
			
			}
			
			System.out.println("=====getUsersList () 메소드 -  성공");

			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("=====getUsersList () 메소드 -  실패");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return userList; 
			
	}
	
	// 로그인 처리 메소드 
	// USERS_LOGIN = "select * from users where id = ? and password = ?"; 
	public UsersDTO login(UsersDTO dto) {
		UsersDTO users = null;  
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt= conn.prepareStatement(USERS_LOGIN); 
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			
			rs = pstmt.executeQuery(); 
			
			//레코드가 존재할때  : 작동 
			while ( rs.next()) {
				
				users = new UsersDTO(); 
				
				users.setId(rs.getString("ID"));
				users.setName(rs.getNString("NAME"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setRole(rs.getString("ROLE"));
				
				System.out.println("인증 성공 : DB에 해당 ID와 Password가 존재함 ");
			}					
			
			//rs의 값이 존재하면 : 인증 성공 
			//rs의 값이 존재하지 않으면 : 인증 실패 
			System.out.println("로그인 메소드 잘작동 ");
			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 처리시 오류 발생");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
			
		return users; 	
	}
	
	
}
