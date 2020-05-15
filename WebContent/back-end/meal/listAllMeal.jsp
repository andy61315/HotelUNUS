<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>

<%
	MealService mealSvc = new MealService();
	List<MealVO> list = mealSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListAll</title>

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
		width: 100px;
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
  
  	<!-- sm 範圍以上，分別佔 8 欄及 4 欄 -->
	<div class="row header">
		<div class="col">Header-Left</div>
		<div class="col">Header-Right<a href="addMeal.jsp">AddMeal</a></div>
	</div>
  
	<!-- Main -->
	<div class="row">
		<table>
			<tr>
				<th>餐點編號</th>
				<th>餐廳編號</th>
				<th>餐點類別</th>
				<th>餐點價格</th>
				<th>餐點名稱</th>
				<th>修改日期</th>
				<th>餐點狀態</th>
				<th>餐點圖片</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file" %>
			<jsp:useBean id="mealTypeSvc" scope="page" class="com.mealtype.model.MealTypeService" />
			<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />
			<c:forEach var="mealVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${mealVO.meal_no}</td>
					<td>
						<c:forEach var="restaurantVO" items="${restaurantSvc.all}">
							<c:if test="${mealVO.res_no==restaurantVO.resNo}">
								${restaurantVO.resName}
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="mealTypeVO" items="${mealTypeSvc.all}">
							<c:if test="${mealVO.meal_type_no==mealTypeVO.meal_type_no}">
								${mealTypeVO.type_name}
							</c:if>
						</c:forEach>
					</td>
					<td>${mealVO.price}</td>
					<td>${mealVO.meal_name}</td>
					<td>${mealVO.meal_date}</td> 
					<td>
						<c:choose>
							<c:when test="${mealVO.meal_status==0}">下架</c:when>
							<c:when test="${mealVO.meal_status==1}">上架</c:when>
						</c:choose>
					</td>
					<td>
						<img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=${mealVO.meal_no}">
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/meal/meal.do">
						    <input type="submit" value="修改">
						    <input type="hidden" name="meal_no"  value="${mealVO.meal_no}">
						    <input type="hidden" name="action"	value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/meal/meal.do">
						    <input type="submit" class="delete" value="刪除">
						    <input type="hidden" name="meal_no"  value="${mealVO.meal_no}">
						    <input type="hidden" name="action"	value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file" %>
	</div>
  
	<!-- Page -->
	<div class="row">
	</div>
  
	<!-- sm 範圍以上，各欄均分 -->
	<div class="row footer">
		<div class="col">Footer-1</div>
		<div class="col">Footer-2</div>
		<div class="col">Footer-3</div>
	</div>

</div>

</body>
</html>