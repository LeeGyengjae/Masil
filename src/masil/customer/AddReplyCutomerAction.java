package masil.customer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import masil.user.Action;
import masil.user.ActionForward;
import masil.user.UserDAO;

public class AddReplyCutomerAction implements Action {
	String ARTICLE_IMAGE_REPO = "C:\\masil\\image";
	
	   @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
	         throws Exception {
	      request.setCharacterEncoding("UTF-8");
	      CustomerVO customervo = new CustomerVO();
	      CustomerDAO customerdao = new CustomerDAO();
	      ActionForward forward = new ActionForward();

	      UserDAO userdao = new UserDAO();
	      
	      int check = userdao.searchingId("master");
	      
	      if(check==1){
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out=response.getWriter();
	         out.println("<script>");
	         out.println("alert('마스터 권한이 필요합니다.');");
	         out.println("history.back();");
	         out.println("</script>");
	         out.close();
	         return null;
	         
	      }else{
	         customervo.setId("master");
	         int customerNO = customerdao.getCustomerNO();
	         Map<String, String> articleMap = upload(request, response);
			String title = articleMap.get("title");
			String content = articleMap.get("content");
			String img = articleMap.get("img");
			
			HttpSession session = request.getSession();
	        String parentNO = (String) session.getAttribute("parentNO"); 
	        System.out.println("parentNO : "+parentNO);
//	        customervo.setParentNO(parentNO);
			
	         customervo.setTitle(title);
	         customervo.setContent(content);
	         
	         
	       	 if (img != null && img.length() != 0) {
	       		 	customervo.setImg(img);
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + img);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + customerNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
	         
	         customerdao.replyCustomer(customervo);
	         
	         
	         
	      }
	      
	      forward.setRedirect(false);
	      forward.setPath("/Customer/customer.do");
	      
	      return forward;
	   }
	   
	   private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Map<String, String> articleMap = new HashMap<String, String>();
			String encoding = "utf-8";
			File currentDirPath = new File(ARTICLE_IMAGE_REPO);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(currentDirPath);
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List items = upload.parseRequest(request);
				for (int i = 0; i < items.size(); i++) {
					FileItem fileItem = (FileItem) items.get(i);
					if (fileItem.isFormField()) {
						articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					} else {
						if (fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}

							String fileName = fileItem.getName().substring(idx + 1);
							articleMap.put(fileItem.getFieldName(), fileName); 
							File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
							fileItem.write(uploadFile);

						} // end if
					} // end if
				} // end for
			} catch (Exception e) {
				System.out.println("upload()오류 : "+e);
			}
			return articleMap;
		}
	   
}
