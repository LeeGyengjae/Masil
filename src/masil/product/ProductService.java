package masil.product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// View -> Controller -> [Service] -> DAO
public class ProductService {
	
	ProductDAO productDAO;
	
	public ProductService() {
		productDAO = new ProductDAO();
	}
	
	public List<Map<String,String>> listProducts() {
		List<Map<String,String>> productList = productDAO.selectAllProducts();
		return productList;
	}
 
	public List<Map<String,String>> viewProduct(String code, String sub_code) {
		List<Map<String,String>> productDetail = productDAO.selectProduct(code, sub_code);
		System.out.println("Service : "+productDetail);
		return productDetail;
	}

//	public int insertProduct(ProductVO productVO, Pro_writeVO prowriteVO, Pro_detailVO prodetailVO) {
//		int re = productDAO.insertProduct(productVO,prowriteVO,prodetailVO);
//		System.out.println("Service : 호출");
//		return re;
//	}
	
//	public int insertProduct(Map<String, String> productMap) {
//		int re = productDAO.insertProduct(productMap);
//		System.out.println("Service : 호출");
//		return re;
//	}
	
	public int insertProduct(Map<String, String> productMap) {
		Pro_detailVO detailVO = new Pro_detailVO();
		Map detailMap = new HashMap();
		Iterator<String> keys = productMap.keySet().iterator();
		
		for ( int i=0; i<productMap.size(); i++ ) {
		    String key = keys.next();
		    
		    while(key.contains("_")){
		    	int idx = key.indexOf("_");
		    	boolean isNumber =false;
		    	try {
		    		Integer.parseInt(key.substring(0,idx));
		    		isNumber=true;
		    		if(isNumber){
		    			System.out.println("test : "+key.substring(0, idx));
				    	detailMap.put(key.substring(idx+1), productMap.get(key));
				    	System.out.println("test2 : "+key.substring(idx+1));
				    	
				    }//if 
				} catch (Exception e) {}
		    }//while
		    
		    System.out.println("(productMap.get(code) : "+(productMap.get("code")).toString());
		    System.out.println("day : "+detailMap.get("day").toString());
		    System.out.println("content : "+detailMap.get("day_content").toString());
		    System.out.println("course : "+detailMap.get("day_course").toString());
		    System.out.println("dattitle : "+detailMap.get("day_title").toString());
		    System.out.println("imgcontnet : "+detailMap.get("img_content").toString());
		    System.out.println("meal : "+detailMap.get("meal").toString());
		    System.out.println("stay : "+detailMap.get("stay").toString());
		    
		    detailVO.setCode(productMap.get("code"));
		    detailVO.setDay(detailMap.get("day").toString());
		    detailVO.setDay_content(detailMap.get("day_content").toString());
		    detailVO.setDay_course(detailMap.get("day_course").toString());
		    detailVO.setDay_title(detailMap.get("day_title").toString());
		    //detailVO.setImage(detailMap.get("image").toString());
		    detailVO.setImage("test");
		    detailVO.setImg_content(detailMap.get("img_content").toString());
		    detailVO.setMeal(detailMap.get("meal").toString());
		    detailVO.setStay(detailMap.get("stay").toString());
		    
		    
		}//for
		
		System.out.println("detailMap : "+detailMap);
		System.out.println("detailVO : "+detailVO.toString());
//		System.out.println("detailMap.get(day).toString() : "+detailMap.get("day").toString());
		
		int re = productDAO.insertProduct(productMap, detailVO);
		System.out.println("Service : 호출끝");
		return 0;
	}
	
}
