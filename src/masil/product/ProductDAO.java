package masil.product;

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

	//상품 전체 목록(리스트)
	public List<Map<String,String>> selectAllProducts() {
		List<Map<String,String>> productList = new ArrayList<Map<String,String>>();
		try {
			conn=getConnection();
			sql= "select a.code, c.sub_code, a.continent, a.period, a.course, a.comment,"
					+" b.image, c.title, c.start_date, c.end_date, c.price, d.recnt, d.rating"
					+" from product a join (select image, code from pro_detail where img_boss='Y') b"
					+" on a.code=b.code"
					+" join pro_write c"
					+" on b.code=c.code"
					+" join (select code, count(*) recnt, round(avg(rating),1) rating from review group by code) d"
					+" on c.code = d.code"
					+" order by c.start_date";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				/* VO에 담기 -> VO클래스 만들어야함.
				Product_joinVO productjoinVO = new Product_joinVO();
				productjoinVO.setPrice(rs.getString("price"));
				productjoinVO.setImage(rs.getString("image"));
				productjoinVO.setTitle(rs.getString("title"));
				productjoinVO.setComment(rs.getString("comment"));
				productjoinVO.setPeriod(rs.getString("period"));
				*/
				//Map에 담기
				Map<String, String> proJoinRe = new HashMap<String,String>();
				proJoinRe.put("code", rs.getString("code"));			
				proJoinRe.put("sub_code", rs.getString("sub_code"));
				proJoinRe.put("continent", rs.getString("continent"));
				proJoinRe.put("period", rs.getString("period"));
				proJoinRe.put("course", rs.getString("course"));
				proJoinRe.put("comment", rs.getString("comment"));
				proJoinRe.put("image", rs.getString("image"));
				proJoinRe.put("title", rs.getString("title"));			
				proJoinRe.put("start_date", rs.getDate("start_date").toString());			
				proJoinRe.put("end_date", rs.getDate("end_date").toString());			
				proJoinRe.put("price", rs.getString("price"));			
				proJoinRe.put("recnt", rs.getString("recnt"));
				proJoinRe.put("rating", rs.getString("rating"));
				
				productList.add(proJoinRe);
			}
			System.out.println("selectAllProducts()성공!!");
		} catch (Exception e) {
			System.out.println("selectAllProducts()오류 : "+e);
		} finally { closeDB(); }
		return productList;
	}//selectAllProducts()

	//상품 상세페이지로 출력 - 상품 한개씩 가져오기
	public List<Map<String,String>> selectProduct(String code, String sub_code) {
		List<Map<String,String>> productDetail = new ArrayList<Map<String,String>>();
		try {
			conn = getConnection();
			sql= "select a.code, c.sub_code, a.continent, a.course, a.period, a.comment, "
				+" b.day, b.day_title, b.day_course, b.day_content, b.stay, b.meal, b.image, b.img_content,"
				+" c.title, c.start_date, c.end_date, c.max_num, c.curr_num, c.price, c.visible"
				+" from product a join pro_detail b"
				+" on a.code=b.code"
				+" join pro_write c"
				+" on b.code=c.code"
				+" where a.code=? and c.sub_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, sub_code);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//결과 Map에 담아서 Service로 보내기
				Map<String, String> sub_product = new HashMap<String,String>();
				sub_product.put("code", rs.getString("code"));
				sub_product.put("sub_code", rs.getString("sub_code"));
				sub_product.put("continent", rs.getString("continent"));
				sub_product.put("course", rs.getString("course"));
				sub_product.put("period", rs.getString("period"));
				sub_product.put("comment", rs.getString("comment"));
				sub_product.put("day", rs.getString("day"));
				sub_product.put("day_title", rs.getString("day_title"));
				sub_product.put("day_course", rs.getString("day_course"));
				sub_product.put("day_content", rs.getString("day_content"));
				sub_product.put("stay", rs.getString("stay"));
				sub_product.put("meal", rs.getString("meal"));
				sub_product.put("image", rs.getString("image"));
				sub_product.put("img_content", rs.getString("img_content"));
				sub_product.put("title", rs.getString("title"));
				sub_product.put("start_date", rs.getDate("start_date").toString());
				sub_product.put("end_date", rs.getDate("end_date").toString());
				sub_product.put("max_num", rs.getString("max_num")); 
				sub_product.put("curr_num", rs.getString("curr_num")); 
				sub_product.put("price", rs.getString("price")); 
				sub_product.put("visible", rs.getString("visible")); 
				productDetail.add(sub_product);
			}
			System.out.println("selectProduct()성공!!");
		} catch (Exception e) {
			System.out.println("selectProduct() 오류 "+e);
		} finally { closeDB(); }
		return productDetail;
	}//selectProduct()

