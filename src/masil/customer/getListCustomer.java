package masil.customer;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import masil.user.Action;
import masil.user.ActionForward;
import masil.user.UserDAO;

public class getListCustomer implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		ActionForward forward = new ActionForward();
		// 현재 페이지를 저장
		int page = 0;
		// 만약 자기의 글이 3번 페이지에 있엇는데 View 페이지에서 읽다가 목록으로 나올시. 원래 페이지로 돌아가기위해
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page")); 
		}
		ArrayList<CustomerVO> List = new CustomerDAO().getCustomerList(page);
		
		//게시판 글 갯수를 저장하는 변수
		int allCount = new CustomerDAO().customerAllCount();
		//유저 id 확인
		String user = (String)request.getSession().getAttribute("id");
//		int idcheck = new UserDAO().searchingId(id);
//		if(idcheck==0){
//			System.out.println("아이디가 있다");
//		}else{
//			System.out.println("아이디가 없다");
//		}
		
		//얻어온 자료 ArrayList 넘기기
		request.setAttribute("List", List);
		//현재 페이지 넘버 넘기기
		request.setAttribute("page", page);
		//게시판 글 갯수 넘기기
		request.setAttribute("AllCount", allCount);
		//아이디 넘기기
		request.setAttribute("user", user);
		
		
		forward = new ActionForward();
		forward.setPath("/customer/customer.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
