package masil.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masil.user.*;


public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		UserDAO userDAO = new UserDAO(); 
		
		int check = userDAO.userCheck(id, pwd);
		
		if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==-1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디 입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;	
		}
		
		
			HttpSession session=request.getSession();
			session.setAttribute("id",id);
		
			ActionForward forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/masil/main.do"); 
			
			return forward;
	}
	

}
