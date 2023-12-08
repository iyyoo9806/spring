<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/analyticBootStrap/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/analyticBootStrap/css/main.css">
<title>분석</title>
</head>
<body>

	<div class="contact1">
		<div class="container-contact1">
			<div class="contact1-pic js-tilt" data-tilt>
				<img src="${pageContext.request.contextPath}/resources/analyticBootStrap/images/img-01.png" alt="IMG">
			</div>

<form action="/" id="action" name="action" method="POST" class="contact1-form validate-form">
		<span class="contact1-form-title">
			자동차 분석
		</span>
        
        <div class="form-group">
			<label for="male">Age</label>
            <input type="text" class="form-control" id="age" name="age">
        </div>
        <div class="form-group">
		    <input type="radio" id="male" name="gender" value=0>
		    <label for="male">Male</label>
		    <input type="radio" id="female" name="gender" value=1>
		    <label for="female">Female</label>
        </div>
        <div class="form-group">
            <label for="miles">Miles:</label>
            <input type="text" class="form-control" id="miles" name="miles">
        </div>
        <div class="form-group">
            <label for="debt">Debt:</label>
            <input type="text" class="form-control" id="debt" name="debt">
        </div>
        <div class="form-group">
            <label for="income">Income:</label>
            <input type="text" class="form-control" id="income" name="income">
        </div>
        <div id="result" class="form-group">
		<label for="income">분석결과:</label>	
		</div>
		<button id="submit" type="button" class="btn btn-primary">분석</button>
		<a href="/board" class="btn btn-primary">뒤로가기</a>
</form>

</body>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/analyticBootStrap/vendor/tilt/tilt.jquery.min.js"></script>
<script >
	$('.js-tilt').tilt({
		scale: 1.1
	})
</script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-23581568-13');
</script>
<script src="${pageContext.request.contextPath}/resources/analyticBootStrap/js/main.js"></script>
<script>
$("#submit").click(function(){
	var formData = $("#action").serialize();
	console.log(formData);
	let data = {
		age: $("#age").val(),
		gender: $("input[name='gender']:checked").val(),
		miles: $("#miles").val(),
		debt: $("#debt").val(),
		income: $("#income").val(),
		
	};
	console.log(data);
	$.ajax({
		headers:{
			"Content-Type": "application/json"
		},
		type:"POST",
		url:"http://localhost:9092/analyze",
		data:JSON.stringify(data),
		success:function(data){
			console.log(data.analytic)
			result = Math.round(Number(data.analytic))
			console.log(result)
			alert("분석완료");
			$("#result").text("분석 결과 : " + result);
		}
	})
})

</script>
</html>