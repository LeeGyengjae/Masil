package masil.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import masil.user.Action;
import masil.user.ActionForward;
import masil.user.UserLoginAction;


@WebServlet("/Customer/*")
public class CustomerController extends HttpServlet {
	
    public CustomerController() {}
    
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
		
		
		ActionForward forward = null;
		String ContextPath = request.getContextPath();
		Action action = null;
		String command = request.getPathInfo();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("/write.do")){
			 
			action=new CustomerWriteAction(); 
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				System.out.println("/write.do 에러 : "+e);
			}
			
			
			
		}else if(command.equals("/customer.do")){
			action = new getListCustomer();
			try {
				forward= action.execute(request, response);
			} catch (Exception e) {
				System.out.println("/customer.do 에러 : "+e);
			}
			
			
		}else{
			
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath(ContextPath+"/index.jsp");
			
		}
		
		///////////////////////////////////////////////////////////
		
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
