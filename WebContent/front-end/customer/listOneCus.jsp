<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cus.model.*"%>

<%-- <% --%>
<!-- //   CustomerVO customerVO = (CustomerVO) session.getAttribute("customerVO"); //EmpServlet.java(Concroller), 存入req的empVO物件 -->
<%-- %> --%>

<html>
<head>
<title>會員資料</title>
   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-table.min.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   
    
<style>
  table#table-1 {
	background-color: none;
    text-align: center;
	margin-top:10px;
    margin-left:550px;
  }
  table#table-1 h4 {
    color: red;
    display: block;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table#table-2 {
	width: 40%;
	background-color: none;
	margin-top: 5px;
	margin-bottom: 50px;
	margin-left:380px;
  }
   table#table-2,th,td{
    border: 1px solid black;
  }
   th#tr-1,td#td-1{
    border: none;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
    th#tr-2, td#td-2 {
    padding: 5px;
    text-align: left;
  }
    td#td-2 {
    width: 100px;
    text-align: left;
    font-weight:bold;
    text-align:justify;

  }
    th#th-3, td#td-3 {
    text-align: right;
    border-right:none;
    borger-buttom:none;
    border-left:none;
  }
  .button {
  width: 50px !important;
  position:absolute;
  right:330px;
  top:510px;
  display: inline-block;
  padding: 6px 8px;
  font-size: 15px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 1px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>

</head>
<body bgcolor='white'>
        <%@ include file="/front-end/indexFile/header.file" %>   

<table id="table-1">
	<tr id="tr-1"><td id="td-1">
		 <h2>會員資料</h2>
	</td></tr>
</table>

<table id="table-2">
	<tr id="tr-2"><td id="td-2">會員編號</td>
	<td>${customerVO.cus_Id}</td></tr>
	<tr id="tr-2"><td id="td-2">會員姓名</td>
	<td>${customerVO.cus_Name}</td></tr>
	<tr id="tr-2"><td id="td-2">信箱</td>
	<td>${customerVO.cus_Email}</td></tr>
	<tr id="tr-2"><td id="td-2">手機</td>
	<td>${customerVO.cus_Cel}</td></tr>
	<tr id="tr-2"><td id="td-2">國內 / 外</td>
	<td>${country[customerVO.country]}</td></tr>
	<tr id="tr-2"><td id="td-2">生日</td>
	<td>${customerVO.cus_Bir}</td></tr>
	<tr id="tr-2"><td id="td-2">護照照片</td>
	<td><img src="<%=request.getContextPath() %>/CusPhotoReader?cus_Id=${customerVO.cus_Id}"  width="200"height="100"></td></tr>
	<tr id="tr-2"><td id="td-2">認證會員</td>
	<td>${cus_Ck[customerVO.cus_Ck]}</td></tr>
	</table>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" style="margin-bottom: 0px;">
	
	<input class="button" type="submit" value="修改">
	
	<input type="hidden" name="cus_Id" value="${customerVO.cus_Id}">
	<input type="hidden" name="action"	value="getOne_For_Update">
	<input type="hidden" name="hiddenUrl" value="frontEnd"/>  
	</FORM> 
	
	
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker --> 
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap-table.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>         
		<%@ include file="/front-end/indexFile/footer.file" %>   
    
</body>
</html>