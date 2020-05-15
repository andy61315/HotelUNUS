<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.saleplan.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    SalePlanService SalSvc = new SalePlanService();
    List<SalePlanVO> list = SalSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
  
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--引用SweetAlert2.js-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<%@ include file="/back-end/homepage/head.file" %> 
<style type="text/css">
table{
	margin-top:10px;
}

</style>
<script type="text/javascript">

$("#Pagination").pagination(56, {
	num_edge_entries: 2,
	num_display_entries: 4,
	callback: pageselectCallback,
	items_per_page:1
	});
</script>
</head>
<body onload="connect();" onunload="disconnect();">
<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >優惠專案<!-- 自己的標頭名稱功能名稱 --></h2>
    			<div class="col-12">

<table class="pagination">
	<tr>
		<th>優惠編號</th>
		<th>優惠名稱</th>
		<th>優惠內容</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>優惠價格</th>
		<th>優惠狀態</th>
		<th>修改</th>
		<th>新增</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<%@ include file="page1.file"%>
	<c:forEach var="salVo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr${(salVo.sapl_no==param.sapl_no) ? 'bgcolor=red':''}>
			<td>${salVo.sapl_no}</td>
			<td>${salVo.sapl_name}</td>
			<td>${salVo.detail}</td>
			<td>${salVo.start_date}</td>
			<td>${salVo.end_date}</td> 
			<td>${salVo.sapl_discount}</td> 
			<td>${saplStatus[salVo.status]}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/saleplan/saleplan.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="sapl_no"  value="${salVo.sapl_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			<button id="addSal" ><a href="<%=request.getContextPath()%>/back-end/saleplan/addSal.jsp">新增</a></button>
			</td>
		</tr>
	</c:forEach>
	
</table>
<jsp:include page="listAllOnSal.jsp" />
    
<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
//  var endPointURL = "ws://localhost:8081/DA106G1/MyEchoServer";
	
   
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket 成功連線");
		};

		webSocket.onmessage = function(event) {
			updateStatus("商品推播中");
			sendMessage();
	        
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");	
		};

	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	function sendMessage() {	
		   alert("新優惠出爐");
	}
</script>
<%@ include file="page2.file" %>
</div>
</div>
<%@ include file="/back-end/homepage/footer.file" %>
</body>

</html>