package com.mysite.test;

import com.mysite.users.UsersDAO;
import com.mysite.users.UsersDTO;

public class InsertUsers_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1. dto 객체에 setter 를 사용해서 값을 주입 
		UsersDTO dto = new UsersDTO(); 
		dto.setId("userA");
		dto.setPassword("1234");
		dto.setName("사용자A");
		dto.setRole("Users");
		
		//2. DAO 객체 생성후 메소드 호출 insertUsers(dto) 
		UsersDAO dao = new UsersDAO(); 
		dao.insertUsers(dto);
		
		
	}

}
