package com.mysite.board;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class BoardDTO2 {
	
	/* 
	  	ROMBOK : 1. 라이브러리 등록 : webapp\WEB-INF\lib\lombok.jar
	  			 2. 이클립스 종료후 다시 시작
	  			 3. 클래스 상단에 어노테이션 등록 
	  			 	@Getter : 
	  			 	@Setter : 
	  			 	@NOArgeContructor : 기본생성자 
	  			 	@ToString : toString 메소드 오버라이딩 
	  			 	
	  			 	@Data : 모든것을 한꺼번에 생성 . 
	
	 */
	
	
	private int seq; 
	private String title; 
	private String write; 
	private String content; 
	private Date regdate; 
	private int cnt; 

}
