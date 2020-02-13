package masil.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masil.user.ActionForward;

public class UpdateUserAction implements Action{

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
		 
		result = userDAO.updateUser(userVO);
		 
		if(result=false){
			return null;
		}
		
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/masil/index.jsp");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('정보수정이 완료되었습니다');");
		out.println("location.href='./Main.do';");
		out.println("</script>");
		out.close();
		
		return forward;
		
		
	}//execute() 끝
	

}// UserJoinAction 끝
