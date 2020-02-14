<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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

<script type="text/javascript">

	//기간 입력시 일정을 입력하는 태그들을 기간수만큼 동적으로 생성하기
	function periodSet(){
		
	}
	
	var cnt=0;
	//이미지 추가 버튼 클릭시 동적으로 이미지 태그 추가(최대 10장)
	function imgAdd() {
	  	cnt++;
		var pimg = document.getElementById("pimg");
		pimg.innerHTML += "<input type=file name='image' class='form-control valid'>";
		
		var addbtn = document.getElementById("addbtn");
	  	if(cnt>=10){
	  		alert("최대 10장까지만 추가할 수 있습니다.");
	  		addbtn.disabled=true;
	  		addbtn.hidden=true;
	  	}
	}


</script>


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
					<div class="col-12">
                        <h2 class="contact-title">새상품 업로드</h2>
                    </div>
                    <div class="col-lg-8">
                        <form class="form-contact contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
                            <div class="row">
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>메인코드</span>
                                        <input class="form-control" name="code" id="code" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="메인코드">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>세부코드</span>
                                        <input class="form-control" name="sub_code" id="sub_code" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="세부코드">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                   	 	<span>대륙</span>
                                        <input class="form-control" name="continent" id="continent" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="대륙">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                    <%--전체 코스 : 아래쪽에서 일자별 코스 입력시 자동 입력되도록 function 만들어야됨 --%>
                                  	  <span>전체 코스</span>
                                        <input class="form-control" name="course" id="course" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="코스" disabled="disabled">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                    <%--기간 입력 후 기간 <= 도착 일자 제한하도록 function설정 해야됨
                                    	기간 입력시 아래쪽에 일정 입력 칸 동적으로 기간 만큼 생성되도록 설정해야됨
                                     --%>
                                    	<span>기간</span>
                                        <input class="form-control" name="period" id="period" type="text" 
                                        onfocus="this.placeholder = ''" onblur="periodSet(this)"
                                        placeholder="기간" >
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>출발일자</span>
                                        <input class="form-control" name="start_date" id="start_date" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="출발일자"> 
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>도착일자</span>
                                        <input class="form-control" name="end_date" id="subject" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="도착일자">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                   		<span>최대참여인원</span>
                                        <input class="form-control" name="max_num" id="max_num" type="text" 
                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
                                        placeholder="최대참여인원">
                                    </div>
                                </div>
                                <h3>일정</h3>
                                <%-- <c:forEach var="product" items="${productDetail}" varStatus="productNum"> --%>
                                <%-- <c:forEach var="i" begin="0" end="${period-1}" step="1"> --%>
                               		<div class="col-12">
                               		<h4>${i+1}일자 </h4>
	                                    <div class="form-group">
	                                    	<span>소제목</span>
	                                    	<input class="form-control" name="day_title" id="day_title" type="text" 
		                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" 
		                                        placeholder="소주제">
	                                    </div>
                               		</div>
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                    	<span>숙박</span>
	                                        <input class="form-control valid" name="stay" id="stay" type="text" 
	                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'"
	                                        placeholder="숙박">
	                                    </div>
	                                </div>
                                    <div class="col-sm-6">
	                                    <div class="form-group">
	                                    	<span>식사</span>
	                                        <input class="form-control valid" name="meal" id="meal" type="text" 
	                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'"
	                                        placeholder="식사">
	                                    </div>
	                                 </div>
	                                <div class="col-12">
	                                    <div class="form-group">
	                                    	<span>일정내용</span>
	                                        <textarea class="form-control w-100" name="day_content" id="day_content" cols="30" rows="9" 
	                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Message'" 
	                                        placeholder="일정내용"></textarea>
	                                    </div>
	                                </div>
	                                <div class="col-12">
	                                    <div class="form-group">
	                                    	<span>이미지 설명</span>
	                                        <textarea class="form-control w-100" name="img_content" id="img_content" cols="30" rows="9" 
	                                        onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Message'" 
	                                        placeholder="이미지 설명"></textarea>
	                                    </div>
	                                </div>
	                                <div class="col-12">
	                                    <div class="form-group">
	                                    	<span>대표이미지</span>
	                                        <input class="form-control valid" name="image" id="img_boss" type="file" 
	                                        onfocus="this.placeholder = ''" 
	                                        onblur="this.placeholder = 'Enter email address'" >
	                                    </div>
	                                 </div>
	                                 <div class="col-12">
	                                    <div class="form-group" id="pimg">
	                                    	<span>이미지</span>
	                                        <input type="button" id="addbtn" onclick="imgAdd()" class="genric-btn primary-border small" value="추가">
	                                        <input class="form-control valid" name="image" id="image" type="file" 
	                                        onblur="this.placeholder = 'Enter email address'" >
	                                    </div>
	                                 </div>
	                                  
                                <%-- </c:forEach> --%>
                                </div>
                            
                            <div class="form-group mt-3">
                                <button type="submit" class="button button-contactForm boxed-btn">상품등록</button>
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