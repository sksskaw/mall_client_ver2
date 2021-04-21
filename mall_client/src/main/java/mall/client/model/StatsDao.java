package mall.client.model;

import java.sql.*;
import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class StatsDao {
	private DBUtil dbutil;
	
	public int selectStatsByToday() {
		this.dbutil = new DBUtil();
		Stats stats = new Stats();
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT * FROM stats WHERE stats_day = DATE(NOW())";
			stmt = conn.prepareStatement(sql);
			System.out.println("selectStatsByToday " + stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("stats_count");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbutil.close(rs, stmt, conn);
		}
		return count;
	}
	
	// 오늘의 첫 방문자일 시 생성
	public void insertStats() {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "INSERT INTO stats(stats_day, stats_count) VALUES (DATE(NOW()), 1)";
			stmt = conn.prepareStatement(sql);
			System.out.println("insertStats " + stmt);
			stmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
		return;
	}
	
	public void updateStats() {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "UPDATE stats SET stats_count = stats_count + 1 WHERE stats_day = DATE(NOW())";
			stmt = conn.prepareStatement(sql);
			System.out.println("updateStats " + stmt);
			stmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
		
		return;
	}
	
	public long selectStatsTotal() {
		this.dbutil = new DBUtil();
		long totalCount = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT SUM(stats_count) FROM stats";
			stmt = conn.prepareStatement(sql);
			System.out.println("selectStatsTotal " + stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getLong("SUM(stats_count)");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbutil.close(rs, stmt, conn);
		}
		return totalCount;
	}
}