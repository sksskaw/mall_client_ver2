package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;
import mall.client.vo.Orders;

public class OrdersDao {
	private DBUtil dbutil;
	
	public List<Map<String, Object>> selectOrderListByClient(int clientNo){
		
		List<Map<String, Object>> list = new ArrayList();
		
		this.dbutil = new DBUtil();
		Orders orders = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT o.orders_no, o.ebook_no, o.orders_date, o.orders_state, e.ebook_title, e.ebook_price FROM orders o, ebook e WHERE o.ebook_no=e.ebook_no AND o.client_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clientNo);
			System.out.println("selectOrderListByClient " + stmt);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ordersNo", rs.getInt("orders_no"));
				map.put("ebookNo", rs.getInt("ebook_no"));
				map.put("ordersDate", rs.getString("orders_date"));
				map.put("ordersState", rs.getString("orders_state"));
				map.put("ebookTitle", rs.getString("ebook_title"));
				map.put("ebookPrice", rs.getInt("ebook_price"));
				list.add(map);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	public void insertOrders(Orders orders) {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "INSERT INTO orders(ebook_no, client_no, orders_date, orders_state) VALUES (?,?,NOW(),'주문완료')";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getEbookNo());
			stmt.setInt(2, orders.getClientNo());
			System.out.println("insertOrders " + stmt);
			
			stmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
		
		return;
	}
}
