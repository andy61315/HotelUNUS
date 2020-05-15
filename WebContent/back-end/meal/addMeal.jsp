<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meal.model.*"%>


<%
	MealVO mealVO = (MealVO) request.getAttribute("MealVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jquery&popper.js's CDN -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<!-- bootstrap's CDN Version-4.4.1 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<style type="text/css">
	div.container{
	  border: 1px solid red;
	}
	div.row{
	  border: 1px solid green;
	}
	div.col{
	  border: 1px solid blue;
	}
	table {
		width: 100%
	}
	table, th, td {
		border: 1px solid #CCCCFF;
	}
	th, td {
		text-align: center;
	}
	img {
		width: 100%
	}
</style>

</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<div class="container">
		<div class="row">
		  header<a href="listAllMeal.jsp">回清單</a>
		</div>
		<form method="post" action="<%=request.getContextPath()%>/meal/meal.do" enctype="multipart/form-data">
			<div class="row">
				<!-- 左側 -->
				<div class="col-4">
				圖片欄位
					<div class="row">
						<label for="imageFile">
							<img src="<%= request.getContextPath() %>/NoData/null2.jpg">
						</label>
					</div>
					<div class="row">
						<input type="file" id="imageFile" style="display:none" name="meal_picture" accept="image/*" >
					</div>
				</div>
				<!-- 右側 -->
				<div class="col-6">
				  資料欄位
					<div class="row">
						<table>
							<tr>
								<td>餐點名稱:</td>
								<td><input type="text" name="meal_name" /></td>
							</tr>
							<tr>
								<td>價格:</td>
								<td><input type="text" name="price" /></td>
							</tr>
							<!-- 要做 useBean + forEach -->
							<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />
							<tr>
								<td>餐廳:</td>
								<td>
								<select size="1" name="res_no">
									<c:forEach var="restaurantVO" items="${restaurantSvc.all}">
										<option value="${restaurantVO.resNo}" ${(mealVO.res_no==restaurantVO.resNo)?'selected':'' }>${restaurantVO.resName}</option>
									</c:forEach>
								</select>
								</td>
							</tr>
							<!-- 要做 useBean + forEach -->
							<jsp:useBean id="mealTypeSvc" scope="page" class="com.mealtype.model.MealTypeService" />
							<tr>
								<td>餐點類別:</td>
								<td>
									<select size="1" name="meal_type_no">
										<c:forEach var="mealTypeVO" items="${mealTypeSvc.all}">
											<option value="${mealTypeVO.meal_type_no}" ${(mealVO.meal_type_no==mealTypeVO.meal_type_no)?'selected':'' } >${mealTypeVO.type_name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<!-- 下拉式選單 上架/下架 -->
							<tr>
								<td>餐點狀態:</td>
								<td>
									<select size="1" name="meal_status">
										<option value="0" ${(mealVO.meal_status==0)?'selected':'' } >下架</option>
										<option value="1" ${(mealVO.meal_status==1)?'selected':'' } >上架</option>
									</select>								
								</td>
							</tr>
							<tr>
								<td>修改日期:</td>
								<td><input type="date" name="meal_date" /></td>
							</tr>
							<tr>
								<td>餐點介紹:</td>
								<td><textarea cols="20" rows="4" name="meal_introduction"></textarea></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<input type="hidden" name="action" value="insert">
			<input type="submit" class="btn btn-outline-primary" value="送出修改">
		</form>
		
		<div class="row">
		footer
		</div>
	</div>

</body>
</html>