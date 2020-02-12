package masil.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masil.user.ActionForward;

public class UserJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		request.setCharacterEncoding("utf-8");
		UserVO userVO = new UserVO();
		
		
		userVO.setId(request.getParameter("id"));
		userVO.setpwd(request.getParameter("pwd"));
		userVO.setName(request.getParameter("name"));
		userVO.setPname(request.getParameter("pname"));
		userVO.setPnum(request.getParameter("pnum"));
		userVO.setJumin1(request.getParameter("jumin1"));
		userVO.setJumin2(request.getParameter("jumin2"));
		userVO.setGender(request.getParameter("gender"));
		
		boolean result = false;
		
		UserDAO userDAO = new UserDAO();
		 
		result = userDAO.insertUser(userVO);
		if(result=false){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패 하셨습니다');");
			out.println("location.href='./Main.do';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		HttpSession session=request.getSession();
		String user_name =request.getParameter("user_name");
		String user_id =request.getParameter("user_id");
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/masil/login.jsp");
		
		return forward;
		
		
	}//execute() 끝
	

}// UserJoinAction 끝
