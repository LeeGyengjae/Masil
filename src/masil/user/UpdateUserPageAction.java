package masil.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
					throws Exception {
		
		String id = (String)request.getSession().getAttribute("id");
		
		UserDAO userDAO = new UserDAO();
		UserVO userVO = userDAO.getUser(id);
		
		request.setAttribute("userVO",userVO );
		
		ActionForward forward = null;
			
		forward=new ActionForward();
		forward.setRedirect(false);

		forward.setPath("/updateUser.jsp");
		
		return forward;
	}
	
	

}
