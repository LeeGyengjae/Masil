package masil.res_table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB작업
public class Res_tableDAO {

	//DB연결 관련 변수 모음
	private Connection conn;
	private Context init;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;
	private String sql;
	
	//기본 생성자
	public Res_tableDAO() { }
	//기본 생성자
	
	//DB연결 메소드 getConnecion()
	private Connection getConnection() throws Exception{
		conn =null;
		init = new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/masil");
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
