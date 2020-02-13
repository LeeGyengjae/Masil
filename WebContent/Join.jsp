<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
    <%-- JSTL태그 라이브러리 사용을 위한 선언 --%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<html class="no-js" lang="zxx">
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Travelo</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	 <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <!-- <link rel="manifest" href="site.webmanifest"> -->
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gijgo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slicknav.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript">
	$(function() {
		
		idck=false;
		
		$("#id").on("click",function(){
			idck=false;
			$("#checklabel").html("<b>!</b> 6~15자의 영문 소문자 ,숫자만 사용가능");
		});
	});//idck
	
    
    function idcheck() {
		var id = $("#id").val();
		var getId = RegExp(/^[a-z0-9]{6,15}$/);
		
		if(getId.test($("#id").val())==true){
		
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/idcheck',
			data : {id : id},
			success : function(result) {
				if (result == 1) { //결과가 1이면 사용할수 있는 아이디
					$("#checklabel").html("<b class='text-success'>√ 사용할 수 있는 아이디입니다. </b>");
					idck=true;
				} else {//결과가 1이 아니라면 사용할수 없는 아이디
					$("#checklabel").addClass("text-danger");
					$("#checklabel").html("<b>! </b>  사용중인 아이디입니다.");
					$("#id").val("");
					$("#id").focus();
					idck=false;
				}
			}
		});
		
		}else{
			$("#checklabel").html("<b>!</b> 6~15자의 영문 소문자 ,숫자만 사용가능");
			$("#id").val("");
			$("#id").focus();
			idck=false;
		}
	}//idcheck() 끝
    
	function juminchkeck(){
		var num = 0;
		num += $('#jumin1').val().charAt(0) * 2;
		num += $('#jumin1').val().charAt(1) * 3;
		num += $('#jumin1').val().charAt(2) * 4;
		num += $('#jumin1').val().charAt(3) * 5;
		num += $('#jumin1').val().charAt(4) * 6;
		num += $('#jumin1').val().charAt(5) * 7;
		num += $('#jumin2').val().charAt(0) * 8;
		num += $('#jumin2').val().charAt(1) * 9;
		num += $('#jumin2').val().charAt(2) * 2;
		num += $('#jumin2').val().charAt(3) * 3;
		num += $('#jumin2').val().charAt(4) * 4;
		num += $('#jumin2').val().charAt(5) * 5;
		
		if(11-(num%11) == $('#jumin2').val().charAt(6) ){
			alert("인증 성공하셨습니다.");
		}else{
			alert("주민등록번호를 확인해주세요.");
			$('#jumin1').val("");
			$('#jumin2').val("");
			$('#jumin1').focus();
		}
	} //jumincheck() 끝
	
    function notNull(){
 		if(document.fr.id.value == ""){
 			alert("아이디를 입력하세요.");
 			document.fr.id.focus();
 			return;
 		}else if(document.fr.pwd.value == ""){
 			alert("비밀번호를 입력하세요.");
 			document.fr.passwd.focus();
 			return;
 		}else if(document.fr.pwd.value != document.fr.pwd2.value){
 			alert("비밀번호와 비밀번호확인의 값이 다릅니다.");
 			document.fr.passwd.focus();
 			return;
 		}else if(document.fr.name.value == ""){
 			alert("이름을 입력하세요.");
 			document.fr.name.focus();
 			return;
 		}else if(document.fr.pname.value == ""){
 			alert("여권이름을 입력하세요.");
 			document.fr.pname.focus();
 			return;
 		}else if(document.fr.pnum.value == ""){
 			alert("여권번호를 입력하세요.");
 			document.fr.pnum.focus();
 			return;
 		}else if(document.fr.jumin1.value == ""){
 			alert("주민등록번호를 입력하세요.");
 			document.fr.jumin.focus();
 			return;
 		}else if(document.fr.jumin2.value == ""){
 			alert("주민등록번호를 입력하세요.");
 			document.fr.jumin2.focus();
 			return;
 		}else if(idck==false){
 			alert("id중복체크를 실시해주세요.");
 			document.fr.id.focus();
 			return;
 		}else{ 
 			fr.submit(); }
 	}//notNull()
 	
 	
 	$(document).ready(function(){
	  	$('#pwd2').focusout(function(){
		   if($('#pwd').val()!=$('#pwd2').val()){
		    $('#checklabel1').html("<b>! </b>  비밀번호가 일치하지 않습니다");
		    $("#pwd2").val("");
			$("#pwd2").focus();
   		}else{
		    $('#checklabel1').html("<b class='text-success'>√ 비밀번호가 일치합니다");
		   }
  		}); 	
	 });
 	
 	$(document).ready(function(){
 		$('#jumin2').focusout(function(){
 			
 	 		if($('#jumin2').val().charAt(0) == "1"){
 	 			$('#gender').val('남자');
 	 		}else if($('#jumin2').val().charAt(0) == "3"){
 	 			$('#gender').val('남자');
 	 		}else if($('#jumin2').val().charAt(0) == "2"){
 	 			$('#gender').val('여자');
 	 		}else if($('#jumin2').val().charAt(0) == "4"){
 	 			$('#gender').val('여자');
 	 		}else{
 	 			$('#gender').val('주민등록 번호를 제대로 입력해 주세요.');
 	 		}
 		});
 		
 	});
 	
    </script>