//	public int insertProduct(ProductVO productVO, Pro_writeVO prowriteVO, Pro_detailVO prodetailVO) {
//		int re=0;
//		try {
//			conn = getConnection();
//			sql ="insert into product (code, continent, period, course, comment)"
//				+ " values (?,?,?,?,?)";
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, productVO.getCode());
//			pstmt.setString(2, productVO.getContinent());
//			pstmt.setString(3, productVO.getPeriod());
//			pstmt.setString(4, productVO.getCourse());
//			pstmt.setString(5, productVO.getComment());
//			re = pstmt.executeUpdate();
//			System.out.println("sql 1 성공? re : "+re);
//			
//			sql = "insert into pro_detail (code, day, day_title, day_course, stay, meal, day_content, image, img_content)"
//				+ "values (?,?,?,?,?,?,?,?,?)";
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, productVO.getCode());
//			pstmt.setString(2, prodetailVO.getDay());
//			pstmt.setString(3, prodetailVO.getDay_title());
//			pstmt.setString(4, prodetailVO.getDay_course());
//			pstmt.setString(5, prodetailVO.getStay());
//			pstmt.setString(6, prodetailVO.getMeal());
//			pstmt.setString(7, prodetailVO.getDay_content());
//			pstmt.setString(8, prodetailVO.getImage().toString());
//			pstmt.setString(9, prodetailVO.getImg_content());
//			re = pstmt.executeUpdate();
//			System.out.println("sql 2성공? re : "+re);
//			
//			sql = "insert into pro_write (code, sub_code, title, start_date, end_date, max_num, price)"
//				+ "values (?,?,?,?,?,?,?)";
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, productVO.getCode());
//			pstmt.setString(2, prowriteVO.getSub_code());
//			pstmt.setString(3, prowriteVO.getTitle());
////			pstmt.setDate(4, prowriteVO.getStart_date());
////			pstmt.setDate(5, prowriteVO.getEnd_date());
//			
//			pstmt.setDate(4, java.sql.Date.valueOf(prowriteVO.getStart_date()));
//			pstmt.setDate(5, java.sql.Date.valueOf(prowriteVO.getEnd_date()));
//			
//			
//			pstmt.setInt(6, prowriteVO.getMax_num());
//			pstmt.setInt(7, prowriteVO.getPrice());
//			re = pstmt.executeUpdate();
//			System.out.println("sql 3성공? re : "+re);
//			
//		} catch (Exception e) {
//			System.out.println("insertProduct() 오류 "+e);
//		} finally { closeDB(); }
//		
//		return re;
//		
//	}//insertProduct
	
	public int insertProduct(Map productMap, Pro_detailVO detailVO) {
		int re=0;
		try {
			conn = getConnection();
			sql ="insert into product (code, continent, period, course, comment)"
				+ " values (?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productMap.get("code").toString());
			pstmt.setString(2, productMap.get("continent").toString());
			pstmt.setString(3, productMap.get("period").toString());
			pstmt.setString(4, productMap.get("course").toString());
			pstmt.setString(5, productMap.get("comment").toString());
			re = pstmt.executeUpdate();
			System.out.println("sql 1 성공 re : "+re);
			
			sql = "insert into pro_detail (code, day, day_title, day_course, stay, meal, day_content, image, img_content)"
				+ "values (?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, detailVO.getCode());
			pstmt.setString(2, detailVO.getDay());
			pstmt.setString(3, detailVO.getDay_title());
			pstmt.setString(4, detailVO.getDay_course());
			pstmt.setString(5, detailVO.getStay());
			pstmt.setString(6, detailVO.getMeal());
			pstmt.setString(7, detailVO.getDay_content());
			pstmt.setString(8, detailVO.getImage().toString());
			pstmt.setString(9, detailVO.getImg_content());
			re = pstmt.executeUpdate();
			System.out.println("sql 2성공 re : "+re);
			
			sql = "insert into pro_write (code, sub_code, title, start_date, end_date, max_num, price)"
				+ "values (?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productMap.get("code").toString());
			pstmt.setString(2, productMap.get("sub_code").toString());
			pstmt.setString(3, productMap.get("title").toString());
//			pstmt.setDate(4, prowriteVO.getStart_date());
//			pstmt.setDate(5, prowriteVO.getEnd_date());
			
			//pstmt.setDate(4, java.sql.Date.valueOf(productMap.getStart_date()));
			//pstmt.setDate(5, java.sql.Date.valueOf(productMap.getEnd_date()));
			pstmt.setString(4, productMap.get("start_date").toString());
			pstmt.setString(5, productMap.get("end_date").toString());
			
			pstmt.setString(6, productMap.get("max_num").toString());
			pstmt.setString(7, productMap.get("price").toString());
			re = pstmt.executeUpdate();
			System.out.println("sql 3성공 re : "+re);
			
		} catch (Exception e) {
			System.out.println("insertProduct() 오류 "+e);
		} finally { closeDB(); }
		
		return re;
		
	}//insertProduct
	
	
	
	
}
