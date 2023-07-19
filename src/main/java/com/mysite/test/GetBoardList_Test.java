package com.mysite.test;

import java.util.ArrayList;
import java.util.List;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;

public class GetBoardList_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. DTO 객체 생성 
		BoardDTO dto = new BoardDTO(); 
		
		//2. DAO 객체 생성 
		BoardDAO dao = new BoardDAO();
		
	    //3. 메소드 호출시 리턴 받을 리스트 변수 선언 : List<BoardDTO> 
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		
		//boardList 는 board 테이블의 레코드를 dto에 저장후 List에 추가됨 
		boardList = dao.getBoardList(dto); 
		
		
		
		//ArrayList 에 저장된 값을 끄집어 내서 출력 
		//for 문을 사용해서 출력 
		System.out.println("======For 문을 사용해서 출력 ===============");
		for ( int i = 0 ; i < boardList.size(); i++) {
			
			System.out.println(boardList.get(i));
		}
		
		// Enhanced For 문을 사용해서 출력 
		System.out.println("====== Enhanced For 문을 사용해서 출력 ===============");
		for (BoardDTO k : boardList ) {
			System.out.println(k);
		}
		

	}

}
