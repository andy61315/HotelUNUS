<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.diaryreport.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
DiaryReportVO diaryReportVO = (DiaryReportVO) request.getAttribute("diaryReportVO"); //EmpServlet.java(Concroller), 存入req的diaryReportVO物件
%>

<html>
<head>
<title>檢舉資料 - listOneDiaryReport.jsp</title>

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
	width: 1200px;
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
		 <h3>檢舉資料 - ListOneDiaryReport.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>檢舉編號</th>
		<th>員工編號</th>
		<th>日誌編號</th>
		<th>檢舉項目</th>
		<th>檢舉日期</th>
		<th>檢舉狀態</th>		
		
	</tr>
	<tr>
		<td><%=diaryReportVO.getDiary_report_no()%></td>
		<td><%=diaryReportVO.getCus_id()%></td>
		<td><%=diaryReportVO.getDiary_no()%></td>
		<td><%=diaryReportVO.getReport_project()%></td>
		<td><%=diaryReportVO.getReport_date()%></td>
		<td><%=diaryReportVO.getDiary_report_status()%></td>
		
	</tr>
</table>

</body>
</html>