package mall.client.model;

import java.sql.*;
import mall.client.commons.DBUtil;
import mall.client.vo.*;

public class ClientDao {
	private DBUtil dbutil;
	
	// 회원 탈퇴 메소드
	public void deleteClient(Client client) {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "DELETE FROM client WHERE client_mail=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			System.out.println("deleteClient " + stmt);
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbutil.close(null, stmt, conn);
		}
	}
	
	// 비밀번호 수정
	public void updateClientPw(Client client) {

		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "UPDATE client SET client_pw = PASSWORD(?) WHERE client_mail=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientPw());
			stmt.setString(2, client.getClientMail());
			System.out.println("updateClientPw " + stmt);
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbutil.close(null, stmt, conn);
		}

	}
	
	// (비밀번호 수정을 위한) 현재 비밀번호 체크, 회원 탈퇴 시 비밀번호 체크
	public Client checkClientCurrenPw(Client client) {
		Client returnClient = null;
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT client_pw clientPw, client_mail clientMail FROM client WHERE client_mail = ? AND client_pw = PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			System.out.println("checkClientCurrenPw " + stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClient = new Client();
				returnClient.setClientMail(rs.getString("clientMail"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbutil.close(rs, stmt, conn);
		}
		
		return returnClient;
	}
	
	// 회원정보
	public Client selectClientOne(String clientMail) {
		this.dbutil = new DBUtil();
		Client client = new Client();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT client_no clientNo, client_mail clientMail, client_date clientDate FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			System.out.println("ClientDao " + stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				client.setClientNo(rs.getInt("clientNo"));
				client.setClientMail(rs.getString("clientMail"));
				client.setClientDate(rs.getString("clientDate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbutil.close(rs, stmt, conn);
		}
		return client;
	}
	
	// 회원가입 시 메일 중복검사
	public String selectClientMail(String clientMail) {
		
		this.dbutil = new DBUtil();
		String returnClientMail = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT client_mail FROM client WHERE client_mail=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				returnClientMail = rs.getString("client_mail");
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}

		return returnClientMail;
	}
	
	// 회원가입(회원 정보 입력)
	public void insertClient(Client client) {
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "INSERT INTO client(client_mail, client_pw, client_date) VALUES (?,PASSWORD(?),NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			stmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(null, stmt, conn);
		}
		
		return;
	}
	
	// 로그인 정보 가져오기
	public Client login(Client client) {
		this.dbutil = new DBUtil();
		Client returnCLient = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT * FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				returnCLient = new Client();
				returnCLient.setClientNo(rs.getInt("client_no"));
				returnCLient.setClientMail(rs.getString("client_mail"));
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			this.dbutil.close(rs, stmt, conn);
		}
		
		return returnCLient;
	}
}
