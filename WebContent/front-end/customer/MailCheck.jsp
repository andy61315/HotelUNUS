<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>

<%
	CustomerVO customerVO = (CustomerVO) session.getAttribute("customerVO");//sign in session
%>
  
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
		<script language="JavaScript">
		window.alert('${message}');
		</script>
		</c:forEach>
	</ul>
</c:if>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員驗證</title>
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
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/jquery.datetimepicker.css" />
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
    .button {
  position:absolute;
  right:280px;
  top:275px;
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
  .button1 {
  position:absolute;
  right:370px;
  top:275px;
  display: inline-block;
  padding: 6px 8px;
  font-size: 15px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: BROWN;
  border: none;
  border-radius: 15px;
  box-shadow: 0 1px #999;
}

.button1:hover {background-color: #947D6B}

.button1:active {
  background-color: #413C2A;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
  table#table-2 {
	width: 45%;
	background-color: none;
	margin-top: 5px;
	margin-bottom: 50px;
	margin-left:350px;
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
</style>

</head>
<body bgcolor='white'>
        <%@ include file="/front-end/indexFile/header.file" %>   

<table id="table-1">
	<tr id="tr-1"><td id="td-1">
	<h3>會員驗證</h3>
	</td></tr>
</table>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do">
<table id="table-2">
	<tr id="tr-2"><td id="td-2">會員編號:<font color=red><b>*</b></font></td>
		<td id="td-2"><%=customerVO.getCus_Id()%></td>
	</tr>
	<tr id="tr-2"><td id="td-2">信箱:</td>
		<td id="td-2"><%=customerVO.getCus_Email()%></td>
	</tr>
	<tr id="tr-2"><td id="td-2">驗證碼:</td>
		<td id="td-2"><input type="Text" name="captcha" size="45" value="請輸入驗證碼" ></td>
	</tr>

</table>

	<input type="hidden" name="action" value="checkCaptcha">
	<input type="hidden" name="cus_Id" value="<%=customerVO.getCus_Id()%>">
	<input class="button"type="submit" value="送出驗證">
	</FORM>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" >
       <input type="hidden" name="action" value="mailSend">
       <input type="hidden" name="cus_Id" value="<%=customerVO.getCus_Id()%>">
       <input class="button1"type="submit" value="發送驗證信">
    </FORM>
</body>



		<%@ include file="/front-end/indexFile/footer.file" %>   
<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

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

</script>
</html>