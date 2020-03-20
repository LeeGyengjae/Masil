package masil.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class CustomerDAO {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	DataSource ds = null;

	private void getConnection() throws Exception{
		Context init = new InitialContext();
		
		ds = (DataSource)init.lookup("java:comp/env/jdbc/masil");
		con = ds.getConnection();
	}//getConnection() 끝
	
	public void allClose(){
		try {
			if(con != null) con.close();
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			
		} catch (Exception e) {
			System.out.println("allClose()오류 : "+e);
		}
	}//allClose() 끝
	
	
	public CustomerDAO() {
		
	}

	public ArrayList<CustomerVO> getCustomerList(int page) {
		ArrayList<CustomerVO> result = new ArrayList<CustomerVO>();
		try {
			getConnection();
			String sql = "select * from customer order by idx DESC limit ?,10";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page*10);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CustomerVO customervo = new CustomerVO();
				customervo.setId(rs.getString("id"));
				customervo.setContent(rs.getString("content"));
				customervo.setWrite_date(rs.getDate("write_date"));
				customervo.setIdx(rs.getInt("idx"));
				customervo.setTitle(rs.getString("title"));
				result.add(customervo);
			}
			
		} catch (Exception e) {
			System.out.println("getCustomerList() 오류 : "+e);
		}finally {
			allClose();
		}
		
		return result;
	}//getCustomerList() 끝
	

	public int customerAllCount() {
		int result = 0;
		try {
			getConnection();
			String sql = "select count(*) from customer";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("customerAllCount() 오류 : "+e);
		}finally {
			allClose();
		}
		
		return result;
	}//CustomerAllCount() 끝

	public boolean writeCustomer(CustomerVO customervo) {
		int customerNO = getCustomerNO();
		System.out.println("writeCustomer() 실행");
		System.out.println("ID : "+customervo.getId());
		System.out.println("Title : "+customervo.getTitle());
		System.out.println("Content : "+customervo.getContent());
		System.out.println("Img : "+customervo.getImg());
		boolean result = false;
		try {
			getConnection();
			String sql = "insert into masil.customer (idx,id,write_date,title,content";
			if(customervo.getImg()!=null){
				sql += ",img) values(?,?,now(),?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(5, customervo.getImg());
			}else{
				sql += ") values(?,?,now(),?,?)";
				pstmt = con.prepareStatement(sql);
			}
			pstmt.setInt(1, customerNO);
			pstmt.setString(2, customervo.getId());
			pstmt.setString(3, customervo.getTitle());
			pstmt.setString(4, customervo.getContent());
			if(pstmt.executeUpdate()==1){
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("writeCustomer() 오류 : "+e);
		} finally {
			allClose();
		}
		
		return result;
		
	}// writeCustomer() 끝
	
	public int getCustomerNO(){
		try{
			getConnection();
			String query = "select max(idx) from customer";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(query);
			if(rs.next()){
				return (rs.getInt(1)+1);
			}
			
		} catch(Exception e){
			System.out.println("getCustomerNO() 오류 : "+e);
		}finally {
			allClose();
		}
		
		return 0;
	}

	

	public CustomerVO getBoard(int num) {
		
		CustomerVO result = new CustomerVO();
		try {
			getConnection();
			String sql = "select * from masil.customer where idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result.setContent(rs.getString("content"));
				result.setWrite_date(rs.getDate("write_date"));
				result.setIdx(rs.getInt("idx"));
				result.setTitle(rs.getString("title"));
				result.setId(rs.getString("id"));
				result.setImg(rs.getString("img"));
			}
			
		} catch (Exception e) {
			System.out.println("getBoard() 오류 : "+e);
		}finally {
			allClose();
		}
		
		return result;
	}

	public void modArticle(CustomerVO customerVO) {
		
		int idx = customerVO.getIdx();
		String title = customerVO.getTitle();
		String content = customerVO.getContent();
		String img = customerVO.getImg();
		try {
			getConnection();
			String query = "update masil.customer  set title=?,content=?";
			if (img != null && img.length() != 0) {
				query += ",img=?";
			}
			query += " where idx=?";

			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if (img != null && img.length() != 0) {
				pstmt.setString(3, img);
				pstmt.setInt(4, idx);
			} else {
				pstmt.setInt(3, idx);
			}
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("modArticle() 에러 : "+e);
		}
		
	}
	
	

	
}
