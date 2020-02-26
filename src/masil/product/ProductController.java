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
    //->��� ã�� �� ���� ��
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
				//�����ڿ�-��ǰ ����Ʈ ������ ���� 
				//-�۵��� �ϴµ� �������� ������ 
				//-���Ÿ� jsp������ ���� �ʿ�
				productList = productService.listProducts();
				request.setAttribute("productList", productList);
				nextPage = "/product/pre_write.jsp";
				
			}
			else if(action.equals("/callwrite.do")){
				//�̹� ��ϵ� ��ǰ�� ���/���� ��¥�� �ٲ㼭 ����ϰ� ������
				//����� ���� �ҷ��ͼ� ��¥�� �ٲ� �� �ֵ��� �ϴ� ������
				//-�̿ϼ�
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				productDetail = productService.viewProduct(code, sub_code);
				request.setAttribute("productDetail", productDetail);

				nextPage = "/product/callwrite.jsp";
			}
			else if(action.equals("/addProduct.do")){
				System.out.println("controller if������ �Ѿ��");
				
				Map<String, Object> productMap = upload(request, response);
				
				String code = productMap.get("code").toString();
				String sub_code = productMap.get("subCode").toString();
				
				productService.insertProduct(productMap);
				
				System.out.println("Controller ��");
				
				//nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code; //������ �̵� �ȵ� ��
				nextPage="/product1/product.do";
				
			}
			else if(action.equals("/addProduct2.do")){
				System.out.println("controller if������ �Ѿ��");
				
				Map<String, Object> productMap = upload(request, response);
				
				String code = productMap.get("code").toString();
				String sub_code = productMap.get("subCode").toString();
				
				productService.insertProduct(productMap);
				
				System.out.println("Controller ��");
				
				//nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code; //������ �̵� �ȵ� ��
				nextPage="/product1/product.do";
				
			}
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//doHandle()

	//���� ���ε� ó���� ���� upload�޼ҵ�
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
			
			//request�������� �Ѿ�� ���� �ִ� ���� �ݺ�
			while(paramNames.hasMoreElements()){
				String keyname = (String) paramNames.nextElement();
				String[] values = multi.getParameterValues(keyname);
				
				//���� name�� �迭�� �Ѿ��-> �� �迭�� ���̰� 1���� ũ�� -> ������ ���� ����
				if(values.length>1){
					productMap.put(keyname, values);
				}else {	//�ƴϸ� �迭�� ���� 1���̹Ƿ� ù��°���� ����
					productMap.put(keyname, values[0]);
				}//if(values.length>1)
				
			}//while
			
			//���ε�� ���� �̸�
			Enumeration imgfile=multi.getFileNames();
			Map<String, String> fileList = new HashMap<String, String>();
			
//			System.out.println("imgfile.nextElement().getClass() : " + (imgfile.nextElement().getClass()));	//String
//			System.out.println("imgfile.hasMoreElements() : "+imgfile.hasMoreElements());	//true
			
			//���ε��� ������ �������
			if(imgfile.hasMoreElements()){
				while(imgfile.hasMoreElements()){
					String file = (String) imgfile.nextElement();
					System.out.println("file    : "+file);
					
					filename=multi.getFilesystemName(file);
					System.out.println("file : "+file+"\t\tfilename : "+filename);
					
					//name : 1_image_0 �� ���� �������� �Ѿ��. ���� day ���� �̹���number
					int idx1 = file.indexOf("_");
					int idx2 = file.lastIndexOf("_");
					int daynum = Integer.parseInt(file.substring(0,idx1)); //list index number�� ����ϱ� ����
					
					String tmp = file.substring(0,idx2);
				    //tmp�� key���ϴ� ���� �ִٸ�
				    if(fileList.get(tmp)!=null){
				    	String tempValue = filename+","+fileList.get(tmp);
				    	fileList.put(tmp,tempValue);
				    } else {	//tmp�� key���ϴ� ���� ���ٸ�
				    	fileList.put(tmp,filename);
				    }//else
				    
				}//while(imgfile.hasMoreElements())
				productMap.put("image", fileList);
			}
			//���ε��� ����(����)�� �������
			else if(!imgfile.hasMoreElements()){
				//������ǰ����/�ҷ��ͼ� ������ ��� hidden���� �ѿ��� ���ε�Ǿ� �ִ� �̹����� ���
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
				//�� ��ǰ �����ε� ������ ���� ���
				//->?
			}//else if
//			System.out.println("TEST *** productMap : "+productMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productMap; //HashMap����
	}//upload()
	
	
}//ProductController class
