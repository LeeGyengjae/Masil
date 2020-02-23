<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
	//이미지 태그 추가
	function addImgfile(addImgCnt, next) {
// 		console.log( "addImgCnt : "+addImgCnt );
// 		console.log( "addImgHere0 : "+addImgHere0.getAttribute('class') );
		
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
			//name : 1_image_0 과 같은 형식으로 들어감. 앞쪽 day 뒤쪽 이미지number
		}//for
		
		//이미지 업로드 10장 제한
		if(addImgCnt<=10){
			next.html(msg);
		}else {
			alert('이미지는 10장까지만 추가 가능');
		}
		
	}//addInput()
	
	//기간 입력시 동적으로 태그 추가
	function periodSet() {

		var period = $("#period").val();
		var div = $("#periodAdd");
		
		var msg = "";
		
		for (i = 1; i < (Number(period)+1); i++) {
			
			msg += "<h4>"+i+" 일자</h4>"
			+ "<input type='hidden' name='"+i+"_day' value='"+i+"day' >"
			+ "<div class='form-group'>"
			+ "<span>일정제목</span>" 
			+ "<input class='form-control' name='"+i+"_dayTitle' id='"+i+"dayTitle' type='text' placeholder='일정제목'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>"+i+" 일자 코스</span>" 
			+ "<input class='form-control' name='"+i+"_dayCourse' id='"+i+"dayCourse' type='text' placeholder='일정별 코스'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>숙박</span>"
			+ "<input class='form-control valid' name='"+i+"_stay' id='"+i+"stay' type='text' placeholder='숙박'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>식사</span>"
			+ "<input class='form-control valid' name='"+i+"_meal' id='"+i+"meal' type='text' placeholder='식사'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>일정내용</span>"
			+ "<textarea class='form-control w-100' name='"+i+"_dayContent' id='"+i+"dayContent' cols='30' rows='9' placeholder='일정내용'></textarea>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>이미지 설명</span>"
			+ "<textarea class='form-control w-100' name='"+i+"_imgContent' id='"+i+"imgContent' cols='30' rows='9' placeholder='이미지 설명'></textarea>"
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
	}
	
	
</script>

</head>
<body>
	
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
                                        <input class="form-control" name="subCode" id="subCode" type="text" 
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
                                <div class="col-12">
                                    <div class="form-group">
                                   		<span>최대참여인원</span>
                                        <input class="form-control" name="maxNum" id="maxNum" type="text" 
                                        placeholder="최대참여인원">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                   		<span>가격</span>
                                        <input class="form-control" name="price" id="price" type="text" 
                                        placeholder="가격">
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<span>출발일자</span>
                                        <input class="form-control" name="startDate" id="startDate" type="date" 
                                        placeholder="출발일자"> 
                                    </div>
                                </div>
                            	<div class="col-sm-6">
                                    <div class="form-group">
                                    	<%--도착일자 : 기간과 맞춰야됨. --%>
                                    	<span>도착일자</span>
                                        <input class="form-control" name="endDate" id="endDate" type="date" 
                                        placeholder="도착일자">
                                    </div>
                                </div>
                                
                                
                        
	<!-- <span>기간</span>
	<input class="form-control" name="period" id="period" type="text" onblur="periodSet()"
		placeholder="기간">
	<input type="button" value="추가" id="addBtn" onclick="periodSet()"
		class="genric-btn primary-border small"> -->

	<div class="col-12" id="periodAdd"></div> 
	
	<div id="addPeriodHere"></div>

<input type="submit" class="button button-contactForm boxed-btn" value="상품등록">


</body>
</html>