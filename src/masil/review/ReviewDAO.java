package masil.review;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB�۾�
public class ReviewDAO {

	//DB���� ���� ���� ����
	private Connection conn;
	private Context init;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;
	private String sql;
	
	//�⺻ ������
	public ReviewDAO() { }
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

	//��ǰ ������������ �ı� ����ϱ�
	public List<Map<String,String>> selectReview(String code) {
		List<Map<String,String>> reviewList = new ArrayList<Map<String,String>>();
		try {
			conn=getConnection();
			sql = "select a.code, a.id, a.content, a.write_date, a.rating, a.end_date, b.reviewCnt"
				+ " from review a join (select count(code) reviewCnt, code from review where code=?) b"
				+ " on a.code=b.code"
				+ " where a.code=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, code);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Map<String, String> reviewMap = new HashMap<String, String>();
				reviewMap.put("id", rs.getString("id"));
				reviewMap.put("content", rs.getString("content"));
				reviewMap.put("write_date", rs.getDate("write_date").toString());
				reviewMap.put("rating", Integer.toString(rs.getInt("rating")));
				reviewMap.put("end_date", rs.getDate("end_date").toString());
				reviewMap.put("reviewCnt", rs.getString("reviewCnt"));
				reviewList.add(reviewMap);
			}
			System.out.println("selectReview����!!");
		} catch (Exception e) {
			System.out.println("selectReview() ���� "+e);
		} finally { closeDB(); }
		return reviewList;
	}//selectReview(String code)

	//�ı� �Է�
	public int insertReview(ReviewVO reviewVO) {
		int re=0;
		try {
			conn=getConnection();
			sql = "insert into review (code, id, content, write_date, rating, end_date)"
					+ " values (?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reviewVO.getCode());
			pstmt.setString(2, reviewVO.getId());
			pstmt.setString(3, reviewVO.getContent());
			pstmt.setString(4, reviewVO.getWrite_date());
			pstmt.setInt(5, reviewVO.getRating());
			pstmt.setString(6, reviewVO.getEnd_date());
			re=pstmt.executeUpdate();
			System.out.println("insertReview()����!!");
		} catch (Exception e) {
			System.out.println("insertReveiw() ���� "+e);
		} finally { closeDB(); }
		return re;
	}//insertReview()
	
	//�ı� ����
	public int updateReivew(ReviewVO reviewVO){
		int re=0;
		try {
			conn=getConnection();
			sql="select id from review where code=?";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(reviewVO.getId().equals("masiladmin")){	//������ �ı� �ۼ��� �����ڰ� ���� ��ư Ŭ���Ͽ� ���� ���� �� �ֵ�����
					sql="update review set content=? where code=? and id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "�����ڿ� ���� ������ �ı��Դϴ�");
					pstmt.setString(2, reviewVO.getCode());
					pstmt.setString(3, reviewVO.getId());
					re=pstmt.executeUpdate();
					System.out.println("������ ���� �ı� ���� �۾� �Ϸ�");
				}else if(reviewVO.getId().equals(rs.getString("id"))){	//�ı� �� ȸ���� ���� ��ư Ŭ����
					sql="update review set content=? where code=? and id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getContent());
					pstmt.setString(2, reviewVO.getCode());
					pstmt.setString(3, reviewVO.getId());
					re=pstmt.executeUpdate();
					System.out.println("�ı� ���� �۾� �Ϸ�");
				}
			} else { re=0; }
		} catch (Exception e) {
			System.out.println("updateReivew() ���� "+e);
		} finally { closeDB(); }
		return re;
	}//updateReview()
	
	//�ı� ����  
	public int deleteReview(ReviewVO reviewVO){
		int re=0;
		try {
			conn=getConnection();
			sql="select code, id from review where code=? and id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reviewVO.getCode());
			pstmt.setString(2, reviewVO.getId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(reviewVO.getId().equals("masiladmin")){
					sql="delete from review where code=? and id=? and end_date=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getCode());
					pstmt.setString(2, reviewVO.getId());
					pstmt.setString(3, reviewVO.getEnd_date());
					re=pstmt.executeUpdate();
				}else if(reviewVO.getId().equals(rs.getString("id"))){
					sql="delete from review where code=? and id=? and end_date=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getCode());	
					pstmt.setString(2, reviewVO.getId());
					pstmt.setString(3, reviewVO.getEnd_date());
					re=pstmt.executeUpdate();
				}
				System.out.println("�ı� ���� ����!!");
			}else{
				re=0;
			}
		} catch (Exception e) {
			System.out.println("deleteReview()����"+e);
		} finally {
			closeDB();
		}//try catch finally
		return re;
	}//deleteComment()
	
	
	
	
}//reviewDAO class
