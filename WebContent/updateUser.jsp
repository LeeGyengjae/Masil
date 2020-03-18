<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>

    
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Travelo</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="manifest" href="site.webmanifest"> -->
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
    <link rel="stylesheet" href="../css/gijgo.css">
    <link rel="stylesheet" href="../css/animate.css">
    <link rel="stylesheet" href="../css/slick.css">
    <link rel="stylesheet" href="../css/slicknav.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="../css/style.css">
    
    <script type="text/javascript">
    function deleteuser(){
    	fr.submit();
    }
    
    </script>
   </head>

<body>
    <!-- header-start -->
    <header>
    	<jsp:include page="/inc/header2.jsp"/>
    </header>
    <!-- header-end -->

    <!-- myPage start  -->
    <section class="container mt-3" style="max-width: 560px;">
		<form method="post" action="${contextPath}/masil/user/updateUser.do">
			<div class="form-group">
				<label>아이디</label>
				<input type="text" name="id" class="form-control" value="${userVO.id }" readonly="readonly">
			</div>
			<div class="form-group">
				<label>비밀번호</label>
				<input type="password" name="pwd" class="form-control">
			</div>
			<div class="form-group">
				<label>이름</label>
				<input type="text" name="name" class="form-control" value=${userVO.name }>
			</div>
			<div class="form-group">
				<label>여권이름</label>
				<input type="text" name="pname" class="form-control" value=${userVO.pname }>
			</div>
			<div class="form-group">
				<label>여권번호</label>
				<input type="text" name="pnum" class="form-control" value=${userVO.pnum }>
			</div>
			<div class="form-group">
				<label>주민번호</label>
				<table>
					<td width="46%">
						<input type="text" name="jumin1" class="form-control" maxlength="6" value=${userVO.jumin1 }>
					</td>
					<td width="8%">
						<input type="text" class="form-control" value="-" readonly="readonly">
					</td>
					<td width="46%">
						<input type="password" name="jumin2" class="form-control" maxlength="7" value=${userVO.jumin2 }>
					</td>
				</table>
			</div>
			<div class="form-group">
				<label>성별</label>
				<br>
				<select name="gender" class="form-control">
                    <option name="남성" selected>남성</option>
                    <option name="여성">여성</option>
                </select>
                <br>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">정보수정</button>
			<input type="button" class="btn btn-danger" onclick="deleteuser()" value="회원탈퇴">
		
		</form>
		<form method="post" action="${pageContext.request.contextPath}/user/deleteUser.do" name="fr">
			<p class="form-group">
				<input type="hidden" name="id" class="form-control" value="${userVO.id }" readonly="readonly">
			</p>
		</form>
	
	</section>
    <!--/myPage end  -->

    



    <footer class="footer">
        <jsp:include page="/inc/footer2.jsp"/>
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