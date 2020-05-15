<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resmealom.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<jsp:useBean id="getOrderResDetailDisplay" scope="request" type="java.util.List<ResMealOrderMasterVO>" />



<style>
#bodVoTable{
	margin-top:80px;
}
</style>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="bodVoTable">
	<tr>
<!-- 		<th>�q��s��</th> -->
		<th>�\�U���\�q��s��</th>
		<th>�q�Эq��s��</th>
		<th>�ู</th>
		<th>�`��</th>
		<th>�q����</th>
		<th>�q�檬�A</th>
		<th>�S��n�D</th>
		
<!-- 		<th>�ק�</th> -->
		
		
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
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="b_order_no"  value="${bodVo.b_order_no}"> --%>
<%-- 			     <input type="hidden" name="room_type_no"  value="${bodVo.room_type_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			
		</tr>
	</c:forEach>
</table>

