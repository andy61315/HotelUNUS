<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bod.model.*"%>
<%@ page import="com.roomtype.model.*"%>

<%-- <jsp:useBean id="list2" scope="request" type="java.util.List<BookOrderDetailVO>" /> --%>
<%List<BookOrderDetailVO> list2 =(List<BookOrderDetailVO>)request.getAttribute("list2") ;
RoomTypeService roomTypeSvc = new RoomTypeService();
List<RoomTypeVO> roomTypelist = roomTypeSvc.getAll();
pageContext.setAttribute("roomTypeSvc",roomTypeSvc);

%>
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
		<th>房型編號</th>
		<th>數量</th>
		<th>加房人數</th>
		<th>金額</th>
<!-- 		<th>修改</th> -->
		
		
	</tr>
	

	<c:forEach var="bodVo" items="${list2}" >
	
		
		<tr>
<%-- 			<td>${bodVo.b_order_no}</td> --%>
		<td>${roomTypeSvc.findOneByNo(bodVo.room_type_no).room_Type_Name}</td>
			<td>${bodVo.quantity}</td>
			<td>${bodVo.total_add_people}</td>
			<td>${bodVo.price}</td> 
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
