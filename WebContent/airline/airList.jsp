
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
	airResult = (String)request.getAttribute("airResult");	
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject)jsonParser.parse(airResult);
	
	
	
	
%>
<c:set var="airList" value="<%=jsonObject%>"/>


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
                        <form class="form-contact contact_form" action="/airline/airReserv.jsp" method="get" id="airReserv">
                            <div class="row">
					<div class="section-top-border">
				<h2 class="contact-title">스케줄 조회</h2>
					
					
				<div style="text-align: center;">
					<div class="progress-table">					
						<table style="width: 100%">
							<c:choose>							
								<c:when test="${airList.response.body.totalCount == 0 }">								
									<tr>
										<td colspan="7" align="center" style="margin-top:20px;">조회된 항공이 없습니다.</td>
									</tr>
								</c:when>							
										
														    
									
									<c:when test="${airList.response.body.totalCount == 1}">									
																				
										<tr align="right">
								<th width="15%">항공사</td>
								<!-- <td width="10%">도착공항</td> -->
								<th width="15%">출발시간</td>	
								<th width="15%">도착시간</td>
								<!-- <td width="10%">출발공항</td> -->
								
								<th width="15%">일반석</td>
								<th width="15%">비지니스</td>							
								<th width="15%">항공ID</td>													
							</tr>			
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
												<td><fmt:formatNumber  groupingUsed="true"  value="${airList.response.body.items.item.economyCharge}"/></td>	
												<td><fmt:formatNumber  groupingUsed="true"  value="${airList.response.body.items.item.prestigeCharge}"/></td>																
												<td>${airList.response.body.items.item.vihicleId}</td>																																												
										</tr>
									</c:when>												
									<c:otherwise>
									<tr align="right">
								<th width="20%">항공사</td>
								<!-- <td width="10%">도착공항</td> -->
								<th width="17%">출발시간</td>	
								<th width="17%">도착시간</td>
								<!-- <td width="10%">출발공항</td> -->
								
								<th width="12.5%">일반석</td>
								<th width="12.5%">비지니스</td>							
								<th width="14%">항공ID</td>																
							</tr>			
									<c:forEach var="airLine" items="${airList.response.body.items.item}"  >
										
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
												<td><fmt:formatNumber  groupingUsed="true"  value="${airLine.economyCharge}"/></td>	
												<td><fmt:formatNumber  groupingUsed="true"  value="${airLine.prestigeCharge}"/></td>																
												<td>${airLine.vihicleId}</td>																										
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
							  <% request.setAttribute("airResult", airResult); %>	
                              <button type="submit" class="button button-contactForm boxed-btn">예약</button>
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