<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>

<%
	CustomerVO customerVO = (CustomerVO) session.getAttribute("customerVO");//sign in session
%>
  

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-table.min.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/jquery.datetimepicker.css" />
<style>
  table#table-1 {
	background-color: none;
    text-align: center;
	margin-top:10px;
    margin-left:550px;
  }
  table#table-1 h4 {
    color: red;
    display: block;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table#table-2 {
	width: 45%;
	background-color: none;
	margin-top: 5px;
	margin-bottom: 50px;
	margin-left:350px;
  }
   table#table-2,th,td{
    border: 1px solid black;
  }
   th#tr-1,td#td-1{
    border: none;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
    th#tr-2, td#td-2 {
    padding: 5px;
    text-align: left;
  }
    td#td-2 {
/*     width: 100px; */
    text-align: left;
    font-weight:bold;
    text-align:justify;

  }
    th#th-3, td#td-3 {
    text-align: right;
    border-right:none;
    borger-buttom:none;
    border-left:none;
  }
  
  #ui-datepicker-div{
  width:auto !important;
  }
  .button {
  width:80px  !important;
  position:absolute;
  right:360px;
  top:700px;
  display: inline-block;
  padding: 6px 8px;
  font-size: 15px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 1px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
  .button1 {
  width:80px !important;
  position:absolute;
  right:480px;
  top:700px;
  display: inline-block;
  padding: 6px 8px;
  font-size: 15px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: BROWN;
  border: none;
  border-radius: 15px;
  box-shadow: 0 1px #999;
}
.country{
width:20% !important;
}

.button1:hover {background-color: #947D6B}

.button1:active {
  background-color: #413C2A;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>

</head>
<body bgcolor='white'>
        <%@ include file="/front-end/indexFile/header.file" %>   

<table id="table-1">
	<tr id="tr-1"><td id="td-1">
	<h3>資料修改</h3>
	</td></tr>
</table>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
		<script language="JavaScript">
		window.alert('${message}');
		</script>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" name="form1" enctype="multipart/form-data">

<table id="table-2">
	<tr id="tr-2"><td id="td-2">會員編號:<font color=red><b>*</b></font></td>
		<td id="td-2"><%=customerVO.getCus_Id()%></td>
	</tr>
	<tr id="tr-2"><td id="td-2">會員姓名:</td>
		<td id="td-2"><input type="TEXT" name="cus_Name" size="45" value="<%=customerVO.getCus_Name()%>" /></td>
	</tr>
	<tr id="tr-2"><td id="td-2">信箱:</td>
		<td id="td-2"><input type="TEXT" name="cus_Email" readonly size="45" value="<%=customerVO.getCus_Email()%>" /></td>
	</tr>
	<tr id="tr-2"><td id="td-2">手機:</td>
		<td id="td-2"><input type="Text" name="cus_Cel" size="45" value="<%=customerVO.getCus_Cel() %>" ></td>
	</tr>
	<tr id="tr-2"><td id="td-2">國內/外:</td>
		<td id="td-2">
			<input type="RADIO" class="country" name="country" size="45"
			 value="0" <%= (customerVO.getCountry()==0)?"checked":"" %>/><label for="tw">國內</label>
			<input type="RADIO" class="country"  name="country" size="45"
			 value="1" <%= (customerVO.getCountry()==1)?"checked":"" %>/><label for="for">國外</label>
		</td>
	</tr>
	<tr id="tr-2"><td id="td-2">身分證字號:</td>
		<td id="td-2"><input type="Text" name="id_Num" size="45" value="<%=customerVO.getId_Num() %>" ></td>
	</tr>
	<tr id="tr-2"><td id="td-2">生日:</td>
		<td id="td-2">
			<input name="cus_Bir" id="inputBir" readonly type="text">
		</td>
	</tr>
	
	<tr id="tr-2"><td id="td-2">密碼:</td>
		<td id="td-2"><input type="PASSWORD" name="cus_Password" size="45" value="<%=customerVO.getCus_Password()%>" /></td>
	</tr>
	<tr id="tr-2"><td id="td-2">認證:</td>
		<td id="td-2">
		<input type="hidden" name="cus_Ck" size="45" value="<%=customerVO.getCus_Ck()%>"  readonly />
		${cus_Ck[customerVO.cus_Ck]}
		</td>
	</tr>
	
	<tr id="tr-2"><td id="td-2">上傳護照</td>
		<td id="td-2">
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
<input type="hidden" name="hiddenUrl" value="frontEnd"/>
<input type="hidden" name="cus_Id" value="<%=customerVO.getCus_Id()%>">
<input class="button"type="submit" value="送出修改"></FORM>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" >
       <input type="hidden" name="action" value="getOne_CK">
       <input type="hidden" name="cus_Id" value="<%=customerVO.getCus_Id()%>">
       <input class="button1"type="submit" value="會員認證">
    </FORM>
</body>



<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker --> 
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap-table.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>    
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<%-- <script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script> --%>
<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

<!-- <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<script src="<%=request.getContextPath()%>/front-end/js/jquery.datetimepicker.full.js"></script>
		<%@ include file="/front-end/indexFile/footer.file" %>   
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: 'dark',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=customerVO.getCus_Bir()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
//         $('#f_date1').on('dp.change', function(e){ 
//         	$("#inputBir").val(e.date);
// })

 $( "#inputBir" ).datepicker({
                dateFormat: "yy-mm-dd",
                maxDate : "+0d",
                defaultDate:'-18y',
                changeYear: true,
                yearRange : "1920:2002",
                orientation:'bottom',
                
            });
            $('#inputBir').datepicker('setDate', 'today+1d');
            
//    $("#inputBir").datetimepicker({
//     theme: 'dark',              //theme: 'dark',
//     timepicker:false,       //timepicker:true,
//     step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//     format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=customerVO.getCus_Bir()%>', // value:   new Date(), --%> 
// //    disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
// //    startDate:	            '2017/07/10',  // 起始日
// //    minDate:               '-1970-01-01', // 去除今日(不含)之前
// //    maxDate:               '+1970-01-01'  // 去除今日(不含)之後
// });
// $('#inputBir').on('dp.change', function(e){ 
// // 	$("#inputBir").val(e.date);
// alert(e.date);
// })
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