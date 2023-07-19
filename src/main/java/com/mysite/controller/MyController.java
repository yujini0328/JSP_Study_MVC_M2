package com.mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysite.board.BoardDAO;
import com.mysite.board.BoardDTO;
import com.mysite.products.ProductsDAO;
import com.mysite.products.ProductsDTO;
import com.mysite.users.UsersDAO;
import com.mysite.users.UsersDTO;

/**
 * Servlet implementation class MyController
 */
//@WebServlet("/MyController")      <== 어노테이션으로 요청을 처리 ( spring ) 
	/*
	    Controller : Client 의 요청을 처리함. ( get, post )
	     		- doGet() {} : client 에서 보내는 get 요청을 처리하는 메소드 ,   <form method = "get" action= "a.jsp">, <a href = "b.jsp"> 
	     		- doPost() {} : client 에서 보내는 Post 요청을 처리하는 메소드 , <form method = "post" action="a.jsp"> 
	     		
	      controller 요청을 처리 하는 방법 : 
	      		web.xml : 클라이언트의 요청에 대한 controller를 지정함. 
	      		@WebServlet : 어노테이션을 사용해서 처리 
	      		
	      		
	      client 가 보내는 요청정보의 URI를 캐취해서 분기처리 
	 */

public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at (MyController) : ").append(request.getContextPath());
		
		// client 에서 get 방식으로 넘어오는 요청 처리를 doPost에서 한꺼번에 처리하도록 던져줌 	
		request.setCharacterEncoding("UTF-8"); 
		doPost ( request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//client 에서 Get / Post 모두를 한꺼번에 처리함. 
		
		// URL : http://localhost:8181/JSP_Study_MVC_M2/my.do
		// URI : /JSP_Study_MVC_M2/my.do
		
		request.setCharacterEncoding("UTF-8"); 
		
		
		//1. Client의 요청 정보를 path 변수에 등록 함. 
		String url = request.getRealPath(getServletInfo()); 
			//System.out.println("클라이언트가 보내는 전체 URL(실제 시스템의 물리적 경로)  : " + url );
		
		StringBuffer urll = request.getRequestURL(); 
		System.out.println("System URL : " + urll);
		
		String uri = request.getRequestURI(); 
			//System.out.println("클라이언트가 보내는 요청 uri : " + uri);
		
		String path = uri.substring(uri.lastIndexOf("/")); 
			//System.out.println("파일 명을 읽어옴 : " + path );
			
		//2. 클라이언트의 요청 정보에 따라서 적절히 분기 처리 한다. 
		if ( path.equals("/login.do")) {       
			System.out.println("login.do 요청을 했습니다. ");
			// 로그인을 처리하는 코드 블락 
			
			//1. client form 에서 넘어온 변수 : id , password
			String id = request.getParameter("id"); 
			String password = request.getParameter("password"); 
			
			//2. DTO에 저장 
			UsersDTO dto = new UsersDTO(); 
			dto.setId(id); 
			dto.setPassword(password); 
			
			//3. DAO 에 메소드 호출 
			UsersDAO dao = new UsersDAO(); 
			
			
			//users : null 경우 <== 해당 ID와 Password가 일치 하지 않는 경우 
			//users : null 아닐경우	<==  해당 ID와 Password가 DB에 존재함.     <=== session 에 변수에 정보값을 입력 
			UsersDTO users = dao.login(dto); 
			
			if ( users == null) {	//인증 실패 
				System.out.println("인증 실패 했습니다.");
				
				response.sendRedirect("LoginForm.jsp"); 
				
			}else {					//인증 성공 
				System.out.println("인증 성공 했습니다.");
				
				// Session 변수를 생성하고 변수에 ID값을 담아서 클라이언트 view 페이지로 전송 
				HttpSession session = request.getSession(); 
				System.out.println(session);
				
				//세션에 변수에 dto의 값을 할당. 
				//     id  <== DB에서 가져온 ID 
				//     role <== DB에서 가져온 ROLE 
				session.setAttribute("id", users.getId()); 
				session.setAttribute("role", users.getRole()); 
				
				System.out.println("===세션 변수에 담기는 값=====");
				System.out.println(users.getId());
				System.out.println(users.getRole());
				
				response.sendRedirect("LoginForm.jsp"); 
				
			}
					
		} else if (path.equals("/logout.do")) {
			System.out.println("logout.do 요청을 했습니다. ");
			//로그아웃 요청을 처리하는 코드 블락 
			
			// 1. 세션의 모든 변수와 값을 모두 삭제 
			HttpSession session = request.getSession(); 
			
			//접속한 클라이언트에 session에 저장된 모든 변수의 값을 삭제 
			session.invalidate(); 
			
			// 2. 세션 삭제후 이동 페이지 
			response.sendRedirect("/JSP_Study_MVC_M2"); 
			
			
			
			
		} else if (path.equals("/insertBoard.do")) {
			System.out.println("insertBoard.do 요청을 했습니다. ");
			// 게시판에서 값을 DB에 저장함.
			
			// 1. client 폼에서 넘어오는 변수의 값을 받아서 새로운 변수에 제 할당. 
			String title = request.getParameter("title"); 
			String writer = request.getParameter("writer"); 
			String content = request.getParameter("content");
			
			//2. DTO 객체를 생성해서 Setter 주입 
			BoardDTO dto = new BoardDTO (); 
			dto.setTitle(title); 
			dto.setWrite(writer); 
			dto.setContent(content); 
			
			//3. DAO 객체 생성후 insertBoard(dto)  
			BoardDAO dao = new BoardDAO (); 
			dao.insertBoard(dto);        //Insert 완료 
			
			//4. 비즈니스 로직을 처리후 view 페이지로 이동 
			response.sendRedirect("getBoardList.do"); 
		
			
		} else if (path.equals("/getBoard.do")) {
			System.out.println("getBoard.do 를 요청 했습니다. ");
			// 게시판의 값을 읽어 올때 (글 상세)
			
			//request.getParameter 으로 넘어오는 모든 변수의 값은 String
			// Spring 에서는 자동으로 Framework에서 자동으로 처리됨 
			
				//Integer.parseInt(seq) 
			
			String seq = request.getParameter("seq");
			
			//1. DTO 에 seq 의 값을 setter 주입 
			BoardDTO dto = new BoardDTO(); 
			dto.setSeq(Integer.parseInt(seq));
			
			//3. DAO 객체에 getBoard(dto) 넣어서 호출 ====> 리턴으로 dto 를 받아온다. 
			BoardDAO dao = new BoardDAO(); 
			
			//리턴으로 돌아오는 DTO 값을 받을 변수 선언 
			BoardDTO board = new BoardDTO(); 
			board = dao.getBoard(dto); 
			
			// System.out.println(board);
			
			
			//4. session 에 값을 저장후 뷰 페이지로 전달 : Model 
				// 현재 클라이언트가 서버에 접속한 세션을 가지오 옴 
			
			// 세션 변수 선언 
			HttpSession session = request.getSession(); 
			
			//세션 에 변수에 DB에서 select 한 DTO를 저장후 뷰 페이지로 전송 
			session.setAttribute("board", board); 
			
			response.sendRedirect("getBoard.jsp"); 
			
			
		} else if (path.equals("/getBoardList.do")) {
			// 글 목록 
			System.out.println("getBoardList.do 를 요청 했습니다. ");
			
			//클라이언트에서 받은 검색어를 DTO에 저장후 메소드 호출 
			String searchCondition = request.getParameter("searchCondition"); 
			String searchKeyword = request.getParameter("searchKeyword"); 
			
			
			// Null을 처리 해야 함.   : 주의 : URL에서 요청을 했을때 NULL이 적용됨 : getBoardList.do
			if ( searchCondition == null) {
				searchCondition = "TITLE"; 	
			} 
			if ( searchKeyword == null ) {
				searchKeyword = ""; 
			}
			
			
			System.out.println("====검색어 출력 =====");
			System.out.println(searchCondition);
			System.out.println(searchKeyword);
			
			
			//1. DTO 객체 생성 
			BoardDTO dto = new BoardDTO(); 
			
			dto.setSearchCondition(searchCondition); 
			dto.setSearchKeyword(searchKeyword); 
			
			
			//2. DAO의 getBoardList(dto) 
			BoardDAO dao = new BoardDAO(); 
			
			List<BoardDTO> boardList = new ArrayList<BoardDTO>(); 
			
			//boardList 에는 board 테이블의 각 레코드를 dto에 저장후 boardList 에 추가된 객체를 리턴 
			boardList = dao.getBoardList(dto); 
			
			
			//리턴받은 boardList를 Clent View 페이지로 전송 , ( Session 에 리스트를 저장후 클라이언트로 전송 ) 
			// Session : 접속한 client에 고유하게 부여된 식별자가 서버 메모리에 할당. 
			
			//세션 변수 선언 
			HttpSession session = request.getSession(); 
			
			//세션에 boardList를 추가 
			session.setAttribute("boardList", boardList); 
			
			//클라이언트 뷰페이지로 이동 
			response.sendRedirect("getBoardList.jsp"); 
			
							
		}else if (path.equals("/insertUsers.do")) {
			System.out.println("/insertUsers.do - 요청 ");
			//Users테이블에 값을 Insert 코드 블락 
			
			//1. Form에서 넘어오는 값을 읽어와서 새로운 변수에 할당 
			String id = request.getParameter("id"); 
			String password = request.getParameter("password"); 
			String name = request.getParameter("name"); 
			String role = request.getParameter("role"); 
			
			//2. DTO 에 값을 할당. 
			UsersDTO dto = new UsersDTO(); 
			dto.setId(id); 
			dto.setPassword(password); 
			dto.setName(name); 
			dto.setRole(role); 
			
			//3. DAO 의 메소드 호출  : insertUsers(dto) 
			UsersDAO dao = new UsersDAO(); 
			dao.insertUsers(dto); 
			
			//4. 비즈니스로직 모두 처리후 view 페이지 이동 
			response.sendRedirect("getUsersList.do"); 
			
		}else if ( path.equals("/getUsersList.do")) {
			System.out.println("/getUsersList.do 요청 ");
			//Users테이블의 값을 Select 해서 출력 
			
			//1. 객체 생성 
			UsersDTO dto = new UsersDTO(); 
			UsersDAO dao = new UsersDAO(); 
			
			//2. 메소드 호출후 리턴으로 List 받음. 
			List<UsersDTO> usersList = new ArrayList<UsersDTO>(); 
			usersList = dao.getUsersList(dto); 
			
			//3. Session 객체를 사용해서 변수에 userList 를 저장함. 
			HttpSession session = request.getSession(); 
			
			session.setAttribute("usersList", usersList); 
			
			
			//4. 비즈니스로직 모두 처리후 view 페이지 이동 
			response.sendRedirect("getUsersList.jsp"); 
			
			
			//글 수정 
		}else if (path.equals("/updateBoard.do")) {
			//
			System.out.println("/updateBoard.do - 요청 성공 ");
			
			//1. 클라이언트에서 넘어오는 변수 값을 받음. 
			String title = request.getParameter("title"); 
			String content = request.getParameter("content"); 
			String seq = request.getParameter("seq"); 
			
			System.out.println("넘버 변수 변환 : " + seq);
			
			//2. DTO에 Setter 주입 
			BoardDTO dto = new BoardDTO(); 
			dto.setTitle(title); 
			dto.setContent(content); 
			dto.setSeq(Integer.parseInt(seq)); 
			
			//3. DAO의 메소드 호출 : updateBoard(dto) 
			BoardDAO dao = new BoardDAO(); 
			dao.updateBoard(dto); 
			
			// 4. view 페이지로 이동 
			
			response.sendRedirect("getBoardList.do"); 			
			
		} else if (path.equals("/deleteBoard.do")) {
			System.out.println("/deleteBoard.do 요청 성공 ");
			String seq = request.getParameter("seq"); 
			String write = request.getParameter("write"); 
			
			//System.out.println("seq : " + seq );
			//System.out.println("write : " + write );
			
			// 1. 변수를 DTO에 저장 
			BoardDTO dto = new BoardDTO(); 
			dto.setSeq(Integer.parseInt(seq)); 
			dto.setWrite(write); 
			
			//2. DAO의 메소드 호출  
			BoardDAO dao = new BoardDAO(); 
			dao.deleteBoard(dto); 
			
			//3. view 페이지로 이동 
			response.sendRedirect("getBoardList.do"); 
			
			
		}else if (path.equals("/insertProducts.do")) {
			System.out.println("/insertProducts.do 요청 성공");
			
			String p_code = request.getParameter("p_code"); 
			String p_name = request.getParameter("p_name"); 
			String p_kind = request.getParameter("p_kind"); 
			String p_price = request.getParameter("p_price"); 
			String p_content = request.getParameter("p_content"); 
			String p_quantity = request.getParameter("p_quantity"); 
			
			ProductsDTO products = new ProductsDTO(); 
			
			products.setP_code(Integer.parseInt(p_code)); 
			products.setP_name(p_name); 
			products.setP_kind(p_kind); 
			products.setP_price(p_price); 
			products.setP_content(p_content); 
			products.setP_quantity(p_quantity); 
			
			//dao 메소드 호출 
			ProductsDAO dao = new ProductsDAO (); 
			dao.insertProducts(products); 
			
			//view페이지 이동 
			response.sendRedirect("getProductsList.do"); 
			
			
			
		}else if (path.equals("/getProductsList.do")) {
			System.out.println("/insertProducts.do 요청 성공");
			
			//1. DTO 생성 
			ProductsDTO dto = new ProductsDTO(); 
						
			//2. DAO 메소드 호출 
			ProductsDAO dao = new ProductsDAO(); 
			
			List<ProductsDTO> productsList = dao.getProductsList(dto); 
			
			//3. Session 변수에 저장후 출력 
			HttpSession session = request.getSession();
			session.setAttribute("productsList", productsList);
			
			//4. view 페이지로 이동 
			response.sendRedirect("getProductsList.jsp");
			
		}
		
		
			
		
		
	}

}
