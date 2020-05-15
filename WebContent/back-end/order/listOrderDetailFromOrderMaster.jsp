<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roommealorderdetail.model.*" %>

<%
//	RoomMealOrderDetailService roomMealOrderDetailSvc = new RoomMealOrderDetailService();
	List<RoomMealOrderDetailVO> roomMealOrderDetails = (List<RoomMealOrderDetailVO>) request.getAttribute("roomMealOrderDetails");
// 	List<RoomMealOrderDetailVO> roomMealOrderDetails = roomMealOrderDetailSvc.getAll();
	pageContext.setAttribute("roomMealOrderDetails", roomMealOrderDetails);
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



</head>
<body>
	<div class="container">
		<div class="row body">
			<table class="table table-striped table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">訂餐訂單編號</th>
						<th scope="col">餐點編號</th>
						<th scope="col">數量</th>
						<th scope="col">單價</th>
						<th scope="col">修改</th>
						<th scope="col">刪除</th>
					</tr>
				</thead>
				<c:forEach var="roomMealOrderDetailVO" items="${roomMealOrderDetails}" >
				<tbody>
					<tr>
						<td>${roomMealOrderDetailVO.room_meal_order_no}</td>
						<td>${roomMealOrderDetailVO.meal_no}</td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order/order.do">
							<td>
								<input name="${roomMealOrderDetailVO.room_meal_order_no}_${roomMealOrderDetailVO.meal_no}_quantity" type="number" value="${roomMealOrderDetailVO.quantity}" min="0" max="99">
							</td>
							<td>${roomMealOrderDetailVO.price}</td>
							<td>
								    <input type="submit" class="delete" value="修改">
								    <input type="hidden" name="room_meal_order_no" value="${roomMealOrderDetailVO.room_meal_order_no}">
								    <input type="hidden" name="meal_no" value="${roomMealOrderDetailVO.meal_no}">
								    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								    <input type="hidden" name="action" value="updateOneOrderDetail">
							</td>
						</FORM>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order/order.do">
							    <input type="submit" class="delete" value="刪除">
							    <input type="hidden" name="room_meal_order_no" value="${roomMealOrderDetailVO.room_meal_order_no}">
							    <input type="hidden" name="meal_no" value="${roomMealOrderDetailVO.meal_no}">
							    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							    <input type="hidden" name="action" value="deleteOneOrderDetail">
							</FORM>						
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>		
		
	</div>
</body>
</html>