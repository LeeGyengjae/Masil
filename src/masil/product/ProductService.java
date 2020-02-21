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
	
	public int insertProduct(Map productMap) {
		Pro_detailVO detailVO = new Pro_detailVO();
		Map detailMap = new HashMap();
		Iterator<String> keys = productMap.keySet().iterator();
		
		int p = Integer.parseInt(productMap.get("period").toString());
		
		for ( int i=0; i<productMap.size(); i++ ) {
		    String key = keys.next();
		    
		    if(key.contains("_")){
		    	int idx = key.indexOf("_");
		    	
	    		String ss = key.substring(0,idx);
	    		for(int j=0; j<ss.length(); j++){
	    			char tmp = ss.charAt(i);
	    			if(Character.isDigit(tmp)){
	    				//↓ error. 다른방법으로 해야됨
//	    				for(int k=0; k<=p; k++){	
	    					detailMap.put(key.substring(idx+1), productMap.get(key));
	    					productMap.put("dayMap"+tmp, detailMap);
//	    				}
	    			}
	    		}//for
	    		
//	    		System.out.println("test : "+key.substring(0, idx));
//				System.out.println("test2 : "+key.substring(idx+1));
				
				
				//detailMap.put(key.substring(idx+1), productMap.get(key));
		    
		    }//if
	    	
	    }//while
		
		for(int a=0; a<productMap.size(); a++){
			System.out.println("detailMap : "+detailMap);
			System.out.println("productMap : "+productMap);
		}
		    
//		    System.out.println("(productMap.get(code) : "+(productMap.get("code")).toString());
//		    System.out.println("day : "+detailMap.get("day").toString());
//		    System.out.println("content : "+detailMap.get("day_content").toString());
//		    System.out.println("course : "+detailMap.get("day_course").toString());
//		    System.out.println("dattitle : "+detailMap.get("day_title").toString());
//		    System.out.println("imgcontnet : "+detailMap.get("img_content").toString());
//		    System.out.println("meal : "+detailMap.get("meal").toString());
//		    System.out.println("stay : "+detailMap.get("stay").toString());
//		    
//		    detailVO.setCode(productMap.get("code").toString());
//		    detailVO.setDay(detailMap.get("day").toString());
//		    detailVO.setDay_content(detailMap.get("day_content").toString());
//		    detailVO.setDay_course(detailMap.get("day_course").toString());
//		    detailVO.setDay_title(detailMap.get("day_title").toString());
//		    //detailVO.setImage(detailMap.get("image").toString());
//		    detailVO.setImage("test");
//		    detailVO.setImg_content(detailMap.get("img_content").toString());
//		    detailVO.setMeal(detailMap.get("meal").toString());
//		    detailVO.setStay(detailMap.get("stay").toString());
		    
		
//		System.out.println("detailMap : "+detailMap);
//		System.out.println("detailVO : "+detailVO.toString());
//		System.out.println("detailMap.get(day).toString() : "+detailMap.get("day").toString());
		
		int re = productDAO.insertProduct(productMap, detailVO);
		System.out.println("Service : 호출끝");
		return 0;
		
	}//insertProduct
	
}
