<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.mealtype.model.*"%>

	<!-- NAVIGATION -->
	<div id="navigation">
		<!-- container -->
		<div class="container">
			<div id="responsive-nav">
				<!-- category nav -->
				<div class="category-nav show-on-click">
					<span class="category-header">分類目錄 <i class="fa fa-list"></i></span>
					<ul class="category-list">
					
					
					
						<li class="dropdown side-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">餐廳<i class="fa fa-angle-right"></i></a>
							<div class="custom-menu">
								<div class="row">
									<div class="col-md-4">
										<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />
										<ul class="list-links">
											<c:forEach var="restaurant" items="${restaurantSvc.all}">
												<li>
													<a href="<%= request.getContextPath() %>/meal/mealJson.do
															?action=findMealsByCategory&resNo=${restaurant.resNo}">
														${restaurant.resName}
													</a>
												</li>
											</c:forEach>
										</ul>
										<hr class="hidden-md hidden-lg">
									</div>
								</div>
<!-- 								<div class="row hidden-sm hidden-xs"> -->
<!-- 									<div class="col-md-12"> -->
<!-- 										<hr> -->
<!-- 										<a class="banner banner-1" href="#"> -->
<!-- 											<img src="./img/banner05.jpg" alt=""> -->
<!-- 											<div class="banner-caption text-center"> -->
<!-- 												<h2 class="white-color">NEW COLLECTION</h2> -->
<!-- 												<h3 class="white-color font-weak">HOT DEAL</h3> -->
<!-- 											</div> -->
<!-- 										</a> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>
						</li>
						<li class="dropdown side-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">餐點類別<i class="fa fa-angle-right"></i></a>
							<div class="custom-menu">
								<div class="row">
									<div class="col-md-4">
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
										<hr class="hidden-md hidden-lg">
									</div>
								</div>
<!-- 								<div class="row hidden-sm hidden-xs"> -->
<!-- 									<div class="col-md-12"> -->
<!-- 										<hr> -->
<!-- 										<a class="banner banner-1" href="#"> -->
<!-- 											<img src="./img/banner05.jpg" alt=""> -->
<!-- 											<div class="banner-caption text-center"> -->
<!-- 												<h2 class="white-color">NEW COLLECTION</h2> -->
<!-- 												<h3 class="white-color font-weak">HOT DEAL</h3> -->
<!-- 											</div> -->
<!-- 										</a> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>
						</li>
						<li><a href="<%= request.getContextPath() %>/front-end/meal/listAllMeal.jsp">View All</a></li>
					</ul>
				</div>
				<!-- /category nav -->

				<!-- menu nav -->
				<div class="menu-nav">
					<span class="menu-header">Menu <i class="fa fa-bars"></i></span>
					<ul class="menu-list">
<!-- 					導向到首頁 -->
						<li><a href="<%= request.getContextPath() %>/front-end/index.jsp">HOTEL UNUS</a></li>
						
						
<!-- 						<li class="dropdown mega-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Women <i class="fa fa-caret-down"></i></a> -->
<!-- 							<div class="custom-menu"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-md-4"> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 										<hr class="hidden-md hidden-lg"> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-4"> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 										<hr class="hidden-md hidden-lg"> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-4"> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="row hidden-sm hidden-xs"> -->
<!-- 									<div class="col-md-12"> -->
<!-- 										<hr> -->
<!-- 										<a class="banner banner-1" href="#"> -->
<!-- 											<img src="./img/banner05.jpg" alt=""> -->
<!-- 											<div class="banner-caption text-center"> -->
<!-- 												<h2 class="white-color">NEW COLLECTION</h2> -->
<!-- 												<h3 class="white-color font-weak">HOT DEAL</h3> -->
<!-- 											</div> -->
<!-- 										</a> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</li> -->
						
						
						
						
<!-- 						<li class="dropdown mega-dropdown full-width"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Men <i class="fa fa-caret-down"></i></a> -->
<!-- 							<div class="custom-menu"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<div class="hidden-sm hidden-xs"> -->
<!-- 											<a class="banner banner-1" href="#"> -->
<!-- 												<img src="./img/banner06.jpg" alt=""> -->
<!-- 												<div class="banner-caption text-center"> -->
<!-- 													<h3 class="white-color text-uppercase">Womenâs</h3> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 											<hr> -->
<!-- 										</div> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<div class="hidden-sm hidden-xs"> -->
<!-- 											<a class="banner banner-1" href="#"> -->
<!-- 												<img src="./img/banner07.jpg" alt=""> -->
<!-- 												<div class="banner-caption text-center"> -->
<!-- 													<h3 class="white-color text-uppercase">Menâs</h3> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</div> -->
<!-- 										<hr> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<div class="hidden-sm hidden-xs"> -->
<!-- 											<a class="banner banner-1" href="#"> -->
<!-- 												<img src="./img/banner08.jpg" alt=""> -->
<!-- 												<div class="banner-caption text-center"> -->
<!-- 													<h3 class="white-color text-uppercase">Accessories</h3> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</div> -->
<!-- 										<hr> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<div class="hidden-sm hidden-xs"> -->
<!-- 											<a class="banner banner-1" href="#"> -->
<!-- 												<img src="./img/banner09.jpg" alt=""> -->
<!-- 												<div class="banner-caption text-center"> -->
<!-- 													<h3 class="white-color text-uppercase">Bags</h3> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</div> -->
<!-- 										<hr> -->
<!-- 										<ul class="list-links"> -->
<!-- 											<li> -->
<!-- 												<h3 class="list-links-title">Categories</h3></li> -->
<!-- 											<li><a href="#">Womenâs Clothing</a></li> -->
<!-- 											<li><a href="#">Menâs Clothing</a></li> -->
<!-- 											<li><a href="#">Phones & Accessories</a></li> -->
<!-- 											<li><a href="#">Jewelry & Watches</a></li> -->
<!-- 											<li><a href="#">Bags & Shoes</a></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</li> -->
						
						
						<li><a href="<%= request.getContextPath() %>/front-end/meal/listAllMeal.jsp">餐點瀏覽</a></li>
						<li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">分頁 <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="<%= request.getContextPath() %>/front-end/index.jsp">HOTEL UNUS</a></li>
								<li><a href="<%= request.getContextPath() %>/front-end/meal/listAllMeal.jsp">餐點瀏覽</a></li>
								<li><a href="<%= request.getContextPath() %>/front-end/meal/checkout.jsp">購物車</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- menu nav -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /NAVIGATION -->