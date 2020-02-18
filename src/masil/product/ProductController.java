package masil.product;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/product1/*")
public class ProductController extends HttpServlet {
	
    ProductService productService;
    ProductVO productVO;
    Pro_detailVO prodetailVO;
    Pro_writeVO prowriteVO;
    
	public ProductController() {}
	
	@Override
	public void init() throws ServletException {
		productService = new ProductService();
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
			
			if(action==null){
				productList = productService.listProducts();
				request.setAttribute("productList", productList);
				nextPage = "/product/product.jsp";
				
			}else if(action.equals("/product.do")){
				productList = productService.listProducts();
				request.setAttribute("productList", productList);
				nextPage = "/product/product.jsp";
				
			} 
			else if(action.equals("/blog.do")){
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				productDetail = productService.viewProduct(code, sub_code);
				request.setAttribute("productDetail", productDetail);
				nextPage = "/product/blog.jsp";
				
				System.out.println("controller : "+productDetail);
			}
			else if(action.equals("/pre_write.do")){
				//관리자용-상품 리스트 간단히 보기 
				//-작동은 하는데 쓸지말지 미지수 
				productList = productService.listProducts();
				request.setAttribute("productList", productList);
				nextPage = "/product/pre_write.jsp";
				
			}
			else if(action.equals("/callwrite.do")){
				//이미 등록된 상품의 출발/도착 날짜만 바꿔서 등록하고 싶을때
				//저장된 내용 불러와서 날짜만 바꿀 수 있도록 하는 페이지
				//-미완성
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				productDetail = productService.viewProduct(code, sub_code);
				request.setAttribute("productDetail", productDetail);

				nextPage = "/product/callwrite.jsp";
			}
			else if(action.equals("/write.do")){
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String title = request.getParameter("title");
				String continent = request.getParameter("continent");
				String course = request.getParameter("course");
				String period = request.getParameter("period");
				String comment = request.getParameter("comment");
				String startDateStr = request.getParameter("start_date");
				String endDateStr = request.getParameter("end_date");
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date start_date = (Date) dateformat.parse(startDateStr);
				Date end_date = (Date) dateformat.parse(endDateStr);
				int max_num = Integer.parseInt(request.getParameter("max_num"));
				int price = Integer.parseInt(request.getParameter("price"));
				
				productVO = new ProductVO(code, continent, period, course, comment); 
				prowriteVO = new Pro_writeVO(sub_code, title, start_date, end_date, max_num, price);
				
				int period2 = Integer.parseInt(period);
				for(int i=0; i<period2; i++){
					String day_title = request.getParameter(i+"day_title");
					String day_course = request.getParameter(i+"day_course");
					String stay = request.getParameter(i+"stay");
					String meal = request.getParameter(i+"meal");
					String day_content = request.getParameter(i+"day_content");
					String img_content = request.getParameter(i+"img_content");
					String[] image = request.getParameterValues(i+"day_image");
					String day = day_title.substring(1,1);
					prodetailVO = new Pro_detailVO(day, day_title, day_course, stay, meal, day_content, image, img_content);
				}
				
				
				
				// request.getParameter-> 전부 map?
				//=> map / for[day~ img{~}]
				// productVO, pro_detailVO, pro_writeVO 나눠서?
				
				//pro_detail 내용 day_title, day_course, stay, meal, day_content, img_content => 앞에 1 
				//=> 반복문 사용 편리 (period parseInt string)
				//=> 글자 잘라서 검사 후 day값으로 전달?
				// 이미지 : 6day_image => 같은 name 으로 전달하므로 [] 사용?
				// 이미지 name 받을때 : request.getParameterValues("6day_image");  => 반복문
				// 코스별 이미지파일명 겹치게 x
				// 업로드 이미지 저장 -> product code+sub_code명 폴더 생성 후 차곡차곡 저장
				// 하면 글쓸때마다 이미지는 사용자가 알아서 업로드
				
				
				//업로드한 상품의 상세페이지로 포워딩해서 업로드한 상품 바로 확인하기
				nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code;
			}
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//doHandle()

}
