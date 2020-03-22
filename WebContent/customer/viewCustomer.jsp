<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
    
<html class="no-js" lang="zxx">


<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Travelo</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <link rel="stylesheet" href="../css/magnific-popup.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/themify-icons.css">
    <link rel="stylesheet" href="../css/nice-select.css">
    <link rel="stylesheet" href="../css/flaticon.css">
    <link rel="stylesheet" href="../css/gijgo.css">
    <link rel="stylesheet" href="../css/animate.css">
    <link rel="stylesheet" href="../css/slick.css">
    <link rel="stylesheet" href="../css/slicknav.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="../css/style.css">
    
    <style>
     #tr_btn_modify{
       display:none;
     }
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
   
   function backToList(obj){
       obj.action="${contextPath}/masil/Customer/customer.do";
       obj.submit();
     }
   
   function fn_enable(obj){
	   
			document.getElementById("i_title").disabled=false;
			document.getElementById("i_content").disabled=false;
			if(document.getElementById("i_imageFileName") !=null){
			document.getElementById("i_imageFileName").disabled=false;}
			document.getElementById("tr_btn_modify").style.display="block";
			document.getElementById("tr_btn").style.display="none";
	   
	 }
   
   function fn_modify_article(obj){
		 obj.action="${contextPath}/masil/Customer/modcustomer.do";
		 obj.submit();
	 }
   
   function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }
   
   
   ///////////////////////////////////
    //답글 쓰기 버튼을 클릭 했을때 호출 되는 함수 
   	function fn_reply_form(url, parentNO, title) {
   		
    	var form = document.createElement("form");
    	form.setAttribute("method", "post");
    	form.setAttribute("action", url);
    	var parentNOInput = document.createElement("input");
    	parentNOInput.setAttribute("type", "hidden");
    	parentNOInput.setAttribute("name", "parentNO");
    	parentNOInput.setAttribute("value", parentNO);
    	
    	var TitleInput = document.createElement("input");
    	TitleInput.setAttribute("type", "hidden");
    	TitleInput.setAttribute("name", "title");
    	TitleInput.setAttribute("value", title);
    	
    	form.appendChild(parentNOInput);
    	form.appendChild(TitleInput);
    	document.body.appendChild(form);
    	form.submit();
	}
	 
	 
	 function fn_remove_article(url,articleNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);		   
	     var idxInput = document.createElement("input");
	     idxInput.setAttribute("type","hidden");
	     idxInput.setAttribute("name","idx");
	     idxInput.setAttribute("value", idx);
	      
	     form.appendChild(idxInput);

	     document.body.appendChild(form);
	
		 form.submit();
	 
	 }
	 
	
 </script>
   </head>
<body>
    <!-- header-start -->
    <header>
       <jsp:include page="../inc/header2.jsp"/>
    </header>
    <!-- header-end -->

    <!-- bradcam_area  -->
