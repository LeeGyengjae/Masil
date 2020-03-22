package masil.customer;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import masil.user.Action;
import masil.user.ActionForward;

public class CustomerRemoveAction implements Action {

    String ARTICLE_IMAGE_REPO = "C:\\masil\\image";

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    ActionForward forward = new ActionForward();
	    CustomerDAO customerdao = new CustomerDAO();
	      
		int idx = Integer.parseInt(request.getParameter("idx"));
		File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + idx );
		if (imgDir.exists()) {
				FileUtils.deleteDirectory(imgDir);
		}
			customerdao.deleteCustomer(idx);
		
				forward.setRedirect(false);
				forward.setPath("/Customer/customer.do");
				request.setAttribute("idx", idx);
				
				return forward;
	}

}
