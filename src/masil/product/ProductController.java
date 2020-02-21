package masil.product;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/product1/*")
public class ProductController extends HttpServlet {
	
    ProductService productService;
    ProductVO productVO;
    Pro_detailVO prodetailVO;
    Pro_writeVO prowriteVO;
    
    //test
    //private static String ARTICLE_IMAGE_REPO = "F:\\product\\productImage";
    //->경로 찾을 수 없음 ㅠ
    private static String ARTICLE_IMAGE_REPO = "C:\\board\\product";
    
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
			else if(action.equals("/addProduct.do")){
				System.out.println("controller if안으로 넘어옴");
				
				Map<String, String> productMap = upload(request, response);
				
				String code = productMap.get("code");
				String sub_code = productMap.get("sub_code");
//				String title = productMap.get("title");
//				String continent = productMap.get("continent");
//				String course = productMap.get("course");
//				String period = productMap.get("period");
//				String comment = productMap.get("comment");
//				
//				System.out.println("productMap.get(max_num) : "+productMap.get("max_num"));
//				
//				int max_num = Integer.parseInt(productMap.get("max_num"));
//				int price = Integer.parseInt(productMap.get("price"));
//				
//				String startDateStr = productMap.get("start_date");
//				String endDateStr = productMap.get("end_date");
//				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//				//Date start_date = (Date) dateformat.parse(startDateStr);
//				//Date end_date = (Date) dateformat.parse(endDateStr);
//				//->java.util.Date cannot be cast to java.sql.Date
//				
//				Date start_date = new Date(dateformat.parse(startDateStr).getTime());
				
				//productVO = new ProductVO(code, continent, period, course, comment); 
				//prowriteVO = new Pro_writeVO(sub_code, title, startDateStr, endDateStr, max_num, price);
				
				//VO전달
				//productService.insertProduct(productVO,prowriteVO,prodetailVO);
				
				productService.insertProduct(productMap);
				
				System.out.println("Controller 된거 같음?");
				
//				String imageFileName = code+"_"+sub_code;
//				if(imageFileName != null && imageFileName.length() != 0){
//					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
//					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + imageFileName);
//					destDir.mkdirs();
//					FileUtils.moveFileToDirectory(srcFile, destDir, true);
//				}
				
				nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code;
			}
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//doHandle()

	//파일 업로드 처리를 위한 upload메소드
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException{
		
		Map<String, String> productMap = new HashMap<String, String>();
		String encoding = "utf-8";

		//File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		//String realPath = application.getRealPath("/upload");
		//String realPath = request.getServletContext().getInitParameter("/product/upload");
		String realPath = request.getServletContext().getRealPath("/product/upload");
		System.out.println("realPath : "+realPath);
		String filename="";
		int maxSize = 1024 * 1024 * 5;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			//List items = multi.getParameterNames();
			//System.out.println("multi.getParameterNames().nextElement() : "+multi.getParameterNames().nextElement());
			
			Enumeration paramNames = multi.getParameterNames();
			
			//파라미터가 있는 동안 반복
			while(paramNames.hasMoreElements()){
				String keyname = (String) paramNames.nextElement();
				String[] values = multi.getParameterValues(keyname);
				for (String value : values) {
					System.out.println("keyname : " + keyname + "\t\tvalue : " + value);
					productMap.put(keyname, value);
				} //for String value : values
			}//while
			
			System.out.println("TEST *** productMap : "+productMap);
			
			//업로드된 파일 이름
			Enumeration imgfile=multi.getFileNames();
			String file = (String) imgfile.nextElement();
			filename=multi.getFilesystemName(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productMap; //HashMap리턴
	}//upload()
	
	
}//ProductController class
