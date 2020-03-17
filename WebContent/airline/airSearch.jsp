<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");	
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
<html class="no-js" lang="zxx">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Travelo</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- <link rel="manifest" href="../site.webmanifest"> -->
<link rel="shortcut icon" type="../image/x-icon" href="../img/favicon.png">
<!-- Place favicon.ico in the root directory -->

<!-- CSS here -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/magnific-popup.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/themify-icons.css">
<link rel="stylesheet" href="../css/nice-select.css">
<link rel="stylesheet" href="../css/flaticon.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/gijgo.css">
<link rel="stylesheet" href="../css/animate.css">
<link rel="stylesheet" href="../css/slick.css">
<link rel="stylesheet" href="../css/slicknav.css">

<link rel="stylesheet" href="../css/style.css">
<!-- <link rel="stylesheet" href="../css/responsive.css"> -->

<script type="text/javascript">
	
	


</script>


</head>

<body>
	<!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

	<!-- header-start -->
	<header>
		<jsp:include page="../inc/header2.jsp"></jsp:include>
	</header>
	<!-- header-end -->

	<!-- bradcam_area  -->
	<div class="bradcam_area bradcam_bg_4">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="bradcam_text text-center">
						<h3>elements</h3>
						<p>Pixel perfect design with awesome contents</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/ bradcam_area  -->

	<%--상품 쓰기 --%>
	<div class="whole-wrap">
		<div class="container box_1170">
			<div class="section-top-border">
				<div class="row">
					<div class="col-12">
                        <h2 class="contact-title">항공권조회</h2>
                    </div>
                    <div class="col-lg-12">
                        <form class="form-contact contact_form" action="${contextPath}/board/airline.do" method="get" id="airForm">
                            <div class="row">
                            	<div class="col-sm-4">
                                    <div class="form-group">
                                    	<span>출발공항</span> &nbsp;
                                        <input type="text" list="choices1" class="form-control" name="depAirportId" id="depAirportId" width="400px"
                                         onfocus="this.placeholder = ''" onblur="this.placeholder = '출발공항을 입력하세요'"
                                        placeholder="출발공항">
                                        <datalist id="choices1">
	                                        <option value="NAARKJB" label="무안"></option>
	                                        <option value="NAARKJJ" label="광주"></option>
	                                        <option value="NAARKJK" label="군산"> </option>
	                                        <option value="NAARKJY" label="여수"></option>
	                                        <option value="NAARKNW" label="원주"> </option>
	                                        <option value="NAARKNY" label="양양"></option>
	                                        <option value="NAARKPC" label="제주"></option>
	                                        <option value="NAARKPK" label="김해"></option>
	                                        <option value="NAARKPS" label="사천"></option>
	                                        <option value="NAARKPU" label="울산"></option>
	                                        <option value="NAARKSI" label="인천"></option>
	                                        <option value="NAARKSS" label="김포"></option>
	                                        <option value="NAARKTH" label="포항"></option>
	                                        <option value="NAARKTN" label="대구"></option>
	                                        <option value="NAARKTU" label="청주"></option>
                                        </datalist>
                                       
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                    	<span>도착공항</span><br>
                                        <input type="text" list="choices2" class="form-control" name="arrAirportId" id="arrAirportId" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = '도착공항을 입력하세요'" 
                                        placeholder="도착공항">
	                                        <datalist id="choices2">
	                                        <option value="NAARKJB" label="무안"></option>
	                                        <option value="NAARKJJ" label="광주"></option>
	                                        <option value="NAARKJK" label="군산"> </option>
	                                        <option value="NAARKJY" label="여수"></option>
	                                        <option value="NAARKNW" label="원주"> </option>
	                                        <option value="NAARKNY" label="양양"></option>
	                                        <option value="NAARKPC" label="제주"></option>
	                                        <option value="NAARKPK" label="김해"></option>
	                                        <option value="NAARKPS" label="사천"></option>
	                                        <option value="NAARKPU" label="울산"></option>
	                                        <option value="NAARKSI" label="인천"></option>
	                                        <option value="NAARKSS" label="김포"></option>
	                                        <option value="NAARKTH" label="포항"></option>
	                                        <option value="NAARKTN" label="대구"></option>
	                                        <option value="NAARKTU" label="청주"></option>
                                        </datalist>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                   	 	<span>출발일</span>
                                        <input class="form-control" name="depPlandTime" id="depPlandTime" type="text" 
                                        onfocus="this.placeholder = '20191231와 같은 형식으로 입력하세요'" onblur="this.placeholder = '출발일을 입력하세요'" 
                                        placeholder="20191231">
                                    </div>
                                </div>
                                
                            <div class="form-group mt-3">
                                <button type="submit" class="button button-contactForm boxed-btn">항공조회</button>
                            </div>
                            </div>
                        </form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%--상품 쓰기 --%>






	<!-- footer start -->
	<footer class="footer">
		<jsp:include page="../inc/footer2.jsp" />
	</footer>
	<!--/ footer end  -->

	<!-- Modal -->
	<div class="modal fade custom_search_pop" id="exampleModalCenter"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="serch_form">
					<input type="text" placeholder="Search">
					<button type="submit">search</button>
				</div>
			</div>
		</div>
	</div>

	<!-- JS here -->
	<script src="../js/vendor/modernizr-3.5.0.min.js"></script>
	<script src="../js/vendor/jquery-1.12.4.min.js"></script>
	<script src="../js/popper.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/owl.carousel.min.js"></script>
	<script src="../js/isotope.pkgd.min.js"></script>
	<script src="../js/ajax-form.js"></script>
	<script src="../js/waypoints.min.js"></script>
	<script src="../js/jquery.counterup.min.js"></script>
	<script src="../js/imagesloaded.pkgd.min.js"></script>
	<script src="../js/scrollIt.js"></script>
	<script src="../js/jquery.scrollUp.min.js"></script>
	<script src="../js/wow.min.js"></script>
	<script src="../js/nice-select.min.js"></script>
	<script src="../js/jquery.slicknav.min.js"></script>
	<script src="../js/jquery.magnific-popup.min.js"></script>
	<script src="../js/plugins.js"></script>
	<script src="../js/gijgo.min.js"></script>

	<!--contact js-->
	<script src="../js/contact.js"></script>
	<script src="../js/jquery.ajaxchimp.min.js"></script>
	<script src="../js/jquery.form.js"></script>
	<script src="../js/jquery.validate.min.js"></script>
	<script src="../js/mail-script.js"></script>

	<script src="../js/main.js"></script>
	<script>
		$('#datepicker').datepicker({
			iconsLibrary : 'fontawesome',
			icons : {
				rightIcon : '<span class="fa fa-caret-down"></span>'
			}
		});
		$('#datepicker2').datepicker({
			iconsLibrary : 'fontawesome',
			icons : {
				rightIcon : '<span class="fa fa-caret-down"></span>'
			}

		});
	</script>
</body>
</html>