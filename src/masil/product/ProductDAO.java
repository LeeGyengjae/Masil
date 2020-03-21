package masil.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//DB�۾�
public class ProductDAO {

	//DB���� ���� ���� ����
	private Connection conn;
	private Context init;
	private PreparedStatement pstmt;
	private DataSource ds;
	private ResultSet rs;
	private String sql;
	
	//�⺻ ������
	public ProductDAO() {
		
	}//�⺻ ������
	
	//DB���� �޼ҵ� getConnecion()
	private Connection getConnection() {
		conn =null;
		try {
			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/masil");
			conn=ds.getConnection();
			System.out.println("DB���� ����~!!");
		} catch (Exception e) {
			System.out.println("DB���� ���� "+e);
		}
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
				+" substring_index(b.image, ',',1) image, c.title, c.start_date, c.end_date, c.price, d.recnt, d.rating"
				+" from product a join (select image, code from pro_detail group by code) b"
				+" on a.code=b.code"
				+" join pro_write c"
				+" on b.code=c.code"
				+" left join (select code, count(*) recnt, round(avg(rating),1) rating from review group by code) d"
				+" on c.code = d.code"
				+" where c.visible='Y'"
				+" order by c.start_date desc";
//				+" order by c.start_date desc limit ?,5";
			pstmt=conn.prepareStatement(sql);
//			pstmt.setInt(1, pageNum);
			rs=pstmt.executeQuery();
			while(rs.next()){
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
	
	//�Խõ� ��ǰ�� ���
	public int countProduct(){
		int count=0;
		try {
			conn=getConnection();
			sql="select count(*) from pro_write where visible='Y'";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count=Integer.parseInt(rs.getString(1));
			else count=0;
		} catch (Exception e) {
			System.out.println("countProduct()���� : "+e);
		} finally { closeDB(); }
		return count;
	}

	//��ǰ ���������� ��� - ��ǰ �Ѱ��� ��������
	public List<Map<String,String>> selectProduct(String code, String sub_code) {
		List<Map<String,String>> productDetail = new ArrayList<Map<String,String>>();
		try {
			conn = getConnection();
			sql= "select a.code, c.sub_code, a.continent, a.course, a.period, a.comment, "
				+" b.day, b.day_title, b.day_course, b.day_content, b.stay, b.meal, b.image, b.img_content,"
				+" c.title, c.start_date, c.end_date, c.max_num, c.curr_num, c.price, c.visible, d.rating"
				+" from product a join pro_detail b"
				+" on a.code=b.code"
				+" join pro_write c"
				+" on b.code=c.code"
				+" left join (select code, count(*) recnt, round(avg(rating),1) rating from review group by code) d"
				+" on c.code = d.code"
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
				sub_product.put("rating", rs.getString("rating")); 
				productDetail.add(sub_product);
			}
			System.out.println("selectProduct()����!!");
		} catch (Exception e) {
			System.out.println("selectProduct() ���� "+e);
		} finally { closeDB(); }
		return productDetail;
	}//selectProduct()

	//�� ��ǰ ���ε�
	public int insertProduct(Map<String, Object> productMap) {
		System.out.println("insertProduct����");
		System.out.println("DAO productMap : "+productMap);
		int re=0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); //Ʈ����� ó�� ���� auto commit off
			sql ="insert into product (code, continent, period, course, comment)"
				+ " values (?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productMap.get("code").toString());
			pstmt.setString(2, productMap.get("continent").toString());
			pstmt.setString(3, productMap.get("period").toString());
			pstmt.setString(4, productMap.get("course").toString());
			pstmt.setString(5, productMap.get("comment").toString());
			re = pstmt.executeUpdate();
			System.out.println("sql 1 ���� re : "+re);
			
			if(productMap.get("image") !=null || productMap.get("imgContent") != null){
				System.out.println("�̹��� ����");
				sql = "insert into pro_detail (code, day, day_title, day_course, stay, meal, day_content, image, img_content)"
						+ "values (?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
			}else {
				sql = "insert into pro_detail (code, day, day_title, day_course, stay, meal, day_content)"
					+ "values (?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
			}
			
			if(Integer.parseInt(productMap.get("period").toString()) > 1){
				for(int j=0;j<Integer.parseInt(productMap.get("period").toString()); j++){
				
			    	String[] day = (String[]) productMap.get("day");
			    	String[] day_title = (String[]) productMap.get("dayTitle");
			    	String[] day_course = (String[]) productMap.get("dayCourse");
			    	String[] stay = (String[]) productMap.get("stay");
			    	String[] meal = (String[]) productMap.get("meal");
			    	String[] day_content = (String[]) productMap.get("dayContent");
			    	
		    		pstmt.setString(1, productMap.get("code").toString());
		    		pstmt.setString(2, day[j]);
		    		pstmt.setString(3, day_title[j]);
		    		pstmt.setString(4, day_course[j]);
		    		pstmt.setString(5, stay[j]);
		    		pstmt.setString(6, meal[j]);
		    		pstmt.setString(7, day_content[j]);

		    		if(productMap.get("image") != null || productMap.get("imgContent") != null ){
		    			//���� �ø� �̹��� ������
		    			String[] img_content = (String[]) productMap.get("imgContent");
		    			
		    			Map<String,String> imageMap = (HashMap<String,String>) productMap.get("image");
						pstmt.setString(8, imageMap.get(j+"_image").toString());
						pstmt.setString(9, img_content[j]);
		    		}
					re = pstmt.executeUpdate();
		    		System.out.println("sql 2���� re : "+re);
					
				}//for
				
			}else if(Integer.parseInt(productMap.get("period").toString())==1){
				pstmt.setString(1, productMap.get("code").toString());
	    		pstmt.setString(2, productMap.get("day").toString());
	    		pstmt.setString(3, productMap.get("dayTitle").toString());
	    		pstmt.setString(4, productMap.get("dayCourse").toString());
	    		pstmt.setString(5, productMap.get("stay").toString());
	    		pstmt.setString(6, productMap.get("meal").toString());
	    		pstmt.setString(7, productMap.get("dayContent").toString());
	    		
	    		if(productMap.get("image") != null || productMap.get("imgContent") != null ){
	    			//���� �ø� �̹��� ������
	    			System.out.println("�̹��� ����");
	    			Map<String,String> imageMap = (HashMap<String,String>) productMap.get("image");
					pstmt.setString(8, imageMap.get(1+"_image").toString());
					pstmt.setString(9, productMap.get("imgContent").toString());
	    		}
				re = pstmt.executeUpdate();
	    		System.out.println("sql 2���� re : "+re);
			}
			
			sql = "insert into pro_write (code, sub_code, title, start_date, end_date, max_num, price)"
				+ "values (?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productMap.get("code").toString());
			pstmt.setString(2, productMap.get("subCode").toString());
			pstmt.setString(3, productMap.get("title").toString());
			pstmt.setString(4, productMap.get("startDate").toString());
			pstmt.setString(5, productMap.get("endDate").toString());
			pstmt.setString(6, productMap.get("maxNum").toString());
			pstmt.setString(7, productMap.get("price").toString());
			re = pstmt.executeUpdate();
			System.out.println("sql 3���� re : "+re);
			conn.commit();    //Ʈ����� ����, commit�ϱ�
			
		} catch (Exception e) {
			if(conn!=null) { //���� �߻��� �ѹ� ó��
				try {
					conn.rollback();
				} catch (Exception e2) {
					System.out.println("conn.rollback() ����"+e2);
				}
			}
			System.out.println("insertProduct() ���� "+e);
		} finally { 
			try {
				conn.setAutoCommit(true);	//�ٽ� auto Commit on
			} catch (SQLException e) {
				System.out.println("conn.setAutoCommit(true) ���� "+e);
			}    
			closeDB(); 
		}
		return re;
	}//insertProduct
	
	//���� ��ǰ���� ���/���� �� ��¥�� �ٲ㼭 �� ��ǰ���� ����� �� ���
	public int insertProduct2(Map<String, Object> productMap) {
		System.out.println("insertProduct2����");
		System.out.println("DAO productMap : "+productMap);
		int re=0;
		try {
			conn = getConnection();
			sql = "insert into pro_write (code, sub_code, title, start_date, end_date, max_num, price)"
				+ "values (?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productMap.get("code").toString());
			pstmt.setString(2, productMap.get("subCode").toString());
			pstmt.setString(3, productMap.get("title").toString());
			pstmt.setString(4, productMap.get("startDate").toString());
			pstmt.setString(5, productMap.get("endDate").toString());
			pstmt.setString(6, productMap.get("maxNum").toString());
			pstmt.setString(7, productMap.get("price").toString());
			re = pstmt.executeUpdate();
			if(re==1) System.out.println("sql 3���� re : "+re);
			else System.out.println("��� ����");
			
		} catch (Exception e) {
			System.out.println("insertProduct() ���� "+e);
		} finally { closeDB(); }
		return re;
	}//insertProduct2
	
	//��ǰ����
	public int updateProduct(Map<String, Object> productMap) {
		System.out.println("updateProduct����");
		System.out.println("DAO productMap : "+productMap);
		int re=0;
		try {
			System.out.println("updateProduct try-catch ���� ����");
			
			conn = getConnection();
			conn.setAutoCommit(false); //Ʈ����� ó�� ���� auto commit off
			
			sql ="update product set continent=?, period=?, course=?, comment=?"
					+ " where code=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, productMap.get("continent").toString());
			pstmt.setString(2, productMap.get("period").toString());
			pstmt.setString(3, productMap.get("course").toString());
			pstmt.setString(4, productMap.get("comment").toString());
			pstmt.setString(5, productMap.get("code").toString());
				
			re = pstmt.executeUpdate();
			if(re==1)
				System.out.println("sql 1 ���� re : "+re);
			else re=0;
			
			System.out.println("productMap.get(image) : "+productMap.get("image"));
			System.out.println("productMap.get(image_content) : "+productMap.get("image_content"));
			System.out.println("productMap.get(old_image) : "+productMap.get("old_image"));
			
			sql = "update pro_detail set day_title=?, day_course=?, stay=?, meal=?, day_content=?";
			if(productMap.get("image") != null || productMap.get("image_content") != null || productMap.get("old_image") != null){
				sql += ", image=?, img_content=?";
			}
			sql += " where code=? and day=?";
			
			pstmt=conn.prepareStatement(sql);
			
			if(Integer.parseInt(productMap.get("period").toString()) > 1){
				System.out.println("productMap.get(period).toString() : "+productMap.get("period").toString());
				
				for(int j=0;j<Integer.parseInt(productMap.get("period").toString()); j++){
					System.out.println("for�� ����");
					System.out.println("productMap.get(day) : "+productMap.get("day"));
					
			    	String[] day = (String[]) productMap.get("day");
			    	String[] day_title = (String[]) productMap.get("dayTitle");
			    	String[] day_course = (String[]) productMap.get("dayCourse");
			    	String[] stay = (String[]) productMap.get("stay");
			    	String[] meal = (String[]) productMap.get("meal");
			    	String[] day_content = (String[]) productMap.get("dayContent");
			    	
			    	System.out.println("day : "+day[j]);
			    	
		    		pstmt.setString(1, day_title[j]);
		    		pstmt.setString(2, day_course[j]);
		    		pstmt.setString(3, stay[j]);
		    		pstmt.setString(4, meal[j]);
		    		pstmt.setString(5, day_content[j]);
		    		
		    		if(productMap.get("old_image") == null && productMap.get("image") == null ){
		    			//���� �̹��� ���� ���� �ø� �̹��� ������
			    		pstmt.setString(6, productMap.get("code").toString());
			    		pstmt.setString(7, day[j]);
//			    		System.out.println("���� �̹��� ���� ���� �ø� �̹��� ������");
			    		
		    		}else{
		    			//���� �̹��� ������ Ȥ�� ���� �ø� �̹��� ������
		    			String[] img_content = (String[]) productMap.get("imgContent");
		    			
		    			if(productMap.get("image") != null){
		    				//���� �ø� �̹��� ������
			    			Map<String,String> imageMap = (HashMap<String,String>) productMap.get("image");
							pstmt.setString(6, imageMap.get((j+1)+"_image").toString());
		    			}
		    			
	    				//���� �ø� �̹� ������ -> ���� �̹����� �� �̹�����ô
		    			System.out.println("productMap.get(old_image).getClass() : "+productMap.get("old_image").getClass());
		    			
		    			if(!productMap.get("old_image").getClass().toString().equals("class java.lang.String")){
		    				String[] old_image = (String[]) productMap.get("old_image");
		    				pstmt.setString(6, old_image[j].toString());
		    			}else{
		    				pstmt.setString(6, productMap.get("old_image").toString());
		    			}
						pstmt.setString(7, img_content[j]);
						pstmt.setString(8, productMap.get("code").toString());
			    		pstmt.setString(9, day[j]);
		    		}
				}//for
			}else if(Integer.parseInt(productMap.get("period").toString())==1){
				
	    		pstmt.setString(1, productMap.get("dayTitle").toString());
	    		pstmt.setString(2, productMap.get("dayCourse").toString());
	    		pstmt.setString(3, productMap.get("stay").toString());
	    		pstmt.setString(4, productMap.get("meal").toString());
	    		pstmt.setString(5, productMap.get("dayContent").toString());
	    		
	    		if(productMap.get("old_image") == null && productMap.get("image") == null ){
	    			//���� �̹��� ���� ���� �ø� �̹��� ������
		    		pstmt.setString(6, productMap.get("code").toString());
		    		pstmt.setString(7, productMap.get("day").toString());
	    		}else{
	    			//���� �̹��� ������ 
	    			if(productMap.get("image") != null){
	    				//���� �ø� �̹��� ������
		    			Map<String,String> imageMap = (HashMap<String,String>) productMap.get("image");
						pstmt.setString(6, imageMap.get(1+"_image").toString());
	    			}else {
	    				//���� �ø� �̹� ������ -> ���� �̹����� �� �̹�����ô
		    			System.out.println("�Ⱓ1��, �̹��� Ȯ�� : "+productMap.get("old_image").toString());
		    			pstmt.setString(6, productMap.get("old_image").toString());
	    			}
    				pstmt.setString(7, productMap.get("imgContent").toString());
					pstmt.setString(8, productMap.get("code").toString());
					pstmt.setString(9, productMap.get("day").toString());
	    		}
			}
			re = pstmt.executeUpdate();
			if(re==1)
				System.out.println("sql 2���� re : "+re);
			else re=0;
				
			sql = "update pro_write set title=?, start_date=?, end_date=?, max_num=?, price=?"
				+ " where code=? and sub_code=?";
			pstmt=conn.prepareStatement(sql);
			
			System.out.println("productMap.get(title) : "+productMap.get("title"));
			
			pstmt.setString(1, productMap.get("title").toString());
			pstmt.setString(2, productMap.get("startDate").toString());
			pstmt.setString(3, productMap.get("endDate").toString());
			pstmt.setString(4, productMap.get("maxNum").toString());
			pstmt.setString(5, productMap.get("price").toString());
			pstmt.setString(6, productMap.get("code").toString());
			pstmt.setString(7, productMap.get("subCode").toString());
			
			re = pstmt.executeUpdate();
			if(re==1)
				System.out.println("sql 3���� re : "+re);
			else re=0;
			conn.commit();    //Ʈ����� ����, commit�ϱ�
			
		} catch (Exception e) {
			if(conn!=null) { //���� �߻��� �ѹ� ó��
				try {
					conn.rollback();
				} catch (Exception e2) {
					System.out.println("conn.rollback() ����"+e2);
				}
			}
			System.out.println("updateProduct() ���� "+e);
//			System.out.println("updateProduct() ���� ");
		} finally { 
			try {
				conn.setAutoCommit(true);	//�ٽ� auto Commit on
			} catch (SQLException e) {
				System.out.println("conn.setAutoCommit(true) ���� "+e);
			}    
			closeDB(); 
		}
		return re;
	}//updateProduct
	
	//��ǰ ���� - ������ ������ �ƴ϶� ������������ ��Ͽ��� ���⵵�� ��.
	public int deleteProduct(String code, String sub_code) {
		System.out.println("deleteProduct����");
		int re=0;
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
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, sub_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				sql = "update pro_write set visible=?"
					+ " where code=? and sub_code=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "N");
				pstmt.setString(2, code);
				pstmt.setString(3, sub_code);
				re = pstmt.executeUpdate();
				System.out.println("��ǰ ���� ���� : "+re);
			}
		} catch (Exception e) {
			System.out.println("updateProduct() ���� "+e);
		} finally { closeDB(); }
		return re;
	}//deleteProduct
	
	
	
	
}//productDAO
