package masil.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB작업
public class ProductDAO {

	//DB연결 관련 변수 모음
	Connection conn;
	Context init;
	PreparedStatement pstmt;
	DataSource ds;
	ResultSet rs;
	String sql;
	
	//기본 생성자
	public ProductDAO() {
		
	}//기본 생성자
	
	//DB연결 메소드 getConnecion()
	private Connection getConnection() throws Exception{
		conn =null;
		init = new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/mytest");
		conn=ds.getConnection();
		return conn;
	}//getConnection()
	
	//DB연결 해제
	private void closeDB(){
		try {//최초사용되었던 순서 역순으로 닫음
			if(rs!=null) rs.close(); 
			if(pstmt!=null) pstmt.close(); 
			if(conn!=null) conn.close();
		} catch (Exception e) { e.printStackTrace(); }
	}//closeDB
	
	
}
