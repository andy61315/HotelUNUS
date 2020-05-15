<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resmealom.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<jsp:useBean id="getOrderResDetailDisplay" scope="request" type="java.util.List<ResMealOrderMasterVO>" />



<style>
#bodVoTable{
	margin-top:80px;
}
</style>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="bodVoTable">
	<tr>
<!-- 		<th>訂單編號</th> -->
		<th>餐廳用餐訂單編號</th>
		<th>訂房訂單編號</th>
		<th>桌號</th>
		<th>總價</th>
		<th>訂單日期</th>
		<th>訂單狀態</th>
		<th>特殊要求</th>
		
<!-- 		<th>修改</th> -->
		
		
	</tr>
	

	<c:forEach var="resOrder" items="${getOrderResDetailDisplay}" >
	
		
		<tr>
<%-- 			<td>${bodVo.b_order_no}</td> --%>
			<td>${resOrder.resMealOrderNo}</td>
			<td>${resOrder.bOrderNo}</td>
			<td>${resOrder.tableNo}</td>
			<td>${resOrder.totalPrice}</td> 
			<td>${resOrder.orderDate}</td>
			<td>${resMOMStatus[resOrder.orderStatus]}</td>
			<td>${resOrder.specialRequirement}</td> 
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/bod/bod.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="b_order_no"  value="${bodVo.b_order_no}"> --%>
<%-- 			     <input type="hidden" name="room_type_no"  value="${bodVo.room_type_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			
		</tr>
	</c:forEach>
</table>

