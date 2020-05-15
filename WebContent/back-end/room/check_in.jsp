<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file"%>
<style>
h6 {
	color: #fff;
	width:200px;
	height:20px;
	margin-left:45%;
	background-color:#343232;
}
h4 {
border-bottom:2px solid black;
	color: #fff;
	background-color:#343232;
}
</style>
</head>
<body>

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<%@ include file="/back-end/homepage/middle.file"%>


	<div id="OnSiteBooking" class="tableStyle">
		<h2 class="col-12 title">CHECK IN</h2>
		<div class="col-12">

			<jsp:useBean id="roomSvc" scope="page"
				class="com.room.model.RoomService" />
			<div id="checkinSearch">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/BookingOrderMaster/bom.do" >
				<h6>請輸入會員身份證字號：</h6>
				<input type="text" name="id_Num" value=""><input
					type="hidden" name="action" value="searchBomByCus_Id_Num">
					  <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
				<input type="submit" value="訂單查詢" id="searchBomByCheckIn">
				
				<c:if test="${not empty errorMsgs}">
					<script>
								Swal.fire({
										icon : "question",
										html:
										'<c:forEach var="message" items="${errorMsgs}">'+
										'<b style="color:red">${message}</b>'+
										'</c:forEach>'
										
									});
					</script>
					
	    			
				
				</c:if>
				
				
	
			</FORM>
				
			</div>
			
			<%
			if (session.getAttribute("searchBomByCus_Id_Num") != null) {
							%>
			<jsp:include page="/back-end/BookingOrderMaster/allBomByCus.jsp" />
			<%
								}
							%>
			
		
			<%
			if (request.getAttribute("roomlist2") != null) {
							%>
			<jsp:include page="/back-end/room/allRoomTable.jsp" />
			<%}%>


				<c:if test="${sweetalert}">
				<script>
								Swal.fire({
										icon : "success",
										text:"已成功CHECKIN"
										
									});
				</script>
				<c:remove var="sweetalert"/>
			</c:if>


			
		
<script>
$(function(){


	
	
});
</script>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>