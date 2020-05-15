<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.cus.model.*"%>
<%
  RoomVO roomVo = (RoomVO) request.getAttribute("getCusFromTable"); 
  CustomerService cusSvc = new CustomerService();
  CustomerVO cusVo = cusSvc.getOneCus(roomVo.getCus_id());
 
%>
<style>
h4{
color:#E2943B;
}
#search{
height:2rem;
vertical-align:bottom;
}
</style>
<c:if test="${not empty errorMsgs}">
	<h4>旅客資訊:</h4><br>
		<c:forEach var="message" items="${errorMsgs}">
			<h6 style="color:red">${message}</h6><br>
		</c:forEach>
		
	
	<form action="room.do" method="post">
	<h6>會員身份證字號：</h6>
　	<input type="text" name="id_Num" value="" id="search">
	<input type="hidden" name="action" value="search">
	<input type="hidden" name="room_id" value="<%=roomVo.getRoom_id()%>">
	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
	<input type="submit" class="btn btn-outline-info" value="查詢">
	<br/>
	<hr/>
　		 
</form>
	
</c:if>
	
<%-- 	<%if (request.getAttribute("getCusFromTable")!=null){%> --%>
<%--        <jsp:include page="roomCusBySearch.jsp" /> --%>
<%-- 	<%} %> --%>


