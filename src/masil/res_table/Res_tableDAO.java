package masil.res_table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB�۾�
public class Res_tableDAO {

	//DB���� ���� ���� ����
	private Connection conn;
	private Context init;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;
	private String sql;
	
	//�⺻ ������
	public Res_tableDAO() { }
	//�⺻ ������
	
	//DB���� �޼ҵ� getConnecion()
	private Connection getConnection() throws Exception{
		conn =null;
		init = new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/masil");
		conn=ds.getConnection();
		return conn;
	}//getConnection()
	
	//DB���� ����
	private void closeDB(){
		try {//���ʻ��Ǿ��� ���� �������� ����
			if(rs!=null) rs.close(); 
			if(pstmt!=null) pstmt.close(); 
			if(conn!=null) conn.close();
		} catch (Exception e) { e.printStackTrace(); }
	}//closeDB
	
	
			
}
