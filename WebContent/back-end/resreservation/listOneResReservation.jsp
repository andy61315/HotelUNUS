<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.resreservation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ResReservationVO resrVO = (ResReservationVO) request.getAttribute("resrVO"); 
    //�w��Servlet.java(Concroller), �s�Jreq��empVO����
%>
<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<jsp:useBean id="cusSvc" scope="page" class="com.cus.model.CustomerService"/>
<%-- �]���n���o�\�U�W�٩ҥH�ݭn�\ť�u�Y
<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService"/> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>list One Reservation</title>
<%@ include file="/back-end/homepage/reshead.file"%>

<style>


h4 {
	color: red;
	display: inline;
}

</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:30px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
<script type="text/javascript">
 $(document).ready(function(){
	  <c:if test="${'update2'.equals(param.action)}">
	  swal("�w�w�ƤJ�y", "Good job!", "success");
	  </c:if>
	  <c:if test="${'cancel'.equals(param.action)}">
	  swal("�w���w����", "Good job!", "success");
	  </c:if>
	});
</script>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<%@ include file="/back-end/homepage/middle.file"%>

	<div id="lineQA" class="tableStyle">
		<h2 class="col-12 title">
			�w���޲z
			<!-- �ۤv�����Y�W�٥\��W�� -->
		</h2>
		<div class="col-12">
			
			
						<h4>�@���w��</h4>
						<h4>
							<a href="<%=request.getContextPath()%>/back-end/resreservation/select_page.jsp">�^���� /</a>
							<a href="<%=request.getContextPath()%>/back-end/restaurant/listAllRestaurant.jsp">�i�J�I�\</a>
						</h4>
					
			<table>
				<tr>
					<th>�w���s��</th>
					<th>�U��</th>
					<th>�\�U�s��</th>
					<th>�w�����</th>
					<th>�w���ɬq</th>
					<th>�H��</th>
					<th>���A</th>
<!-- 					<th>�ק�q���T</th> -->
					<th>�R��</th>
					<th>�����w��</th>

				</tr>
				<tr>
					<td><%=resrVO.getReservationNo()%></td>
					<td>${cusSvc.getOneCus(resrVO.custId).cus_Name}</td>
					<td>${restSvc.getOneRestaurant(resrVO.resNo).resName}</td>
					<td><%=resrVO.getReservationDate()%></td>
					<td>${resreservation[resrVO.resvPeriod]}</td>
					<td><%=resrVO.getResvPeople()%></td>
					<td>${resvStatus[resrVO.resvStatus]}</td>
					
<!-- 					<td> -->
<!-- 						<FORM METHOD="post"  -->
<%-- 						    ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" style="margin-bottom: 0px;"> --%>
<!-- 							<input type="submit" value="�ק�">  -->
<%-- 							<input type="hidden" name="reservationNo" value="${resrVO.reservationNo}">  --%>
<!-- 							<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 						</FORM> -->
<!-- 					</td> -->
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" style="margin-bottom: 0px;">
							<input type="submit" value="�R��"> 
							<input type="hidden" name="reservationNo" value="${resrVO.reservationNo}"> 
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" style="margin-bottom: 0px;">
							<input type="submit" value="�����w��"> 
							<input type="hidden" name="reservationNo" value="${resrVO.reservationNo}"> 
							<input type="hidden" name="action" value="cancel">
						</FORM>
					</td>

				</tr>
			</table>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>