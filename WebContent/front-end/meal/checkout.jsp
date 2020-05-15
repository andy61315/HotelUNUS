<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Check Out Page</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" /> -->

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

	<!-- 	Sweet Alert 2 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
	
	<!-- 	Sweet Alert 2 Plugin -->
	
	<!-- 	bootstrap 3.3.7 -->
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>

	<jsp:include page="header.jsp" />

	<jsp:include page="navigation.jsp" />

	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="index.jsp">Home</a></li>
				<li class="active">Checkout</li>
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
				<form id="checkout-form" class="clearfix">
					<div class="col-md-12">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">填寫訂餐相關資料</h3>
							</div>
							<jsp:useBean id="bomSvc" scope="page" class="com.bom.model.BookingOrderMasterService" />
							<div class="form-group">
								<c:choose>
									<c:when test="${not empty bomSvc.getBOMByCusIDAndCheckINStatus(sessionScope.customerVO.cus_Id)}">
										<select size="1" name="b_Order_No"  id="form-b-order-no">
											<c:forEach var="bomVO" items="${bomSvc.getBOMByCusIDAndCheckINStatus(sessionScope.customerVO.cus_Id)}">
												<option value="${bomVO.b_Order_No}">${bomVO.b_Order_No}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<hr>
											<span>請先去櫃檯 Checkin 之後再來點餐</span>
										<hr>
									</c:otherwise>
								</c:choose>
							</div>
							<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
							<div class="form-group">
								<c:choose>
									<c:when test="${not empty roomSvc.getRoomByCusID(sessionScope.customerVO.cus_Id)}">
										<select size="1" name="room_no" id="form-room_no">
											<c:forEach var="roomVO" items="${roomSvc.getRoomByCusID(sessionScope.customerVO.cus_Id)}">
												<option value="${roomVO.room_no}">${roomVO.room_no}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<hr>
										<span>請先去櫃檯 Checkin 之後再來點餐</span>
										<hr>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="special_requirement" placeholder="特別需求">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="order-summary clearfix">
							<div class="section-title">
								<h3 class="title">Order Review</h3>
							</div>
							<table class="shopping-cart-table table">
								<thead>
									<tr>
										<th>Product</th>
										<th></th>
										<th class="text-center">Price</th>
										<th class="text-center">Quantity</th>
										<th class="text-right"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cart" items="${sessionScope.cart}">
									<tr>
										<td class="thumb"><img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=${cart.meal_no}" alt=""></td>
										<td class="details">
											<a href="#">${cart.meal_name}</a>
										</td>
										<td class="price text-center"><strong>${cart.price}</strong></td>
										<td class="qty text-center">
											<input class="meal_no" type="hidden" value="${cart.meal_no}">
											<input class="input meal_quantity_input" type="number" value="${cart.quantity}" max="99" min="0">
										</td>
										<td class="text-right">
											<input class="meal_no" type="hidden" value="${cart.meal_no}">
											<button class="main-btn icon-btn delete-btn">
												<i class="fa fa-close"></i>
											</button>
										</td>
									</tr>
									</c:forEach>
								<tbody>
								<tfoot>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>TOTAL</th>
										<th colspan="2" class="total">$${total}</th>
									</tr>
								</tfoot>
							</table>
							<div class="pull-right">
								<c:choose>
									<c:when test="${empty sessionScope.cart}">
										<button class="primary-btn" style="display: none;">Place Order</button>
									</c:when>
									<c:when test="${empty bomSvc.getBOMByCusIDAndCheckINStatus(sessionScope.customerVO.cus_Id)}">
										<button class="primary-btn" style="display: none;">Place Order</button>
									</c:when>
									<c:when test="${empty roomSvc.getRoomByCusID(sessionScope.customerVO.cus_Id)}">
										<button class="primary-btn" style="display: none;">Place Order</button>
									</c:when>
									<c:otherwise>
										<button class="primary-btn" style="display: inline-block;">Place Order</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

					</div>
				</form>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

<%-- 	<jsp:include page="footer.jsp" /> --%>

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
<!-- 	<script src="js/bootstrap.min.js"></script> -->
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		// 查看購物車的重導向
		$('#viewCart-btn').on('click', function() {
			window.location.href = "checkout.jsp";
		});
		
		// 數量變更時總金額隨之變動
		$('.meal_quantity_input').on('change', function () {
				var data = {};
				data.action = 'getNewTotal';
				data.quantityInput = $(this).val();
				data.meal_no = $(this).prevAll(".meal_no").val();
				console.log("data.quantityInput : "+data.quantityInput);
				console.log("data.meal_no : "+data.meal_no);
				$.ajax({
					 type: "POST",
					 url: "../../order/orderJson.do",
					 data: data,
					 dataType: "json",
					 success: function (data) {
						$('.total').html(data.total);
					},
		             error: function(){alert("AJAX 發生錯誤囉!")}
		         });
		});
		
		
		// 新增訂單 b_order_no room_no
		$('.primary-btn').on('click', function (event){
		  event.preventDefault();
       		var data = {};
       		data.action = 'addOrder';
       		data.b_order_no = $("#form-b-order-no option:selected").val();
       		data.room_no = $('#form-room_no  option:selected').val();
       		console.log("action : "+data.action);
       		console.log("b_order_no : "+data.b_order_no);
       		console.log("room_no : "+data.room_no);
       		
       		
       		addOrder(data);
       		
       		
		});
		
		// 刪除單筆餐點
		$('.delete-btn').on('click', function () {
			var data = {};
			data.action = 'deleteOneMeal';
			data.meal_no = $(this).prev('.meal_no').val();
			
			console.log("data.action : "+data.action);
			console.log("data.meal_no : "+data.meal_no);
			
			deleteOneMeal(data);
			
		});
		
		function deleteOneMeal(data) {
			$.ajax({
				 type: "POST",
				 url: "../../meal/mealJson.do",
				 data: data,
				 dataType: "json",
				 success: function () {
					 window.location.href = "../meal/checkout.jsp";
				},
	             error: function(){alert("AJAX 發生錯誤囉!")}
	         });
		}
		
		function addOrder(data){
			$.ajax({
				 type: "POST",
				 url: "../../order/orderJson.do",
				 data: data,
				 dataType: "json",
				 success: function () {
					 window.location.href = "<%=request.getContextPath()%>/front-end/order/orderFinished.jsp";
				},
	             error: console.log("失敗")
	         });
		}
		
	</script>

</body>

</html>
