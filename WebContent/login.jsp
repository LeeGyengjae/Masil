<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%-- JSTL태그 라이브러리 사용을 위한 선언 --%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

    
<html class="no-js" lang="zxx">
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Travelo</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gijgo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slicknav.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
   </head>

<body>
    <!-- header-start -->
    <header>
    	<jsp:include page="/inc/header.jsp"/>
    </header>
    <!-- header-end -->

    <!-- bradcam_area  -->
    <div class="bradcam_area bradcam_bg_3">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <div class="bradcam_text text-center">
                        <h3>Welcome!</h3>
                        <section class="container mb-3 mt-3" style="max-width: 560px;">
							<form method="post" action="${pageContext.request.contextPath}/user/login.do">
								<div class="form-.group">
									<input type="text" name="id" class="form-control" placeholder="ID">
								</div>
								<br>
								<div class="form-group">
									<input type="password" name="pwd" class="form-control" placeholder="Password">
								</div>
								<button type="submit" class="btn btn-primary">Login</button>
								<input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/Join.jsp'" value="Join">
							</form>
						</section>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ bradcam_area  -->

	


    <footer class="footer">
        <jsp:include page="/inc/footer.jsp"/>
    </footer>


  <!-- Modal -->
  <div class="modal fade custom_search_pop" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="serch_form">
            <input type="text" placeholder="Search" >
            <button type="submit">search</button>
        </div>
      </div>
    </div>
  </div>
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