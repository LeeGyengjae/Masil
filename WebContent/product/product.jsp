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

<!-- <link rel="manifest" href="site.webmanifest"> -->
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
	<div class="bradcam_area bradcam_bg_2">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="bradcam_text text-center">
						<h3>Destinations</h3>
						<p>Pixel perfect design with awesome contents</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/ bradcam_area  -->

	<!-- where_togo_area_start  -->
	<div class="where_togo_area">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-3">
					<div class="form_area">
						<h3>Where you want to go?</h3>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="search_wrap">
						<form class="search_form" action="#">
							<div class="input_field">
								<input type="text" placeholder="Where to go?">
							</div>
							<div class="input_field">
								<input id="datepicker" placeholder="Date">
							</div>
							<div class="input_field">
								<select>
									<option data-display="Travel type">Travel type</option>
									<option value="1">Some option</option>
									<option value="2">Another option</option>
								</select>
							</div>
							<div class="search_btn">
								<button class="boxed-btn4 " type="submit">Search</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- where_togo_area_end  -->


	<div class="popular_places_area">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="filter_result_wrap">
						<h3>Filter Result</h3>
						<div class="filter_bordered">
							<div class="filter_inner">
								<div class="row">
									<div class="col-lg-12">
										<div class="single_select">
											<select>
												<option data-display="Country">Country</option>
												<option value="1">Africa</option>
												<option value="2">canada</option>
												<option value="3">USA</option>
											</select>
										</div>
									</div>
									<div class="col-lg-12">
										<div class="single_select">
											<select>
												<option data-display="Travel type">Travel type</option>
												<option value="1">advance</option>
												<option value="2">advance</option>
												<option value="3">premium</option>
											</select>
										</div>
									</div>
									<div class="col-lg-12">
										<div class="range_slider_wrap">
											<span class="range">Prise range</span>
											<div id="slider-range"></div>
											<p>
												<input type="text" id="amount" readonly
													style="border: 0; color: #7A838B; font-weight: 400;">
											</p>
										</div>
									</div>
								</div>


							</div>

							<div class="reset_btn">
								<button class="boxed-btn4" type="submit">Reset</button>
							</div>
						</div>
					</div>
				</div>


				<%-- ↓상품 띄울 영역↓ --%>
				<div class="col-lg-8">
					<div class="row">
						<c:choose>
							<%--상품 없을때--%>
							<c:when test="${requestScope.productList == null }">
								<div class="col-lg-6 col-md-6">
									<div class="single_place">
										<div class="thumb">
											<img src="../img/place/1.png" alt=""> 
											<a href="productDetail.do" class="prise">$0</a>
										</div>
										<div class="place_info">
											<%--여행지역,상품명 --%>
											<a href="productDetail.do">
												<h3>상품 없음</h3>
											</a>
											<p>상품 없어요ㅠㅠ</p>

											<div class="rating_days d-flex justify-content-between">
												<span
													class="d-flex justify-content-center align-items-center">
													<%-- 별점 --%> 
													<i class="fa fa-star"></i> 
													<i class="fa fa-star"></i> 
													<i class="fa fa-star"></i> 
													<i class="fa fa-star"></i> 
													<i class="fa fa-star"></i> 
													
													<%-- 후기 개수 --%>
													<a href="productDetail.do">(0 Review)</a>
												</span>
												<%-- 여행기간 --%>
												<div class="days">
													<i class="fa fa-clock-o"></i> <a href="productDetail.do">0 Days</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:when>
							 
							<%-- 상품 있을 때 : ↓상품 1개씩↓ --%>
							<c:when test="${requestScope.productList != null }">
								<c:forEach var="product" items="${productList}" varStatus="productNum">
									<div class="col-lg-6 col-md-6">
										<div class="single_place">
											<div class="thumb">
												<img src="../img/place/1.png" alt="${product.image}"> 
												<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}"	class="prise">
													${product.price}
												</a>
											</div>
											<div class="place_info">

												<%--여행지역,상품명 --%>
												<%-- <a href="productDetail.do?code=${product.code}"> --%>
												<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}">
													<h3>${product.title}</h3>
												</a>
												<p>${product.code} ${product.sub_code} <br>
												 ${product.comment} | start : ${product.start_date}</p>

												<div class="rating_days d-flex justify-content-between">
													<span
														class="d-flex justify-content-center align-items-center">
														<%-- 별점 --%>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i> 
														<i class="fa fa-star"></i> 
														<i class="fa fa-star"></i> 
														<%-- 후기 개수 --%>
														<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}">
															(${product.recnt} Review)
														</a>
													</span>

													<%-- 여행기간 --%>
													<div class="days">
														<i class="fa fa-clock-o"></i>
														<a href="#">
															<%-- <fmt:parseDate value="${product.end_date}" var="endDate" pattern="yyyy-MM-dd"/>
															<fmt:parseNumber value="${endDate.time / (1000*60*60*24)}" integerOnly="true" var="end_Date"/>
															<fmt:parseDate value="${product.start_date}" var="startDate" pattern="yyyy-MM-dd"/>
															<fmt:parseNumber value="${startDate.time / (1000*60*60*24)}" integerOnly="true" var="start_Date"/> --%>
															${product.period} 일
														</a>
													</div>

												</div>
											</div>
										</div>
									</div>
								</c:forEach>


								<%-- ↑상품 1개씩↑ --%>
							</c:when>
						</c:choose>
					</div>


					<%-- 상품 더 보기 버튼 --%>
					<div class="row">
						<div class="col-lg-12">
							<div class="more_place_btn text-center">
								<a class="boxed-btn4" href="#">상품 더보기</a>
							</div>
						</div>
					</div>
					<%-- 상품 더 보기 버튼 --%>

				</div>
				<%-- ↑상품 띄울 영역↑ --%>

			</div>
		</div>
	</div>

	<!-- newletter_area_start  -->
	<div class="newletter_area overlay">
		<div class="container">
			<div class="row justify-content-center align-items-center">
				<div class="col-lg-10">
					<div class="row align-items-center">
						<div class="col-lg-5">
							<div class="newsletter_text">
								<h4>Subscribe Our Newsletter</h4>
								<p>Subscribe newsletter to get offers and about new places
									to discover.</p>
							</div>
						</div>
						<div class="col-lg-7">
							<div class="mail_form">
								<div class="row no-gutters">
									<div class="col-lg-9 col-md-8">
										<div class="newsletter_field">
											<input type="email" placeholder="Your mail">
										</div>
									</div>
									<div class="col-lg-3 col-md-4">
										<div class="newsletter_btn">
											<button class="boxed-btn4 " type="submit">Subscribe</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- newletter_area_end  -->
	<div class="recent_trip_area">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6">
					<div class="section_title text-center mb_70">
						<h3>Recent Trips</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="single_trip">
						<div class="thumb">
							<img src="../img/trip/1.png" alt="">
						</div>
						<div class="info">
							<div class="date">
								<span>Oct 12, 2019</span>
							</div>
							<a href="#">
								<h3>Journeys Are Best Measured In New Friends</h3>
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="single_trip">
						<div class="thumb">
							<img src="../img/trip/2.png" alt="">
						</div>
						<div class="info">
							<div class="date">
								<span>Oct 12, 2019</span>
							</div>
							<a href="#">
								<h3>Journeys Are Best Measured In New Friends</h3>
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="single_trip">
						<div class="thumb">
							<img src="../img/trip/3.png" alt="">
						</div>
						<div class="info">
							<div class="date">
								<span>Oct 12, 2019</span>
							</div>
							<a href="#">
								<h3>Journeys Are Best Measured In New Friends</h3>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- footer -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- footer -->


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
	<!-- link that opens popup -->


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
	<script src="../js/jquery-ui.min.js">
		
	</script>
	<script src="../js/nice-select.min.js"></script>
	<script src="../js/jquery.slicknav.min.js"></script>
	<script src="../js/jquery.magnific-popup.min.js"></script>
	<script src="../js/plugins.js"></script>
	<script src="../js/range.js"></script>
	<!-- <script src="../js/gijgo.min.js"></script> -->
	<script src="../js/slick.min.js"></script>



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
	</script>
	</script>

</body>

</html>