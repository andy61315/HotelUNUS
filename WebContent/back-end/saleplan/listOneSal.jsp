<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.saleplan.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  SalePlanVO  salVo =(SalePlanVO)request.getAttribute("salVo");
%>
<html>
<head>
<title>訂房訂單資料 - listOneSal.jsp</title>

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
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
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

</head>
<body bgcolor='white'>
	

<table>
	
	<tr>
		<th>優惠編號</th>
		<th>優惠名稱</th>
		<th>優惠內容</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>優惠價格</th>
		<th>優惠狀態</th>
	</tr>
	<tr>
		<td><%=salVo.getSapl_no()%></td>
		<td><%=salVo.getSapl_name()%></td>
		<td><%=salVo.getDetail()%></td>
		<td><%=salVo.getStart_date()%></td>
		<td><%=salVo.getEnd_date()%></td>
		<td><%=salVo.getSapl_discount()%></td>
		<td>${saplStatus[salVo.status]}</td>
	</tr>
</table>

</body>
</html>