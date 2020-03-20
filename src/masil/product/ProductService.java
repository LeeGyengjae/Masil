package masil.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import masil.review.ReviewDAO;

// View -> Controller -> [Service] -> DAO
public class ProductService {
	
	ProductDAO productDAO;
	ReviewDAO reviewDAO;
	
	public ProductService() {
		productDAO = new ProductDAO();
		reviewDAO = new ReviewDAO();
	}
	
	public List<Map<String,String>> listProducts() {
		List<Map<String,String>> productList = productDAO.selectAllProducts();
		return productList;
	}
 
	public List<Map<String,String>> viewProduct(String code, String sub_code) {
		List<Map<String,String>> productDetail = productDAO.selectProduct(code, sub_code);
		return productDetail;
	}
	
	public int insertProduct(Map<String, Object> productMap) {
		System.out.println("service 시작");
//		Iterator<String> keys = productMap.keySet().iterator();
//		for ( int i=0; i<productMap.size(); i++ ) {
//		    String key = keys.next();
//		    System.out.print("key : "+key);
//		    System.out.println("\t\tproductMap.get(key) : "+productMap.get(key));
//	    }//for
		int re = productDAO.insertProduct(productMap);
		System.out.println("Service : 호출끝");
		return re;
	}//insertProduct
	
	public int insertProduct2(Map<String, Object> productMap) {
		System.out.println("service 시작");
//		Iterator<String> keys = productMap.keySet().iterator();
//		for ( int i=0; i<productMap.size(); i++ ) {
//		    String key = keys.next();
//		    System.out.print("key : "+key);
//		    System.out.println("\t\tproductMap.get(key) : "+productMap.get(key));
//	    }//for
		int re = productDAO.insertProduct2(productMap);
		System.out.println("Service : 호출끝");
		return re;
	}//insertProduct
	
	public List<Map<String,String>> reviewList(String code, int pageNum) {
		System.out.println("reviewList 호출하는 service page");
		List<Map<String,String>> reviewList = reviewDAO.selectReview(code, pageNum);
		return reviewList;
	}

	public int updateProduct(Map<String, Object> productMap) {
		System.out.println("updateProduct Service : 호출");
		int re = productDAO.updateProduct(productMap);
		System.out.println("updateProduct Service : 끝");
		return re;
	}
	
	public int deleteProduct(String code, String sub_code) {
		int re = productDAO.deleteProduct(code, sub_code);
		System.out.println("Service : 호출끝");
		return re;
	}

	public int countProduct() {
		int countProduct = productDAO.countProduct();
		return countProduct;
	}
	
	
}
