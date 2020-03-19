package masil.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import masil.user.Action;
import masil.user.ActionForward;

public class CustomerViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("idx"));
		CustomerVO result = new CustomerDAO().getBoard(num);
		
		request.setAttribute("customervo", result);

		forward.setPath("/customer/viewCustomer.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
