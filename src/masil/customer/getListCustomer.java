package masil.customer;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import masil.user.Action;
import masil.user.ActionForward;

public class getListCustomer implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		ActionForward forward = new ActionForward();
		int page = 0;
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page")); 
		}
		ArrayList<CustomerVO> List = new CustomerDAO().getCustomerList(page);
		
		int allCount = new CustomerDAO().customerAllCount();
		String user = (String)request.getSession().getAttribute("id");
		
		request.setAttribute("List", List);
		request.setAttribute("page", page);
		request.setAttribute("AllCount", allCount);
		request.setAttribute("user", user);
		
		
		forward = new ActionForward();
		forward.setPath("/customer/customer.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
