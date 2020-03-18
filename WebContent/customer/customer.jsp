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
   </head>

<body>
    <!-- header-start -->
    <header>
    	<jsp:include page="../inc/header2.jsp"/>
    </header>
    <!-- header-end -->

    <!-- bradcam_area  -->
    <section class="container mt-3" style="max-width: 560px;">
    	<h1>1:1문의</h1>
							<table border="1" >
								<tr class="thead-dark">
									<th width="100">No.</th>
									<th width="400">Title</th>
									<th width="200">Writer</th>
									<th width="200">Date</th>
								</tr>
							<c:forEach var="List" items="${NoticeList }" >
								<tr>
									<td align="center">${List.notice_num }</td>
									<td><a href="View.Notice?Notice_num=${List.notice_num }">${List.notice_title }</a></td>
									<td align="center">${List.notice_count }</td>
									<td>${List.notice_date }</td>
								</tr>
							</c:forEach>
							
							</table>	
								<ul class="pagination">
								  <!--  페이지 넘버가 0이 아닐때   ㅁㅁㅁ 중 가운데에 현페이지 넘버  -->
								<c:choose>
									<c:when test="${Notice_page == 0 }"><!-- 현재페이지가 0 번일때  -->
										<li class="page-item active"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page}">${Notice_page +1}</a></li>
										<c:choose>
											<c:when test="${NoticeAllCount > 10 && NoticeAllCount < 21 }">
												<li class="page-item"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page + 1 }">${Notice_page +2}</a></li>	
											</c:when>
											<c:when test="${NoticeAllCount > 20 }">
												<li class="page-item"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page+ 1 }">${Notice_page +2}</a></li>
												<li class="page-item"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page + 2 }">${Notice_page +3}</a></li>
											</c:when>
										</c:choose>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page - 1}">${Notice_page }</a></li>
								  		<li class="page-item active"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page}">${Notice_page +1}</a></li>
								  		<c:choose>
								  			<c:when test="${NoticeAllCount > (Notice_page+1)*10 }">
								  				<li class="page-item"><a class="page-link" href="Board.Notice?Notice_page=${Notice_page + 1 }">${Notice_page +2}</a></li>
								  			</c:when>
								  		</c:choose>
									</c:otherwise>
								</c:choose>
								</ul>
								
								<c:if test="${user_id == 'masiladmin' }">
									<a href="asdf" class="btn btn-dark float-right btn-sm"><i class="fas fa-pen-fancy"></i>글 쓰기</a>
								</c:if>
							
							
					
       

</section>

    <!--/ bradcam_area  -->

    



    <footer class="footer">
        <jsp:include page="../inc/footer2.jsp"/>
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