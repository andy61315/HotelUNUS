<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>

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
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
 
	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

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
<!-- 	<div id="breadcrumb"> -->
<!-- 		<div class="container"> -->
<!-- 			<ul class="breadcrumb"> -->
<!-- 				<li><a href="index.jsp">Home</a></li> -->
<!-- 				<li class="active">Products</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<!-- /BREADCRUMB -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- ASIDE -->
				<div id="aside" class="col-md-3">
					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">根據餐點類別分類</h3>
						<jsp:useBean id="mealTypeSvc" scope="page" class="com.mealtype.model.MealTypeService" />
						<select class="form-control filter-select" id="meal-select">
								<option class="meal_type_no" value="-1"></option>
							<c:forEach var="mealType" items="${mealTypeSvc.all}">
								<option class="meal_type_no" value="${mealType.meal_type_no}">${mealType.type_name}</option>
							</c:forEach>
						</select>
					</div>
					<!-- /aside widget -->

					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">根據餐廳分類</h3>
						<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />
						<select class="form-control filter-select" id="res-select">
							<option class="resName" value="-1"></option>
							<c:forEach var="restaurant" items="${restaurantSvc.all}">
								<option class="resName" value="${restaurant.resNo}">${restaurant.resName}</option>
							</c:forEach>
						</select>
					</div>
					<!-- /aside widget -->
				</div>
				<!-- /ASIDE -->

				<!-- MAIN -->
				<div id="main" class="col-md-9">
					<!-- store top filter -->
					<div class="store-filter clearfix">
						<div class="pull-left">
<!-- 							<div class="row-filter"> -->
<!-- 								<a href="#"><i class="fa fa-th-large"></i></a> -->
<!-- 								<a href="#" class="active"><i class="fa fa-bars"></i></a> -->
<!-- 							</div> -->
<!-- 							<div class="sort-filter"> -->
<!-- 								<span class="text-uppercase">Sort By:</span> -->
<!-- 								<select class="input"> -->
<!-- 										<option value="0">Position</option> -->
<!-- 										<option value="0">Price</option> -->
<!-- 										<option value="0">Rating</option> -->
<!-- 									</select> -->
<!-- 								<a href="#" class="main-btn icon-btn"><i class="fa fa-arrow-down"></i></a> -->
<!-- 							</div> -->
						</div>
						<div class="pull-right">
							<div class="page-filter">
								<span class="text-uppercase">Show:</span>
								<select class="input" id="meal-page-size-sel">
										<option value="4">4</option>
<!-- 										<option value="5">5</option> -->
<!-- 										<option value="10">10</option> -->
									</select>
							</div>
							<ul class="store-pages">
								<li><span class="text-uppercase">Page:</span></li>
								<li class="active">1</li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href=""><i class="fa fa-caret-right"></i></a></li>
							</ul>
						</div>
					</div>
					<!-- /store top filter -->

					<!-- STORE -->
					<div id="store">
						<!-- row -->
						<div class="row product-zone">
							<!-- Product Single -->
							
							<!-- /Product Single -->
						</div>
						<!-- /row -->
					</div>
					<!-- /STORE -->

