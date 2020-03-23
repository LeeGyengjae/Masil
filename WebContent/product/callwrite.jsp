<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	request.setCharacterEncoding("UTF-8");
	pageContext.setAttribute("linechange", "\r\n");
%>

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

<script type="text/javascript">

	//이미지 업로드 태그 추가
	function addImgfile(pnum) {
		var pnum2 = pnum;
		var filecnt = $('.addImgCnt'+pnum2).val();
		var div = $('.addImgHere'+pnum2);
		var msg = "";
		var day = $('#addImgHere'+pnum2).attr('class');
		
		for (i = 0; i < filecnt; i++) {
			msg += "<input type='file' name='"+(pnum2)+"_image_"+i+"'/><br>";
			//name : 1_image_1 과 같은 형식으로 들어감. 앞쪽 day 뒤쪽 이미지number
		}//for
		
		//이미지 업로드 10장 제한
		if(Number(filecnt)<=10){
			div.html(msg);
		}else {
			alert('이미지는 10장까지만 추가 가능  - '+filecnt);
		}
		
	}//addImgfile()
	
	function submit(){
	   writeForm.submit(); 
	}//notNull()

</script>

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
					<div class="col-12">
                        <h2 class="contact-title">상품 업로드</h2>
                    </div>
                    <div class="col-lg-8">
                        <form class="form-contact contact_form" action="${contextPath}/product1/addProduct2.do" 
                        method="post" id="contactForm" enctype="multipart/form-data" name="writeForm" > 
                        
                            <div class="row">
                            <c:forEach var="product" items="${productDetail}" begin="0" end="0" step="1">
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>메인코드</span>
                                        <input class="form-control" name="code" id="code" type="text" 
                                        onfocus="this.placeholder = ''"  
                                        placeholder="${product.code}" disabled="disabled">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>세부코드</span>
                                        <input class="form-control" name="subCode" id="sub_code" type="text">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                   	 	<span>대륙</span>
                                        <input class="form-control" name="continent" id="continent" type="text" 
                                        onfocus="this.placeholder = ''" placeholder="${product.continent}" disabled="disabled">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                  	  <span>코스</span>
                                        <input class="form-control" name="course" id="course" type="text" 
                                        onfocus="this.placeholder = ''" placeholder="${product.course}" disabled="disabled">
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-group">
                                    	<span>기간</span>
                                        <input class="form-control" name="period" id="period" type="text" 
                                        onfocus="this.placeholder = ''" placeholder="${product.period}" disabled="disabled">
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-group">
                                   		<span>가격</span>
                                        <input class="form-control" name="price" id="price" type="text" value="${product.price}" >
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>출발일자</span>
                                        <input class="form-control" name="startDate" id="start_date" type="date" > 
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>도착일자</span>
                                        <input class="form-control" name="endDate" id="subject" type="date" >
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                   		<span>최대참여인원</span>
                                        <input class="form-control" name="maxNum" id="max_num" type="text">
                                    </div>
                                </div>
                                <fmt:parseNumber value="${product.period}" type="number" var="period" />
                                </c:forEach>
                                <h3>일정</h3>
                                <%-- <c:forEach var="product" items="${productDetail}" varStatus="productNum"> --%>
                                <c:forEach var="detail" items="${productDetail}" begin="0" end="${period-1}" step="1">
                               		<div class="col-12">
                               		<h4>${detail.day}일자 </h4>
	                                    <div class="form-group">
	                                    	<span>일정 제목</span>
	                                    	<input class="form-control" name="dayTitle" id='day_title${i+1}' type="text" 
		                                        value="${detail.day_title}">
	                                    </div>
                               		</div>
                               		 <div class="col-sm-12">
	                               		<div class='form-group'>
											<span>${i+1} 일자 코스</span>
											<input class='form-control' name='dayCourse' id='${i+1}dayCourse' type='text' 
											value='${detail.day_course}'>
										</div>
									</div>
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                    	<span>숙박</span>
	                                        <input class="form-control valid" name="stay" id='stay${i+1}' type="text" 
	                                        value="${detail.stay}">
	                                    </div>
	                                </div>
                                    <div class="col-sm-6">
	                                    <div class="form-group">
	                                    	<span>식사</span>
	                                        <input class="form-control valid" name="meal" id='meal${i+1}' type="text" 
	                                        value="${detail.meal}">
	                                    </div>
	                                 </div>
	                                <div class="col-12">
	                                    <div class="form-group">
	                                    	<span>일정내용</span>
	                                        <textarea class="form-control w-100" name="dayContent" id='day_content${i+1}' cols="30" rows="9">
	                                        	${fn:replace(detail.day_content, linechange, '<br/>')}
	                                        </textarea>
	                                    </div>
	                                </div>
                                  	<div class="col-12">
	                                    <div class="form-group">
	                                    	<span>이미지 설명</span>
	                                        <textarea class="form-control w-100" name="imgContent" id='img_content${i+1}' cols="30" rows="9">
	                                        	<c:out value="${fn:replace(detail.img_content, linechange, '<br/>')}"/>
	                                        </textarea>
	                                    </div>
	                                </div>
	                                <div class="col-12">
		                                <div class='form-group'>
											<span>첨부된 이미지</span>
											<div class="thumb">
												<c:forTokens items="${detail.image}" delims="," var="images">
													<img alt="${images}" src="${contextPath}/product/upload/${images}" width="50px" height="50px">
													<input type="hidden" name="old_image" value="${i+1}_old${images}"> 
												</c:forTokens>
											</div>
										</div>
									</div>
									<div class="col-12">
										<div class='form-group'>
											<span>이미지 새로 등록하기</span>
											<input type='text' value='' id='addImgCnt${i+1}' class='addImgCnt${i+1} form-control'
											onfocus="this.placeholder = ''" onblur="this.placeholder = '등록할 이미지 파일 개수 입력'" 
											placeholder="등록할 이미지 파일 개수 입력" >
											<input type='button' value='추가' id='addBtn' onclick='addImgfile(${i+1})' class='genric-btn primary-border small'>									
											<div id='addImgHere${i+1}' class='addImgHere${i+1}'></div>
										</div>
									</div>
                                </c:forEach>
                            
	                           <div class="form-group mt-3">
	                                <button type="submit" class="button button-contactForm boxed-btn" onclick='submit()'>상품등록</button>
	                                <input type="reset" value="다시쓰기" class="button button-contactForm boxed-btn">
	                                <button type="button" class="button button-contactForm boxed-btn" onclick="location.href='${contextPath}/product1/product.do'">상품목록</button>
	                            </div>
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
		<jsp:include page="../inc/footer3.jsp" />
	</footer>
	<!-- footer end  -->

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