
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	request.setCharacterEncoding("UTF-8");
	String airResult = "";
	airResult = (String) request.getAttribute("airResult");
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(airResult);
%>
<c:set var="airList" value="<%=jsonObject%>" />


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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


<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>


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
					<div class="col-lg-8">
						<form class="form-contact contact_form"
							action="/airline/airReserv.jsp" method="get" id="airReserv">
							<div class="row">
								<div class="section-top-border">
									<h2 class="contact-title">스케줄 조회 결과</h2>
									
									<div style="text-align: center;">
										<div class="progress-table">
											<table style="width: 100%">
												<c:choose>
													<c:when test="${airList.response.body.totalCount == 0 }">
														<tr>
															<td colspan="7" align="center" style="margin-top: 20px;">항공편이 조회되지 않습니다.</td>
														</tr>
													</c:when>


													<c:when test="${airList.response.body.totalCount == 1}">

														</tr>
														<div style="overflow: auto;  height: 400px">
  														<table class="table mt-3">

    													<tbody>
														<tr class="bg-light">
														<td>항공사 </td>
														<td>출발시각</td>		
														<td>도착시각</td>
														<td>금액</td>
														<td>편명</td>
			
														</tr>
												
		    											</tbody>
														<tr align="right">
															<td>${airList.response.body.items.item.airlineNm}</td>
															<td>${airList.response.body.items.item.arrAirportNm}</td>
															<td>
																${fn:substring(airList.response.body.items.item.depPlandTime,4,6)}/${fn:substring(airList.response.body.items.item.depPlandTime,6,8)}&nbsp;
																${fn:substring(airList.response.body.items.item.depPlandTime,8,10)}:${fn:substring(airList.response.body.items.item.depPlandTime,10,12)}</td>
															<td>
																${fn:substring(airList.response.body.items.item.arrPlandTime,4,6)}/${fn:substring(airList.response.body.items.item.arrPlandTime,6,8)}&nbsp;
																${fn:substring(airList.response.body.items.item.arrPlandTime,8,10)}:${fn:substring(airList.response.body.items.item.arrPlandTime,10,12)}</td>
															<td>${airList.response.body.items.item.depAirportNm}</td>
															<td><fmt:formatNumber groupingUsed="true"
																	value="${airList.response.body.items.item.economyCharge}" /></td>
					
															<td>${airList.response.body.items.item.vihicleId}</td>
															<td><input type="radio" name="rsv" id="${count}"
																value="${count}" style="align: center;" /></td>
														</tr>
													</c:when>
													<c:otherwise>
															</tr>
															
															 <div class="col-md-9 order-md-1">
	
														<div style="overflow: auto;  height: 400px">
  														<table class="table mt-3">

    													<tbody>
														<tr class="bg-light">
														<tr class="bg-light">
														<td>항공사 </td>
														<td>출발시각</td>		
														<td>도착시각</td>
														<td>금액</td>
														<td>편명</td>
														<td>예약</td>
				
														</tr>
												
		    											</tbody>
														<tr align="right">
														<c:forEach var="airLine"
															items="${airList.response.body.items.item}">

															<tr align="right">

																<td>${airLine.airlineNm}</td>
																<%-- <td>${airLine.arrAirportNm}</td> --%>
																<td>
																	${fn:substring(airLine.depPlandTime,4,6)}/${fn:substring(airLine.depPlandTime,6,8)}&nbsp;
																	${fn:substring(airLine.depPlandTime,8,10)}:${fn:substring(airLine.depPlandTime,10,12)}</td>
																<td>
																	${fn:substring(airLine.arrPlandTime,4,6)}/${fn:substring(airLine.arrPlandTime,6,8)}&nbsp;
																	${fn:substring(airLine.arrPlandTime,8,10)}:${fn:substring(airLine.arrPlandTime,10,12)}</td>
																<%-- <td>${airLine.depAirportNm}</td> --%>
																<td><fmt:formatNumber groupingUsed="true"
																		value="${airLine.economyCharge}" /></td>
																
																<td>${airLine.vihicleId}</td>
																<td><input type="radio" name="rsv" id="${count}"
																	value="${count}" style="align: center;" /></td>

															</tr>
														</c:forEach>
													</c:otherwise>

												</c:choose>
											</table>

										</div>
									</div>
								</div>
							</div>
							<div class="form-group mt-3">
								<%
									request.setAttribute("airResult", airResult);
								%>
								<a class="button button-contactForm boxed-btn" href="#" role="button" id="check_module">결제</a>
							
								<button onclick="location.href='../product/blog.jsp'"
									class="button button-contactForm boxed-btn">상품목록</button>
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
	
	<script>
		$("#check_module").click(function() {
			var IMP = window.IMP; // 생략가능
			IMP.init("imp11426028");
			// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
			// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
			IMP.request_pay({
				pg : 'inicis' , // version 1.1.0부터 지원.
				/*
				 'kakao':카카오페이,
				 html5_inicis':이니시스(웹표준결제)
				 'nice':나이스페이
				 'jtnet':제이티넷
				 'uplus':LG유플러스
				 'danal':다날
				 'payco':페이코
				 'syrup':시럽페이
				 'paypal':페이팔
				 */

				pay_method : 'card',
				/*
				 'samsung':삼성페이,
				 'card':신용카드,
				 'trans':실시간계좌이체,
				 'vbank':가상계좌,
				 'phone':휴대폰소액결제
				 */
				merchant_uid : 'merchant_' + new Date().getTime(),
				/*
				 merchant_uid에 경우
				 https://docs.iamport.kr/implementation/payment
				 위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
				 참고하세요.
				 나중에 포스팅 해볼게요.
				 */
				name : '부산-제주',
				//결제창에서 보여질 이름
				amount : 61600,
				//가격
				buyer_email : 'airbusan@payment.com',
				buyer_name : '성민규',
				buyer_tel : '051-890-0909',
				buyer_addr : '부산시 강서구 중앙대로1 에어부산 사옥 2F',
				buyer_postcode : '211-0005',
				m_redirect_url : 'https://www.yourdomain.com/payments/complete'
			/*
			 모바일 결제시,
			 결제가 끝나고 랜딩되는 URL을 지정
			 (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
			 */
			}, function(rsp) {
				console.log(rsp);
				if (rsp.success) {
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid;
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
				}
				alert(msg);
			});
		});
	</script>
</body>
</html>