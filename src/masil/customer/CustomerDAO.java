package masil.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	
}
