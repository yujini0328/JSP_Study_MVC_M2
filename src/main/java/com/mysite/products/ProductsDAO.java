package com.mysite.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysite.common.JDBCUtil;

public class ProductsDAO {
	
	private Connection conn = null ; 
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null; 
	
	//SQL 쿼리를 상수로 정의후에 각각 필요한 메소드에서 사용 
	//MVC M2 환경으로 CRUD 
	private final String PRODUCTS_INSERT = 
			"insert into products values ( ?, ?, ?, ?, ?, ?, default)"; 
	private final String PRODUCTS_LIST = "select * from products order by p_code desc"; 


	//상품 입력
	public void insertProducts(ProductsDTO dto) {
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(PRODUCTS_INSERT); 
			pstmt.setInt(1, dto.getP_code());
			pstmt.setString(2, dto.getP_name());
			pstmt.setString(3, dto.getP_kind());
			pstmt.setString(4, dto.getP_price());
			pstmt.setString(5, dto.getP_content());
			pstmt.setString(6, dto.getP_quantity());
			
			pstmt.executeUpdate(); 
			
			
			System.out.println("products insert 성공");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("products insert 실패");
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	// 제품 리스트 출력 
	public List<ProductsDTO> getProductsList(ProductsDTO dto) {
		List<ProductsDTO> productsList = new ArrayList<>(); 
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(PRODUCTS_LIST); 
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				ProductsDTO products = new ProductsDTO();
				
				products.setP_code(rs.getInt("P_CODE"));
				products.setP_name(rs.getString("P_NAME"));
				products.setP_kind(rs.getString("P_KIND"));
				products.setP_price(rs.getString("P_PRICE"));
				products.setP_content(rs.getString("P_CONTENT"));
				products.setP_quantity(rs.getString("P_QUANTITY"));
				products.setIndate(rs.getDate("INDATE"));
				
				productsList.add(products); 
				
			}
			
			System.out.println("products 읽어오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("products 읽어오기 실패");
			
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return productsList; 
	}
}
