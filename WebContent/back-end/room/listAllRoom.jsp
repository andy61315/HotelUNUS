<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    RoomService roomSvc = new RoomService();
    List<RoomVO> list = roomSvc.getAll();
    pageContext.setAttribute("list",list);
    RoomTypeService roomTypeSvc = new RoomTypeService();
    List<RoomTypeVO> roomTypelist = roomTypeSvc.getAll();
    pageContext.setAttribute("roomTypeSvc",roomTypeSvc);
    pageContext.setAttribute("roomTypelist",roomTypelist);
   
    
%>
<!-- 於EL此行可省略 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file" %> 
<style type="text/css">
#listOneRoom{
	margin-top:3%;	
}
#from1{
	color: #fff;
	background-color:#343232;
	border-radius:5px;
	text-align:center;
}
h4{
	border-bottom:2px solid black;
	color: #fff;
	background-color:#343232;
	}
#addRoom{
	float:right;
	height:30px;
}
b{
color:#fff;
}
</style>
<script>

var checked =false;
// function btn(room_id){
// 	alert(room_id);
// }

$(document).ready(function() {	

	$(".getRoomStatus_Update").click(function() {

		var room_id = $(this).parent().find(".room_id").val();
// 		alert($(this).parent().find(".room_id").prop("class"));

// 		var room_id = $("[name='room_id']").val();
		var requestURL = $("[name='requestURL']").val();
		var whichPage = $("[name='whichPage']").val();
		checked = false;
		$.ajax({
			type : 'POST',
			data : {
				room_id : room_id,
				requestURL:requestURL,
				whichPage:whichPage,
				action : 'getRoomStatus_Update'
			},
			dataType: "json",
			url :"<%=request.getContextPath()%>/back-end/room/room.do",
			 async: false, 
			success : function(data) {
				var room_no =data.room_no;
				var room_status = data.room_status;

				Swal.fire({
					
					  title: "房間狀態修改"+'<br>' +"房號："+room_no,
					  input:room_no,
					  showCancelButton: true,
					  showConfirmButton: false,
					  html:
					  '<FORM METHOD="post" action="<%=request.getContextPath()%>/back-end/room/room.do" id="form1">'
					  +'<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">'
					  +'<select size="1" name="room_status" >'
			       	  +'<option value="'+room_status+'">'+'選擇狀態</option>'
			           +'<option value="0">空房</option>'
			           +'<option value ="1">有房客</option>'
			    		+ ' <option value ="2">已排房</option>'
			            +'<option value="3">將離館/已排房  </option>'
			            +'<option value="4">將離館  </option>'  
			            +'<option value="5">不再使用 </option> '  
			         +'</select>'
					  +'<input type="hidden" name="action" value="updateRoomStatus">'
					  +'<input type="hidden" name="room_no" value="'+room_no+'">'
					  +'<input type="submit" value="確認修改">'
					  +'</FORM>'
					 
					})

				
	
			},
		})

	});

});
</script>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle col-12">
    			<h2  class="col-12 title" >房間管理<!-- 自己的標頭名稱功能名稱 --></h2>
    			<div class="col-12">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div>
<form method="post" action="<%=request.getContextPath()%>/back-end/room/room.do" name="from1" id="from1">
		


<span>輸入房型</span>
    
    <select size="1" name="room_type_no">
      <option value="">
      <c:forEach var="roomtypeVO" items="${roomTypelist}">
      <option value="${roomtypeVO.room_Type_No}">${(roomtypeVO.room_Type_Name)}</option>
      </c:forEach>
    </select>
    
    <span>輸入房間</span>
    <select size="1" name="room_no">
      <option value="">
      <c:forEach var="roomVO" items="${list}">
      <option value="${roomVO.room_no}">${(roomVO.room_no)}</option>
      </c:forEach>
    </select>
    
    
    <span>輸入會員編號</span>
    <input type="text" name="cus_id" value="">
    <span>輸入房客姓名</span>
    <input type="text" name="tenant_name" value="">
    <br>
    <span>輸入房客電話</span>
    <input type="text" pattern="[0-9]*" name="tenant_phone" value="">
    
    <span>房間狀態:</span>
       <select size="1" name="room_status" >
          <option value="">選擇狀態</option>
          <option value="0">空房</option>
          <option value ="1">有房客</option>
        <option value ="2">已排房</option>
          <option value="3">將離館/已排房  </option>
          <option value="4">將離館  </option>  
          <option value="5">不再使用 </option>   
       </select>
    
    <span>輸入房間清潔狀態</span>
       <select size="1" name="clean_status" >
        <option value="">選擇狀態</option>
          <option value="0">故障</option>
          <option value ="1">乾淨</option>
        <option value ="2">未打掃</option>
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listRoom_ByCompositeQuery">
      
    </form>



</div>
 
<div id="listOneRoom">
<c:if test="${not empty roomVO}">
	
		<jsp:include page="listOneRoom.jsp"></jsp:include>
	
</c:if>
<c:if test="${not empty listRoom_ByCompositeQuery}">
	
		<jsp:include page="listRoom_ByCompositeQuery.jsp"></jsp:include>
	
</c:if>

</div>
<h4>所有房間 </h4><button id="addRoom"><a href='addRoom.jsp' style="text-decoration:none;">Add a new Room.</a></button>
<table>
	<tr>
<!-- 		<th>房間編號</th> -->
		<th>房型</th>
		<th>房號</th>
		<th>房間狀態</th>
		<th>房間資料修改</th>
		<th>房間狀態修改</th>
		<th>查看房間</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
			<tr ${(roomVO.room_id==param.room_id) ||(roomVO.room_no==param.room_no) ? 'bgcolor=red':''}>
<%-- 			<td>${roomVO.room_id}</td> --%>
			<td>${roomTypeSvc.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
			<td>${roomVO.room_no}</td>
			<td>${roomStatus[roomVO.room_status]}</td>
			

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/room/room.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="room_id"  value="${roomVO.room_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
	
			
			
			<td>
			  <FORM style="margin-bottom: 0px;">
			     <input type="hidden" name="room_id" class="room_id" value="${roomVO.room_id}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			     <input type="hidden" name="whichPage" value="<%=whichPage %>">
<%-- 			     <input type="button" value="修改" id="getRoomStatus_Update" class="getRoomStatus_Update" onclick="btn('${roomVO.room_id}');" > --%>
			     <input type="button" value="修改" id="getRoomStatus_Update" class="getRoomStatus_Update" >
			     <input type="hidden" name="action"	value="getRoomStatus_Update">
		    </FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/room/room.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看房間">
			     <input type="hidden" name="room_id"  value="${roomVO.room_id}">
			     <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
			     <input type="hidden" name="whichPage" value="<%=whichPage %>">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<br>
<br>

</div>
</div>


<%@ include file="/back-end/homepage/footer.file" %>

