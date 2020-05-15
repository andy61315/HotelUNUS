<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roommealordermaster.model.*"%>


<jsp:useBean id="getOrderMealDetailDisplay" scope="request" type="java.util.List<RoomMealOrderMasterVO>" />

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
		<th>�ȩЭq�\�q��s��</th>
		<th>�q�Эq��s��</th>
		<th>�и�</th>
		<th>���u�s��</th>
		<th>�`��</th>
		<th>�q����</th>
		<th>�q�\���A</th>
		<th>�S��n�D</th>
<!-- 		<th>�ק�</th> -->
		
		
	</tr>


	<c:forEach var="roomMealVO" items="${getOrderMealDetailDisplay}" >
	
		
		<tr>
<%-- 			<td>${bodVo.b_order_no}</td> --%>
			<td>${roomMealVO.room_meal_order_no}</td>
			<td>${roomMealVO.b_order_no}</td>
			<td>${roomMealVO.room_no}</td>
			<td>${roomMealVO.emp_id}</td> 
			<td>${roomMealVO.total_price}</td> 
			<td>${roomMealVO.order_date}</td> 
			<td>${ro_order_status[roomMealVO.ro_order_status]}</td> 
			<td>${roomMealVO.special_requirement}</td> 
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
