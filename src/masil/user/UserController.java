package masil.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import masil.user.ActionForward;
import masil.user.UserLogoutAction;
import masil.user.Action;

 


@WebServlet("/user/*")
public class UserController extends HttpServlet {
    
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
		
		String nextPage = null;
		ActionForward forward = null;
		String ContextPath = request.getContextPath();
		Action action = null;
		String command = request.getPathInfo();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("/login.do")){
			 
			action=new UserLoginAction(); 
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("/login.do 에러 : "+e);;
			}
			
			
			
			
		}else if(command.equals("/addUser.do")){
			
			action = new UserJoinAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("/addUser.do 에러 : "+e);
			}
			
			
			
			
		}else if(command.equals("/myPage.do")){	
			
			action = new MyPageAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("/myPage.do 에러 : "+e);
			}
		
			
			
			
			
			
		
		}else if(command.equals("/logout.do")){
			action=new UserLogoutAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
		}else{
			
			forward=new ActionForward();
			forward.setRedirect(true);


			forward.setPath(ContextPath+"/index.jsp");
			
		}
		////////////////////////////////////////////////////
		
				
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
			
		
		
		
		
	}

	

}
