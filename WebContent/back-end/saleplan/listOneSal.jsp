<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.saleplan.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  SalePlanVO  salVo =(SalePlanVO)request.getAttribute("salVo");
%>
<html>
<head>
<title>�q�Эq���� - listOneSal.jsp</title>

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
		<th>�u�f�s��</th>
		<th>�u�f�W��</th>
		<th>�u�f���e</th>
		<th>�}�l���</th>
		<th>�������</th>
		<th>�u�f����</th>
		<th>�u�f���A</th>
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