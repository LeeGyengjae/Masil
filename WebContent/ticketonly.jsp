<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
    <!-- Place favicon.ico in the root directory -->
 
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
    <link rel="stylesheet" href="css/slicknav.css">
    <link rel="stylesheet" href="css/style.css">
   

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
                        <h3>For backpakers</h3>
                        <p>you can buy only Flights tickets</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ bradcam_area  -->
    
    <!-- 현재 판매중인 상품을 대상으로 결제 혹은 예약을 해주는 화면의 첫단계 -->
    <!-- 판매중 상품의 리스트를 출력해주고 각 상품별로 패키지 예약(결제) / 
    	  항공권만 예약(결제) 만 가능 하도록 구현 한다 -->
    
    <div class="col-lg-16">
					<div class="row">
						<c:choose>
							<%-- 판매중 상품 없을때--%>
							<c:when test="${requestScope.productList == null }">
								<div class="col-lg-6 col-md-6">
									<div class="single_place">
										<div class="thumb">
											<img src="img/place/1.png"> 
				
										</div>
										<div class="place_info">
											<%--상품명 --%>
												<h3>판매중인 상품이 없습니다.</h3>
											</a>
											<p>현재 여행상품 기획중입니다.</p>
										
										</div>
									</div>
								</div>
							</c:when>
							 
							<%-- 상품 있을 때 : ↓상품 1개씩↓ --%>
							<c:when test="${requestScope.productList != null }">
								<c:forEach var="product" items="${productList}">
									<div class="col-lg-6 col-md-6">
										<div class="single_place">
											<div class="thumb">
												<c:forTokens items="${product.image}" delims="," var="images">
													<img alt="${images}" src="${contextPath}/product/upload/${images}"> 
												</c:forTokens>
												<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}"	class="prise">
													
												<fmt:formatNumber type="currency" value="${product.price}" currencySymbol="￦ "/>	
													
												</a>
											</div>
											<div class="place_info">

												<%--여행지역,상품명 --%>
												<a href="blog.do?code=${product.code}&sub_code=${product.sub_code}">
													<h3>${product.title}</h3>
												</a>
												<p>${product.code} ${product.sub_code} <br>
												 ${product.comment} | start : ${product.start_date}</p>

												<div class="rating_days d-flex justify-content-between">
													<span
														class="d-flex justify-content-center align-items-center">
														<%-- 별점 ${product.rating} 해서 받아온 값만큼만 ★ 출력, 나머진 ☆ 처리 해야됨--%>
															<i class="fas fa-star"></i>
														<p>${product.rating}</p>
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
					
					<div class="whole-wrap">
		<div class="container box_1170">
			<div class="section-top-border">
				<div class="row">
					<div class="col-12">
                        <h2 class="contact-title">국내선 항공운항 정보</h2>
                    </div>
                    <div class="col-lg-12">
                        <form class="form-contact contact_form" action="${contextPath}/board/airline.do" method="get" id="airForm">
                            <div class="row">
                            	<div class="col-sm-4">
                                    <div class="form-group">
                                    	<span>출발지</span> &nbsp;
                                        <input type="text" list="choices1" class="form-control" name="depAirportId" id="depAirportId" width="400px"
                                         onfocus="this.placeholder = ''" onblur="this.placeholder = '출발공항을 입력하세요'"
                                        placeholder="출발공항">
                                          <datalist id="choices1">
	                                        <option value="NAARKNY" label="양양국제(YNY)"></option>
	                                        <option value="NAARKPC" label="제주국제(JEJ)"></option>
	                                        <option value="NAARKPK" label="김해국제(GMH)"></option>
	                                        <option value="NAARKPU" label="울산공항(ULS)"></option>
	                                        <option value="NAARKSI" label="인천국제(ICN)"></option>
	                                        <option value="NAARKSS" label="김포국제(GMP)"></option>
	                                        <option value="NAARKTN" label="대구국제(DGU)"></option>
	                                        <option value="NAARKTU" label="청주국제(CJC)"></option>
                                        </datalist>
                                       
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                    	<span>목적지</span><br>
                                        <input type="text" list="choices2" class="form-control" name="arrAirportId" id="arrAirportId" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = '도착공항을 입력하세요'" 
                                        placeholder="도착공항">
	                                        <datalist id="choices2">
	                                        <option value="NAARKNY" label="양양국제(YNY)"></option>
	                                        <option value="NAARKPC" label="제주국제(JEJ)"></option>
	                                        <option value="NAARKPK" label="김해국제(GMH)"></option>
	                                        <option value="NAARKPU" label="울산공항(ULS)"></option>
	                                        <option value="NAARKSI" label="인천국제(ICN)"></option>
	                                        <option value="NAARKSS" label="김포국제(GMP)"></option>
	                                        <option value="NAARKTN" label="대구국제(DGU)"></option>
	                                        <option value="NAARKTU" label="청주국제(CJC)"></option>
                                        </datalist>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                   	 	<span>출발일</span>
                                   	 	 <input class="form-control" name="depPlandTime" id="startDate" type="text" 
                                        onblur="this.placeholder = '출발일을 입력하세요'" 
                                        placeholder="YYYY-MM-DD">
                                        
                                    </div>
                                </div>
                                
                            <div class="form-group mt-3">
                                <button type="submit" class="button button-contactForm boxed-btn">조회</button>
                                <button type="reset" class="button button-contactForm boxed-btn">재입력</button>
                            </div>
                            </div>
                        </form>
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



</body>

</html>