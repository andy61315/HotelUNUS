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
	width:250px;
	margin-left:38%;
	background-color:#343232;
	border-radius:10px;
}
.btn{
margin-left:38%;
}
h4 {
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
		<h2 class="col-12 title">CHECK OUT</h2>
		<div class="col-12">

			
			</div>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/room/room.do" id="checkoutSearch" >
				<h6>輸入房客姓名：<input type="text" name="tenant_name" value=""></h6>
				
				<h6>輸入房客電話：<input type="text" pattern="[0-9]*" value=""></h6>
			 	<input
					type="hidden" name="action" value="checkoutRoomSearch"> <input
					type="submit" value="房間查詢">
					
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
			<%
			if (session.getAttribute("bomCheckoutList") != null) {
							%>
			<jsp:include page="/back-end/BookingOrderMaster/allBomByCus_checkOut.jsp" />
			<%
								}
			%>

		

			<% 
								if (request.getAttribute("listRoom_ByCompositeQuery") != null) { 
							%>
			<jsp:include page="/back-end/room/checkoutRoomSearch.jsp" />
			<% 
							}
							%>
							
			
			<c:if test="${checkOutalert}">
				<script>
							Swal.fire({
								icon : "info",
								html:'<h3>須繳費用</h3>'+
									'<p>${checkOutPrice}</p>'+
									'<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/room/room.do">'
										+ '<input type="hidden" name="action" value="checkoutpay" id="money">'
										+ '<input type="hidden" name="b_order_no" value="${b_order_no}">'
										+ '<input type="submit" value="確認收款" id="checkout" class="btn">',
								showConfirmButton: false
							});
				</script>
				<c:remove var="checkOutalert"/>
			</c:if>
			<c:if test="${checkout}">
				<script>
						Swal.fire({
							html: '<h3>CHECKOUT已完成:)</h3>'
						});
					
					
			</script>
				<c:remove var="checkout"/>
				<c:set var="roomlist2" value="null" scope="page"/>
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