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

//DB작업
public class ReviewDAO {

	//DB연결 관련 변수 모음
	private Connection conn;
	private Context init;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;
	private String sql;
	
	//기본 생성자
	public ReviewDAO() { }
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

	//상품 상세페이지에서 후기 출력하기
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
			System.out.println("selectReview성공!!");
		} catch (Exception e) {
			System.out.println("selectReview() 실패 "+e);
		} finally { closeDB(); }
		return reviewList;
	}//selectReview(String code)

	//후기 입력
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
			System.out.println("insertReview()성공!!");
		} catch (Exception e) {
			System.out.println("insertReveiw() 실패 "+e);
		} finally { closeDB(); }
		return re;
	}//insertReview()
	
	//후기 수정
	public int updateReivew(ReviewVO reviewVO){
		int re=0;
		try {
			conn=getConnection();
			sql="select id from review where code=?";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(reviewVO.getId().equals("masiladmin")){	//문제성 후기 작성시 관리자가 수정 버튼 클릭하여 내용 가릴 수 있도록함
					sql="update review set content=? where code=? and id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "관리자에 의해 가려진 후기입니다");
					pstmt.setString(2, reviewVO.getCode());
					pstmt.setString(3, reviewVO.getId());
					re=pstmt.executeUpdate();
					System.out.println("관리자 권한 후기 수정 작업 완료");
				}else if(reviewVO.getId().equals(rs.getString("id"))){	//후기 쓴 회원이 수정 버튼 클릭시
					sql="update review set content=? where code=? and id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getContent());
					pstmt.setString(2, reviewVO.getCode());
					pstmt.setString(3, reviewVO.getId());
					re=pstmt.executeUpdate();
					System.out.println("후기 수정 작업 완료");
				}
			} else { re=0; }
		} catch (Exception e) {
			System.out.println("updateReivew() 실패 "+e);
		} finally { closeDB(); }
		return re;
	}//updateReview()
	
	//후기 삭제  
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
				System.out.println("후기 삭제 성공!!");
			}else{
				re=0;
			}
		} catch (Exception e) {
			System.out.println("deleteReview()오류"+e);
		} finally {
			closeDB();
		}//try catch finally
		return re;
	}//deleteComment()
	
	
	
	
}//reviewDAO class
