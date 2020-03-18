package masil.review;

import java.io.IOException;

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
	public void init() throws ServletException {
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
			
			if(action.equals("insertReview.do")){
				System.out.println("reviewController");
				
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = request.getParameter("id");
				String content = request.getParameter("content");
				String write_date = request.getParameter("write_date");
				int rating = Integer.parseInt(request.getParameter("rating"));
				String end_date = request.getParameter("end_date");
				reviewVO = new ReviewVO(code, id, content, write_date, rating, end_date);
				reviewService.insertReview(reviewVO);
				System.out.println("reviewVO : "+reviewVO.toString());
				
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
				
			} else if (action.equals("updateReview.do")){
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = request.getParameter("id");
				String content = request.getParameter("content");
				String write_date = request.getParameter("write_date");
				int rating = Integer.parseInt(request.getParameter("rating"));
				String end_date = request.getParameter("end_date");
				reviewVO = new ReviewVO(code, id, content, write_date, rating, end_date);
				reviewService.updateReview(reviewVO);
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
				
			} else if (action.equals("deleteReview.do")){
				String code = request.getParameter("code");
				String sub_code = request.getParameter("sub_code");
				String id = request.getParameter("id");
				String content = request.getParameter("content");
				String write_date = request.getParameter("write_date");
				int rating = Integer.parseInt(request.getParameter("rating"));
				String end_date = request.getParameter("end_date");
				reviewVO = new ReviewVO(code, id, content, write_date, rating, end_date);
				reviewService.deleteReview(reviewVO);
				nextPage = "/product1/blog.do?code="+code+"&sub_code="+sub_code;
				
			}
			
			
			
			RequestDispatcher dispatche = request.getRequestDispatcher(nextPage);
			dispatche.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//doHandle

}//Controller class
