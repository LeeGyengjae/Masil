
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import masil.product.Pro_detailVO;
import masil.product.Pro_writeVO;
import masil.product.ProductService;
import masil.product.ProductVO;
import masil.review.ReviewService;
import masil.user.UserDAO;
import masil.user.UserVO;


@WebServlet("/main.do")
public class Main extends HttpServlet {
	
    ProductService productService;
    ProductVO productVO;
    Pro_detailVO prodetailVO;
    Pro_writeVO prowriteVO;
    
    ReviewService reviewService;
    
	public Main() {}
	
	@Override
	public void init() throws ServletException {
		productService = new ProductService();
		reviewService = new ReviewService();
	}
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nextPage="";
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action );
		
		try {
			List<Map<String,String>> productList;
			List<Map<String,String>> productDetail;
			List<Map<String,String>> reviewList;
			int countProduct=0;
			UserDAO userDAO = new UserDAO(); 
			
			if(action==null){
				productList = productService.listProducts();
				countProduct = productService.countProduct();
				request.setAttribute("productList", productList);
				request.setAttribute("countProduct", countProduct);
				nextPage = "/index.jsp";
			}	
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}//doHandle()

	
	
	
}//ProductController class
