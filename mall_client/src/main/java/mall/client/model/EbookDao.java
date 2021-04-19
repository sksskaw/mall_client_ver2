package mall.client.model;

import java.sql.*;
import java.util.*;
import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class EbookDao {
	private DBUtil dbutil = null;
	
	// 이번달에 나오는 책
	public List<Map<String, Object>> selectEbookListByMonth(int year, int month){
		this.dbutil = new DBUtil();
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbutil.getConnection();
			String sql = "SELECT ebook_no, ebook_title, DAY(ebook_date) d FROM ebook WHERE YEAR(ebook_date) = ? AND MONTH(ebook_date) = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			System.out.println("selectEbookListByMonth " + stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ebookNo", rs.getInt("ebook_no"));
				map.put("ebookTitle", rs.getString("ebook_title"));
				map.put("day", rs.getInt("d"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbutil.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	// ebook 천체 행 수 가져오기
	public int totalCount(String categoryName, String searchTitle) {
		
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalRow = 0;
		
		if(searchTitle == null) {
			searchTitle = "";
		}
		
		try {
			conn = dbutil.getConnection();
			String sql = null;
			if(categoryName == null) { // 카테고리 선택 안했을 경우(전체 선택)
				sql = "SELECT COUNT(*) FROM ebook WHERE ebook_title like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchTitle+"%");
			} else {
				sql = "SELECT COUNT(*) FROM ebook WHERE category_name=? AND ebook_title like ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				stmt.setString(2, "%"+searchTitle+"%");
			}

			System.out.println("totalCount " + stmt); //디버깅
			
			rs = stmt.executeQuery();

			if(rs.next()) {
				totalRow = rs.getInt("count(*)");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbutil.close(rs, stmt, conn);
		}

		return totalRow;
	}
	
	public Ebook selectEbookOne(int ebookNo) {
		this.dbutil = new DBUtil();
		Ebook ebook = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbutil.getConnection();
			String sql = "SELECT ebook_no ebookNo, ebook_isbn ebookISBN, category_name categoryName, ebook_title ebookTitle, ebook_author ebookAuthor, ebook_company ebookCompany, ebook_page_count ebookPageCount, ebook_price ebookPrice, ebook_img ebookImg, ebook_summary ebookSummary, ebook_date ebookDate,ebook_state ebookState FROM ebook WHERE ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ebookNo);
			System.out.println("selectEbookOne " + stmt);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebookNo"));
		        ebook.setCategoryName(rs.getString("categoryName"));
		        ebook.setEbookISBN(rs.getString("ebookISBN"));
		        ebook.setEbookTitle(rs.getString("ebookTitle"));
		        ebook.setEbookAuthor(rs.getString("ebookAuthor"));
		        ebook.setEbookCompany(rs.getString("ebookCompany"));
		        ebook.setEbookPageCount(rs.getInt("ebookPageCount"));
		        ebook.setEbookPrice(rs.getInt("ebookPrice"));
		        ebook.setEbookSummary(rs.getString("ebookSummary"));
		        ebook.setEbookImg(rs.getString("ebookImg"));
		        ebook.setEbookDate(rs.getString("ebookDate"));
		        ebook.setEbookState(rs.getString("ebookState"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbutil.close(rs, stmt, conn);
		}
		
		return ebook;
	}
	
	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage, String categoryName, String searchTitle){
		//필요 객체 초기화
		List<Ebook> list = new ArrayList<>();
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		if(searchTitle == null) {
			searchTitle = "";
		}
		
		try {
			conn = dbutil.getConnection();
			System.out.println("DAO categoryName " + categoryName);
			if(categoryName == null) { // 카테고리 선택 안했을 경우(전체 선택)
				String sql = "SELECT * FROM ebook WHERE ebook_title like ? ORDER BY ebook_date DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchTitle+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);

			} else {
				String sql = "SELECT * FROM ebook WHERE category_name=? AND ebook_title like ? ORDER BY ebook_date DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				stmt.setString(2, "%"+searchTitle+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				
			}
			
			System.out.println("selectEbookListByPage " + stmt); //디버깅
			
			rs = stmt.executeQuery();

			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebook_No"));
				ebook.setCategoryName(rs.getString("category_name"));
				ebook.setEbookTitle(rs.getString("ebook_title"));
				ebook.setEbookPrice(rs.getInt("ebook_price"));
				ebook.setEbookImg(rs.getString("ebook_img"));
				list.add(ebook);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbutil.close(rs, stmt, conn);
		}
		
		return list;
	}
}
