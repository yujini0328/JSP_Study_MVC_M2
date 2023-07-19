package com.mysite.test;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class UpdateBoard_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. DTO 에 값을 할당. 
		BoardDTO dto = new BoardDTO (); 
		dto.setTitle("테스트로 제목 수정");
		dto.setContent("테스트 에서 내용수정");
		dto.setSeq(6);
		
		//2. DAO 에서 updateBoard(dto) 
		BoardDAO dao = new BoardDAO();
		dao.updateBoard(dto);
		
		

	}

}
