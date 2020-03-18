package masil.customer;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import masil.user.Action;
import masil.user.ActionForward;
import masil.user.UserDAO;

public class CustomerWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		CustomerDAO customerdao = new CustomerDAO();
		String title = request.getParameter("title");
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String img = request.getParameter("img");
		
		UserDAO userdao = new UserDAO();
		
		int check = userdao.searchingId(id);
		
		if(check==1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else{
			
		}
		
		
		
		return null;
	}

}
