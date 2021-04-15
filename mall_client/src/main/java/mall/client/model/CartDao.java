package mall.client.model;

import java.sql.*;
import java.util.*;
import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class CartDao {
	
	private DBUtil dbutil;
	
	// 장바구니 항목 삭제
	public void deleteCart(Cart cart) {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "DELETE FROM cart WHERE cart_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cart.getCartNo());
			System.out.println("deleteCart " + stmt);
			
			stmt.executeUpdate();

			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
		
		return;
	}
	
	// 장바구니 중복 처리 메소드 (장바구니에 같은 책 못넣게 막기위한)
	public int selectCartNo(Cart cart) {
		int cartNo = 0; //0이 리턴된다면 장바구니에 없다라는 뜻!
		
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT * FROM cart WHERE client_mail=? AND ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.println("selectCartNo " + stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cartNo = rs.getInt("cart_no");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}
		return cartNo;
	}
	
	// 장바구니 추가
	public void insertCart(Cart cart) {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "INSERT INTO cart(client_mail,ebook_no,cart_date) VALUES (?,?,NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.println("insertCart " + stmt);
			
			stmt.executeUpdate();

			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
		
		return;
	}
	
	// 현재 로그인 한 계정의 장바구니 정보 가져오기
	public List<Map<String, Object>> selectCartList(String clientMail){
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT e.ebook_no, e.ebook_title, e.ebook_price, c.cart_date, c.cart_no FROM cart c ,ebook e WHERE c.client_mail=? AND c.ebook_no = e.ebook_no";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail); //세션에서 가져온 clientMail값
			System.out.println("selectCartList " + stmt);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ebookNo", rs.getInt("e.ebook_no"));
				map.put("ebookTitle", rs.getString("e.ebook_title"));
				map.put("ebookPrice", rs.getString("e.ebook_price"));
				map.put("cartDate", rs.getString("c.cart_date"));
				map.put("cartNo", rs.getString("c.cart_no"));
				list.add(map);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}
		return list;
	}
}
