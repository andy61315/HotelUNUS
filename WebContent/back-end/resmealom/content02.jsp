<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resmealom.model.*"%>

<%
	ResMealOrderMasterService resmomSvc = new ResMealOrderMasterService();
	List<ResMealOrderMasterVO> list = resmomSvc.getAll();
	pageContext.setAttribute("list", list);
%>
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
<title>餐廳訂單</title>
<%@ include file="/back-end/homepage/head.file"%>

<style>
  table {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
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
			餐廳訂單
			<!-- 自己的標頭名稱功能名稱 -->
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
					<th>訂單編號</th>
					<th>訂房訂單編號</th>
					<th>桌號</th>
					<th>總價</th>
					<th>訂單日期</th>
					<th>訂單狀態</th>
					<th>特殊要求</th>
					<th>修改</th>
					<th>刪除</th>

				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="resmomVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<tr>
						<td>${resmomVO.resMealOrderNo}</td>
						<td>${resmomVO.bOrderNo}</td>
						<td>${resmomVO.tableNo}</td>
						<td>${resmomVO.totalPrice}</td>
						<td>${resmomVO.orderDate}</td>
						<td>${resmomVO.orderStatus}</td>
						<td>${resmomVO.specialRequirement}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/resmealom/resmom.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="resMealOrderNo" value="${resmomVO.resMealOrderNo}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/resmealom/resmom.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden"
									name="resMealOrderNo" value="${resmomVO.resMealOrderNo}">
								<input type="hidden" name="action" value="delete">
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