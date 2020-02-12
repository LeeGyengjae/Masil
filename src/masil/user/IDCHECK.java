package masil.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/idcheck")
public class IDCHECK extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		dohandle(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		dohandle(request, response);
	}
	
	protected void dohandle(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id=request.getParameter("id");
		
		UserDAO userDAO = new UserDAO();
		
		int result = userDAO.searchingId(id);
		
		PrintWriter out= response.getWriter();
		
		out.println(result);
		
	}
}
