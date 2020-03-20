<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("UTF-8");
	pageContext.setAttribute("newLineChar", "\n"); //개행문자 처리
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:forEach var="product" items="${productDetail}" begin="0" end="0"
	step="1">
	<c:set var="code" value="${product.code}" />
	<c:set var="sub_code" value="${product.sub_code}" />
</c:forEach>

<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Travelo</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- <link rel="manifest" href="../site.webmanifest"> -->
<link rel="shortcut icon" type="../image/x-icon"
	href="../img/favicon.png">
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
		$('.starRev span').click(function() {
			$(this).parent().children('span').removeClass('on');
			$(this).parent().children('span').removeClass('ratings');
			$(this).addClass('on').prevAll('span').addClass('on');
			$(this).addClass('ratings').prevAll('span').addClass('ratings');
			var rate = $('.ratings').length;
			$('#rating').attr('value', rate);
			return false;
		});
	});
	
	function submit() {
		writeForm.submit();
	}//notNull()
	
	
</script>

<style type="text/css">
.starR {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat right 0;
	background-size: auto 100%;
	width: 30px;
	height: 30px;
	display: inline-block;
	text-indent: -9999px;
	cursor: pointer;
}

.starR.on {
	background-position: 0 0;
}
</style>
<body>
	<section class="blog_area section-padding align-items-center">
		<div class="container">
			<div class="row align-items-center">
				
				<c:choose>
				<c:when test="${fn:length(reviewVO) == 0}">
					ㅜㅜㅜ
				</c:when>
				<c:when test="${fn:length(reviewVO) != 0}">
				
				<c:forEach var="review" items="${reviewVO}" varStatus="revNum">
				<form class="form-contact comment_form" method="post"
					action="${contextPath}/review1/updateReview.do" id="commentForm">

					<div class="row">
						<input type="hidden" name="end_date" id="end_date" value="${review.end_date}"> 
						<input type="hidden" name="id" id="id" value="${review.id}">
						<input type="hidden" name="code" id="code" value="${review.code}">
						<input type="hidden" name="sub_code" id="sub_code" value="${sub_code}">
						<input type="hidden" name="idx" id="idx" value="${review.idx}">

						<%--별점 --%>
						<div class="col-sm-6">
							<div class="form-group starRev">
								<p>${review.id}님</p>
								
								<p class="date">
									<c:if test="${review.rating != null}">
										<fmt:parseNumber var="ratingNum" value="${review.rating}" integerOnly="true" />
										<fmt:parseNumber var="ratingNum2" value="${review.rating}" />
											평점 : 
										<c:forEach var="ratNum" begin="1" end="${(ratingNum*10)/10}">
											<span class="starR on ratings">별1</span> 
										</c:forEach>
										<c:forEach var="ratNum" begin="1" end="${5.0-(ratingNum*10)/10}">
											<span class="starR">별1</span> 
										</c:forEach>
									</c:if>
								</p>
								
								<input type="hidden" name="rating" id="rating">
								<p>여행 다녀온 날짜 : ${review.end_date}</p>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<button type="submit" class="genric-btn success radius"
									onclick='submit()'>후기 수정</button>
							</div>
						</div>

						<div class="col-12">
							<div class="form-group">
								<textarea class="form-control" name="content" id="content"
									cols="30" rows="9" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '후기를 입력해주세요.'"
									placeholder="후기를 입력해주세요.">${review.content}</textarea>
							</div>
						</div>

					</div>

				</form>
				
				</c:forEach>
				</c:when>
				
				</c:choose>
				
				
			</div>
		</div>
	</section>
</body>
</html>