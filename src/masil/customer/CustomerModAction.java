package masil.customer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import masil.user.Action;
import masil.user.ActionForward;

public class CustomerModAction implements Action {

	
	String ARTICLE_IMAGE_REPO = "C:\\masil\\image";
	CustomerVO customerVO;
	CustomerDAO customerDAO;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
        
        
		String id = (String)request.getSession().getAttribute("id");
		/////////////////////////////////////////////////////////////////////////
		System.out.println("customermodAction 실행");
		/////////////////////////////////////////////////////////////////////////
		
		Map<String, String> articleMap = upload(request, response);
		int idx = Integer.parseInt(articleMap.get("idx"));
		
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String img = articleMap.get("img");
		
		customerVO.setIdx(idx);
		System.out.println("idx : "+idx);
		customerVO.setTitle(title);
		System.out.println("title : "+title);
		customerVO.setContent(content);
		System.out.println("content : "+content);
		
		if (img != null && img.length() != 0) {
			String originalFileName = articleMap.get("originalFileName");
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + img);
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + idx);
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + idx + "\\" + originalFileName);
			oldFile.delete();

			customerVO.setImg(img);
			System.out.println("img : "+img);
		}
		
		System.out.println("modArticle()들어가기 전!");
		customerDAO.modArticle(customerVO);
		
//		PrintWriter pw = response.getWriter();
//		pw.print("<script>" + "  alert('글을 수정했습니다.');" + " location.href='" + request.getContextPath()
//				+ "/board/viewArticle.do?articleNO=" + idx + "';" + "</script>");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
	    forward.setPath("/Customer/customer.do");
	    System.out.println(forward.getPath());
	      
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
