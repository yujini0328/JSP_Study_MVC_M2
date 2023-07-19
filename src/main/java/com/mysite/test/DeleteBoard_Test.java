package com.mysite.test;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class DeleteBoard_Test {

	public static void main(String[] args) {
		// 단위 테스트 : 메소드가 잘 동작 하는지 테스트 
		
		//1. 객체 생성 : DTO  
		BoardDTO dto = new BoardDTO(); 
		dto.setSeq(10);
		
		//2. 객체 생성 : DAO
		BoardDAO dao = new BoardDAO(); 
		dao.deleteBoard(dto);
		

	}

}
