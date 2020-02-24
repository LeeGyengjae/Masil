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

	//이미지 업로드 태그 추가
	function addImgfile(addImgCnt, next) {
// 		console.log( "addImgCnt : "+addImgCnt );
// 		console.log( "addImgHere1 : "+addImgHere1.getAttribute('class') );
// 		console.log( "next : "+ next );
		
		var period = $("#period").val();
		var filecnt = new Array();
		var div = new Array();
		var msg = "";
		var day = next.attr('class');
		var dayStr = Number(day.substr(10,1));
		
		for (i = 0; i < period; i++) {
			filecnt[i] = $('.addImgCnt'+i).val();	
			div[i] = $('.addImgHere'+i);
		}
		
		for (i = 0; i < addImgCnt; i++) {
			var file = filecnt[i];
			msg += "<input type='file' name='"+(dayStr)+"_image_"+i+"'/><br>";
			//name : 1image_1 과 같은 형식으로 들어감. 앞쪽 day 뒤쪽 이미지number
		}//for
		
		//이미지 업로드 10장 제한
		if(addImgCnt<=10){
			next.html(msg);
		}else {
			alert('이미지는 10장까지만 추가 가능');
		}
		
	}//addInput()
	
	//기간 입력시 동적으로 일정 입력 태그 추가
	function periodSet() {
	
		var period = $("#period").val();
		var div = $("#periodAdd");
		
		var msg = "";
		
		for (i = 1; i < (Number(period)+1); i++) {
			
			msg += "<h4>"+i+" 일자</h4>"
			+ "<input type='hidden' name='day' value='"+i+"day' >"
			+ "<div class='form-group'>"
			+ "<span>일정제목</span>" 
			+ "<input class='form-control' name='dayTitle' id='"+i+"dayTitle' type='text' placeholder='일정제목'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>"+i+" 일자 코스</span>" 
			+ "<input class='form-control' name='dayCourse' id='"+i+"dayCourse' type='text' placeholder='일정별 코스'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>숙박</span>"
			+ "<input class='form-control valid' name='stay' id='"+i+"stay' type='text' placeholder='숙박'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>식사</span>"
			+ "<input class='form-control valid' name='meal' id='"+i+"meal' type='text' placeholder='식사'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>일정내용</span>"
			+ "<textarea class='form-control w-100' name='dayContent' id='"+i+"dayContent' cols='30' rows='9' placeholder='일정내용'></textarea>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>이미지 설명</span>"
			+ "<textarea class='form-control w-100' name='imgContent' id='"+i+"imgContent' cols='30' rows='9' placeholder='이미지 설명'></textarea>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>이미지</span>"
			+ "<input type='text' value='' id='addImgCnt"+i+"' class='addImgCnt"+i+"'>"
			+ "<input type='button' value='추가' id='addBtn' onclick='addImgfile(addImgCnt"+i+".value, $(this).next())' class='genric-btn primary-border small'>"
			+ "<div id='addImgHere"+i+"' class='addImgHere"+i+"'>"
			+ "</div>"
			+ "</div>";
		
		}
		div.html(msg);
	}//periodSet()
	
	//업로드 파일 검사 - 수정 필요
// 	function fileCheck(){
// 		var imgID = $("[id^='image']").val();
// 		var fileExt = imgID.substring(file.lastIndexOf('.')+1); 
// 		if(!imgID){
// 			writeForm.submit();
// 		}else if(!(fileExt.toUpperCase()=="TXT"||fileExt.toUpperCase()=="PNG"||fileExt.toUpperCase()=="JPG" ) ){
// 			alert("TXT, JPG, PNG 파일만 업로드 가능");
// 	        return false;     
// 		}
// 		 frm.submit();
// 	}//업로드 파일 검사
	

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
                    
                    <!-- form div -->
                    <div class="col-lg-8">
                        <form class="form-contact contact_form" action="${contextPath}/masil/product1/addProduct.do" 
                        method="post" id="contactForm" enctype="multipart/form-data" name="writeForm" > 
                        	  
                            <div class="row">
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>메인코드</span>
                                        <input class="form-control" name="code" id="code" type="text" 
                                        placeholder="메인코드">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>세부코드</span>
                                        <input class="form-control" name="sub_code" id="sub_code" type="text" 
                                        placeholder="세부코드">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>제목</span>
                                        <input class="form-control" name="title" id="title" type="text" 
                                        placeholder="제목">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                   	 	<span>대륙</span>
                                        <input class="form-control" name="continent" id="continent" type="text" 
                                        placeholder="대륙">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                    <%--전체 코스 : 아래쪽에서 일자별 코스 입력시 자동 입력되도록 function 만들어야됨 --%>
                                  	  <span>전체 코스</span>
                                        <input class="form-control" name="course" id="course" type="text" 
                                        placeholder="코스">
                                    </div>
                                </div>
                                 <div class="col-12">
                                    <div class="form-group">
                                  	  <span>상품 한줄 설명</span>
                                        <input class="form-control" name="comment" id="comment" type="text" 
                                        placeholder="상품 한줄 설명">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                    <%--기간 입력 후 기간 <= 도착 일자 제한하도록 function설정 해야됨 --%>
                                    	<span>기간</span>
										<input class="form-control" name="period" id="period" type="text" onblur="periodSet()"
											placeholder="기간">
										<input type="button" value="일정 입력칸 추가" id="addBtn" onclick="periodSet()"
											class="genric-btn primary-border small">
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-group">
                                   		<span>최대참여인원</span>
                                        <input class="form-control" name="max_num" id="max_num" type="text" 
                                        placeholder="최대참여인원">
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-group">
                                   		<span>가격</span>
                                        <input class="form-control" name="price" id="price" type="text" 
                                        placeholder="가격">
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>출발일자</span>
                                        <input class="form-control" name="start_date" id="start_date" type="date" 
                                        placeholder="출발일자"> 
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<%--도착일자 : 기간과 맞춰야됨. --%>
                                    	<span>도착일자</span>
                                        <input class="form-control" name="end_date" id="subject" type="date" 
                                        placeholder="도착일자">
                                    </div>
                                </div>
                                
                                
                                
                                <%--기간 입력하면 동적으로 태그 추가됨. --%>
                                <h3>일정</h3>
                                <div class="col-12" id="periodAdd"></div> 
								<div id="addPeriodHere"></div>
	                                  
                            </div>
                            
                            <div class="form-group mt-3">
                                <!-- <input type="submit" class="button button-contactForm boxed-btn" onclick="fileCheck(contactForm.writeForm)" value="상품등록"> -->
                                <input type="submit" class="button button-contactForm boxed-btn" value="상품등록">
                                <input type="reset" value="다시쓰기" class="button button-contactForm boxed-btn">
                                <input type="button" value="상품목록" class="button button-contactForm boxed-btn" onclick="location.href='${contextPath}/product1/product.do'">
                            </div>
                        </form>
					</div>
					<!-- form div -->
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