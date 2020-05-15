<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cus.model.*"%>

<%
	CustomerService customerSvc = new CustomerService();
    List<CustomerVO> list = customerSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllCus.jsp</title>

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
	width: 1300px;
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


<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllCus.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/customer/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>信箱</th>
		<th>手機</th>
		<th>國內外</th>
		<th>身份證字號</th>
		<th>生日</th>
		<th>密碼</th>
		<th>註冊日期</th>
		<th>護照照片</th>
		<th>認證會員</th>
		<th>驗證碼</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="customerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${customerVO.cus_Id}</td>
			<td>${customerVO.cus_Name}</td>
			<td>${customerVO.cus_Email}</td>
			<td>${customerVO.cus_Cel}</td>
			<td>${country[customerVO.country]}</td>
			<td>${customerVO.id_Num}</td>
			<td>${customerVO.cus_Bir}</td>
			<td>${customerVO.cus_Password}</td>
			<td>${customerVO.reg_Date}</td>
			<td><img src="<%=request.getContextPath() %>/CusPhotoReader?cus_Id=${customerVO.cus_Id}"  width="200"height="100"></td>
			<td>${cus_Ck[customerVO.cus_Ck]}</td>
			<td>${customerVO.captcha}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/customer/cus.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cus_Id" value="${customerVO.cus_Id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			   	 <input type="hidden" name="hiddenUrl" value="backEnd"/>  
			   </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>