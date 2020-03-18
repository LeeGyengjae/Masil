package masil.customer;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masil.user.Action;
import masil.user.ActionForward;
import masil.user.UserDAO;

public class CustomerWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

//		String ARTICLE_IMAGE_REPO = "C:\\masil\\image";
		CustomerVO customervo = new CustomerVO();
		CustomerDAO customerdao = new CustomerDAO();
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		
		UserDAO userdao = new UserDAO();
		
		int check = userdao.searchingId((String)request.getSession().getAttribute("id"));
		
		if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else{
			customervo.setId(request.getParameter("id"));
			customervo.setTitle(request.getParameter("title"));
			customervo.setContent(request.getParameter("content"));
			if(request.getParameter("imageFileName") != null){
				customervo.setImg(request.getParameter("imageFileName"));
			}
			
			boolean result = customerdao.wirteCustomer(customervo);
			
			
			if(result){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('글 작성 성공.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}else{
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('글 작성 실패.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			
		}
		
		forward.setRedirect(false);
		forward.setPath("/Customer/customer.do");
		
		return forward;
	}

}