<!-- 					store bottom filter -->
<!-- 					<div class="store-filter clearfix"> -->
<!-- 						<div class="pull-left"> -->
<!-- 							<div class="row-filter"> -->
<!-- 								<a href="#"><i class="fa fa-th-large"></i></a> -->
<!-- 								<a href="#" class="active"><i class="fa fa-bars"></i></a> -->
<!-- 							</div> -->
<!-- 							<div class="sort-filter"> -->
<!-- 								<span class="text-uppercase">Sort By:</span> -->
<!-- 								<select class="input"> -->
<!-- 										<option value="0">Position</option> -->
<!-- 										<option value="0">Price</option> -->
<!-- 										<option value="0">Rating</option> -->
<!-- 									</select> -->
<!-- 								<a href="#" class="main-btn icon-btn"><i class="fa fa-arrow-down"></i></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="pull-right"> -->
<!-- 							<div class="page-filter"> -->
<!-- 								<span class="text-uppercase">Show:</span> -->
<!-- 								<select class="input"> -->
<!-- 										<option value="0">10</option> -->
<!-- 										<option value="1">20</option> -->
<!-- 										<option value="2">30</option> -->
<!-- 									</select> -->
<!-- 							</div> -->
<!-- 							<ul class="store-pages"> -->
<!-- 								<li><span class="text-uppercase">Page:</span></li> -->
<!-- 								<li class="active">1</li> -->
<!-- 								<li><a href="#">2</a></li> -->
<!-- 								<li><a href="#">3</a></li> -->
<!-- 								<li><a href="#"><i class="fa fa-caret-right"></i></a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					/store bottom filter -->
				</div>
				<!-- /MAIN -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript">
		
		// 讀入時載入資料
		$(document).ready(function(){
			// 第一次從後端撈資料
			let param = {};
			param.action = 'getData';
			param.pageNo = 1;
			param.pageSize = $('#meal-page-size-sel option:selected').val();
			param.meal_type_no = $('#meal-select option:selected').val();
			param.resNo = $('#res-select option:selected').val();
			
			
			loadData(param);
		});
	
		// 點擊 filter 變更資料
		$('.filter-select').on('change', function () {
			let param = {};
			param.action = 'getData'; 
			param.pageNo = 1;
			param.pageSize = $('#meal-page-size-sel option:selected').val();
			param.meal_type_no = $('#meal-select option:selected').val();
			param.resNo = $('#res-select option:selected').val();
			
			console.log("param.action : "+param.action);
			console.log("param.meal_type_no : "+param.meal_type_no);
			console.log("param.resNo : "+param.resNo);
			
			loadData(param);
		});
		
		
		// 加入購物車
		$('.product-zone').on('click', '.add-to-cart-btn', function () {
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
				 url: "../../meal/mealJson.do",
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
		$('.product-zone').on('click', '.add-to-favorite-btn', function () {
			var data = {};
			data.action = 'addToFavorite';
			data.meal_no = $(this).prevAll(".meal_no").val();
			
			console.log("data.action : "+data.action);
			console.log("data.meal_no : "+data.meal_no);
			
			$.ajax({
				 type: "POST",
				 url: "../../meal/mealJson.do",
				 data: data,
				 dataType: "json",
				 success: function(data) {
					alert(data.sysMessage);
				},
	             error: function(){alert("AJAX 發生錯誤囉!")}
	         });
			
		});
		
		// 檢視商品頁面
		$('.product-zone').on('click', '.quick-view', function () {
			let meal_no = $(this).prevAll(".meal_no").val();
			let action = 'findOneMeal';
			console.log("meal_no : "+meal_no);
			console.log("action : "+action);
			// 後面要改成 Servlet 路徑	
			window.location.href = "../../meal/mealJson.do" + "?action=" + action + "&meal_no=" + meal_no;
		});
		
// 		// 查看購物車的重導向
// 		$('#viewCart-btn').on('click', function() {
// 			window.location.href = "checkout.jsp";
// 		});
		
		// 點擊 Header 購物車的重導向
		$('.header-cart').on('click', function() {
			window.location.href = "checkout.jsp";
		});
		
		
		// 廢棄, 統一使用 Session 拿購物車資料(需要重導)
// 		function loadCartData(parama) {
// 			$.ajax({
// 				 type: "POST",
// 				 url: "../../meal/mealJson.do",
// 				 data: param,
// 				 dataType: "json",
// 				 success: function(data) {
// 					 let txt = '';
					 
// 					 if (data.meals.length === 0){
						 
// 					 } else {
// 						 for (var i = 0; i < data.meals.length; i++) {
// 	 						 txt +=
// 								'<div class="product product-widget">' +
// 									'<div class="product-thumb">' +
<%-- 										'<img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=' + data.meals[i]["meal_no"] +'" alt="">' + --%>
// 									'</div>' +
// 									'<div class="product-body">' +
// 										'<h3 class="product-price">'+ data.meals[i]["price"] +'<span class="qty">x' + data.meals[i]["quantity"] +'</span></h3>' +
// 										'<h2 class="product-name"><a href="#">' + data.meals[i]["meal_name"] +'</a></h2>' +
// 									'</div>' +
// 									'<button class="cancel-btn"><i class="fa fa-trash"></i></button>' +
// 								'</div>';
// 						 }
// 					 }
// 					 $('.shopping-cart-list').html(txt);
// 				 },
// 				error: function(){alert("AJAX-grade發生錯誤囉!")}
// 			});
// 		}
		
		// 讀入餐點資料
		function loadData(param){
			$.ajax({
				 type: "POST",
				 url: "../../meal/mealJson.do",
				 data: param,
				 dataType: "json",
				 success: function (data){
					// foreach 丟東西到指定的 div 內
					let txt = '';
					let paginationTxt = '';
					console.log("data.pageTotal%3="+data.pageTotal);
					
					if (data.rows.length === 0) {
						txt += 
							'<div class="col-md-4 col-sm-6 col-xs-6">' +
								'<span>沒有符合條件的餐點</span>';
							'</div>';
					} else {
						for (var i = 0; i < data.rows.length; i++) {
// 						     alert("meal_no=" + data.rows[i]["meal_no"]);
							txt +=
								'<div class="col-md-3 col-sm-3 col-xs-3">' +
									'<div class="product product-single">' +
										'<div class="product-thumb">' +
											'<input class="meal_no" type="hidden" value="' + data.rows[i]["meal_no"] +'">' +
											'<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>' +
											'<img src="<%= request.getContextPath() %>/GetMealPicture?meal_no='+ data.rows[i]["meal_no"] +'" alt="">' +
										'</div>' +
										'<div class="product-body">' +
											'<h3 class="product-price">$' + data.rows[i]["price"] +'</h3>' +
											'<h2 class="product-name"><a href="#">' + data.rows[i]["meal_name"] +'</a></h2>' +
											'<div class="product-btns">' +
												'<input class="price" type="hidden" value="' + data.rows[i]["price"] +'">' +
												'<input class="meal_name" type="hidden" value="' + data.rows[i]["meal_name"] + '">' +
												'<input class="meal_no" type="hidden" value="' + data.rows[i]["meal_no"] + '">' +
												'<button class="main-btn icon-btn add-to-favorite-btn"><i class="fa fa-heart"></i></button>' +
												'<button class="primary-btn add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Add to Cart</button>' +
											'</div>' +
										'</div>' +
									'</div>' +
								'</div>';
						}
						
						for (var j = 1; j <= data.pageTotal; j++) {
							paginationTxt +=
								'<li><a class="pagination-btn" style="cursor: pointer;">' + j + '</a></li>';
						}
					}
					$('.product-zone').html(txt);
					$('.store-pages').html(paginationTxt);
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         });
		};
		
		$('.store-pages').on('click', '.pagination-btn', function() {
			let pageNo = $(this).text();
			let pageSize = $('#meal-page-size-sel option:selected').val();
			
			let param = {};
			param.action = 'getData'; 
			param.pageNo = pageNo;
			param.pageSize = pageSize;
			param.meal_type_no = $('#meal-select option:selected').val();
			param.resNo = $('#res-select option:selected').val();
			
			console.log("param.action : "+param.action);
			console.log("param.pageNo : "+param.pageNo);
			console.log("param.pageSize : "+param.pageSize);
			
			loadData(param);
		});
		
		
	</script>
</body>

</html>
