package masil.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



//DB�۾�
public class UserDAO {
	
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
	
	public boolean insertUser(UserVO userVO){
		String sql = "";
		int result = 0;
		try {
			getConnection();
			sql = "insert into "
					+ "masil.user(id,pwd,name,pname,pnum,jumin1,jumin2,gender) values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userVO.getId());
			pstmt.setString(2, userVO.getpwd());
			pstmt.setString(3, userVO.getName());
			pstmt.setString(4, userVO.getPname());
			pstmt.setString(5, userVO.getPnum());
			pstmt.setString(6, userVO.getJumin1());
			pstmt.setString(7, userVO.getJumin2());
			pstmt.setString(8, userVO.getGender());
			result = pstmt.executeUpdate();	
			if (result != 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("insertUser() 오류 : "+e);
		} finally {
			allClose();
		}
		return false;
	}//insertUser() 끝

	public int userCheck(String id, String pwd) {
		con = null;
		pstmt = null;
		rs = null;
		String sql = "";
		int check = -1;	//  1 -> 아이디, 비밀번호 맞음
						//  0 -> 아이디 맞음 비밀번호 틀림
						// -1 -> 아이디 틀림
		try {
			getConnection();
			pstmt = con.prepareStatement("select pwd from masil.user where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if (pwd.equals(rs.getString("pwd"))) {
					check = 1;
				}else {
					check = 0;
				}
			}
		} catch (Exception e) {
			System.out.println("userCheck() 오류 : "+e);
		} finally {
			allClose();
		}
		return check;
	}//userCheck() 끝
	
	public UserVO getUser(String id) {
		
		
		String sql="";
		
		UserVO userVO = null;
	
		try {
			getConnection();
			sql="select * from user where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				userVO = new UserVO();
				userVO.setId(rs.getString("id"));
				userVO.setpwd(rs.getString("pwd"));
				userVO.setName(rs.getString("name"));
				userVO.setPname(rs.getString("pname"));
				userVO.setPnum(rs.getString("pnum"));
				userVO.setJumin1(rs.getString("jumin1"));
				userVO.setJumin2(rs.getString("jumin2"));
				userVO.setGender(rs.getString("gender"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return userVO;		
	}//getUser() 끝
	
	public int searchingId(String id) {
		
		int result = 0;
		
		
		try {
			getConnection();
			String sql = "select * from masil.user where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = 0;
			}else{
				result = 1;
			}
			
		} catch (Exception e) {
			System.out.println("searchingID() 오류 : "+e);
		}finally {
			allClose();
		}
		
		return result;
		
	}//searchingID() 끝
	
	public boolean updateUser(UserVO userVO) {
		String sql = "";
		int result = 0;
		try {
			getConnection();
			sql = "update masil.user set pwd=?,name=?,pname=?,pnum=?,jumin1=?,jumin2=?,gender=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userVO.getpwd());
			pstmt.setString(2, userVO.getName());
			pstmt.setString(3, userVO.getPname());
			pstmt.setString(4, userVO.getPnum());
			pstmt.setString(5, userVO.getJumin1());
			pstmt.setString(6, userVO.getJumin2());
			pstmt.setString(7, userVO.getGender());
			pstmt.setString(8, userVO.getId());
			result = pstmt.executeUpdate();	
			if (result != 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("updateUser() 오류 : "+e);
		} finally {
			allClose();
		}
		return false;
	}//updateUser() 끝
	
	public boolean deleteUser(UserVO userVO) {
		String sql = "";
		int result = 0;
		try {
			getConnection();
			sql = "delete from masil.user where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userVO.getId());
			result = pstmt.executeUpdate();	
			if (result != 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("deleteUser() 오류 : "+e);
		} finally {
			allClose();
		}
		return false;
	}//deleteUser() 끝
	
	
}//UserDAO() 끝
