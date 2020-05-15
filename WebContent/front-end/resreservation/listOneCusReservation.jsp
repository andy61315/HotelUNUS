<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.resreservation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    ResReservationVO resrVO = (ResReservationVO) request.getAttribute("resrVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService" />

<!DOCTYPE html>
<html lang="en">
<head>

<style>
  table#table-1 {
	margin-left:300px;
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
	width: 800px;
	margin-left:300px;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid green;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Journey HTML CSS Template</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css"> 
     <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">  
    
	<!-- jquery-3.4.1.slim.min -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
 <script>
   $(document).ready(function(){
	  <c:if test="${'cancelfront'.equals(param.action)}">
	  swal("預約已取消", "Good job!", "success");
	  </c:if>
	});
 </script>                               <!-- Templatemo style -->
</head>

<body>
        <%@ include file="/front-end/indexFile/header.file" %>   
                
   <table id="table-1">
	<tr><td>
		 <h3>預約詳情 </h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/resreservation/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<div id="wrapper">
<table>
	<tr>
		<th>預約編號</th>
<!-- 		<th>顧客編號</th> -->
		<th>餐廳</th>
		<th>預約日期</th>
		<th>預約時段</th>
		<th>人數</th>
		<th>預約狀態</th>
	</tr>
	<tr>
		<td><%=resrVO.getReservationNo()%></td>
<%-- 		<td><%=resrVO.getCustId()%></td> --%>
		 <td>${restSvc.getOneRestaurant(resrVO.resNo).resName}</td>
		<td><%=resrVO.getReservationDate()%></td>
		<td>${resreservation[resrVO.resvPeriod]}</td>
		<td><%=resrVO.getResvPeople()%></td>
		<td>${resvStatus[resrVO.resvStatus]}</td> 
                
</tr>
</table>                
</div>                
                
                
                
                
           
		<%@ include file="/front-end/indexFile/footer.file" %>   
    <!-- load JS files -->
<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker --> 
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script> 
        $(function(){

            // Change top navbar on scroll
            $(window).on("scroll", function() {
                if($(window).scrollTop() > 100) {
                    $(".tm-top-bar").addClass("active");
                } else {                    
                 $(".tm-top-bar").removeClass("active");
                }
            });

            // Smooth scroll to search form
            $('.tm-down-arrow-link').click(function(){
                $.scrollTo('#tm-section-search', 300, {easing:'linear'});
            });


            // Update nav links on scroll
            $('#tm-top-bar').singlePageNav({
                currentClass:'active',
                offset: 60
            });

            // Close navbar after clicked
            $('.nav-link').click(function(){
                $('#mainNav').removeClass('show');
            });

            // Slick Carousel
            $('.tm-slideshow').slick({
                infinite: true,
                arrows: true,
                slidesToShow: 1,
                slidesToScroll: 1
            });
        });
    </script>             
</body>
</html>