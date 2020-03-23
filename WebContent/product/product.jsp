<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
	String pageNum = request.getParameter("pageNum");
	String id = (String)request.getSession().getAttribute("id");
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

<script type="text/javascript">

	//더보기 버튼 클릭시 상품 목록 5개씩 추가 출력 -해야함
	function moreList() {
		if (count > 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 5;
			int startPage = ((currentPage / pageBlock) - (currentPage % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			if (startPage > pageBlock) {
				pageNum=startPage - pageBlock;
			}
			
			for (i = startPage; i <= endPage; i++) {
				pageNum=i;
			}
			
		}
		
	}//function moreList


</script>

</head>

<body>

<%-- 	<c:set var="count" value="${countProduct}" /> --%>
<%-- 	<c:set var="pageSize" value="5" /> --%>
<%-- 	<c:if test="${pageNum==null}"> --%>
<%-- 		<c:set var="pageNum" value="1"/> --%>
<%-- 	</c:if> --%>
<%-- 	<fmt:parseNumber value="pageNum" type="number" var="currentPage"/> --%>
<%-- 	<c:set var="startRow" value="${(currentPage-1)*pageSize}" /> --%>


	<!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

	<!-- header-start -->
	<jsp:include page="../inc/header2.jsp"></jsp:include>
	<!-- header-end -->

	<!-- bradcam_area  -->
	<div class="bradcam_area bradcam_bg_2" id="TOPHERE"> 
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="bradcam_text text-center">
						<h3>Destinations</h3>
						<p>뜨끈한 여행상품~!!</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/ bradcam_area  -->

	<c:if test="${id eq 'master'}">
		<div class="where_togo_area">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-3">
						<div class="form_area">
							<h3>관리자용 메뉴</h3>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="search_wrap">
							<div class="button-group-area mt-40">
								<a href="${contextPath}/product/write.jsp" class="boxed-btn4">새상품업로드</a>
								<a href="${contextPath}/product1/pre_write.do" class="boxed-btn4">상품 목록2</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	
	<div class="popular_places_area">
        <div class="container">
            
            <div class="row">
                <c:choose>
					<%--상품 없을때--%>
					<c:when test="${requestScope.productList == null }">
						<div class="col-lg-4 col-md-6">
							<div class="single_place">
								<div class="thumb">
									<img src="img/place/1.png" alt=""> 
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
							<c:forEach var="product" items="${productList}">
								<div class="col-lg-6 col-md-6">
									<div class="single_place" style="cursor:pointer;" 
									onclick="location.href='blog.do?code=${product.code}&sub_code=${product.sub_code}'" >
										<div class="thumb">
											<c:if test="${product.image!=null}">
												<c:forTokens items="${product.image}" delims="," var="images">
													<img alt="${images}" src="${contextPath}/product/upload/${images}" >
												</c:forTokens>
											</c:if>
											<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}"	class="prise">
												<fmt:formatNumber type="currency" value="${product.price}" currencySymbol="￦ "/>	
											</a>
										</div>
										<div class="place_info">

											<%--여행지역,상품명 --%>
											<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}">
												<h3>${product.title}</h3>
											</a>
											<p>${product.code}-${product.sub_code} <br>
											 ${product.comment} | start : ${product.start_date}</p>

											<div class="rating_days d-flex justify-content-between">
												<span
													class="d-flex justify-content-center align-items-center">
														
													<c:if test="${product.rating != null}">
														<fmt:parseNumber var="ratingNum" value="${product.rating}" integerOnly="true" />
														<fmt:parseNumber var="ratingNum2" value="${product.rating}" />
														
														<c:forEach var="ratNum" begin="1" end="${(ratingNum*10)/10}">
															<i class="fas fa-star"></i>
														</c:forEach>
														<c:if test="${ratingNum2-(ratingNum*10)/10 != 0}">
															<i class="fas fa-star-half"></i>
														</c:if>
													</c:if>

													
													<%-- 후기 개수 --%>
													<c:if test="${product.recnt != null}">
														<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}">
															(${product.recnt} Review)
														</a>
													</c:if>
												</span>
												<%-- 여행기간 --%>
												<div class="days">
													<i class="fa fa-clock-o"></i>
													<a href="#">
														${product.period} 일
													</a>
												</div>
												<%-- 여행기간 --%>
											</div>
										</div>
									</div>
									
								</div>
							</c:forEach>
						<%-- ↑상품 1개씩↑ --%>
					</c:when>
				</c:choose>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="more_place_btn text-center">
                        <a class="boxed-btn4" href="#TOPHERE">TOP</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<!-- footer -->
		<jsp:include page="../inc/footer3.jsp"></jsp:include>
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