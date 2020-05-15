<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.restaurant.model.*" %>

<%
	MealVO mealVO = (MealVO) request.getAttribute("MealVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>E-SHOP HTML Template</title>

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

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<jsp:include page="navigation.jsp"></jsp:include>

	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="<%= request.getContextPath() %>/front-end/meal/index.jsp">Home</a></li>
				<li><a href="<%= request.getContextPath() %>/front-end/meal/listAllMeal.jsp">Products</a></li>
				<li class="active">${mealVO.meal_name}</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!--  Product Details -->
				<div class="product product-details clearfix">
<!-- 					圖片相關 -->
					<div class="col-md-6">
						<div id="product-main-view">
							<div class="product-view">
								<img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=${mealVO.meal_no}" alt="">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="product-body">
							<jsp:useBean id="mealTypeSvc" scope="page" class="com.mealtype.model.MealTypeService" />
							<div class="product-label">
								<span>${mealTypeSvc.findByPK(mealVO.meal_type_no).type_name}</span>
							</div>
							<h2 class="product-name">${mealVO.meal_name}</h2>
							<h3 class="product-price">${mealVO.price} $</h3>
							<p><strong>Availability:</strong> In Stock</p>
							<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />
							<p><strong>餐廳:</strong> ${restaurantSvc.getByPrimaryKey(mealVO.res_no).resName}</p>
<!-- 							介紹 -->
							<p>${mealVO.meal_introduction}</p>
							<div class="product-options">
								
							</div>
							<div class="product-btns">
								<input class="price" type="hidden" value="${mealVO.price}">
								<input class="meal_name" type="hidden" value="${mealVO.meal_name}">
								<input class="meal_no" type="hidden" value="${mealVO.meal_no}">
								<button class="primary-btn add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
								<div class="pull-right">
									<input class="meal_no" type="hidden" value="${mealVO.meal_no}">
									<button class="main-btn icon-btn add-to-favorite-btn"><i class="fa fa-heart"></i></button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Details -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- section title -->
<!-- 				<div class="col-md-12"> -->
<!-- 					<div class="section-title"> -->
<!-- 						<h2 class="title">Picked For You</h2> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<!-- section title -->

				<!-- Product Single -->
<!-- 				<div class="col-md-3 col-sm-6 col-xs-6"> -->
<!-- 					<div class="product product-single"> -->
<!-- 						<div class="product-thumb"> -->
<!-- 							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button> -->
<%-- 							<img src="<%= request.getContextPath() %>/front-end/e-shop/img/product04.jpg" alt=""> --%>
<!-- 						</div> -->
<!-- 						<div class="product-body"> -->
<!-- 							<h3 class="product-price">$32.50</h3> -->
<!-- 							<div class="product-rating"> -->
<!-- 								<i class="fa fa-star"></i> -->
<!-- 								<i class="fa fa-star"></i> -->
<!-- 								<i class="fa fa-star"></i> -->
<!-- 								<i class="fa fa-star"></i> -->
<!-- 								<i class="fa fa-star-o empty"></i> -->
<!-- 							</div> -->
<!-- 							<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2> -->
<!-- 							<div class="product-btns"> -->
<!-- 								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button> -->
<!-- 								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button> -->
<!-- 								<button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<!-- /Product Single -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- jQuery Plugins -->
	<script src="<%= request.getContextPath() %>/front-end/meal/js/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/bootstrap.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/slick.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/nouislider.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/jquery.zoom.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/meal/js/main.js"></script>
	<script type="text/javascript">
	// 加入購物車
	$('.add-to-cart-btn').on('click', function () {
		var data = {};
		data.action = 'addToCart';
		data.meal_no = $(this).prevAll(".meal_no").val();
		data.meal_name = $(this).prevAll(".meal_name").val();
		data.price = $(this).prevAll(".price").val();
		
		console.log("data.action : "+data.action);
		console.log("data.meal_no : "+data.meal_no);
		console.log("data.meal_name : "+data.meal_name);
		console.log("data.price : "+data.price);
		
		$.ajax({
			 type: "POST",
			 url: "../meal/mealJson.do",
			 data: data,
			 dataType: "json",
			 success: function (result) {
				$('#itemNumberInCart').html(result.itemNumberInCart);
				$('.total').html(result.total+"$");
			},
             error: function(){alert("AJAX 發生錯誤囉!")}
         });
		
	});
	
		// 加入收藏, cus_no 是寫死的, 後面要改過來
	$('.add-to-favorite-btn').on('click', function () {
		var data = {};
		data.action = 'addToFavorite';
		data.meal_no = $(this).prevAll(".meal_no").val();
		
		console.log("data.action : "+data.action);
		console.log("data.meal_no : "+data.meal_no);
		
		$.ajax({
			 type: "POST",
			 url: "../meal/mealJson.do",
			 data: data,
			 dataType: "json",
			 success: function(data) {
					alert(data.sysMessage);
				},
             error: function(){alert("AJAX 發生錯誤囉!")}
         });
		
	});
		
	// 點擊 Header 購物車的重導向
	$('.header-cart').on('click', function() {
		window.location.href = "../front-end/meal/checkout.jsp";
	});
	</script>


</body>

</html>