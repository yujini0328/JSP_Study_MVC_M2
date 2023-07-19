package com.mysite.test;

import java.util.ArrayList;
import java.util.List;

import com.mysite.users.UsersDAO;
import com.mysite.users.UsersDTO;

public class GetUsersList_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1. 객체 생성 
		UsersDTO dto = new UsersDTO();
		UsersDAO dao = new UsersDAO(); 
		
		//2. getUsersList(dto) 메소드 호출후 리턴값을 받음. 
		List<UsersDTO> userList = new ArrayList<UsersDTO>(); 
		
		userList = dao.getUsersList(dto); 
		
		//3. for 문을 사용해서 출력 
		
		System.out.println("===== FOR 문을 사용해서 값을 출력 =======");
		for (int i = 0 ; i < userList.size(); i++) {
			System.out.println(userList.get(i));
		}
		
		System.out.println("=====향상된 FOR 문을 사용해서 값을 출력 =======");

		for (UsersDTO k : userList) {
			System.out.println(k);
		}
		
	}

}
