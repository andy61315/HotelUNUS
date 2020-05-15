<%@page import="com.restaurant.model.RestaurantVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>
<%-- 由餐廳管理進入點餐--%>

<%
RestaurantVO restVO = (RestaurantVO)session.getAttribute("restVO");//餐廳那邊要把VO放進session
MealService mealSvc = new MealService();
List<OrderMealVO> list = mealSvc.getByRestaurant(restVO.getResNo());
pageContext.setAttribute("list",list);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<title>點餐系統</title>

<!-- CSS -->

	<!-- bootstrap/4.4.1 -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
<!-- CSS -->


<!-- JS -->

	<!-- jquery-3.4.1 -->
	<script
	  src="https://code.jquery.com/jquery-3.4.1.js"
	  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	  crossorigin="anonymous">
	</script>
	
	<!-- jquery-3.4.1.slim.min -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	
	<!-- popper.js@1.16.0 -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	
	<!-- bootstrap/4.4.1 -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
	<!-- 裡面有 jquery 語法需要先引入 jquery -->
	<%@ include file="/back-end/homepage/reshead.file" %>
	
<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/resmeal/css/ShoppingCart.css">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script> -->

<style type="text/css">
 h4 { 
     color: blue;
     margin:5px;
     display: inline; 
  } 
  
</style>

<style>
   table#table1 { 
 	width:500px;
 	
 	margin-right: 10px; 
 	margin-bottom: 5px; 
 	opacity:0.9;
   } 
   table#table2 { 
 	width: 800px; 
 	background-color: white; 
 	opacity:0.9;
/*  	margin-top: 30px;  */
/*  	margin-bottom: 5px;  */
   } 
   
    table#table3 { 
 	width: 800px; 
 	background-color: white; 
/*  	margin-top: 130px;  */
 	margin-bottom: 5px; 
   } 
   table, th, td { 
     border: 1px solid #CCCCFF; 
   } 
  
   div#cart {
/*       margin-top:300px; */
   }
   
#right{
	text-align:left;
}
</style>

</head>

<body bgcolor='white'>
<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" > ${restVO.resName} >>>點餐系統</h2>
    			<hr>


<table id="table1">	
<form name="checkoutForm" id="checkoutForm" action="<%=request.getContextPath()%>/back-end/resmeal/Shopping.html" method="POST">
	
	<h4><a href="<%=request.getContextPath()%>/back-end/restaurant/listAllRestaurant.jsp">回首頁</a></h4>
	<h3>請輸入以下資料: </h3>
	
	<tr>
	<th>桌號 </th><td><input type="text" name="table_no" id="tableNo" value=""placeholder="必填"></td>
	</tr>
	<tr>
	<th>房號 </th><td><input type="text" name="room_no" id="roomNo" value=""></td>
	</tr>
	<tr>
	<th>特殊要求 </th><td><input type="text" name="require" value=""></td>
	</tr>
	<input type="hidden" name="total" value="" id="hiddenTotal">
	<input type="hidden" name="action"  value="CHECKOUT"> 
	
</form>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">輸入資料有誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="table2">
	
</table>

<table id="table3">
      <tr>
		<th  width="100">餐點編號</th>
		<th  width="100">名稱</th>
		<th  width="100">價格</th>
		<th  width="100">圖片</th>
		<th  width="100">數量</th>
		<th  width="100"><img src="<%=request.getContextPath()%>/back-end/resmeal/images/shopping-cart.png" width="45px" height="35px"></th>
		
	</tr>
<%@ include file="page1.file" %> 
<c:forEach var="mealVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<form name="shoppingForm" action="<%=request.getContextPath()%>/back-end/resmeal/Shopping.html" method="POST">	

	
		<tr>
			<td  width="100">${mealVO.meal_no}</td>
			<td  width="100">${mealVO.meal_name}</td>
			<td  width="100">${mealVO.price}</td>
			<td  width="100"><img src="<%= request.getContextPath() %>/GetMealPicture?meal_no=${mealVO.meal_no}"  width="100"></td>
			<td  width="100"><div align="center">數量：<select name="quantity">
			                                              <option value=1>1</option>
			                                              <option value=2>2</option>
			                                              <option value=3>3</option>
			                                              <option value=4>4</option>
			                                              <option value=5>5</option>
			                                              <option value=6>6</option>
			                                              </select></td>
			<td  width="100">
			<div align="center"> <input type="submit" class="button" id ="add" value="加入餐點"> </div></td>
			
		</tr>	
		


      <input type="hidden" name="meal_no" value="${mealVO.meal_no}">
      <input type="hidden" name="meal_name" value="${mealVO.meal_name}">
      <input type="hidden" name="price" value="${mealVO.price}">
<%--       <input type="hidden" name="meal_picture" value="${mealVO.meal_picture}"> --%>
      <input type="hidden" name="action" value="ADD">
</form>
</c:forEach>
<tr><td colspan="6"><input type="submit" value="付款結帳" class="button" id="checkOut" onclick="checkOut()"></td></tr>
</table>
<%@ include file="page2.file" %>
<div id="cart">
<c:if test="${shoppingcart!=null}">
<jsp:include page="Cart.jsp" flush="true" />
</c:if>
</div>


<script>

function checkOut(){
	var total = document.getElementById("total").value;
	document.getElementById("hiddenTotal").value=total;//把從Cart取得的total放入上面的表單一起送出
	document.getElementById("checkoutForm").submit();
}
</script>


<script type="text/javascript">
  var roomNo =  document.getElementById("roomNo");
  var tableNo = document.getElementById("tableNo");
  var total = document.getElementById("total");
  document.getElementById("checkOut").addEventListener("click",function(){
	  if(roomNo.value=="" && tableNo.value!=""){
		  alert("此次消費 "+total.value+"元");
		  //swal("Good job!", "You clicked the button!", "success");
// 		  Swal.fire({
// 			  position: 'top-end',
// 			  icon: 'success',
// 			  title: 'Your work has been saved',
// 			  showConfirmButton: false,
// 			  timer: 1500
// 			})
	  }  
	  
	});
</script>
</div>
<%-- <%@ include file="/back-end/homepage/footer.file" %> --%>
</body>
</html>