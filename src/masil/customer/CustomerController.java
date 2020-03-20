package masil.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masil.user.Action;
import masil.user.ActionForward;


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
   
   @SuppressWarnings("null")
protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
      
      
      ActionForward forward = null;
      String ContextPath = request.getContextPath();
      Action action = null;
      String command = request.getPathInfo();
      
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=utf-8");
      
      if(command.equals("/customer.do")){
         action = new getListCustomer();
         try {
            forward= action.execute(request, response);
         } catch (Exception e) {
            System.out.println("/customer.do 에러 : "+e);
         }
         
         
      }else if(command.equals("/write.do")){
         action=new CustomerWriteAction();  
         try {
            forward=action.execute(request, response);
         } catch (Exception e) {
            System.out.println("/write.do 에러 : "+e);
         }
         
      }else if(command.equals("/view.do")){
    	  action = new CustomerViewAction();
    	  try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			System.out.println("/view.do 에러 : "+e);
		}
    	  
    	  
      }else if(command.equals("/modcustomer.do")){
    	  
    	  System.out.println("/modcustomer.do 실행");
    	  action = new CustomerModAction();
    	  try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			System.out.println("/modcustomer.do 에러 : ");
			e.printStackTrace();
		}
    	  
    	  
      }else if(command.equals("/replyForm.do")){

		
    	  try {
    		int parentNO = Integer.parseInt(request.getParameter("parentNO"));
	  		HttpSession session = request.getSession();
	  		session.setAttribute("parentNO", parentNO); 
	  		action = new Action() {
				@Override
				public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
						throws Exception {
					ActionForward forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("/customer/addReply.jsp"); 
					return forward;
				}
			};
			forward = action.execute(request, response);
		} catch (Exception e) {
			System.out.println("/replyForm.do 에러 : "+e);
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