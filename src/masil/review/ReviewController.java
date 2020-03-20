package masil.review;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review1/*")
public class ReviewController extends HttpServlet {
		
	ReviewService reviewService;
    ReviewVO reviewVO;
	
	public ReviewController() {}
    
    @Override
//	public void init() throws ServletException {
    	public void init()  {
		reviewService = new ReviewService();
	}
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
    	 {
		doHandle(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
    	{
//		doHandle(request, response);
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
		{
		
		String nextPage="";
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
		
		String ContextPath = request.getContextPath();
		System.out.println("ContextPath : "+ContextPath);
		
		String action = request.getPathInfo();
		System.out.println("action : " + action );
		
		try {
			List<ReviewVO> revList;
			
			if(action.equals("/insertReview.do")){
				System.out.println("reviewController page");
				
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = (String) request.getSession().getAttribute("id");
				String content = request.getParameter("content");
				int rating = Integer.parseInt(request.getParameter("rating"));
				String end_date = request.getParameter("end_date");
				
				reviewVO = new ReviewVO(code, id, content, rating, end_date);
				System.out.println("reviewVO : "+reviewVO.toString());
				
				reviewService.insertReview(reviewVO);
				
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
//				nextPage = "/masil/NewFile.jsp";
				
			} else if (action.equals("/updateReview.do")){
				System.out.println("updateReview.do ����");
				
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = (String) request.getSession().getAttribute("id");
				String content = request.getParameter("content");
				int rating = Integer.parseInt(request.getParameter("rating"));
				String end_date = request.getParameter("end_date");
				
				reviewVO = new ReviewVO(code, id, content, rating, end_date);
				int result = reviewService.updateReview(reviewVO);
				if(result == 1)
					System.out.println("�ı� ���� ����!!!!");
				else
					System.out.println("����");
				
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
				
			} else if (action.equals("/deleteReview.do")){

				System.out.println("request.getParameter(code) : "+request.getParameter("code"));
				System.out.println("request.getParameter(sub_code) : "+request.getParameter("sub_code"));
				System.out.println("request.getParameter(id) : "+request.getSession().getAttribute("id"));
				System.out.println("request.getParameter(idx) : "+request.getParameter("idx"));
				
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = (String) request.getSession().getAttribute("id");
//				String content = request.getParameter("content");
//				int rating = Integer.parseInt(request.getParameter("rating"));
//				String end_date = request.getParameter("end_date");
				int idx = Integer.parseInt(request.getParameter("idx"));
				
				reviewVO = new ReviewVO(code, id, idx);
				reviewService.deleteReview(reviewVO);
				
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
				
			} else if(action.equals("/reviewPageAction.do")){
				System.out.println("request.getParameter(code) : "+request.getParameter("code"));
				System.out.println("request.getParameter(sub_code) : "+request.getParameter("sub_code"));
				System.out.println("request.getParameter(id) : "+request.getSession().getAttribute("id"));
				System.out.println("request.getParameter(idx) : "+request.getParameter("idx"));
				
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = (String) request.getSession().getAttribute("id");
				int idx = Integer.parseInt(request.getParameter("idx"));
				
				revList = reviewService.selReview(id, sub_code);
				request.setAttribute("revList", revList);
				
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
			}
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		
	}//doHandle

}//Controller class
