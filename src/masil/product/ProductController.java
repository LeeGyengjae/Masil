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

import com.oreilly.servlet.MultipartFilter;
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
				//-쓸거면 jsp디자인 수정 필요
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
				
				Map<String, Object> productMap = upload(request, response);
				
				String code = productMap.get("code").toString();
				String sub_code = productMap.get("subCode").toString();
				
				productService.insertProduct(productMap);
				
				System.out.println("Controller 됨");
				
				//nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code; //페이지 이동 안됨 ㅠ
				nextPage="/product1/product.do";
				
			}
			else if(action.equals("/addProduct2.do")){
				System.out.println("controller if안으로 넘어옴");
				
				Map<String, Object> productMap = upload(request, response);
				
				String code = productMap.get("code").toString();
				String sub_code = productMap.get("subCode").toString();
				
				productService.insertProduct(productMap);
				
				System.out.println("Controller 됨");
				
				//nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code; //페이지 이동 안됨 ㅠ
				nextPage="/product1/product.do";
				
			}
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//doHandle()

	//파일 업로드 처리를 위한 upload메소드
	private Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException{
		
		Map<String, Object> productMap = new HashMap<String, Object>();
		String encoding = "utf-8";

		//File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		//String realPath = application.getRealPath("/upload");
		//String realPath = request.getServletContext().getInitParameter("/product/upload");
		String realPath = request.getServletContext().getRealPath("/product/upload");
		System.out.println("realPath : "+realPath);
		String filename="";
		int maxSize = 1024 * 1024 * 10;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration paramNames = multi.getParameterNames();
			
			//request영역에서 넘어온 값이 있는 동안 반복
			while(paramNames.hasMoreElements()){
				String keyname = (String) paramNames.nextElement();
				String[] values = multi.getParameterValues(keyname);
				
				//같은 name은 배열로 넘어옴-> 그 배열의 길이가 1보다 크면 -> 일정에 들어가는 내용
				if(values.length>1){
					productMap.put(keyname, values);
				}else {	//아니면 배열에 값은 1개이므로 첫번째값만 저장
					productMap.put(keyname, values[0]);
				}//if(values.length>1)
				
			}//while
			
			//업로드된 파일 이름
			Enumeration imgfile=multi.getFileNames();
			Map<String, String> fileList = new HashMap<String, String>();
			
//			System.out.println("imgfile.nextElement().getClass() : " + (imgfile.nextElement().getClass()));	//String
//			System.out.println("imgfile.hasMoreElements() : "+imgfile.hasMoreElements());	//true
			
			//업로드한 파일이 있을경우
			if(imgfile.hasMoreElements()){
				while(imgfile.hasMoreElements()){
					String file = (String) imgfile.nextElement();
					System.out.println("file    : "+file);
					
					filename=multi.getFilesystemName(file);
					System.out.println("file : "+file+"\t\tfilename : "+filename);
					
					//name : 1_image_0 과 같은 형식으로 넘어옴. 앞쪽 day 뒤쪽 이미지number
					int idx1 = file.indexOf("_");
					int idx2 = file.lastIndexOf("_");
					int daynum = Integer.parseInt(file.substring(0,idx1)); //list index number로 사용하기 위함
					
					String tmp = file.substring(0,idx2);
				    //tmp를 key로하는 값이 있다면
				    if(fileList.get(tmp)!=null){
				    	String tempValue = filename+","+fileList.get(tmp);
				    	fileList.put(tmp,tempValue);
				    } else {	//tmp를 key로하는 값이 없다면
				    	fileList.put(tmp,filename);
				    }//else
				    
				}//while(imgfile.hasMoreElements())
				productMap.put("image", fileList);
			}
			//업로드한 파일(사진)이 없을경우
			else if(!imgfile.hasMoreElements()){
				//기존상품수정/불러와서 쓰기일 경우 hidden으로 넘오온 업로드되어 있던 이미지를 등록
				while(paramNames.hasMoreElements()){
					String keyname = (String) paramNames.nextElement();
					String[] values = multi.getParameterValues(keyname);
					if(keyname.equals("old_image")){
						if(values.length>1){
							productMap.put("image", values);
						}else {	
							productMap.put("image", values[0]);
						}
					}
				}//while
				//새 상품 쓰기인데 사진이 없을 경우
				//->?
			}//else if
//			System.out.println("TEST *** productMap : "+productMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productMap; //HashMap리턴
	}//upload()
	
	
}//ProductController class
