package masil.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masil.user.ActionForward;

public class DeleteUserAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		request.setCharacterEncoding("utf-8");
		UserVO userVO = new UserVO();
		
		System.out.println(request.getParameter("id"));
		userVO.setId(request.getParameter("id"));
		
		boolean result = false;
		
		UserDAO userDAO = new UserDAO();
		 
		result = userDAO.deleteUser(userVO);
		 
		if(result=false){
			return null;
		}
		
		HttpSession session=request.getSession();
		session.invalidate();
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/masil/index.jsp");
		
		return forward;
		
		
	}//execute() 끝
	

}// UserJoinAction 끝
