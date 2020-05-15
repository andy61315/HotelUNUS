<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resreservation.model.*"%>

<%
	ResReservationVO resrVO = (ResReservationVO) request.getAttribute("resrVO");
%>
<jsp:useBean id="restSvc" scope="page"
	class="com.restaurant.model.RestaurantService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>addReservation</title>
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

<style type="text/css">
     #wrapper{
			width: 1100px; 
			height:500px;
			margin:30px; 
/* 			background-color: #A62121; */
		}
		#error {
		    width: 400px;
		    margin:5px auto;
		    margin-left:400px;
		    background-color:pink;
		     border: 1px solid #CCCCFF;
		}
		#table1{
		    margin:5px auto;
		}
		#table2{
		   margin:20px auto;
		   margin-left:400px;
		    
		}
		th, td {
		    
		    height:40px;
		    }
    </style>  
</head>
<body>
	<%@ include file="/front-end/indexFile/header.file"%>

	<table id="table1">
		<tr>
			<td>
				<h3><img src="images/sea.png"
						width="150" height="100" border="0">預約訂位 </h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">回訂位首頁</a>
				</h4>
			</td>
		</tr>
	</table>

<div id="wrapper">
<div id="error">
<c:if test="${not empty insertErrorMsgs}">
	<font style="color:red">預約失敗~</font>
	<ul>
		<c:forEach var="message" items="${insertErrorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/resreservation/resr.do" name="form1">

<table id="table2">

	<tr>
		<td width="150">顧客名字:</td>
		<td><font color=red><b>*${customerVO.cus_Name}</b></font></td>
	</tr>
	<tr>
		<td width="150">餐廳:</td>
		<td>
		 <select size="1" name="resNo">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.resNo}" ${(resrVO.resNo==restVO.resNo)? 'selected':'' }>${restVO.resName}
         </c:forEach>   
       </select>
		</td>
	</tr>
	
	<tr>
		<td width="150">預約日期:</td>
		<td><input name="reservationDate" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td width="150">預約時段:</td>
<!-- 		<td><input type="TEXT" name="resvPeriod" size="45" -->
<%-- 			 value="<%= (resrVO==null)? "1" : resrVO.getResvPeriod()%>" /></td> --%>
		<td>
			<select size="1" name="resvPeriod">
		         <c:forEach var="reserv" items="${resreservation}" > 
		          <option value="${reserv.key}" ${(resrVO.resvPeriod==reserv.key)? 'selected':'' }>${reserv.value}
		         </c:forEach>   
	       </select>
		</td>
	</tr>
	<tr>
		<td width="150">人數:</td>
		<td>
			 <select size="1" name="resvPeople">
          <option value="1">1位
          <option value="2">2位
          <option value="3">3位
          <option value="4">4位
          <option value="5">5位
          <option value="6">6位
          <option value="7">7位
          <option value="8">8位
             </select>  
          </td>
	</tr>
	
	<tr><td style="text-align:right;"width="100"><input type="submit" value="預訂"></td><td></td></tr>


</table>

<input type="hidden" name="custId" value="${customerVO.cus_Id}">
<input type="hidden" name="action" value="insertcus">
</FORM>
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
	<!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>
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
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date reservationDate = null;
  try {
	  reservationDate = resrVO.getReservationDate();
   } catch (Exception e) {
	   reservationDate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=reservationDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
//              var somedate1 = new Date('2017-06-15');
//              $('#f_date1').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() <  somedate1.getYear() || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>