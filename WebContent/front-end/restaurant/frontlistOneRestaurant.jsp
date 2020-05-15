<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.restaurant.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    RestaurantVO restVO = (RestaurantVO) request.getAttribute("restVO"); 
    //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>list one</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">


<style type="text/css">

    	h1{
          width:800px;
          height:auto;
          text-align: center;/*文字內容置中*/
          color: brown;
          margin:50px auto;
          background-color: gray;
       }
       p{
       	    width: 800px;
       	    margin:20px auto;
       	    color: #666;/*0,3,6,9,c,f安全色會在裝置上正常顯示*/
       	    text-indent: 40px;/*首行內縮*/
       	    letter-spacing: 1px;
       	    line-height: 24px;
       	    text-align: justify;/*像是左右對其*/
            border:solid;
            padding: 20px;
            box-sizing:border-box;/*讓外框維持一樣大小，圖往內縮*/
       }
      img#a{
       	    width: 470px;
       	    height:230px;
       	    margin:20px auto;
       	    border:2px solid purple;
       	    display: block; /*顯示成區塊型態*/
       	    padding:5px; /*內距*/
       	    /*box-sizing:border-box;*//*讓外框維持一樣大小，圖往內縮*/
       	  
       }
       body{
       	   font-family: "微軟正黑體",microsoft jhenHei;
           /*background-image: url("images/kman.jpg");
           background-repeat: no-repeat;
           background-position: 20% 20%;
           background-attachment: scroll;*/
       }
       

    </style>

</head>
<body>
        <%@ include file="/front-end/indexFile/header.file" %>               
            
            <div id='codingHere'  style="width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">



<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>餐廳編號</th> -->
<!-- 		<th>餐廳名稱</th> -->
<!-- 		<th>座位</th> -->
<!-- 		<th>聯絡人</th> -->
		
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><%=restVO.getResNo()%></td> --%>
<%-- 		<td><%=restVO.getResName()%></td> --%>
<%-- 		<td><%=restVO.getTotalSeat()%></td> --%>
<%-- 		<td><%=restVO.getResContact()%></td> --%>
	
<!-- 	</tr> -->
<!-- </table> -->

<h1><%=restVO.getResName()%></h1>
<div id="wrapper">
<div id="left">
<P>
<a href="<%=request.getContextPath()%>/front-end/resreservation/select_page.jsp">網路預約訂位(請按此)</a>
<br>
樓層：一樓<br>
總座位數：310席<br>
包廂：2間，7間半開放<u><%=restVO.getResName()%></u>沙發區<br>
餐廳訂位專線：${restVO.resPhone} <br>
※ 以上價格需另加一成服務費，以現場公告為主，不另行通知。<br>
※ 自備酒水服務費：葡萄酒類及飲料每瓶500元，烈酒類每瓶1,000元<br>
營業時間<br>
<br>
午餐：11:30 AM – 2:30 PM<br>
下午茶：3:00 – 5:00 PM<br>
晚餐：週一至週四6:00 – 9:30 PM、週五至週日6:00 – 10:00 PM<br>
<br>
※ 最後點餐時間:營業時間結束前30分鐘<br><br>
<br>
為確保孩童安全，請務必遵守以下用餐規範：
<br>
每3位6歲以下孩童需至少有1位成人陪同用餐；每6位6歲以下孩童需至少有2位成人陪同用餐，以此類推，邀您共同守護孩童的用餐安全。<br>
</P>
</div>
	
<div id="right">
<!-- <img id="a" src="images/1.png" alt=""> -->
</div>
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
//         $(function(){
//         	    if ($.browser.msie && $.browser.version.substr(0,1)<7) {
//         	      $('li').has('ul')
//         	      .mouseover(function(){
//         	        $(this).children('ul').css('visibility','visible');
//         	      })
//         	      .mouseout(function(){
//         	        $(this).children('ul').css('visibility','hidden');
//         	      })
//         	    }
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