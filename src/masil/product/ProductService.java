package masil.product;

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
	
}
