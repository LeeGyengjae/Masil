package masil.product;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;


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
				String title = productMap.get("title");
				String continent = productMap.get("continent");
				String course = productMap.get("course");
				String period = productMap.get("period");
				String comment = productMap.get("comment");
				
				System.out.println("productMap.get(max_num) : "+productMap.get("max_num"));
				
				int max_num = Integer.parseInt(productMap.get("max_num"));
				int price = Integer.parseInt(productMap.get("price"));
				
				String startDateStr = productMap.get("start_date");
				String endDateStr = productMap.get("end_date");
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				//Date start_date = (Date) dateformat.parse(startDateStr);
				//Date end_date = (Date) dateformat.parse(endDateStr);
				//->java.util.Date cannot be cast to java.sql.Date
				
				Date start_date = new Date(dateformat.parse(startDateStr).getTime());
				
				//productVO = new ProductVO(code, continent, period, course, comment); 
				//prowriteVO = new Pro_writeVO(sub_code, title, startDateStr, endDateStr, max_num, price);
				
				//VO전달
				//productService.insertProduct(productVO,prowriteVO,prodetailVO);
				
				productService.insertProduct(productMap);
				
				System.out.println("Controller 된거 같음?");
				
				String imageFileName = code+"_"+sub_code;
				if(imageFileName != null && imageFileName.length() != 0){
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + imageFileName);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				
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
		
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024*1);
		factory.setRepository(currentDirPath);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items =  upload.parseRequest(request);
			
			for(int i=0; i<items.size(); i++){
				FileItem fileItem = (FileItem) items.get(i);
				
				if(fileItem.isFormField()){
					System.out.println(fileItem.getFieldName()+ "=" + fileItem.getString(encoding));
					productMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				}else {
					System.out.println("파라미터명 : "+fileItem.getFieldName());
					System.out.println("업로드할 파일명 : "+fileItem.getName());
					System.out.println("업로드할 파일크기 : "+fileItem.getSize()+" bytes");
					
					productMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if(fileItem.getSize()>0){
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1){
							idx= fileItem.getName().lastIndexOf("/"); //-1얻기
						}
						String fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currentDirPath+"\\temp\\"+fileName);
						fileItem.write(uploadFile);
					}
				}//main if else
			}//for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productMap; //HashMap리턴
	}//upload()
	
	
}//ProductController class
