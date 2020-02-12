package masil.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/user/*")
public class UserController extends HttpServlet {
	
	UserDAO userDAO;
	
    public UserController() {}
    
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
		
		//MVC중에 View주소 저장할 용도
		String nextPage = null;
	
		//한글처리
		request.setCharacterEncoding("UTF-8");
		//응답할 데이터 유형 설정
		response.setContentType("text/html;charset=utf-8");
		
		//클라이언트의 2단계의 요청주소값 얻기
		String action = request.getPathInfo();
		
		System.out.println("action : " + action);
		
		if(action == null || action.equals("/index.do")){
			
			nextPage = "/index.jsp";
			
		}else if(action.equals("/login.do")){
			
			String id = request.getParameter("id");
			
		}
		else if(action.equals("/Join.do")){
			
			nextPage = "/Join.jsp";
			
		}else if(action.equals("/addUser.do")){
			
			String id = request.getParameter("userID");
			String pwd = request.getParameter("userPassword");
			String name = request.getParameter("userName");
			String pname = request.getParameter("userPName");		
			String pnum = request.getParameter("userPNum");
			String jumin1 = request.getParameter("userJumin1");	
			String jumin2 = request.getParameter("userJumin2");
			String gender = request.getParameter("userGender");	
			
			UserVO userVO = new UserVO(id , pwd , name, pname , pnum , jumin1, jumin2 , gender);
			
			userDAO.addUser(userVO);
			
			nextPage = "/user/index.do";
		}
		
		
		//nextPage변수의 주소를 통해  포워딩 함
		 RequestDispatcher dispatche = 
				 request.getRequestDispatcher(nextPage);
		 dispatche.forward(request, response);
		 
	}

}
