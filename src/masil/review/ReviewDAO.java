package masil.review;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
		try {
			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/masil");
			conn=ds.getConnection();
			System.out.println("DB연결 성공~!!");
		} catch (Exception e) {
			System.out.println("DB연결 실패 "+e);
		}
		return conn;
	}//getConnection()
	
	//DB연결 해제
	private void closeDB(){
		try {//최초사용되었던 순서 역순으로 닫음
			if(rs!=null) rs.close(); 
			if(pstmt!=null) pstmt.close(); 
			if(conn!=null) conn.close();
			System.out.println("DB연결 해제 성공~!!");
		} catch (Exception e) {
			System.out.println("DB연결 해제 실패 "+e);
		}
	}//closeDB

	//상품 상세페이지에서 후기 출력하기
	public List<Map<String,String>> selectReview(String code, int pageNum) {
		List<Map<String,String>> reviewList = new ArrayList<Map<String,String>>();
		try {
			conn=getConnection();
			sql = "select a.code, a.id, a.content, a.write_date, a.rating, a.end_date, a.idx, b.reviewCnt"
				+ " from review a join (select count(code) reviewCnt, code from review where code=?) b"
				+ " on a.code=b.code"
				+ " where a.code=?";
//				+ " where a.code=? limit ?,5";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, code);
//			pstmt.setInt(3, pageNum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Map<String, String> reviewMap = new HashMap<String, String>();
				reviewMap.put("id", rs.getString("id"));
				reviewMap.put("content", rs.getString("content"));
				reviewMap.put("write_date", rs.getDate("write_date").toString());
				reviewMap.put("rating", Integer.toString(rs.getInt("rating")));
				reviewMap.put("end_date", rs.getDate("end_date").toString());
				reviewMap.put("reviewCnt", rs.getString("reviewCnt"));
				reviewMap.put("idx", rs.getString("idx"));
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
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
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
		System.out.println("reviewVO.getId() : "+reviewVO.getId());
		int re=0;
		try {
			conn=getConnection();
			sql="select * from review where code=? and idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reviewVO.getCode());
			pstmt.setInt(2, reviewVO.getIdx());
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(reviewVO.getId().equals("master")){	//문제성 후기 작성시 관리자가 수정 버튼 클릭하여 내용 가릴 수 있도록함
					sql="update review set content=? where code=? and idx=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "관리자에 의해 가려진 후기입니다");
					pstmt.setString(2, reviewVO.getCode());
					pstmt.setInt(3, reviewVO.getIdx());
					re=pstmt.executeUpdate();
					if(re==1)	System.out.println("관리자 권한 후기 수정 작업 완료");
					else System.out.println("관리자 권한 후기 수정 실패ㅜㅜㅜ");
				}else if(reviewVO.getId().equals(rs.getString("id"))){	//후기 쓴 회원이 수정 버튼 클릭시
					sql="update review set content=?, rating=? where code=? and id=? and idx=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getContent());
					pstmt.setInt(2, reviewVO.getRating());
					pstmt.setString(3, reviewVO.getCode());
					pstmt.setString(4, reviewVO.getId());
					pstmt.setInt(5, reviewVO.getIdx());
					re=pstmt.executeUpdate();
					if(re==1)	System.out.println("후기 수정 작업 완료");
					else System.out.println("후기 수정 실패 ㅜㅜ");
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
				if(reviewVO.getId().equals("master")){
					sql="delete from review where code=? and id=? and idx=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getCode());
					pstmt.setString(2, reviewVO.getId());
					pstmt.setInt(3, reviewVO.getIdx());
					re=pstmt.executeUpdate();
				}else if(reviewVO.getId().equals(rs.getString("id"))){
					sql="delete from review where code=? and id=? and idx=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, reviewVO.getCode());	
					pstmt.setString(2, reviewVO.getId());
					pstmt.setInt(3, reviewVO.getIdx());
					re=pstmt.executeUpdate();
				}
				if(re==1)	System.out.println("후기 삭제 성공!!");
				else System.out.println("후기 삭제 실패");
			}else{
				re=0;
			}
		} catch (Exception e) {
			System.out.println("deleteReview()오류"+e);
		} finally { closeDB(); }
		return re;
	}//deleteComment()
	
	//후기 작성 권한 확인
	public String insertReviewAuth(String id, String sub_code) {
		String user_endDate = "";
		try {
			conn=getConnection();
			sql = "select a.id, a.sub_code, b.end_date"
				+" from res_table a join pro_write b"
				+" on a.sub_code=b.sub_code"
				+" where a.id=? and a.sub_code=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, sub_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user_endDate=rs.getDate("end_date").toString();
			}
		} catch (Exception e) {
			System.out.println("insertReviewAuth() 실패 "+e);
		} finally { closeDB(); }
		return user_endDate;
	}//insertReviewAuth()
	
	//자신이 쓴 후기 조회
	public List<ReviewVO> selReview(String id){
		List<ReviewVO> revList = new ArrayList<ReviewVO>();
		ReviewVO reVO = null;
		try {
			conn=getConnection();
			sql = "select * from review where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				String content = rs.getString("content");
				String write_date = rs.getString("write_date");
				int rating = rs.getInt("rating");
				String end_date = rs.getString("end_date");
				int idx = rs.getInt("idx");
				String code= rs.getString("code");
				reVO = new ReviewVO(idx, code, id, content, write_date, rating, end_date);
				revList.add(reVO);
				System.out.println("후기 조회 성공");
			}
		} catch (Exception e) {
			System.out.println("selReview() 실패 "+e);
		} finally { closeDB(); }
		return revList;
	}//selReview()

	//후기 조회
	public List<ReviewVO> selReview2(ReviewVO reviewVO) {
		List<ReviewVO> revList = new ArrayList<ReviewVO>();
		ReviewVO reVO = null;
		try {
			conn=getConnection();
			sql = "select * from review where id=? and idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reviewVO.getId());
			pstmt.setInt(2, reviewVO.getIdx());
			rs=pstmt.executeQuery();
			while(rs.next()){
				String content = rs.getString("content");
				String write_date = rs.getString("write_date");
				int rating = rs.getInt("rating");
				String end_date = rs.getString("end_date");
				int idx = rs.getInt("idx");
				String code= rs.getString("code");
				String id= rs.getString("id");
				reVO = new ReviewVO(idx, code, id, content, write_date, rating, end_date);
				System.out.println("reVO : "+reVO.toString());
				revList.add(reVO);
				System.out.println("후기 조회 성공");
			}
		} catch (Exception e) {
			System.out.println("selReview() 실패 "+e);
		} finally { closeDB(); }
		return revList;
	}//selReview2()
	
	
}//reviewDAO class
