<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function add_div() {
			var div = document.createElement('div');
			var aaa = document.getElementById('abc').value;
			var cnt=0;
			while(true){
				cnt++;
				div.innerHTML = document.getElementById('room_type').innerHTML;
				document.getElementById('field').appendChild(div);
				
				if(cnt==aaa){
					break;
				}
			}
		}

	</script>

	<input type="text" value="" id='abc' onblur="add_div(this)">
	
	<div id="room_type">
		<div class="form-group">
			<label for="image">제목</label> 
			<input type="text" id="title" name="title" class="form-control"></input>
		</div>
	</div>
	<div id="field"></div>



</body>
</html>