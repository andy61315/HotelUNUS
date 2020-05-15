<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.mealtype.model.*"%>


<%
	if ((List<MealVO>) request.getAttribute("mealList") == null) {
		MealService mealSvc = new MealService();
		List<MealVO> mealList = mealSvc.getAll();
		pageContext.setAttribute("mealList", mealList);
	} else {
		List<MealVO> mealList = (List<MealVO>) request.getAttribute("mealList");
		pageContext.setAttribute("mealList", mealList);
	}
	
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
				<li class="active">Products</li>
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
				<!-- ASIDE -->
				<div id="aside" class="col-md-3">
					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">Filter by MealType</h3>
						<jsp:useBean id="mealTypeSvc" scope="page" class="com.mealtype.model.MealTypeService" />
						<ul class="list-links">
							<c:forEach var="mealType" items="${mealTypeSvc.all}">
								<li>
									<a href="<%= request.getContextPath() %>/meal/mealJson.do
											?action=findMealsByCategory&meal_type_no=${mealType.meal_type_no}">
										${mealType.type_name}
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- /aside widget -->

					<!-- aside widget -->
					<div class="aside">
						<h3 class="aside-title">Filter by Restaurant</h3>
						<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />
						<ul class="list-links">
<!-- 						li 中的 class active 代表選擇 -->
							<c:forEach var="restaurant" items="${restaurantSvc.all}">
								<li>
								<a href="<%= request.getContextPath() %>/meal/mealJson.do
										?action=findMealsByCategory&resNo=${restaurant.resNo}">
									${restaurant.resName}
								</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- /aside widget -->
				</div>
				<!-- /ASIDE -->

				<!-- MAIN -->
				<div id="main" class="col-md-9">
					<!-- store top filter -->
					<div class="store-filter clearfix">
						<div class="pull-left">
							<div class="row-filter">
								<a href="#"><i class="fa fa-th-large"></i></a>
								<a href="#" class="active"><i class="fa fa-bars"></i></a>
							</div>
							<div class="sort-filter">
								<span class="text-uppercase">Sort By:</span>
								<select class="input">
										<option value="0">Position</option>
										<option value="0">Price</option>
										<option value="0">Rating</option>
									</select>
								<a href="#" class="main-btn icon-btn"><i class="fa fa-arrow-down"></i></a>
							</div>
						</div>
						<div class="pull-right">
							<div class="page-filter">
								<span class="text-uppercase">Show:</span>
								<select class="input">
										<option value="0">10</option>
										<option value="1">20</option>
										<option value="2">30</option>
									</select>
							</div>
							<ul class="store-pages">
								<li><span class="text-uppercase">Page:</span></li>
								<li class="active">1</li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#"><i class="fa fa-caret-right"></i></a></li>
							</ul>
						</div>
					</div>
					<!-- /store top filter -->

					<!-- STORE -->
					<div id="store">
						<!-- row -->
						<div class="row">
							<!-- Product Single -->
							<c:forEach var="meal" items="${mealList}">
							<div class="col-md-4 col-sm-6 col-xs-6">
								<div class="product product-single">
									<div class="product-thumb">
										<input class="meal_no" type="hidden" value="${meal.meal_no}">
										<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
										<img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=${meal.meal_no}" alt="">
									</div>
									<div class="product-body">
										<h3 class="product-price">$${meal.price}</h3>
										<h2 class="product-name"><a href="#">${meal.meal_name}</a></h2>
										<div class="product-btns">
											<input class="price" type="hidden" value="${meal.price}">
											<input class="meal_name" type="hidden" value="${meal.meal_name}">
											<input class="meal_no" type="hidden" value="${meal.meal_no}">
											<button class="main-btn icon-btn add-to-favorite-btn"><i class="fa fa-heart"></i></button>
											<button class="primary-btn add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
							<!-- /Product Single -->

							<div class="clearfix visible-sm visible-xs"></div>


							<div class="clearfix visible-md visible-lg"></div>


							<div class="clearfix visible-sm visible-xs"></div>

							<div class="clearfix visible-md visible-lg visible-sm visible-xs"></div>



							<div class="clearfix visible-sm visible-xs"></div>

						</div>
						<!-- /row -->
					</div>
					<!-- /STORE -->

					<!-- store bottom filter -->
					<div class="store-filter clearfix">
						<div class="pull-left">
							<div class="row-filter">
								<a href="#"><i class="fa fa-th-large"></i></a>
								<a href="#" class="active"><i class="fa fa-bars"></i></a>
							</div>
							<div class="sort-filter">
								<span class="text-uppercase">Sort By:</span>
								<select class="input">
										<option value="0">Position</option>
										<option value="0">Price</option>
										<option value="0">Rating</option>
									</select>
								<a href="#" class="main-btn icon-btn"><i class="fa fa-arrow-down"></i></a>
							</div>
						</div>
						<div class="pull-right">
							<div class="page-filter">
								<span class="text-uppercase">Show:</span>
								<select class="input">
										<option value="0">10</option>
										<option value="1">20</option>
										<option value="2">30</option>
									</select>
							</div>
							<ul class="store-pages">
								<li><span class="text-uppercase">Page:</span></li>
								<li class="active">1</li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#"><i class="fa fa-caret-right"></i></a></li>
							</ul>
						</div>
					</div>
					<!-- /store bottom filter -->
				</div>
				<!-- /MAIN -->
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
				 success: console.log("成功"),
	             error: function(){alert("AJAX 發生錯誤囉!")}
	         });
			
		});
		
		// 檢視商品頁面
		$('.quick-view').on('click', function () {
			let meal_no = $(this).prevAll(".meal_no").val();
			let action = 'findOneMeal';
			console.log("meal_no : "+meal_no);
			console.log("action : "+action);
			// 後面要改成 Servlet 路徑
			window.location.href = "../meal/mealJson.do?action=" + action + "&meal_no=" + meal_no;
		})
		
		// 查看購物車的重導向
// 		$('#viewCart-btn').on('click', function() {
// 			window.location.href = "checkout.jsp";
// 		});
		
		// 點擊 Header 購物車的重導向
		$('.header-cart').on('click', function() {
			window.location.href = "../front-end/meal/checkout.jsp";
		});
		
	</script>
</body>

</html>
