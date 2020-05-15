<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resreservation.model.*"%>

<%
	ResReservationVO resrVO = (ResReservationVO) request.getAttribute("resrVO");
%>

<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService" />

<!DOCTYPE html>
<html>
<head>

<style>
  table {
	width: 450px;
	background-color: white;
	opacity:0.9;
	margin-top: 30px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    width: 100px;
    padding: 5px;
    text-align: center;
    color:green;
  }
  h3 img{
  width:150px;
  height:150px;
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
<title>�w���q��</title>
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
			�w���޲z
			<a href="<%=request.getContextPath()%>/back-end/resreservation/select_page.jsp">�^����</a>
		</h2>
		<div class="col-12">
			
			<h3><img src="images/sea.png" width="100" height="100" border="0">��x�w���q��</h3>
					
						
					
			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
<!-- 				<font style="color: red">�Эץ��H�U���~:</font> -->
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/resreservation/resr.do" name="form1">
		<table>		
					<tr>
						<td>�|��E-mail:</td>
						<td><input type="TEXT" name="custMail" size="15" 
						              value="<%=(request.getParameter("custMail") ==null) ? "": request.getParameter("custMail")%>" />
						
					    </td>
					</tr>
					
					<tr>
						<td>�\�U:</td>
						<td>
						<select size="1" name="resNo">
								<c:forEach var="restVO" items="${restSvc.all}">
									<option value="${restVO.resNo}" ${(resrVO.resNo==restVO.resNo)? 'selected':'' }>${restVO.resName}
								</c:forEach>
						</select>
						</td>
					</tr>
					
					<tr>
						<td>�w�����:</td>
						<td><input name="reservationDate" id="f_date1" type="text" size="15"></td>
					</tr>
					<tr>
						<td>�w���ɬq:</td>
						<td>
						<select size="1" name="resvPeriod">
								<c:forEach var="reserv" items="${resreservation}">
									<option value="${reserv.key}" ${(resrVO.resvPeriod==reserv.key)? 'selected':'' }>${reserv.value}
								</c:forEach>
						</select>
						</td>
					</tr>
					
					<tr>
						<td>�H��:</td>
						<td>
						<select size="1" name="resvPeople">
								<option value="1">1��
								<option value="2">2��
								<option value="3">3��
								<option value="4">4��
								<option value="5">5��
								<option value="6">6��
								<option value="7">7��
								<option value="8">8��
						</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
						   <input type="submit" value="�e�X�s�W">
						</td>
					</tr>
					
				</table>
				
				<br> <input type="hidden" name="action" value="insert">
				     
			</FORM>
			
		</div>
	</div>
<%-- 	<%@ include file="/back-end/homepage/footer.file"%> --%>
</body>

<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
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
	       step: 30,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=reservationDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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