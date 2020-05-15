<%@page import="com.resreservation.model.ResReservationVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>


 
<style>
table {
 	width: 800px; 
 	background-color: white; 
	opacity:0.8;
	margin-top: 20px;
 	margin-left:90px; 
}

 table, th, td {
    border:1px solid green;
/*     width: 100%; */
  }

th, td {
    width: 100px;
    padding: 5px;
    text-align: center;
    color:green;
  }
  h4{
  color:green;
  }
</style>
  
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>預約訂位</title>
<%@ include file="/back-end/homepage/reshead.file"%>

</head>
<body>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<%@ include file="/back-end/homepage/middle.file"%>

	<div id="lineQA" class="tableStyle">
		<h2 class="col-12 title">
			預約訂位管理
		</h2>
		
		
			<h4>餐廳預約:美食訂位/訂單查詢</h4>
					
						
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
	</div>	
		
    <div class="row1">
                         
<%--              <li>查看<a href='<%=request.getContextPath()%>/back-end/resreservation/listAllResReservation.jsp'>所有預約(All Reservations.)</a></li> --%>
<%-- 				<jsp:useBean id="resrSvc" scope="page" --%>
<%-- 					class="com.resreservation.model.ResReservationService" /> --%>

<!--  				<li>  -->
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do"> --%>
<!-- 						<b>預約編號查詢:</b> <select size="1" name="reservationNo"> -->
<%-- 							<c:forEach var="resrVO" items="${resrSvc.getAll()}"> --%>
<%-- 								<option value="${resrVO.reservationNo}">${resrVO.reservationNo} --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 						<input type="submit" value="送出"> -->
<!-- 					</FORM> -->
<!-- 				</li> -->
				
   </div>
				<jsp:useBean id="restSvc" scope="page"
					class="com.restaurant.model.RestaurantService" />
       <table>
				<tr><th colspan="2">選擇日期及餐廳:</th></tr>
			
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do">
						<tr><th>選擇日期:</th><td><input name="reservationDate" id="f_date1" type="text" size="10"></td></tr>
						 
						<tr><th>選擇餐廳編號:</th> 
						<td><select size="1" name="resNo">
							<c:forEach var="restVO" items="${restSvc.all}">
								<option value="${restVO.resNo}">${restVO.resName}
							</c:forEach>
						</select></td></tr> 
						<input type="hidden" name="action" value="getDate_For_Display">
						

						<tr ><td></td><td><input type="submit" value="送出"></td></tr>
					</FORM>
				
             

				<tr><th>電話訂位</th><td><h3><a href='<%=request.getContextPath()%>/back-end/resreservation/addResReservation.jsp'>ADD</a></h3></td></tr>
			             


             <tr>
				<th>查看</th><td><a href='<%=request.getContextPath()%>/back-end/resreservation/listAllResReservation.jsp'>所有預約(All Reservations.)</td>
			</tr>
		
		
	
	</table>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  ResReservationVO resrVO = (ResReservationVO) request.getAttribute("resrVO"); 
  
  java.sql.Date reservationDate = null;
  try {
	  reservationDate = resrVO.getReservationDate();
   } catch (Exception e) {
	   reservationDate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/jquery.datetimepicker.full.js"></script>
	<%@ include file="/back-end/homepage/footer.file"%>

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