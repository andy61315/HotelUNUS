<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roommealordermaster.model.*" %>

<%
	if (request.getAttribute("listOrderMasters_ByCompositeQuery") == null) {
		RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
		List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll();
		pageContext.setAttribute("list", list);
	} else {
		List<RoomMealOrderMasterVO> list = (List<RoomMealOrderMasterVO>) request.getAttribute("listOrderMasters_ByCompositeQuery");
		pageContext.setAttribute("list", list);
	}
		
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- jquery&popper.js's CDN -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<!-- bootstrap's CDN Version-4.4.1 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>



</head>
<body>
	---${param.room_meal_order_no}---
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="container">
		
		<div class="row">
			<table class="table table-striped table-hover">
				<thead class="table-primary">
					<tr>
						<th scope="col">訂餐訂單編號</th>
						<th scope="col">訂房訂單編號</th>
						<th scope="col">房號	</th>
						<th scope="col">員工編號	</th>
						<th scope="col">總價</th>
						<th scope="col">訂單日期	</th>
						<th scope="col">訂餐狀態</th>
						<th scope="col">客製化要求</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="roomMealOrderMasterVO" items="${list}" >
					<tr>
						<td>
							<a href="<%= request.getContextPath() %>
							/order/order.do?room_meal_order_no=${roomMealOrderMasterVO.room_meal_order_no}&
							action=getOrderDetailFromOrderMaster&requestURL=<%=request.getServletPath()%>">
								${roomMealOrderMasterVO.room_meal_order_no}
							</a>
						</td>
						<td>${roomMealOrderMasterVO.b_order_no}</td>
						<td>${roomMealOrderMasterVO.room_no}</td>
						<td>${roomMealOrderMasterVO.emp_id}</td>
						<td>${roomMealOrderMasterVO.total_price}</td>
						<td>${roomMealOrderMasterVO.order_date}</td>
						<td>${roomMealOrderMasterVO.ro_order_status}</td>
						<td>${roomMealOrderMasterVO.special_requirement}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>

<!-- bootstrap 燈箱 -->
<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOrderDetailFromOrderMaster.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
		</script>
 </c:if>


</body>
</html>