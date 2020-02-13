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
			sql= "select c.price, b.image, c.title, a.comment, a.period, d.recnt, c.start_date, a.code, c.sub_code"
					+" from product a join (select image, code from pro_detail where img_boss='Y') b"
					+" on a.code=b.code"
					+" join pro_write c"
					+" on b.code=c.code"
					+" join (select count(*) recnt, code from review group by code) d"
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
				proJoinRe.put("price", rs.getString("price"));			
				proJoinRe.put("image", rs.getString("image"));			
				proJoinRe.put("title", rs.getString("title"));			
				proJoinRe.put("comment", rs.getString("comment"));			
				proJoinRe.put("period", rs.getString("period"));			
				proJoinRe.put("recnt", rs.getString("recnt"));			
				proJoinRe.put("start_date", rs.getString("start_date"));			
				proJoinRe.put("code", rs.getString("code"));			
				proJoinRe.put("sub_code", rs.getString("sub_code"));			
				
				productList.add(proJoinRe);
			}
			System.out.println("selectAllProducts()����!!");
		} catch (Exception e) {
			System.out.println("selectAllProducts()���� : "+e);
		} finally { closeDB(); }
		return productList;
	}//selectAllProducts()

	//��ǰ ���������� ��� - ��ǰ �Ѱ��� ��������
	public List<Map<String,String>> selectProduct(String sub_code) {
		List<Map<String,String>> productDetail = new ArrayList<Map<String,String>>();
		try {
			conn = getConnection();
			sql = "select b.code, b.sub_code, a.continent, a.course, a.period, b.start_date, b.end_date,"
					+" c.day, c.day_content, c.image, c.img_content, b.title, c.day_area"
					+" from product a join pro_write b"
					+" on a.code=b.code"
					+" join pro_detail c"
					+" on b.code=c.code"
					+" where b.sub_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sub_code);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//��� Map�� ��Ƽ� Service�� ������
				Map<String, String> sub_product = new HashMap<String,String>();
				sub_product.put("code", rs.getString("code"));
				sub_product.put("sub_code", rs.getString("sub_code"));
				sub_product.put("continent", rs.getString("continent"));
				sub_product.put("course", rs.getString("course"));
				sub_product.put("period", rs.getString("period"));
				sub_product.put("start_date", rs.getDate("start_date").toString());
				sub_product.put("end_date", rs.getDate("end_date").toString());
				sub_product.put("day", rs.getString("day"));
				sub_product.put("day_content", rs.getString("day_content"));
				sub_product.put("image", rs.getString("image"));
				sub_product.put("img_content", rs.getString("img_content"));
				sub_product.put("title", rs.getString("title"));
				sub_product.put("day_area", rs.getString("day_area"));
				productDetail.add(sub_product);
			}
			System.out.println("selectProduct()����!!");
		} catch (Exception e) {
			System.out.println("selectProduct() ���� "+e);
		} finally { closeDB(); }
		return productDetail;
	}

	
	
}
