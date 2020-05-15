<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.cus.model.*"%>

<%
  RoomVO roomVo = (RoomVO) request.getAttribute("getCusFrommTable"); 
  CustomerService cusSvc = new CustomerService();
  CustomerVO cusVo = cusSvc.getOneCus(roomVo.getCus_id());
 
%>


<style>
.input-group input{
	width:10px;
	}
</style>
</head>
<body bgcolor='white'>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">房客資訊:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	
	<h4>會員身分證 :</h4>
	<p>
	<%=cusVo.getId_Num()%>
	</p>

	<h4>會員姓名:</h4>
	<p>
	<%=cusVo.getCus_Name()%>
	</p>
	
	<h4>會員電話 :</h4>
	<p>
	<%=cusVo.getCus_Cel()%>
	</p>
