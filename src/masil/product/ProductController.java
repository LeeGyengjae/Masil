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
				//�����ڿ�-��ǰ ����Ʈ ������ ���� 
				//-�۵��� �ϴµ� �������� ������ 
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
				
				
				
				// request.getParameter-> ���� map?
				//=> map / for[day~ img{~}]
				// productVO, pro_detailVO, pro_writeVO ������?
				
				//pro_detail ���� day_title, day_course, stay, meal, day_content, img_content => �տ� 1 
				//=> �ݺ��� ��� �� (period parseInt string)
				//=> ���� �߶� �˻� �� day������ ����?
				// �̹��� : 6day_image => ���� name ���� �����ϹǷ� [] ���?
				// �̹��� name ������ : request.getParameterValues("6day_image");  => �ݺ���
				// �ڽ��� �̹������ϸ� ��ġ�� x
				// ���ε� �̹��� ���� -> product code+sub_code�� ���� ���� �� �������� ����
				// �ϸ� �۾������� �̹����� ����ڰ� �˾Ƽ� ���ε�
				
				
				//���ε��� ��ǰ�� ���������� �������ؼ� ���ε��� ��ǰ �ٷ� Ȯ���ϱ�
				nextPage = "/product1/blog.do?code="+code+"sub_code="+sub_code;
			}
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//doHandle()

}
