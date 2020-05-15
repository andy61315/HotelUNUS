<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- HEADER -->
	<header>

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="#">
							<img src="./img/logo.png" alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
<!-- 					<div class="header-search"> -->
<!-- 						<form> -->
<!-- 							<input class="input search-input" type="text" placeholder="Enter your keyword"> -->
<!-- 							<select class="input search-categories"> -->
<!-- 								<option value="0">All Categories</option> -->
<!-- 								<option value="1">Category 01</option> -->
<!-- 								<option value="1">Category 02</option> -->
<!-- 							</select> -->
<!-- 							<button class="search-btn"><i class="fa fa-search"></i></button> -->
<!-- 						</form> -->
<!-- 					</div> -->
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<strong class="text-uppercase">My Account <i class="fa fa-caret-down"></i></strong>
							</div>
							<span>${sessionScope.customerVO.cus_Name}</span>
							<ul class="custom-menu">
								<li><a href="<%= request.getContextPath() %>/front-end/customer/listOneCus.jsp"><i class="fa fa-user-o"></i> 會員資料管理</a></li>
								<li><a href="<%= request.getContextPath() %>/front-end/bookingordermaster/listBookingOrder.jsp"><i class="fa fa-bed"></i> 住房訂單管理</a></li>
								<li><a href="<%= request.getContextPath() %>/front-end/resreservation/listCusResReservation.jsp"><i class="fa fa-file-text-o"></i> 餐廳預約管理</a></li>
								<li><a href="#"><i class="fa fa-heart-o"></i> 我的收藏餐點</a></li>
								<li><a href="<%= request.getContextPath() %>/front-end/meal/checkout.jsp"><i class="fa fa-check"></i> 購物車</a></li>
							</ul>
						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-shopping-cart"></i>
									<span class="qty" id="itemNumberInCart">
										<c:choose>
											<c:when test="${empty sessionScope.itemNumberInCart}">
												0
											</c:when>
											<c:otherwise>
												${sessionScope.itemNumberInCart}
											</c:otherwise>
										</c:choose>
									</span>
								</div>
								<strong class="text-uppercase">My Cart:</strong>
								<br>
								<span class="total">
								<c:choose>
									<c:when test="${empty total}">
										-$
									</c:when>
									<c:otherwise>
										${total}$
									</c:otherwise>
								</c:choose>
								</span>
							</a>
<!-- 							<div class="custom-menu"> -->
<!-- 								<div id="shopping-cart"> -->
<!-- 									<div class="shopping-cart-list"> -->
<%-- 										<c:forEach var="cart" items="${sessionScope.cart}"> --%>
<!-- 										<div class="product product-widget"> -->
<!-- 											<div class="product-thumb"> -->
<%-- 												<img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=${cart.meal_no}" alt=""> --%>
<!-- 											</div> -->
<!-- 											<div class="product-body"> -->
<%-- 												<h3 class="product-price">${cart.price}<span class="qty">x${cart.quantity}</span></h3> --%>
<%-- 												<h2 class="product-name"><a href="#">${cart.meal_name}</a></h2> --%>
<!-- 											</div> -->
<!-- 											<button class="cancel-btn"><i class="fa fa-trash"></i></button> -->
<!-- 										</div> -->
<%-- 										</c:forEach> --%>
<!-- 									</div> -->
<!-- 									<div class="shopping-cart-btns"> -->
<!-- 										<button class="main-btn" id="viewCart-btn">View Cart</button> -->
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${empty sessionScope.cart}"> --%>
<!-- 												<button class="primary-btn" style="display: none;">Checkout <i class="fa fa-arrow-circle-right"></i></button> -->
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
<!-- 												<button class="primary-btn" style="display: inline-block;">Checkout <i class="fa fa-arrow-circle-right"></i></button> -->
<%-- 											</c:otherwise> --%>
<%-- 										</c:choose> --%>
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</li>
						<!-- /Cart -->

						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	</header>
	<!-- /HEADER -->

