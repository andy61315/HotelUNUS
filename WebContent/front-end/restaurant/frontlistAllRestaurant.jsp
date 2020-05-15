<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    RestaurantService restSvc = new RestaurantService();
    List<RestaurantVO> list = restSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="resrSvc" scope="page" class="com.resreservation.model.ResReservationService" /> --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Hotel Unus</title>
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
<title>餐廳列表</title>

<style>
  table#table-1 {
    width: 900px;
	margin-left: 182px;
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
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 60px;
  }
  table, th, td {
    border: 1px solid green;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
<style type="text/css">
		body{
			margin:0px;/*清除預設外距*/
/* 			background-color: #000; 背景黑色 */
			font-family: microsoft jhengHei;
		}
		#wrapper{
			width: 900px;
			margin: 0px auto;/*上下/左右間距*/
/* 			background-color: pink; */
            
		}
		#wrapper #content p{
			
			margin: 0px;
			letter-spacing: 1px;
			text-align: justify;/*文章左右對齊*/
			text-indent: 40px;/*首行縮排*/
			color:black;
		}
		#wrapper #cf{
			text-align: center;

		}
		#wrapper #cf img{
			width: 250px;
			height: 200px;
			margin:20px;/*900-(250*3)/6=25 最多外距範圍*/
		}
 		#wrapper #banner img{ 
 			width: 900px; 
 			margin-bottom:10px;
 			 
 		} 
		
		
	</style>
</head>
<body bgcolor='white'>
<%@ include file="/front-end/indexFile/header.file" %>




<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3 style="text-align:center;">精選美饌</h3>
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div id="wrapper">
		<div id="banner"><img src="images/big.png"></div>
		<div id="content">
			<!-- p>lorem200 -->
			<p>坐擁極佳視野景觀，讓受到庸碌生活所束縛的城市人，享受沉靜卻又明亮開闊的氣氛環抱。
			《臺北米其林指南 2018 & 2019》米其林二星餐廳精闢刀工搭配料理巧思及超過20年麵食藝術真功夫呈現眼前。另有豪華貴賓包廂，滿足各式宴會需求、更是作東、開會的絕佳場所。
			  全廳劃分為12區全開放式的美食料理區。包含沙拉區、日式料理區、中華、西洋、東南亞風味、海鮮料理等美食區、現場烘培甜點&麵包區、及各式飲品。
			 主廚推薦：砂鍋一品雞、白鯧米粉湯、冰糖蓋子頭、蘇式小湯包、麻油雞飯、蔥油餅捲、泡菜牛肉絲、乾煸四季豆、泡椒皮蛋、悄悄話、百頁豆腐絲、白玉龍鬚盅、子母壽桃等多道膾炙人口佳餚。
			 SUKHOTHAI秉持著傳統泰國菜之口味，注入變化之元素，菜色呈現出讓人驚艷新風貌，顛覆饕客對泰國菜的刻板印象。傳說中的泰國盛世，遙遠的美食想像。
			 主廚推薦：宮廷酸甜楊桃豆鮮蝦沙拉、脆炸果香石斑魚、月亮蝦餅、馬沙曼咖哩牛腩。
			 嚴選當令食材，以精湛的日式料理功夫及盤飾藝術呈現精緻懷石料理的道地美味，精心打造的桃山在摩登時尚的裝潢中，巧妙保留傳統京都風情的柚木VIP廂房。品嘗扶桑珍饈美饌，寬敞雅致的桃山是您最佳的選擇。
			 頂級牛排，極致味蕾體驗。古典雅致的安東廳，世界酩酒匯集，精彩呈現各式牛排風味饗宴。《臺北米其林指南 2018》米其林入書推薦頂級牛排、淬鍊非凡蘊含食材的簡單原味，呈現頂級牛排的真實魅力，世界名酒一應俱全，專業貼心的桌邊服務，品味美食的完美經典組合。
			主廚推薦：經典龍蝦濃湯、美國頂級鹽燒肋眼牛排、美國蛇河農場極黑和牛30天乾式熟成丁骨牛排、炭烤乾式熟成噶瑪蘭黑豚帶骨肋眼、法國白桃及香草冰淇淋</p>
		</div>
		<div id="cf">
			<img src="images/1.png" alt="">
			<img src="images/2.png" alt="">
			<img src="images/3.png" alt="">
		</div>
<table>
	<tr>
		<th>詳情</th>
		<th>餐廳</th>
		<th>座位數</th>
		<th>訂位電話</th>
		<th>即刻預約</th>
		
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="restVO" items="${list}" >
		
		<tr>
			<td><a href='<%=request.getContextPath()%>/front-end/restaurant/rest.do?action=getOne_For_FrontDisplay&resNo=${restVO.resNo}'>注意事項</a></td>
			<td>${restVO.resName}</td>
			<td>${restVO.totalSeat}</td>
			<td>${restVO.resPhone}</td>
			<td><a href='<%=request.getContextPath()%>/front-end/resreservation/select_page.jsp'>訂位</a></td>
			
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>
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
<%@ include file="/front-end/indexFile/footer.file" %>   
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