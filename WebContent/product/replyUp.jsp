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
<c:forEach var="product" items="${productDetail}" begin="0" end="0" step="1">
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

<body>

	<form class="form-contact comment_form" method="post"
		action="${contextPath}/review1/insertReview.do" id="commentForm">

		<div class="row">
			<input type="hidden" name="end_date" id="end_date"
				value="${reviewAuth}"> <input type="hidden" name="id"
				id="id" value="${id}">

			<c:forEach var="product" items="${productDetail}" begin="0" end="0"
				step="1">
				<input type="hidden" name="code" id="code" value="${product.code}">
				<input type="hidden" name="sub_code" id="sub_code"
					value="${product.sub_code}">
			</c:forEach>

			<%--별점 --%>
			<div class="col-sm-6">
				<div class="form-group starRev">
					<p>${id}님</p>
					<span class="starR">별1</span> <span class="starR">별2</span> <span
						class="starR">별3</span> <span class="starR">별4</span> <span
						class="starR">별5</span> <input type="hidden" name="rating"
						id="rating">
				</div>
			</div>

			<div class="col-sm-6">
				<div class="form-group">
					<button type="submit" class="genric-btn success radius"
						onclick='submit()'>후기 등록</button>
				</div>
			</div>

			<div class="col-12">
				<div class="form-group">
					<textarea class="form-control w-100" name="content" id="content"
						cols="30" rows="9" onfocus="this.placeholder = ''"
						onblur="this.placeholder = '후기를 입력해주세요.'"
						placeholder="후기를 입력해주세요."></textarea>
				</div>
			</div>

		</div>

	</form>
</body>
</html>