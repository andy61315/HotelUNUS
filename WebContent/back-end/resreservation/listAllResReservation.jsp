<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resreservation.model.*"%>

<%
	ResReservationService resrSvc = new ResReservationService();
	List<ResReservationVO> list = resrSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>

<!DOCTYPE html>
<html>
<head>
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
<title>所有預約資料</title>
<%@ include file="/back-end/homepage/reshead.file"%>

<style>
table#table-1 {
/*     width: 1200px; */
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

<style>
table {
 	 
	background-color: white;
	opacity:0.9;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:30px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
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
			訂位預約管理			
		</h2>
		<div class="col-12">
			
						<h3>預約詳情列表</h3>
						<h4>
							<a href="select_page.jsp">回首頁</a>
						</h4>
			
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
					<th>預約編號</th>
					<th>顧客編號</th>
					<th>餐廳編號</th>
					<th>預約日期</th>
					<th>時段</th>
					<th>人數</th>
					<th>狀態</th>
					<th>訂位修改</th>
					<th>刪除</th>
					<th>取消預約</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="resrVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

<jsp:useBean id="cusSvc" scope="page" class="com.cus.model.CustomerService"/>
					<tr>
						<td>${resrVO.reservationNo}</td>
						<td>${cusSvc.getOneCus(resrVO.custId).cus_Name}</td>
						<td>${restSvc.getOneRestaurant(resrVO.resNo).resName}</td>
						<td>${resrVO.reservationDate}</td>
						<td>${resreservation[resrVO.resvPeriod]}</td>
						<td>${resrVO.resvPeople}</td>
						<td>${resvStatus[resrVO.resvStatus]}</td>

						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="reservationNo" value="${resrVO.reservationNo}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden"
									name="reservationNo" value="${resrVO.reservationNo}"> <input
									type="hidden" name="action" value="delete">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="取消預約"> <input type="hidden"
									name="reservationNo" value="${resrVO.reservationNo}"> <input
									type="hidden" name="action" value="cancel">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>