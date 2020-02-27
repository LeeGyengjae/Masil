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
		return 0;
		
	}//insertProduct
	
	public List<Map<String,String>> reviewList(String code, int pageNum) {
		System.out.println("review service page");
		List<Map<String,String>> reviewList = reviewDAO.selectReview(code, pageNum);
		return reviewList;
	}
	
	
	
}
