<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.restaurant.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    RestaurantVO restVO = (RestaurantVO) request.getAttribute("restVO"); 
    //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<title>Insert title here</title>
<!-- bootstrap/4.4.1 -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<style>
  table#table-1 {
/* 	background-color: #CCCCFF; */
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table#table2 {
/* 	width: 600px; */
/* 	background-color: white; */
	margin-top: 120px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- 裡面有 jquery 語法需要先引入 jquery -->
	<%@ include file="/back-end/homepage/head.file" %>
	
<!-- JS -->
<script type="text/javascript">
  $(document).ready(function(){
	  <c:if test="${'update'.equals(param.action)}">
	  swal("Good job!", "修改完成!", "success");
	  </c:if>
	});
</script>
</head>

<body bgcolor='white'>
<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >餐廳管理</h2>

<table id="table-1">
	<tr><td>
		 <h3>餐廳 - ListOneRestaurant</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/restaurant/listAllRestaurant.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table id="table2">
	<tr>
<!-- 		<th>餐廳編號</th> -->
		<th>餐廳名稱</th>
		<th>座位</th>
		<th>聯絡人</th>
		<th>電話</th>
		<th>狀態</th>
	</tr>
	<tr>
<%-- 		<td><%=restVO.getResNo()%></td> --%>
		<td><%=restVO.getResName()%></td>
		<td><%=restVO.getTotalSeat()%></td>
		<td><%=restVO.getResContact()%></td>
		<td><%=restVO.getResPhone()%></td>
		<td>${restaurantStatus[restVO.resStatus]}</td>
	</tr>
</table>
<%@ include file="/back-end/homepage/footer.file" %>
</body>
</html>