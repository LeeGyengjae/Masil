<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
    
<html class="no-js" lang="zxx">


<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Travelo</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
    <!-- Place favicon.ico in the root directory -->
	
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>

    <!-- CSS here -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/gijgo.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/slick.css">
    <link rel="stylesheet" href="css/slicknav.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
</head>

<body>
    
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
    <header>
    	<jsp:include page="/inc/header.jsp"/>
    </header>
    <!-- header-end -->

    <!-- slider_area_start -->
    <div class="slider_area">
        <div class="slider_active owl-carousel">
            <div class="single_slider  d-flex align-items-center slider_bg_1 overlay">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-xl-12 col-md-12">
                            <div class="slider_text text-center">
                                <h3>Happy Trip</h3><br><br>
                                <p>고객과 함께 만들어가는 여행일기</p>
                                <a href="${contextPath}/product1/product.do" class="boxed-btn3">판매중인 여행상품 보기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="single_slider  d-flex align-items-center slider_bg_2 overlay">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-xl-12 col-md-12">
                            <div class="slider_text text-center">
                                <h3>Australia</h3>
                                <p>살기 좋은, 살아보고 싶은 나라 1위 호주</p>
                                <a href="${contextPath}/product1/product.do" class="boxed-btn3">판매중인 여행상품 보기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="single_slider  d-flex align-items-center slider_bg_3 overlay">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-xl-12 col-md-12">
                            <div class="slider_text text-center">
                                <h3>Switzerland</h3>
                                <p>하얀 눈만 있는게 아니랍니다 </p>
                                <a href="${contextPath}/product1/product.do" class="boxed-btn3">판매중인 여행상품 보기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- slider_area_end -->


    <div class="popular_places_area">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="section_title text-center mb_70">
                        <h3>Popular Places</h3>
                        <p>뜨끈한 여행상품~!!</p>
                    </div>
                </div>
            </div>
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
								onclick="location.href='${contextPaht}product1/blog.do?code=${product.code}&sub_code=${product.sub_code}'" >
									<div class="thumb">
										<c:if test="${product.image!=null}">
											<c:forTokens items="${product.image}" delims="," var="images">
												<img alt="${images}" src="product/upload/${images}" >
											</c:forTokens>
											<a href="${contextPaht}product1/blog.do?code=${product.code}&sub_code=${product.sub_code}"	class="prise">
												<fmt:formatNumber type="currency" value="${product.price}" currencySymbol="￦ "/>
											</a>
										</c:if>
									</div>
									<div class="place_info">

										<%--여행지역,상품명 --%>
										<a href="${contextPath}product1/blog.do?code=${product.code}&sub_code=${product.sub_code}">
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
													<a href="${contextPath}product1/blog.do?code=${product.code}&sub_code=${product.sub_code}">
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
                        <a class="boxed-btn4" href="${contextPath}/product1/product.do">More Places</a>
                    </div>
                </div>
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
                                <h4>여행 매거진 이메일 구독</h4>
                                <p>실시간 인기 여행지, MD추천 상품 등의 정보를 담은 메일을 정기적으로 보내드립니다.</p>
                            </div>
                        </div>
                        <div class="col-lg-7">
                            <div class="mail_form">
                                <div class="row no-gutters">
                                    <div class="col-lg-9 col-md-8">
                                        <div class="newsletter_field">
                                            <input type="email" placeholder="구독하실 이메일 주소를 입력해주세요." >
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-4">
                                        <div class="newsletter_btn">
                                            <button class="boxed-btn4 " type="submit" >구독신청</button>
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


    <div class="video_area video_bg overlay">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="video_wrap text-center">
                        <h3>Enjoy Video</h3>
                        <div class="video_icon">
                            <a class="popup-video video_play_button" href="https://www.youtube.com/watch?v=f59dDEk57i0">
                                <i class="fa fa-play"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    


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
