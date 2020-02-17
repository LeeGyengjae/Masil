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
		console.log( "addImgCnt : "+addImgCnt );
		console.log( "addImgHere1 : "+addImgHere1.getAttribute('class') );
		
		console.log( "next : "+ next );
		
		var period = $("#period").val();
		var filecnt = new Array();
		var div = new Array();
		var msg = "";
		
		for (i = 0; i < period; i++) {
			filecnt[i] = $('.addImgCnt'+i).val();	
			div[i] = $('.addImgHere'+i);
		}
		
		for (i = 0; i < addImgCnt; i++) {
			var file = filecnt[i];
			msg += "<input type='file' name='upfile"+i+"'/><br>";
			
			if(filecnt[i]==div[i]){
			}
			
		}//for
		
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
		
		for (i = 0; i < period; i++) {
			
			msg += "<h4>"+(i+1)+"일자</h4>"
			+ "<div class='form-group'>"
			+ "<span>소제목</span>" 
			+ "<input class='form-control' name='day_title"+i+"' id='day_title"+i+"' type='text' placeholder='소주제'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>숙박</span>"
			+ "<input class='form-control valid' name='stay"+i+"' id='stay"+i+"' type='text' placeholder='숙박'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>식사</span>"
			+ "<input class='form-control valid' name='meal"+i+"' id='meal"+i+"' type='text' placeholder='식사'>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>일정내용</span>"
			+ "<textarea class='form-control w-100' name='day_content"+i+"' id='day_content"+i+"' cols='30' rows='9' placeholder='일정내용'></textarea>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>이미지 설명</span>"
			+ "<textarea class='form-control w-100' name='img_content"+i+"' id='img_content"+i+"' cols='30' rows='9' placeholder='이미지 설명'></textarea>"
			+ "</div>"
			+ "<div class='form-group'>"
			+ "<span>대표이미지</span>"
			+ "<input class='form-control valid' name='image"+i+"' id='img_boss"+i+"' type='file'>"
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

	<span>기간</span>
	<input class="form-control" name="period" id="period" type="text" onblur="periodSet()"
		placeholder="기간">
	<input type="button" value="추가" id="addBtn" onclick="periodSet()"
		class="genric-btn primary-border small">

	<div class="col-12" id="periodAdd"></div> 
	
	<div id="addPeriodHere"></div>




</body>
</html>