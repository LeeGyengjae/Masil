<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

    
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
    
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	    function readURL(input) {
		   console.debug(input); 
	       console.debug(input.files); 
		   if (input.files && input.files[0]) {
			  $("#tdImg").html("<img  id='preview' src='#'   width=200 height=200/>");
		      var reader = new FileReader();
		      reader.readAsDataURL(input.files[0]);
		      reader.onload = function (ProgressEvent) {
	    		  console.debug(ProgressEvent);
		      	 $('#preview').attr('src',ProgressEvent.target.result);
		          }
		      }  
		} 
	  function backToList(obj){
	    obj.action="${contextPath}/Customer/customer.do";
	    obj.submit();
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
    <section class="container mt-3" style="max-width: 560px;">
    	<h1>글쓰기</h1>
	  <form name="customerForm" method="post"   
	  						   action="${contextPath}/customer/writePro.do"   
	  						   enctype="multipart/form-data">
	    <table border=0 align="center">
	     <tr >
		   <td align="right" width="500px">글제목: </td>
		   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title" /></td>
		 </tr>
		 <tr>
			<td align="right" valign="top"><br>글내용: </td>
			<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
	     </tr>
	     <tr>
	        <td align="right">이미지파일 첨부:  </td>
		     <td> <input type="file"  name="imageFileName"  onchange="readURL(this);" /></td>
	         <td id="tdImg"></td>
		 </tr>
		 <tr>
		    <td align="right"> </td>
		    <td colspan="2">
		       <input type="submit" value="글쓰기" />
		       <input type=button value="목록보기"onClick="backToList(this.form)" />
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