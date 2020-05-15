<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bom.model.*"%>
<%@ page import="com.cus.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<jsp:useBean id="searchBomByCus_Id_Num" scope="session"
	type="java.util.List<BookingOrderMasterVO>" />
<%-- <jsp:useBean id="cusVO" scope="page" --%>
<%-- 	type="CustomerService" /> --%>
<% 
CustomerService cusSvc = new CustomerService();
pageContext.setAttribute("cusSvc",cusSvc);
%>
<style>

#bomVoTable{
	margin-top:10px; 
 margin-left:20px; 
}
h4{
	margin-top:10px; 
	border-bottom:2px solid black;
}
</style>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	
</c:if>
<h4>今日入住訂單</h4>
<table id="bomVoTable">
	<tr>
		<th>訂單編號</th>
		<th>會員姓名</th>
		<th>會員電話</th>
		<th>訂單總價</th>
		<th>入住日期</th>
		<th>退房日期</th>
		<th>退房時間</th>
		<th>下單日期</th>
		<th>訂單狀態</th>
		<th>搜尋房間</th>
	</tr>

	<c:forEach var="bomVO" items="${searchBomByCus_Id_Num}">
		<c:if test="${bomVO.status != 3}">
			<%--判斷只有狀態不為"取消"的才看的到 --%>
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
				<td>
					<form METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/room/room.do"
						style="margin-bottom: 0px;">
						<input type="hidden" name="b_order_no" value="${bomVO.b_Order_No}">
						<input type="hidden" name="action" value="getBookRoomDisplay">
						<input type="submit" value="送出"
							style="background-color: lightblue" id="SearchRoom">
					</form>
				</td>
			</tr>
		</c:if>
	</c:forEach>


</table>
