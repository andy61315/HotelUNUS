<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS -->
	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/front-end/meal/css/bootstrap.min.css" />
 
	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/front-end/meal/css/slick.css" />
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/front-end/meal/css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/front-end/meal/css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/meal/css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/front-end/meal/css/style.css" />
<!-- CSS -->
</head>
<body>
	<%@ include file="/front-end/meal/header.file" %>
	<%@ include file="/front-end/meal/navigation.file" %>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div class="card">
						<div class="card-body">
							<img src="<%= request.getContextPath() %>/front-end/order/img/ordergood.png" style="width: 100%">
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<h2>訂單成立</h2>
					<hr>
					<h4>謝謝你的購買</h4>
					<div class="card">
						<div class="card-body">
							<button type="button" class="btn btn-secondary back-to-index-btn">
								<span>回到首頁</span>
							</button>
							<button type="button" class="btn btn-primary still-buy-btn">
								<span>繼續購買</span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- JS plugin -->
	<script src="<%= request.getContextPath() %>/front-end/meal/js/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/bootstrap.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/slick.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/nouislider.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/jquery.zoom.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/main.js"></script>
<!-- JS plugin -->

	<script type="text/javascript">
		$('.still-buy-btn').on('click', function() {
			window.location.href = "<%= request.getContextPath() %>/front-end/meal/listAllMeal.jsp";
		});
		
		$('.back-to-index-btn').on('click', function() {
			window.location.href = "<%= request.getContextPath() %>/front-end/index.jsp";
		});
	</script>
</body>
</html>