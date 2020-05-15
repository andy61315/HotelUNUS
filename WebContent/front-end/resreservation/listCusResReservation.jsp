<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.resreservation.model.*"%>
<%@ page import="com.cus.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
// 	List<ResReservationVO> list = (List<ResReservationVO>) request.getAttribute("list");
// 	//取得以客戶查詢結果集合
// 	if (list != null) {
// 		session.setAttribute("clist", list);
// 	} else {
// 		list = (List<ResReservationVO>) session.getAttribute("clist");
// 	}
String custId = ((CustomerVO)session.getAttribute("customerVO")).getCus_Id();

List<ResReservationVO> list = new ResReservationService().getCustomer(custId);
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="restSvc" scope="page"
	class="com.restaurant.model.RestaurantService" />

<!DOCTYPE html>
<html lang="en">
<head>
<style>

  table#table-1 {
  width: 1000px;
	margin-left:200px;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: blue;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  div#wrapper{
  height:600px;
  }
</style>

<style>
  table#table1 {
	width: 1000px;
	margin-left:200px;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align: center;
  }
  table, th, td {
    border: 2px solid black;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
 
 
</style>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>listCusReservation</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<!-- Google web font "Open Sans" -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<!-- Bootstrap style -->
<!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">
                     <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css"> 
    <!-- jquery-3.4.1.slim.min -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
 <script>
   $(document).ready(function(){
	  <c:if test="${'insertcus'.equals(param.action)}">
	  swal("預約已新增", "Good job!", "success");
	  </c:if>
	});
 </script>     
</head>
<body>
	<%@ include file="/front-end/indexFile/header.file"%>
<div id="wrapper">
	<table id="table-1">
		<tr>
			<td>
				<h4>${customerVO.cus_Name} 您好 ! 以下是您的訂位列表</h4>
	
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	
	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="table1">
	<tr>
		<th>預約編號</th>
		<th>餐廳</th>
		<th>預約日期</th>
		<th>預約時段</th>
		<th>人數</th>
		<th>狀態</th>
		<th>取消預約</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	
	<c:forEach var="resrVO" items="${list}">
		
		<tr>
			<td>${resrVO.reservationNo}</td>
			<td>${restSvc.getOneRestaurant(resrVO.resNo).resName}</td>
			<td>${resrVO.reservationDate}</td>
			<td>${resreservation[resrVO.resvPeriod]}</td>
			<td>${resrVO.resvPeople}</td>
			<td>${resvStatus[resrVO.resvStatus]}</td>  
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/resreservation/resr.do" style="margin-bottom: 0px;">
			     <input type="submit" id="cancel" value="取消預約">
			     <input type="hidden" name="reservationNo"  value="${resrVO.reservationNo}">
			     <input type="hidden" name="action" value="cancelfront"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</div>


	<%@ include file="/front-end/indexFile/footer.file"%>
	<!-- load JS files -->
	<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->


	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
	<!-- https://popper.js.org/ -->
	<script
		src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
	<!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker -->
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>
	<!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
	<script
		src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>
	<!-- http://kenwheeler.github.io/slick/ -->
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>
	<!-- https://github.com/flesler/jquery.scrollTo -->
	<script>
		$(function() {

			// Change top navbar on scroll
			$(window).on("scroll", function() {
				if ($(window).scrollTop() > 100) {
					$(".tm-top-bar").addClass("active");
				} else {
					$(".tm-top-bar").removeClass("active");
				}
			});

			// Smooth scroll to search form
			$('.tm-down-arrow-link').click(function() {
				$.scrollTo('#tm-section-search', 300, {
					easing : 'linear'
				});
			});

			// Update nav links on scroll
			$('#tm-top-bar').singlePageNav({
				currentClass : 'active',
				offset : 60
			});

			// Close navbar after clicked
			$('.nav-link').click(function() {
				$('#mainNav').removeClass('show');
			});

			// Slick Carousel
			$('.tm-slideshow').slick({
				infinite : true,
				arrows : true,
				slidesToShow : 1,
				slidesToScroll : 1
			});
		});
	</script>
</body>
</html>