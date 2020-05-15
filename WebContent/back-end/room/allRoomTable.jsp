<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.bom.model.*"%>
<%@ page import="com.bod.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<jsp:useBean id="roomlist2" scope="request" type="java.util.List<RoomVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="bodlist" scope="request" type="java.util.List<BookOrderDetailVO>" /> 
<jsp:useBean id="searchBomByCus_Id_Num" scope="session"
	type="java.util.List<BookingOrderMasterVO>" />
<% 
RoomTypeService roomTypeSve = new RoomTypeService();
RoomTypeVO roomTypeVO = null;
pageContext.setAttribute("roomTypeSve",roomTypeSve);

%>
<style>
#roomtable1{
	margin-left:20px;
}
/* #roomtable{ */
/*  	position:relative;  */
/*  	top:80px; */
/* 	float:left; */
/* 	padding:3px; */
/* 	width:200px; */
/* 	height:150px; */
/* 	text-align:center; */
/* 	border:1px solid black; */
/* 	margin:20px; */
/* 	border-radius:5px; */
/* 	background-color:rgba(256,256,256,0.8); */
/* } */
#roomtable FORM .btn{
	text-align:center;
	width:150px;
	height:30px;
	border-radius:5px;
	background-color:#E2943B;
} 
P{
color:#E2943B;
}

#checkin{
color:black;
border:3px solid black;
margin-top:20px;
background-color:#E2943B; 
}
</style>
<h4>可入住房間</h4>
<table id="roomtable1">
	<tr>
<!-- 		<th>房間編號</th> -->
		<th>下訂房型</th>
		<th>房號</th>
		<th>房間狀態</th>
		<th>房間清潔狀態</th>
<!-- 		<th>會員編號</th> -->
<!-- 		<th>房客姓名</th> -->
<!-- 		<th>房客電話</th> -->
<!-- 		<th>查看房間</th> -->


	
	</tr>
	<c:forEach var="list" items="${roomlist2}" >
		<c:forEach var="roomVO" items="${list}" >
			
			<tr ${(roomVO.room_id==param.room_id) ||(roomVO.room_no==param.room_no) ? 'bgcolor=#CCCCFF':''}>
<%-- 			<td>${roomVO.room_id}</td> --%>
<%-- 			<c:forEach var="bodVO" items="${bodlist}" > --%>
			<td>${roomTypeSve.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
			
<%-- 			</c:forEach> --%>
			
			<td><input name="room_id" type="hidden" value="${roomVO.room_id}">${roomVO.room_no}</td>
			<td>${roomStatus[roomVO.room_status]}</td>
			<td>${cleanStatus[roomVO.clean_status]}</td>
			
		</tr>

		
	</c:forEach>
	</c:forEach>
	
	
</table>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/room/room.do" style="margin-bottom: 0px;" id="checkin-form">
 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
<input type="hidden" name="b_order_no" value="${param.b_order_no}">		     
<input type="hidden" name="action" value="checkin">

<input type="submit" class="btn" value="checkIn" id="checkin"> 
</FORM>
	 <script>
       	document.getElementById("checkin").addEventListener("click",function(){
       		
       	var room_ids = document.getElementsByName("room_id");	
       	var str='';
       	for(var i =0 ; i<room_ids.length; i++){
       		str += "<input type='hidden' name='room_ids' value=' " + room_ids[i].value + "  '>";
       	}	
       	$("#checkin-form").append(str);
       	
    
  	});
    	
 </script>
 	
        
       
<!-- Modal -->


<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
				
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel">房客資訊</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
                
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="roomnoCus.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
    		 
        </script>
 </c:if>
	
<!-- Modal2 -->
<c:if test="${openModal2!=null}">

<div class="modal fade" id="basicModal2" tabindex="-1" role="dialog" aria-labelledby="basicModal2" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
				
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel">房客資訊</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
                
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="roomCus.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
               
                <button type="button" class="btn btn-primary" id="ckeckout">check out</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal2").modal({show: true});
    		 
        </script>
 </c:if>

