<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.bom.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cus.model.*"%>

<jsp:useBean id="searchBomByCus_Id_Num" scope="session"
	type="java.util.List<BookingOrderMasterVO>" />
<% 
CustomerService cusSvc = new CustomerService();
pageContext.setAttribute("cusSvc",cusSvc);
%>

<table>
	<tr><td>
		 <h3>訂單資料</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員姓名</th>
		<th>會員電話</th>
		<th>總價</th>
		<th>開始日期</th>
		<th>退房日期</th>
		<th>退房時間</th>
		<th>下定時間</th>
		<th>訂單狀態</th>
		
	</tr>
	<c:forEach var="bomVO" items="${searchBomByCus_Id_Num}">
	<tr>
		<td>${bomVO.b_Order_No}</td>
				<td>${cusSvc.getOneCus(bomVO.cus_Id).cus_Name}</td>
				<td>${cusSvc.getOneCus(bomVO.cus_Id).cus_Cel}</td>
				<td>${bomVO.total_Price}</td>
				<td>${bomVO.start_Date}</td>
				<td>${bomVO.end_Date}</td>
				<td>${bomVO.co_Time}</td>
				<td>${bomVO.order_Date}</td>
				<td>${bomStatus[bomVO.status]}</td>
	</tr>
	</c:forEach>
</table>
