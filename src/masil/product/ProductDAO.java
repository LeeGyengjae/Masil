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

//DB�۾�
public class ProductDAO {

	//DB���� ���� ���� ����
	Connection conn;
	Context init;
	PreparedStatement pstmt;
	DataSource ds;
	ResultSet rs;
	String sql;
	
	//�⺻ ������
	public ProductDAO() {
		
	}//�⺻ ������
	
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

	//��ǰ ��ü ���(����Ʈ)
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
				/* VO�� ��� -> VOŬ���� ��������.
				Product_joinVO productjoinVO = new Product_joinVO();
				productjoinVO.setPrice(rs.getString("price"));
				productjoinVO.setImage(rs.getString("image"));
				productjoinVO.setTitle(rs.getString("title"));
				productjoinVO.setComment(rs.getString("comment"));
				productjoinVO.setPeriod(rs.getString("period"));
				*/
				//Map�� ���
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
			System.out.println("selectAllProducts()����!!");
		} catch (Exception e) {
			System.out.println("selectAllProducts()���� : "+e);
		} finally { closeDB(); }
		return productList;
	}//selectAllProducts()

	//��ǰ ���������� ��� - ��ǰ �Ѱ��� ��������
	public List<Map<String,String>> selectProduct(String code, String sub_code) {
		List<Map<String,String>> productDetail = new ArrayList<Map<String,String>>();
		try {
			conn = getConnection();
			sql= "select a.code, c.sub_code, a.continent, a.course, a.period, a.comment, "
				+" b.day, b.day_title, b.area, b.day_content, b.stay, b.meal, b.image, b.img_content,"
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
				//��� Map�� ��Ƽ� Service�� ������
				Map<String, String> sub_product = new HashMap<String,String>();
				sub_product.put("code", rs.getString("code"));
				sub_product.put("sub_code", rs.getString("sub_code"));
				sub_product.put("continent", rs.getString("continent"));
				sub_product.put("course", rs.getString("course"));
				sub_product.put("period", rs.getString("period"));
				sub_product.put("comment", rs.getString("comment"));
				sub_product.put("day", rs.getString("day"));
				sub_product.put("day_title", rs.getString("day_title"));
				sub_product.put("area", rs.getString("area"));
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
			System.out.println("selectProduct()����!!");
		} catch (Exception e) {
			System.out.println("selectProduct() ���� "+e);
		} finally { closeDB(); }
		return productDetail;
	}//selectProduct()

	public void insertProduct(ProductVO productVO, Pro_writeVO prowriteVO, Pro_detailVO prodetailVO) {
		try {
			conn = getConnection();
			sql ="insert all"
				+" into product (values )"
				+" into pro_detail (values )"
				+" into pro_write (values )"
				+" select * from dual";
			
			
		} catch (Exception e) {
			System.out.println("insertProduct() ���� "+e);
		} finally { closeDB(); }
		
		
		
	}//insertProduct
	
	
	
	
}
