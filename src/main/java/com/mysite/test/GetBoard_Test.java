package com.mysite.test;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class GetBoard_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. dto에 seq 변수에 값을 할당 
		BoardDTO dto = new BoardDTO(); 
		dto.setSeq(12);
		
		//2. dao의 메소드 호출 
		BoardDAO dao = new BoardDAO(); 
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoard(dto);          //board : DB에서 읽어온 값을 저장 
		
		//3. 출력 
		System.out.println(board);
		
		

	}

}
