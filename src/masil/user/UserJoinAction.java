package masil.user;

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
		
		String jumin = request.getParameter("jumin")+"-"+request.getParameter("jumin2");
		
		userVO.setId(request.getParameter("id"));
		userVO.setpwd(request.getParameter("pwd"));
		userVO.setName(request.getParameter("name"));
		userVO.setPname(request.getParameter("pname"));
		userVO.setPnum(request.getParameter("pnum"));
		userVO.setJumin(jumin);
		userVO.setGender(request.getParameter("gender"));
		
		boolean result = false;
		
		UserDAO userDAO = new UserDAO();
		 
		result = userDAO.insertUser(userVO);
		 
		if(result=false){
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
