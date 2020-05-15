<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.saleplan.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%
   SalePlanVO salVo= (SalePlanVO) request.getAttribute("salVo");
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> roomTypeList = roomTypeSvc.getAll();
	pageContext.setAttribute("roomTypeList",roomTypeList);	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<link href="https://cdn.bootcdn.net/ajax/libs/pretty-checkbox/3.0.3/pretty-checkbox.css" rel="stylesheet">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file" %>
<style type="text/css">
h3{
color:#fff;
}
#submitSal{
position:relative;

}


</style> 
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >優惠專案<!-- 自己的標頭名稱功能名稱 --></h2>
    			<div class="col-12">
    			
<h4><a href="listAllSal.jsp">返回</a></h4>


<h3>優惠新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="saleplan.do" name="form1">
<table>
	
	<tr>
		<td>優惠名稱:</td>
		<td><input type="TEXT" name="sapl_name" size="45"
			 value="<%= (salVo==null)? "" : salVo.getSapl_name()%>" /></td>
	</tr>
	<tr>
		<td>優惠內容:</td>
		<td><input type="TEXT" name="detail" size="45"
			 value="<%= (salVo==null)? "" : salVo.getDetail()%>" /></td>
	</tr>
	
	<tr>
		<td>開始日期:</td>
		<td><input name="start_date" id="f_date1" type="text" onchange="showStr(this.id)"></td>
	</tr>
	<tr>
		<td>結束日期:</td>
		<td><input name="end_date" id="f_date2" type="text"></td>
	</tr>
	
	
	<tr>
		<td>優惠折扣:</td>
		<td><input type="text" name="sapl_discount" size="45" Maxlength="4"  min="0" oninput="this.value=this.value.replace(/\D*(\d*)(\.?)(\d{0,3})\d*/,'$1$2$3')" pattern="[0-9]*\.?[0-9]{0,3}"
			 value="<%= (salVo==null)? "0.8" : salVo.getSapl_discount()%>" /></td>
	</tr>
	
	<tr>
		<td>優惠狀態:</td>
		<td><select size="1" name="status" >
		<option value ="1">上架</option>
          <option value="0">下架</option>
  		  <option value ="2">待審核</option>
       </select></td>
		
	</tr>
	
	<tr>
	  
	<td>選擇優惠房型:</td>
	<td>
	<c:forEach items="${roomTypeList}" var="roomType" varStatus="status" >
	<label>
	<input type="checkbox"  value="${roomType.room_Type_No}" name="room_Type_No"  />
	${roomType.room_Type_Name}<br></label>
		</c:forEach>
	</td>
	</tr>
	

</table>
<br>
                         

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" id="submitSal">

</FORM>
   			



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date start_date = null;
	java.sql.Date end_date = null;
  try {
	  start_date = salVo.getStart_date();
	  end_date = salVo.getEnd_date();
   } catch (Exception e) {
	   start_date = new java.sql.Date(System.currentTimeMillis());
	   end_date = new java.sql.Date(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	      theme: 'dark',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
// 	       step: 60 ,
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=start_date%>',
		   // value:   new Date()
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:new Date(), // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $('#f_date2').datetimepicker({
  	      theme: 'dark',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	          //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=end_date%>',

  		   // value:   new Date()
             //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
 <%--             startDate:'<%=start_date%>',  // 起始日 --%>
			
//              minDate:               '-1970-01-01', // 去除今日(不含)之前
             //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
          });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
 	
    function showStr(x){
　	var str=document.getElementById(x).value;
　	  var somedate1 = new Date(str);
      $('#f_date2').datetimepicker({
          beforeShowDay: function(date) {
        	  if (  date.getYear() <  somedate1.getYear() || 
 		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
 		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
              ) {
                   return [false, ""]
              }
              return [true, ""];
      }});
	}
 		
           

        
        //      2.以下為某一天之後的日期無法選擇
//              var somedate2 = $('#f_date1').val();
//              $('#f_date2').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() >  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});


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
</div>
				</div>
<%@ include file="/back-end/homepage/footer.file" %>
</body>
</html>