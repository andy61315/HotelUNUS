<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>

<%
    RestaurantVO restVO = (RestaurantVO) request.getAttribute("restVO");

    //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file" %> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>餐廳資料修改 - update_Restaurant_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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


    
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >餐廳管理</h2>
    			<div class="col-12">


		 <h3>餐廳資料修改</h3>
		 <h4><a href="listAllRestaurant.jsp">回首頁</a></h4>




<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/restaurant/rest.do" name="form1">
<table>
	<tr>
		<td>餐廳編號:<font color=red><b>*</b></font></td>
		<td><%=restVO.getResNo()%></td>
	</tr>
	<tr>
		<td>餐廳名稱:</td>
		<td><input type="TEXT" name="resName" size="20" value="<%=restVO.getResName()%>" /></td>
	</tr>
	<tr>
		<td>座位:</td>
		<td><input type="TEXT" name="totalSeat" size="20" value="<%=restVO.getTotalSeat()%>" /></td>
	</tr>
	<tr>
		<td>聯絡人:</td>
		<td><input type="TEXT" name="resContact" size="20"	value="<%=restVO.getResContact()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="resPhone" size="20" value="<%=restVO.getResPhone()%>" /></td>
	</tr>
		
	<tr>
		<td>狀態:</td>
		<td>
		    <select size="1" name="resStatus">
			    <c:forEach var="resStatus" items="${restaurantStatus}">
			    <option value="${resStatus.key}">${resStatus.value}
			    </c:forEach>
		    </select></td>
		
	</tr>
    
    <tr>
		
		<td colspan="2">
		    <input type="submit" class="button" id="add" value="送出修改">
		</td>
		
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="resNo" value="<%=restVO.getResNo()%>">

</FORM>
<%@ include file="/back-end/homepage/footer.file" %>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>