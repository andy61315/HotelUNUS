<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resreservation.model.*"%>

<%
	ResReservationVO resrVO = (ResReservationVO) request.getAttribute("resrVO");
	//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
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
<title>Insert title here</title>
<%@ include file="/back-end/homepage/reshead.file"%>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid write;
	text-align: center;
	background-color: pink;
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
	width: 450px;
	
	margin-top: 30px;
	margin-bottom: 1px;
}

table, th, td {
/* 	border: 2px solid write; */
	color:lightblue;
/* 	background-color: pink; */
	
}

th, td {
	padding: 1px;
}
</style>
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
			預約管理
			<!-- 自己的標頭名稱功能名稱 -->
		</h2>
		<div class="col-12">
			
						<h3>預約資料修改</h3>
						<h4>
							<a href="select_page.jsp">回首頁</a>
						</h4><br>
					
			

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: orange">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<jsp:useBean id="cusSvc" scope="page" class="com.cus.model.CustomerService"/>		
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" name="form1">
			
				<table id="table1">
					<tr>
						<td>預約編號:<font color=red><b>*</b></font></td>
						<td><%=resrVO.getReservationNo()%></td>
					</tr>
					<tr>
						<td>顧客:<font color=red><b>*</b></font></td>
						<td>${cusSvc.getOneCus(resrVO.custId).cus_Name}</td>
					</tr>
					<tr>
						<td>餐廳編號:<font color=red><b>*</b></font></td>
						<td>${restSvc.getOneRestaurant(resrVO.resNo).resName}</td>
					</tr>

					<tr>
						<td>預約日期:</td>
						<td><input name="reservationDate" id="f_date1" type="text" size="10"></td>
					</tr>
					<tr>
						<td>預約時段:</td>
						<td><select size="1" name="resvPeriod">
								<c:forEach var="reserv" items="${resreservation}">
									<option value="${reserv.key}">${reserv.value}
								</c:forEach>
						   </select>
						</td>
					</tr>
					<tr>
						<td>預約人數:</td>
						<td><input type="number" name="resvPeople" size="5"
							step="1" min="1" max="8" value="<%=resrVO.getResvPeople()%>" />
							
						</td>
					</tr>


				</table>
				
				<br><br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="reservationNo" value="<%=resrVO.getReservationNo()%>"> 
				<input type="hidden" name="custId" value="<%=resrVO.getCustId()%>">
				<input type="hidden" name="resNo" value="<%=resrVO.getResNo()%>">
				<input type="submit" value="送出修改">
			</FORM>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/jquery.datetimepicker.full.js"></script>

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
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=resrVO.getReservationDate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
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