<section>
    <form name="frmArticle" method="post" action="${contextPath}/masil/Customer/modcustomer.do"  enctype="multipart/form-data">
	  <table  border="0"  align="center">
		  <tr>
			  <td width="150" align="center" bgcolor="#FF9933">글번호</td>
			  <td >
				  <input type="text"  value="${customervo.idx }"  disabled />
				  <input type="hidden" name="idx" value="${customervo.idx}"  />
			  </td>
		  </tr>
		  <tr>
			  <td width="150" align="center" bgcolor="#FF9933"> 작성자 아이디</td>
			  <td>
			  	<input type="text" value="${customervo.id }" name="writer"  disabled />
			  </td>
		  </tr>
		  <tr>
			  <td width="150" align="center" bgcolor="#FF9933">제목</td>
			  <td><input type="text" value="${customervo.title }" id="i_title" name="i_title" disabled />
			  <input type="hidden" name="title" value="${customervo.title}"  />
			  </td>
		  </tr>
		  <tr>
			   <td width="150" align="center" bgcolor="#FF9933">내용</td>
			   <td>
			   		<textarea rows="20" cols="60" name="content"  id="i_content" disabled >${customervo.content }</textarea>
			   </td>  
		  </tr>
	 
	<c:if test="${not empty customervo.img && customervo.img !='null' }">
		<tr>
		   <td width="150" align="center" bgcolor="#FF9933" rowspan="2">이미지</td>
		   <td>
		   	 <!-- 이미지 수정에 대비해 미리 원래 이미지 파일이름을 <hidden>태그로 저장 -->
		     <input  type= "hidden"   name="originalFileName" value="${customervo.img }" />
		     <img src="${contextPath}/download.do?idx=${customervo.idx}&img=${customervo.img}" id="preview"/><br>   
		   </td>   
		</tr>  
		<tr>
		    <td>
		    	<!-- 수정된 이미지 파일 이름을 request에 저장하여 컨트롤러로 전송! -->
		       <input  type="file"  name="img" id="i_imageFileName" disabled onchange="readURL(this);"   />
		    </td>
		</tr>
	 </c:if>
		  <tr>
			   <td width=20% align=center bgcolor=#FF9933> 등록일자</td>
			   <td>
			    <input type="text" value="${customervo.write_date}" />
			   </td>   
		  </tr>
		  <tr id="tr_btn_modify">
			   <td colspan="2"   align="center" >
			     <input type="button" value="수정반영하기"   onClick="fn_modify_article(frmArticle)">
		         <input type="button" value="취소"  onClick="backToList(frmArticle)">
			   </td>   
		  </tr>  
		  <tr id="tr_btn">
		   <td colspan="2" align="center">
				<c:if test="${id==customervo.id }">
					<input type="button" value="수정하기" onClick="fn_enable(this.form)">
			    <input type="button" value="삭제하기" onClick="fn_remove_article('${contextPath}/masil/Customer/removeCustomer.do', ${customervo.idx})">
				</c:if>
			    

			    <input type="button" value="리스트로 돌아가기"  onClick="backToList(this.form)">
			    <!-- 답글쓰기를 클릭하면 fn_reply_form()함술를 호출 하면서 
			                글번호와 답글 요청 주소를 함께전달함 -->
				<c:if test="${id=='master' }">
					<input type="button" value="답글쓰기" onClick="fn_reply_form('${contextPath}/masil/Customer/replyForm.do', ${customervo.idx}, '${customervo.title }')">
				</c:if>

		   </td>
		  </tr>
	 </table>
 </form>
                     
               
       

</section>

    <!--/ bradcam_area  -->

    



    <footer class="footer">
        <jsp:include page="../inc/footer2.jsp"/>
    </footer>


  
    <!-- link that opens popup -->
<!--     
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://static.codepen.io/assets/common/stopExecutionOnTimeout-de7e2ef6bfefd24b79a3f68b414b87b8db5b08439cac3f1012092b2290c719cd.js"></script>

    <script src=" https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"> </script> -->
    <!-- JS here -->
    <script src="js/vendor/modernizr-3.5.0.min.js"></script>
    <script src="js/vendor/jquery-1.12.4.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/isotope.pkgd.min.js"></script>
    <script src="js/ajax-form.js"></script>
    <script src="js/waypoints.min.js"></script>
    <script src="js/jquery.counterup.min.js"></script>
    <script src="js/imagesloaded.pkgd.min.js"></script>
    <script src="js/scrollIt.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/wow.min.js"></script>
    <script src="js/nice-select.min.js"></script>
    <script src="js/jquery.slicknav.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/gijgo.min.js"></script>
    <script src="js/slick.min.js"></script>
   

    
    <!--contact js-->
    <script src="js/contact.js"></script>
    <script src="js/jquery.ajaxchimp.min.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/mail-script.js"></script>


    <script src="js/main.js"></script>
    <script>
        $('#datepicker').datepicker({
            iconsLibrary: 'fontawesome',
            icons: {
             rightIcon: '<span class="fa fa-caret-down"></span>'
         }
        });
    </script>
</body>

</html>