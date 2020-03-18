package masil.customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public boolean wirteCustomer(CustomerVO customervo) {
		boolean result = false;
		try {
			getConnection();
			String sql = "insert into masil.customer (id,write_date,title,content";
			if(customervo.getImg()!=null){
				sql += ",img) values(?,now(),?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(4, customervo.getImg());
			}else{
				sql += ") values(?,now(),?,?)";
				pstmt = con.prepareStatement(sql);
			}
			pstmt.setString(1, customervo.getId());
			pstmt.setString(2, customervo.getTitle());
			pstmt.setString(3, customervo.getContent());
			if(pstmt.executeUpdate()==1){
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("writeCustomer() 오류 : "+e);
		} finally {
			allClose();
		}
		
		return result;
		
	}
	
	

	
}
