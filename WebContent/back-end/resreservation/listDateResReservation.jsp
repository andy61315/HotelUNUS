<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resreservation.model.*"%>

<%
	List<ResReservationVO> list = (List<ResReservationVO>) request.getAttribute("list");

	if (list != null) {//�Ĥ@�����δ������Nlist��iattribute
		session.setAttribute("rlist", list);
	} else {
		list = (List<ResReservationVO>) session.getAttribute("rlist");//�Y�������A�h�L�k�qrequest���ȡA��qsession��
	}
%>

<!DOCTYPE html>
<html>
<head>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table#table-1 {
	width: 800px;
	margin-top: 50px;
	margin-bottom: 5px;
  }
  
   table#table-2 {
	width: 1000px;
	background-color: white;
	opacity:0.9;
	margin-top: 30px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
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
<title>date reservation</title>
<%@ include file="/back-end/homepage/reshead.file"%>

<!-- JS -->
<script type="text/javascript">
 $(document).ready(function(){
	  <c:if test="${'insert'.equals(param.action)}">
	  swal("�w���w�s�W", "Good job!", "success");
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
			�w������
		</h2>
		<div class="col-12">
			
						<h3>�C��w�����</h3>
						<h4>
							<a href="select_page.jsp">�^����</a>
						</h4>
					
			
			<c:if test="${not empty errorMsgs}">
<!-- 				<font style="color: red">�Эץ��H�U���~:</font> -->
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<table id="table-2">
				<tr>
					<th>�w���s��</th>
					<th>�U��</th>
					<th>�\�U�s��</th>
					<th>�w�����</th>
					<th>�ɬq</th>
					<th>�H��</th>
					<th>���A</th>
					<th>�ק�</th>
					<th>�w�J�y</th>
					<th>�����w��</th>
				</tr>
				<%@ include file="page1.file"%>
				<%
// 				System.out.println("list.size() = " + list.size());
// 				System.out.println("pageIndex = " + pageIndex);
// 				System.out.println("pageIndex+rowsPerPage-1 = " + (pageIndex+rowsPerPage-1));
				%>
				
				<c:forEach var="resrVO" items="${rlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<jsp:useBean id="cusSvc" scope="page" class="com.cus.model.CustomerService"/>
					<tr>
						<td>${resrVO.reservationNo}</td>
						<td>${cusSvc.getOneCus(resrVO.custId).cus_Name}</td>
						<td>${restSvc.getOneRestaurant(resrVO.resNo).resName}</td>
						<td>${resrVO.reservationDate}</td>
						<td>${resreservation[resrVO.resvPeriod]}</td>
						<td>${resrVO.resvPeople}</td>
						<td>${resvStatus[resrVO.resvStatus]}</td>

						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" 
							style="margin-bottom: 0px;">
								<input type="submit" value="�ק�q��"> <input type="hidden"
									name="reservationNo" value="${resrVO.reservationNo}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="�w�J�y"> <input type="hidden"
									name="reservationNo" value="${resrVO.reservationNo}"> <input
									type="hidden" name="action" value="update2">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="�����w��"> <input type="hidden"
									name="reservationNo" value="${resrVO.reservationNo}"> <input
									type="hidden" name="action" value="cancel">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>