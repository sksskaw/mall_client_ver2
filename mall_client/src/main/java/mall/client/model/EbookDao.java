package mall.client.model;

import java.sql.*;
import java.util.*;
import mall.client.commons.DBUtil;
import mall.client.vo.Ebook;

public class EbookDao {
	private DBUtil dbutil = null;
	
	
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
	
	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage){
		//필요 객체 초기화
		List<Ebook> list = new ArrayList<>();
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbutil.getConnection();
			String sql = "SELECT * FROM ebook ORDER BY ebook_date DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			System.out.println("selectEbookListByPage " + stmt); //디버깅
			
			rs = stmt.executeQuery();

			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebook_No"));
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
