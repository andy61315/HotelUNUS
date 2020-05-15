<%@ page  contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bom.model.*"%>
<%@ page import="com.bod.model.*"%>
<%@ page import="com.cus.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	BookingOrderMasterService bomSvc = new BookingOrderMasterService();
	List<BookingOrderMasterVO> list = bomSvc.getAll();
	pageContext.setAttribute("list", list);//別忘了這行!	
%>

<% 
CustomerService cusSvc = new CustomerService();
pageContext.setAttribute("cusSvc",cusSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file" %> 
<style type="text/css">
.from1{
	color: #fff;
	background-color:#343232;
	border-radius:5px;
	text-align:center;
}
h4{
	border-bottom:2px solid black;
	color: #fff;
	background-color:#343232;
	}
</style>

<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle col-12" >
    			<h2  class="col-12 title" >訂房訂單管理<!-- 自己的標頭名稱功能名稱 --></h2>
    			<div class="col-12">
  <FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/BookingOrderMaster/bom.do" class="from1">
				<h6>會員身份證字號：</h6>
				<b>訂單狀態:</b>
       <select size="1" name="status" >
          <option value="0">進行中(未入住)</option>
          <option value ="1">進行中(已入住)</option>
  		  <option value ="2">訂單完成(已退房)</option>
          <option value="3">訂單取消</option>
           
       </select>
				<input type="text" name="id_Num" value="">
				  <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
				<input
					type="hidden" name="action" value="searchBomByCus">
				<input	 type="submit" value="訂單查詢">
				
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
	
		<form method="post" action="<%=request.getContextPath()%>/BookingOrderMaster/bom.do" class="from1">
			<b>輸入訂單編號(如BM-20200321-001)</b>
			<input type="text" name="b_Order_No">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</form>	

			
			<%
			if (session.getAttribute("searchBomByCus_Id_Num") != null) {
							%>
			<jsp:include page="listOneBookingOrderMaster.jsp" />
			<%
								}
							%>
			<div id="includeTable">
<c:if test="${not empty list2}">
	<div>
		<jsp:include page="/back-end/bod/showAllBookRoom.jsp"></jsp:include>
	</div>
</c:if>

<c:if test="${not empty getOrderMealDetailDisplay}">
	<div>
		<jsp:include page="/back-end/bod/showAllRoomMealOrder.jsp"></jsp:include>
	</div>
</c:if>

<c:if test="${not empty getOrderResDetailDisplay}">
	<div>
		<jsp:include page="/back-end/bod/showAllResMealOrder.jsp"></jsp:include>
	</div>
</c:if>

</div>
					
    			
    			
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員姓名</th>
		<th>會員電話</th>
		<th>訂單總價</th>
		<th>入住日期</th>
		<th>退房日期</th>
		<th>退房時間</th>
		<th>下單日期</th>
		<th>訂單狀態</th>
		<th>修改訂單</th>
		<th>查看訂房明細</th>
		<th>查看點餐明細</th>
		<th>查看餐聽明細</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="bomVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 		<c:if test="${bomVO.status != 3}">判斷只有狀態不為"取消"的才看的到 --%>
		<tr ${(bomVO.b_Order_No==param.b_Order_No) ? 'bgcolor=red':''}>
			<td>${bomVO.b_Order_No}</td>
			<td>${cusSvc.getOneCus(bomVO.cus_Id).cus_Name}</td>
			<td>${cusSvc.getOneCus(bomVO.cus_Id).cus_Cel}</td>
			<td>${bomVO.total_Price}</td>
			<td>${bomVO.start_Date}</td>
			<td>${bomVO.end_Date}</td>
			<td>${bomVO.co_Time}</td>
			<td>${bomVO.order_Date}</td>
			<td>${bomStatus[bomVO.status]}</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/BookingOrderMaster/bom.do" style="margin-bottom: 0px;">
					<input type="submit" value="修改">		
					<input type="hidden" name="b_Order_No" value="${bomVO.b_Order_No}">
					 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     	<input type="hidden" name="whichPage"	value="<%=whichPage%>">
					<input type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/bod/bod.do" style="margin-bottom: 0px;">
					<input type="submit" value="查看明細">		
					<input type="hidden" name="b_order_no" value="${bomVO.b_Order_No}">
					 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">    
					<input type="hidden" name="action" value="getBookRoomDisplay">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/BookingOrderMaster/bom.do"  style="margin-bottom: 0px;">
					<input type="submit" value="查看明細">		
					<input type="hidden" name="b_order_no" value="${bomVO.b_Order_No}">
					 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">    
					<input type="hidden" name="action" value="getOrderMealDetailDisplay">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/BookingOrderMaster/bom.do"  style="margin-bottom: 0px;">
					<input type="submit" value="查看明細">		
					<input type="hidden" name="b_order_no" value="${bomVO.b_Order_No}">
					 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">    
					<input type="hidden" name="action" value="getOrderResDetailDisplay">
				</form>
			</td>
			
		</tr>
<%-- 		</c:if> --%>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</div>
</div>


<%@ include file="/back-end/homepage/footer.file" %>