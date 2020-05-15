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


<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Examples</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">
<style>

  table{
    color: black;
    border:1px solid black;
  }
  table tr th{
    border:1px solid black;
  }
   table tr td{
    border:1px solid black;
  }
</style>
</head>
<body>
   
	<button id="demo6">Demo 6</button>
	<div></div>
        <script>
       	document.getElementById("demo6").addEventListener("click",function(){
  		swal({
  		icon: "success",
		});
  		
	});
        </script>

		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<table>
	<tr>
		<th>優惠編號</th>
		<th>優惠名稱</th>
		<th>優惠內容</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>優惠價格</th>
		<th>優惠狀態</th>
		<th>修改狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="salVo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${salVo.sapl_no}</td>
			<td>${salVo.sapl_name}</td>
			<td>${salVo.detail}</td>
			<td>${salVo.start_date}</td>
			<td>${salVo.end_date}</td> 
			<td>${salVo.sapl_discount}</td> 
			<td>${salVo.status}</td> 
		
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/saleplan/saleplan.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="sapl_no"  value="${salVo.sapl_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
  
</body>
</html>

