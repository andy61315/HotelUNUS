<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.bom.model.*"%>
<%@ page import="java.util.*"%>



<jsp:useBean id="listRoom_ByCompositeQuery" scope="request" type="java.util.List<RoomVO>" /> <!-- 於EL此行可省略 -->
<% 
RoomTypeService roomTypeSve = new RoomTypeService();
pageContext.setAttribute("roomTypeSve",roomTypeSve);

%>
<style>
table{
margin-left:20px;
margin-top:50px;
}
#checkout{
	width:auto;
	color:lightblue;
}
b{
color:#fff;
}
</style>
<table>
	<tr>
	
		<th>房型編號</th>
		<th>房號</th>
		<th>房客姓名</th>
		<th>房客電話</th>
		<th>訂單編號</th>
	</tr>
<%-- 	<%@ include file="page1_ByCompositeQuery.file"%> --%>
	<c:forEach var="roomVO" items="${listRoom_ByCompositeQuery}" >
		<tr align='center' valign='middle' ${(roomVO.room_id==param.room_id) || (roomVO.room_no==param.room_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			
			<td><input name="room_id" type="hidden" value="${roomVO.room_id}">${roomTypeSve.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
			<td>${roomVO.room_no}</td>
			<td>${roomVO.tenant_name}</td>
			<td>${roomVO.tenant_phone}</td>	
			<td>${roomVO.b_order_no}</td>		
	</c:forEach>
	
</table>
<%-- <%@ include file="page2_ByCompositeQuery.file" %> --%>
<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/room/room.do" style="margin-bottom: 0px;" id="checkout-form">
	<input type="hidden" name="action" value="checkout">
	<input type="hidden" name="room_id" value="${roomVO.room_id}">
	<input type="hidden" name="cus_id" value="${roomVO.cus_id}">
	<b>選擇訂單編號:</b>
       <select size="1" name="b_order_no">
         <c:forEach var="roomVo" items="${listRoom_ByCompositeQuery}" > 
           <option value="${roomVo.b_order_no}">${roomVo.b_order_no}  
         </c:forEach>  
       </select>
      
	<input type="submit" value="CHECKOUT" id="checkout">
	 
	</form>
	

	
 <script>
 
 
//  var checked =false;
//  	$(function(){
//  		$('#checkOutSearch').on('click', function(){
//  			var room_id = $('#room_id').val();
//  			var cus_id = $('#cus_id').val();
//  			var b_order_no = $('#b_order_no').val();
//  			checked = false;
//  			$.ajax({
//  				 type: "POST",
//  				 url:"/back-end/room/room.do",
//  				 data: {"room_id": room_id,"cus_id":cus_id,"b_order_no":b_order_no},
//  				 dataType: "json",
//  				 async: false, // true預設是非同步，也就是"不會等待"; false是同步，"會等待"
//  				 success: function (data){
 				
//  					swal({
// 							icon : "success",
						
// 						});	
//  			     },
//  	            error: function(){alert("AJAX-class發生錯誤囉!")}
//  	        })
//  	        console.log("checked: "+ checked);
//  	        if(checked){
//  		        	$('#checkout-form').submit();
//  	        }
//  		});
//  	});
 </script>
