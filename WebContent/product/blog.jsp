<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
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

<!--아이콘 -->
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

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
<!-- <link rel="stylesheet" href="css/responsive.css"> -->
</head>

<body>
	<!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

	<!-- header-start -->
	<jsp:include page="../inc/header2.jsp"></jsp:include>
	<!-- header-end -->

	<!-- bradcam_area  -->
	<div class="bradcam_area bradcam_bg_4">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="bradcam_text text-center">
						<h3>blog</h3>
						<p>Pixel perfect design with awesome contents</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/ bradcam_area  -->


	<!--================Blog Area =================-->
	<section class="blog_area section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-5 mb-lg-0">
					<div class="blog_left_sidebar">
					
					<c:choose>
						<c:when test="${requestScope.productDetail == null }">
							<h1>상품선택 필요</h1>
						</c:when>
						<c:when test="${requestScope.productDetail != null }">
							<c:forEach var="product" items="${productDetail}" begin="0" end="0" step="1">
							<%--상품소개--%>
							<article class="blog_item">
								<div class="blog_item_img">
									<h1>${product.title}</h1>
									<p>상품 코드 : ${product.code}-${product.sub_code}</p>
									
									<%--출발,도착 등 일정 --%>
									<div class="section-top-border">
										<h3 class="mb-30">이용 교통</h3>
										<div class="row">
											<div class="col-md-4">
												<div class="single-defination">
													<span class="contact-info__icon">
						                            	<i class='fas fa-plane-departure' style='font-size:36px'></i>
						                            	출발
						                            </span>
						                            <div class="media-body">
						                                <p>${product.start_date}</p>
						                            </div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="single-defination">
													<span class="contact-info__icon">
						                            	<i class='fas fa-plane-arrival' style='font-size:36px'></i>
						                            	도착
						                            </span>
						                            <div class="media-body">
						                                <p>${product.end_date}</p>
						                            </div>
												</div>
											</div>
										</div>
									</div>
									<%--출발,도착 등 일정--%>
									
									<%--전체일정 --%>
									<div class="section-top-border">
										<h3 class="mb-30">코스</h3>
										<div class="row">
											<div class="col-lg-12">
												<blockquote class="generic-blockquote">
													${product.course}
												</blockquote>
											</div>
										</div>
										
										<div class="col-md-4 mt-sm-30">
											<h3 class="mb-20">기타</h3>
											<div class="">
												<ul class="unordered-list">
													<li>가격 : ${product.price}</li>
													<li>대륙 : ${product.continent }</li>
													<li>?? : ${product.day_area}</li>
													<li>Make Myspace Your Best Designed Space</li>
													<li>Cleaning And Organizing Your Computer</li>
												</ul>
											</div>
										</div>
									</div>
									<%--전체일정 --%>
								</div>
							</article>
							</c:forEach>
							<%--상품소개--%>
							
							<%--n일차--%> 
							<c:forEach var="product" items="${productDetail}" varStatus="productNum">
								<article class="blog_item">
									<div class="blog_item_img">
										<img class="card-img rounded-0" src="../product/img/osaka/uu.jpg"
											height="350px" width="50px" alt=""> 
										<a href="#" class="blog_item_date">
											<h3>${product.day} 일차</h3>
											<p>${product.day_area}</p>
										</a>
									</div>
		
									<div class="blog_details">
										<a class="d-inline-block" href="#">
											<h2>${product.day_title}</h2>
										</a>
										<p>${product.day_content}</p>
										<p>이미지 : ${product.image}</p>
										<p>이미지 설명 : ${product.img_content}</p>
										<ul class="blog-info-link">
											<li>
												<a href="#"><i class="fa fa-user"></i>${product.stay}</a>
											</li>
											
											<li>
												<a href="#"><i class="fa fa-comments"></i>${product.meal}</a>
											</li>
										</ul>
									</div>
								</article>
							</c:forEach>
							<%--n일차--%>
						</c:when>
					</c:choose>

						<%-- 페이징 - 언제 쓰지? --%>
						<nav class="blog-pagination justify-content-center d-flex">
							<ul class="pagination">
								<li class="page-item">
									<a href="#" class="page-link" aria-label="Previous">
										<i class="ti-angle-left"></i>
									</a>
								</li>
								<li class="page-item">
									<a href="#" class="page-link">1</a>
								</li>
								<li class="page-item active">
									<a href="#" class="page-link">2</a>
								</li>
								<li class="page-item">
									<a href="#" class="page-link" aria-label="Next">
										<i class="ti-angle-right"></i>
									</a>
								</li>
							</ul>
						</nav>
						<%-- 페이징 --%>
						
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================Blog Area =================-->

	<!-- footer start -->
	<jsp:include page="../inc/footer2.jsp"></jsp:include>
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