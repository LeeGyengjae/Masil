package masil.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class checkDAO {private Connection con;
private PreparedStatement pstmt;
private DataSource ds;
private ResultSet rs;

public checkDAO() {
	try {
		Context ctx=new InitialContext();
		ds=(DataSource) ctx.lookup("java:comp/env/jdbc/willpass");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public void free() {
	try {
		if(con != null) con.close();
		if(pstmt != null) pstmt.close();
		if(rs != null) rs.close(); 
	} catch (Exception e) {
		System.out.println("자원해제 오류 "+e.getMessage());
	}
}

	public int searchingId(String id) {
		int result=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(*) cnt from user where user_id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			result=rs.getInt("cnt");
			
			
			
		} catch (Exception e) {
			System.out.println("회원 검색 오류"+e.getMessage());
		}finally {
			free();
			
		}
		return result;
		
	}//id중복체크 메소드
	public int searchingemail(String email) {
		int result=0;
		try {
			con=ds.getConnection();
			
			String sql="select count(*) cnt from user where user_email=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			result=rs.getInt("cnt");
			System.out.println("메소드 안"+result);
			
			
		} catch (Exception e) {
			System.out.println("회원 검색 오류"+e.getMessage());
		}finally {
			free();
			
		}
		return result;
	}//email중복체크 메소드
	
	
	
	//////////////////////////////////////////////////////////25 july 
	
	
	
	public String searchingMobile(String user_mobile) {
		
		String result="";
		
		try {
			con=ds.getConnection();
			
			String sql="select user_email from user where user_mobile=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_mobile);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			result = rs.getString("user_email");
		
			System.out.println("메소드 안"+result);
			
			
		} catch (Exception e) {
			System.out.println("회원 검색 오류"+e.getMessage());
		}finally {
			free();
			
		}
		return result;
		
	}//mobile로 아디(이메일찯는 메소드
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	public String searchingPwd(String user_mobile,String user_email) {
		
		String result=" ";
		
		try {
			con=ds.getConnection();
			
			//String sql="select user_pwd from user  where user_mobile?";
			String sql="select user_pwd from user  where user_mobile=? and user_email=?";
			//String sql="select user_pwd from user where user_mobile=?";
			
			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_mobile);
			pstmt.setString(2, user_email);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			result = rs.getString("user_pwd"); //double check 25/july 결과값이라 맞는
		
			System.out.println("메소드 안"+result);
			
			
		} catch (Exception e) {
			System.out.println("회원 검색 오류"+e.getMessage());
		}finally {
			free();
			
		}
		return result;
	
	}
	
	//////////////////////////////////////////////////////////////////
}



