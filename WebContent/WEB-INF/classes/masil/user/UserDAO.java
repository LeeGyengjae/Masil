package masil.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import util.DatabaseUtil;

//DB�۾�
public class UserDAO {

	
	public int login(String userID , String userPassword) {
		String SQL = "select userPassword from user where userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1; // 아이디 없음
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -2; // 데이터베이스 오류
	}
	
	public int addUser(UserVO userVO) {
		String SQL = "insert into user values(?, ?, ? ,? , ?, ?, ?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userVO.getId());
			pstmt.setString(2,  userVO.getpwd());
			pstmt.setString(3,  userVO.getName());
			pstmt.setString(4,  userVO.getPname());
			pstmt.setString(5,  userVO.getPnum());
			pstmt.setString(6,  userVO.getJumin1());
			pstmt.setString(7,  userVO.getJumin2());
			pstmt.setString(8,  userVO.getGender());
			return pstmt.executeUpdate();
			
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1; // 회원가입 실패
	}		
}
	

