<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roommealordermaster.model.*" %>


<%
	int listSize = 0;
	if (request.getAttribute("listOrderMasters_ByCompositeQuery") == null) {
		RoomMealOrderMasterService roomMealOrderMasterSvc = new RoomMealOrderMasterService();
		List<RoomMealOrderMasterVO> list = roomMealOrderMasterSvc.getAll();
		listSize = list.size();
		pageContext.setAttribute("list", list);
	} else {
		List<RoomMealOrderMasterVO> list = (List<RoomMealOrderMasterVO>) request.getAttribute("listOrderMasters_ByCompositeQuery");
		listSize = list.size();
		pageContext.setAttribute("list", list);
	}
	

	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>ORDER PAGE</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />



<%@ include file="/back-end/homepage/head.file" %> 

</head>
<body>
<!-- <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    				<h2 class="col-12 title" >訂餐訂單管理<!-- 自己的標頭名稱功能名稱 --></h2>
    				<hr>
    				
						<!-- SEARCH BAR -->    				
	    			    <div class="row">
<!-- 					    	<div class="col-md-3"> -->
<!-- 					    		<div class="card"> -->
<!-- 					    			<div class="card-body"> -->
<!-- 					    				<div class="row"> -->
<!-- 					    					<div class="col-md-12"> -->
<!-- 					    						<div class="form-group "> -->
<!-- 					    							<button type="button" class="btn btn-primary form-control" title="Add Member" id='btnAdd'>新增訂單</button> -->
<!-- 					    						</div> -->
<!-- 					    					</div> -->
<!-- 					    				</div> -->
<!-- 					    			</div> -->
<!-- 					    		</div> -->
<!-- 					    	</div> -->
					        <div class="col-md-12">
					            <div class="card">
					                <div class="card-body">
					                	<form action="<%=request.getContextPath()%>/order/order.do" method="post">
					                    <div class="row">
							                <div class="col-md-3">
							                    <div class="form-group ">
							               	  		<input type="text" name="room_meal_order_no" placeholder="請輸入訂餐訂單編號..." id="inputState" class="form-control">
							                    </div>
							                </div>
							                <div class="col-md-3">
							                    <div class="form-group ">
							                    	<input type="text" name="b_order_no" placeholder="請輸入訂房訂單編號..." id="inputState" class="form-control">
							                    </div>
							                </div>
							                <div class="col-md-3">
							               		 <div class="form-group ">
													<select size="1" name="ro_order_status" id="inputState" class="form-control">
														<option value="">選擇訂單狀態</option>
														<option value="0">備餐中</option>
														<option value="1">出餐中</option>
														<option value="2">已出餐</option>
														<option value="3">已完成</option>
														<option value="4">訂餐取消</option>
													</select>
							                	</div>
							                </div>
							                <div class="col-md-3">
							               	 	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
												<input type="hidden" name="action" value="listOrderMasters_ByCompositeQuery">
												<input type="submit" value="搜尋" id="searchOrderMaster" class="btn btn-primary form-control">
							                </div>
					            		</div>
					            		</form>
					                </div>
					            </div>
					        </div>
   						 </div>
   						 <!-- SEARCH BAR -->
   						 
   						 <hr>
   						 
   						 <!-- ORDERMASTER -->
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
										<th scope="col">修改</th>
									</tr>
								</thead>
								<tbody>
								<%@ include file="/back-end/order/page1.file" %>
								<c:forEach var="roomMealOrderMasterVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
										<td>
											<c:choose>
												<c:when test="${roomMealOrderMasterVO.ro_order_status == 0}">
													備餐中
												</c:when>
												<c:when test="${roomMealOrderMasterVO.ro_order_status == 1}">
													出餐中
												</c:when>
												<c:when test="${roomMealOrderMasterVO.ro_order_status == 2}">
													已出餐
												</c:when>
												<c:when test="${roomMealOrderMasterVO.ro_order_status == 3}">
													已完成
												</c:when>
												<c:when test="${roomMealOrderMasterVO.ro_order_status == 4}">
													訂餐取消
												</c:when>
											</c:choose>
										</td>
										<td>${roomMealOrderMasterVO.special_requirement}</td>
										<td>
											<c:choose>
												<c:when test="${roomMealOrderMasterVO.ro_order_status == 0}">
												<form method="post" action="<%=request.getContextPath()%>/order/order.do" >
													<input type="hidden" name="ro_order_status" value="1">
													<input type="hidden" name="room_meal_order_no" class="room_meal_order_no" value="${roomMealOrderMasterVO.room_meal_order_no}">
													<input type="hidden" name="action" value="change2Cooked">
													<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
													<button type="submit" class="btn btn-primary change-to-shipping-btn">餐點完成</button>
												</form>
												</c:when>
												<c:otherwise>
												<form method="post" action="<%=request.getContextPath()%>/order/order.do" >
												    <select class="form-control" name="ro_order_status">
												      <option value="0" ${(0==roomMealOrderMasterVO.ro_order_status)?'selected':'' }>備餐中</option>
												      <option value="1" ${(1==roomMealOrderMasterVO.ro_order_status)?'selected':'' }>出餐中</option>
												      <option value="2" ${(2==roomMealOrderMasterVO.ro_order_status)?'selected':'' }>已出餐</option>
												      <option value="3" ${(3==roomMealOrderMasterVO.ro_order_status)?'selected':'' }>已完成</option>
												      <option value="4" ${(4==roomMealOrderMasterVO.ro_order_status)?'selected':'' }>訂單取消</option>
												    </select>
													<input type="hidden" name="room_meal_order_no" class="room_meal_order_no" value="${roomMealOrderMasterVO.room_meal_order_no}">
													<input type="hidden" name="action" value="changeOMStatus">
													<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
													<button type="submit" class="btn btn-primary change-btn">修改狀態</button>
												</form>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
   						 </div>
   						 <!-- ORDERMASTER -->
 							<div class="row">
								<div class="col-md-6 offset-md-3">
									<%@ include file="/back-end/order/page2.file" %>
								</div>
							</div>
   						 
				</div>
				
				<!-- ORDERDETAIL -->
				<c:if test="${openModal!=null}">
				
					<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					                <h3 class="modal-title" id="myModalLabel">訂餐訂單詳細</h3>
					            </div>
								<div class="modal-body">
					<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
					               <jsp:include page="listOrderDetailFromOrderMaster.jsp" />
					<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
								</div>
								<div class="modal-footer">
					                <button type="button" class="btn btn-dark" data-dismiss="modal">返回</button>
					            </div>
							</div>
						</div>
					</div>
			        <script>
			    		 $("#basicModal").modal({show: true});
					</script>
				</c:if>
				<!-- ORDERDETAIL -->
				
<%-- <%@ include file="/back-end/homepage/footer.file" %> --%>


</body>
</html>