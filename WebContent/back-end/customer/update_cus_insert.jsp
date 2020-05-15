<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>

<%
  CustomerVO customerVO = (CustomerVO) request.getAttribute("customerVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|����ƭק� - update_cus_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���q�|����ƭק�</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=customerVO.getCus_Id()%></td>
	</tr>
	<tr>
		<td>�|���m�W:</td>
		<td><input type="TEXT" name="cus_Name" size="45" value="<%=customerVO.getCus_Name()%>" /></td>
	</tr>
	<tr>
		<td>�H�c:</td>
		<td><input type="TEXT" name="cus_Email" size="45" value="<%=customerVO.getCus_Email()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="Text" name="cus_Cel" size="45" value="<%=customerVO.getCus_Cel() %>" ></td>
	</tr>
	<tr>
		<td>�ꤺ/�~:</td>
		<td><input type="RADIO" name="country" size="45"
			 value="0" <%= (customerVO.getCountry()==0)?"checked":"" %>/><label for="tw">�ꤺ</label>
			<input type="RADIO" name="country" size="45"
			 value="1" <%= (customerVO.getCountry()==1)?"checked":"" %>/><label for="for">��~</label>
		</td>
	</tr>
	
	<tr>
		<td>�����Ҧr��:</td>
		<td><input type="Text" name="id_Num" size="45" value="<%=customerVO.getId_Num() %>" ></td>
	</tr>
	<tr>
		<td>�ͤ�:</td>
		<td><input name="cus_Bir" id="f_date1" type="text" ></td>
	</tr>
	
	<tr>
		<td>�K�X:</td>
		<td><input type="PASSWORD" name="cus_Password" size="45" value="<%=customerVO.getCus_Password()%>" /></td>
	</tr>
	<tr>
		<td>�{��:</td>
		<td><input type="RADIO" name="cus_Ck" size="45"
			 value="0" <%= (customerVO.getCus_Ck()==0)?"checked":"" %>/><label for="no">���{��</label>
			<input type="RADIO" name="cus_Ck" size="45"
			 value="1" <%= (customerVO.getCus_Ck()==1)?"checked":"" %>/><label for="yes">�w�{��</label>
			 <input type="RADIO" name="cus_Ck" size="45"
			 value="2" <%= (customerVO.getCus_Ck()==2)?"checked":"" %>/><label for="ghost">���P</label>
		</td>
	</tr>
	<tr>
		<td>�W���@��</td>
		<td>
		<div id ='output'>
		<img id= 'oimg' width= "100" src="<%=request.getContextPath()%>/CusPhotoReader?cus_Id=${customerVO.cus_Id}">
		</div>
		<input type="file" name="upfile1" onchange="loadFile(event)"/>
		
		</td>
	</tr>

</table>
<script type="text/javascript">
var loadFile = function(event){
	var reader = new FileReader();
	reader.onload = function(){
		var output = document.getElementById('output');
		var oimg = document.getElementById('oimg');
		output.removeChild(oimg);
		output.innerHTML = "<img width ='300' id = 'preview'>";
		document.getElementById("preview").src = reader.result;
	};
	reader.readAsDataURL(event.target.files[0]);
}
</script>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="hiddenUrl" value="backEnd"/>
<input type="hidden" name="cus_Id" value="<%=customerVO.getCus_Id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date cus_Bir = null;
  try {
	    cus_Bir = customerVO.getCus_Bir();
   } catch (Exception e) {
	    cus_Bir = new java.sql.Date(System.currentTimeMillis());
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
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=customerVO.getCus_Bir()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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