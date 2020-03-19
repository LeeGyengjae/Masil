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

import masil.user.*;

public class CustomerWriteAction implements Action {
	
    String ARTICLE_IMAGE_REPO = "C:\\masil\\image";
	
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
         throws Exception {
      request.setCharacterEncoding("UTF-8");
      System.out.println("CustomerWriteAction 실행");
      //////////////////////////
      CustomerVO customervo = new CustomerVO();
      CustomerDAO customerdao = new CustomerDAO();
      ActionForward forward = new ActionForward();
      HttpSession session=request.getSession();
      
      UserDAO userdao = new UserDAO();
      
      int check = userdao.searchingId((String)request.getSession().getAttribute("id"));
      
      if(check==1){
         System.out.println("check값이 1임");
         //////////////////////
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out=response.getWriter();
         out.println("<script>");
         out.println("alert('로그인이 필요합니다.');");
         out.println("history.back();");
         out.println("</script>");
         out.close();
         return null;
         
      }else{
         System.out.println("check값이 0임");
         ////////////////////////
         customervo.setId((String)request.getSession().getAttribute("id"));
         int customerNO = customerdao.getCustomerNO();
         Map<String, String> articleMap = upload(request, response);
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String img = articleMap.get("img");
         customervo.setTitle(title);
         customervo.setContent(content);
       	 if (img != null && img.length() != 0) {
       		 	customervo.setImg(img);
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + img);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + customerNO);
				destDir.mkdirs();
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
         
         boolean result = customerdao.writeCustomer(customervo);
         
         
         if(result){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('글 작성 성공.');");
            out.println("</script>");
            out.close();
         }else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('글 작성 실패.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
         }
         
         
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
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("파일경로인가:" + fileItem.getFieldName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일이름:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName); 
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
						System.out.println("기본경로"+new File(".").getAbsolutePath());

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			System.out.println("upload()오류 : "+e);
		}
		return articleMap;
	}

}