</head>

<body>
    <!-- header-start -->
    <header>
    	<jsp:include page="/inc/header.jsp"/>
    </header>
    <!-- header-end -->

<section class="container mt-3" style="max-width: 560px;">
		<form method="post" action="${contextPath}/user/addUser.do" name="fr">
			<div class="form-group">
				<label>아이디</label>
				<table>
					<td width="88%">
						<input type="text" name="id" id="id" class="form-control">
					</td>
					<td>
						<button type="button" class="btn" onclick="idcheck()">중복확인</button>
					</td>
				</table>
				<div>
						<p class=" d-inline-block col-6 ml-4 pt-2 small text-muted" id="checklabel">* 6~15자의 영문 소문자 ,숫자만 사용가능</p>
				</div>
			</div>
			
			<div class="form-group">
				<label>비밀번호</label>
				<input type="password" name="pwd" id="pwd" class="form-control">
			</div>
			<div class="form-group">
				<label>비밀번호 확인</label>
				<input type="password" name="pwd2" id="pwd2" class="form-control">
				<p class=" d-inline-block col-6 ml-4 pt-2 small text-muted"
					id="checklabel1"></p>
			</div>
			<div class="form-group">
				<label>이름</label>
				<input type="text" name="name" class="form-control">
			</div>
			<div class="form-group">
				<label>여권이름</label>
				<input type="text" name="pname" class="form-control">
			</div>
			<div class="form-group">
				<label>여권번호</label>
				<input type="text" name="pnum" class="form-control">
			</div>
			<div class="form-group">
				<label>주민번호</label>
				<table>
					<td width="40%">
						<input type="text" name="jumin1" id="jumin1" class="form-control" maxlength="6">
					</td>
					<td width="8%">
						<input type="text" class="form-control" value="-" readonly="readonly">
					</td>
					<td width="40%">
						<input type="password" name="jumin2" id="jumin2" class="form-control" maxlength="7">
					</td>
					<td>
						<input type="button" class="btn" onclick="juminchkeck()" value="실명인증">
					</td>
				</table>
				
			</div>
			<div class="form-group">
				<label>성별</label>
				<input type="text" id="gender" name="gender" class="form-control" readonly="readonly">
			</div>
			<br>
			<button type="button" class="btn btn-primary"  onclick="notNull()">회원가입</button>
			<button type="reset" class="btn btn-danger">다시작성</button>
		</form>
	</section>
    



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
