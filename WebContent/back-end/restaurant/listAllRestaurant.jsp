<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>

<%
	RestaurantService restSvc = new RestaurantService();
	List<RestaurantVO> list = restSvc.getAll();//
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>

<style>
 h4 {
    color: red;
    display: block;
    margin-top: 200px;
  }

</style>

<style>
 table
  {
  margin-top: 30px;
  margin-left:20px;
  width:100%;
  }


  table, th, td {
    border:1px solid green;
    width: 100%;
  }
  th, td {
    width: 100px;
    padding: 5px;
    text-align: center;
    color:green;
  }
</style>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>餐廳列表</title>
<%@ include file="/back-end/homepage/reshead.file"%>

</head>
<body>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<%@ include file="/back-end/homepage/middle.file"%>

	<div id="lineQA" class="tableStyle">
		<h2 class="col-12 title">
			餐廳管理
		</h2>
		<div class="col-12">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
<table>
	<tr>
		<th>餐廳編號</th>
		<th>餐廳名稱</th>
		<th>座位</th>
		<th>電話</th>
		<th>狀態</th>
		<th>修改</th>
		<th>點餐</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="restVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${restVO.resNo}</td>
			<td>${restVO.resName}</td>
			<td>${restVO.totalSeat}</td>
			<td>${restVO.resPhone}</td>
			<td>${restaurantStatus[restVO.resStatus]}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/restaurant/rest.do" style="margin-bottom: 0px;">
			     <input type="submit" value="營業修改">
			     <input type="hidden" name="resNo"  value="${restVO.resNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/restaurant/rest.do" style="margin-bottom: 0px;">
			     <input type="submit" value="進入點餐">
			     <input type="hidden" name="resNo"  value="${restVO.resNo}">
			     <input type="hidden" name="action"	value="getOne_For_Meal"></FORM>
			
			</td>
			
		</tr>
		    
	</c:forEach>
	     <tr>
	         <td colspan="7" style="text-align:center;">
			  <a href="<%=request.getContextPath()%>/back-end/resreservation/select_page.jsp"><h4>進入訂位管理</h4></a>
			</td>
	     </tr>
	        
</table>
            
<%@ include file="page2.file" %>

		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>