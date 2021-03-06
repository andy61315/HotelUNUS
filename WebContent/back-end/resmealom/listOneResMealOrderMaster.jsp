<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.resmealom.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    ResMealOrderMasterVO resmomVO = (ResMealOrderMasterVO) request.getAttribute("resmomVO"); 
    //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂單資料 - listOne.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訂單資料 - ListOne.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>訂房編號</th>
		<th>桌號</th>
		<th>總價</th>
		<th>訂單日期</th>
		<th>訂單狀態</th>
		<th>特殊要求</th>
	</tr>
	<tr>
		<td><%=resmomVO.getResMealOrderNo()%></td>
		<td><%=resmomVO.getbOrderNo()%></td>
		<td><%=resmomVO.getTableNo()%></td>
		<td><%=resmomVO.getTotalPrice()%></td>
		<td><%=resmomVO.getOrderDate()%></td>
		<td><%=resmomVO.getOrderStatus()%></td>
		<td><%=resmomVO.getSpecialRequirement()%></td>
	</tr>
</table>

</body>
</html>