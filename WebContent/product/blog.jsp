<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("UTF-8");
	pageContext.setAttribute("newLineChar", "\n");	//개행문자 처리
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

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.starRev span').click(function(){
			$(this).parent().children('span').removeClass('on');
			$(this).parent().children('span').removeClass('ratings');
			$(this).addClass('on').prevAll('span').addClass('on');
			$(this).addClass('ratings').prevAll('span').addClass('ratings');
			var rate = $('.ratings').length;
			$('#rating').attr('value',rate);
			return false;
		});
	});
</script>

<style type="text/css">

.starR{
  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
  background-size: auto 100%;
  width: 30px;
  height: 30px;
  display: inline-block;
  text-indent: -9999px;
  cursor: pointer;
}
.starR.on{background-position:0 0;}


</style>


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
									<p>상품 코드 : ${product.code}-${product.sub_code} </p>
									<p>	
										<c:if test="${product.rating != null}">
											<fmt:parseNumber var="ratingNum" value="${product.rating}" integerOnly="true" />
											<fmt:parseNumber var="ratingNum2" value="${product.rating}" />
												
											<c:forEach var="ratNum" begin="1" end="${(ratingNum*10)/10}">
												평점 : <i class="fas fa-star"></i>
											</c:forEach>
											<c:if test="${ratingNum2-(ratingNum*10)/10 != 0}">
												<i class="fas fa-star-half"></i>
											</c:if>
										</c:if>
									</p>
									
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
													<li>대륙 : ${product.continent}</li>
													<li>기간 : ${product.period} 일</li>
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
								<article class="blog_item slider_area">
									<div class="blog_item_img slider_active owl-carousel">
										<c:forTokens items="${product.image}" delims="," var="images">
											<img class="card-img rounded-0" alt="${images}" 
											src="${contextPath}/product/upload/${images}" height="350px" width="50px" > 
										</c:forTokens>
									</div>
									
									<div class="blog_details">
										<div class="typography">
											<h3>${product.day} 일차</h3>
											<h2>${product.day_title}</h2>
											<p>코스 : ${product.day_course}</p>
										</div>
									
										<p>${fn:replace(product.day_content, newLineChar, "<br/>")}</p>
										<p>이미지 설명 : ${fn:replace(product.img_content, newLineChar, "<br/>")}</p>
										<ul class="blog-info-link">
											<li>
												<i class='fas fa-hotel'></i>숙박 - ${product.stay}
											</li>
											<li>
												<i class="fas fa-bread-slice"></i>식사 - ${product.meal}
											</li>
										</ul>
									</div>
								</article>
							</c:forEach>
							<%--n일차--%>
						</c:when>
					</c:choose>

				<%--후기 : 내용이 없으면 쪼그라드는거 가로 길이 가득 차도록 해야함 / 총 후기 갯수 출력 필요--%>
				<div class="comments-area">
				<c:choose>
					<c:when test="${fn:length(reviewList)==0}">
						<h4>등록된 후기가 없습니다. 후기를 등록해주세요.</h4>
					</c:when>
					
					<c:when test="${fn:length(reviewList)!=0}">
						<h4>${reviewList[0].reviewCnt} Reviews</h4>
						
						<c:forEach var="review" items="${reviewList}">
							<div class="comment-list">
								<div class="single-comment justify-content-between d-flex">
									<div class="user justify-content-between d-flex">
										<div class="desc">
											<div class="d-flex justify-content-between">
								             	<div class="d-flex align-items-center">
													<h5><a href="#">${review.id}</a></h5>
													<p class="date">평점 : ${review.rating}</p>
													<p class="date">작성일자 : ${review.write_date}</p>
												</div>
												<%--후기작성자or관리자일경우에만 수정/삭제 띄우기 해야함 --%>
												<div class="reply-btn">
													<a href="${contextPath}/review1/updateReview.do" class="btn-reply text-uppercase">수정</a>
													<a href="${contextPath}/review1/deleteReview.do" class="btn-reply text-uppercase">삭제</a>
												</div>
											</div>
											<p class="comment">${review.content}</p>
											<p class="comment">여행 다녀온 날짜 : ${review.end_date}</p>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			<%--후기 --%>

						<%-- 페이징 - 후기 최근5개만 출력하고 나머진 페이징 처리해야됨 --%>
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
						<%--페이징 --%>
						
						<%--후기 입력칸 -> 로그인한 사람 중 다녀온 사람만 작성 할 수 있도록 해야함 / ajax로 페이지 이동없이 처리해야함 --%>
						<div class="comment-form">
		                  <h4>여행 후기</h4>
		                  <form class="form-contact comment_form" action="${contextPath}/review1/insertReview.do" id="commentForm">
		                   <div class="row">
		                  	<input type="hidden" name="write_date" id="write_date" value="2020-03-15">
							<input type="hidden" name="end_date" id="end_date" value="2020-03-15">
							<input type="hidden" name="id" id="id" value="${userVO.id}">
							
							<c:forEach var="product" items="${productDetail}" begin="0" end="0" step="1">
								<input type="hidden" name="code" id="code" value="${product.code}">
								<input type="hidden" name="sub_code" id="sub_code" value="${product.sub_code}">
							</c:forEach>
		                  	
		                        <%--별점 --%>
		                        <div class="col-sm-6">
									<div class="form-group starRev">
										<p>${userVO.id} 님</p>
										<span class="starR">별1</span>
										<span class="starR">별2</span>
										<span class="starR">별3</span>
										<span class="starR">별4</span>
										<span class="starR">별5</span>
										<input type="hidden" name="rating" id="rating">
									</div>
								</div>
								
								<div class="col-sm-6">
			                        <div class="form-group">
			                        	<button type="submit" class="genric-btn success radius">후기 등록</button>
			                     	</div>
		                     	</div>
								
		                    	<div class="col-12">
	                    	       <div class="form-group">
		                               <textarea class="form-control w-100" name="comment" id="comment" cols="30" rows="9"
		                               	onfocus="this.placeholder = ''" onblur="this.placeholder = '후기를 입력해주세요.'" 
		                               	placeholder="후기를 입력해주세요."></textarea>
		                           </div>
		                        </div>
		                      </div>  
		                  </form>
		               </div>
		               <%--후기 입력칸 --%>
		               
					</div>
				</div>
			</div>
			<%-- 상품상세페이지 왼쪽 영역 --%>
			
			<%--오른쪽 사이드바 --%>
				<div class="col-lg-4">
                    <div class="blog_right_sidebar">
                    
                    <aside class="single_sidebar_widget">
                    	<h4 class="widget_title">관리자용</h4>
	                    <div class="button-group-area mt-10">
							<input type="button" value="상 품 목 록" class="genric-btn success radius w-100" onclick="location.href='${contextPath}/product1/product.do'">
							<input type="button" value="상 품 수 정" class="genric-btn success radius w-100" onclick="location.href='${contextPath}/product1/update.do'">
							<input type="button" value="상 품 삭 제" class="genric-btn success radius w-100" onclick="location.href='${contextPath}/product1/delete.do'">
						</div>
					</aside>
						
						
                        <aside class="single_sidebar_widget search_widget">
                            <form action="#">
                                <div class="form-group">
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder='Search Keyword'
                                            onfocus="this.placeholder = ''"
                                            onblur="this.placeholder = 'Search Keyword'">
                                        <div class="input-group-append">
                                            <button class="btn" type="button"><i class="ti-search"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <button class="genric-btn success radius w-100"
                                    type="submit">Search</button>
                            </form>
                        </aside>

                        <aside class="single_sidebar_widget post_category_widget">
                            <h4 class="widget_title">Category</h4>
                            <ul class="list cat-list">
                                <li>
                                    <a href="#" class="d-flex">
                                        <p>Resaurant food</p>
                                        <p>(37)</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="d-flex">
                                        <p>Travel news</p>
                                        <p>(10)</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="d-flex">
                                        <p>Modern technology</p>
                                        <p>(03)</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="d-flex">
                                        <p>Product</p>
                                        <p>(11)</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="d-flex">
                                        <p>Inspiration</p>
                                        <p>21</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="d-flex">
                                        <p>Health Care (21)</p>
                                        <p>09</p>
                                    </a>
                                </li>
                            </ul>
                        </aside>

                        <aside class="single_sidebar_widget popular_post_widget">
                            <h3 class="widget_title">Recent Post</h3>
                            <div class="media post_item">
                                <img src="../img/post/post_1.png" alt="post">
                                <div class="media-body">
                                    <a href="single-blog.jsp">
                                        <h3>From life was you fish...</h3>
                                    </a>
                                    <p>January 12, 2019</p>
                                </div>
                            </div>
                            <div class="media post_item">
                                <img src="../img/post/post_2.png" alt="post">
                                <div class="media-body">
                                    <a href="single-blog.jsp">
                                        <h3>The Amazing Hubble</h3>
                                    </a>
                                    <p>02 Hours ago</p>
                                </div>
                            </div>
                            <div class="media post_item">
                                <img src="../img/post/post_3.png" alt="post">
                                <div class="media-body">
                                    <a href="single-blog.jsp">
                                        <h3>Astronomy Or Astrology</h3>
                                    </a>
                                    <p>03 Hours ago</p>
                                </div>
                            </div>
                            <div class="media post_item">
                                <img src="../img/post/post_4.png" alt="post">
                                <div class="media-body">
                                    <a href="single-blog.jsp">
                                        <h3>Asteroids telescope</h3>
                                    </a>
                                    <p>01 Hours ago</p>
                                </div>
                            </div>
                        </aside>
                        <aside class="single_sidebar_widget tag_cloud_widget">
                            <h4 class="widget_title">Tag Clouds</h4>
                            <ul class="list">
                                <li>
                                    <a href="#">project</a>
                                </li>
                                <li>
                                    <a href="#">love</a>
                                </li>
                                <li>
                                    <a href="#">technology</a>
                                </li>
                                <li>
                                    <a href="#">travel</a>
                                </li>
                                <li>
                                    <a href="#">restaurant</a>
                                </li>
                                <li>
                                    <a href="#">life style</a>
                                </li>
                                <li>
                                    <a href="#">design</a>
                                </li>
                                <li>
                                    <a href="#">illustration</a>
                                </li>
                            </ul>
                        </aside>


                        <aside class="single_sidebar_widget instagram_feeds">
                            <h4 class="widget_title">Instagram Feeds</h4>
                            <ul class="instagram_row flex-wrap">
                                <li>
                                    <a href="#">
                                        <img class="img-fluid" src="../img/post/post_5.png" alt="">
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="img-fluid" src="../img/post/post_6.png" alt="">
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="img-fluid" src="../img/post/post_7.png" alt="">
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="img-fluid" src="../img/post/post_8.png" alt="">
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="img-fluid" src="../img/post/post_9.png" alt="">
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="img-fluid" src="../img/post/post_10.png" alt="">
                                    </a>
                                </li>
                            </ul>
                        </aside>
                    </div>
                </div>
				<%--오른쪽 사이드바 --%>
			
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