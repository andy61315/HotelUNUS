<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.bom.model.*"%>
<%@ page import="java.util.*"%>



<jsp:useBean id="listRoom_ByCompositeQuery" scope="request" type="java.util.List<RoomVO>" /> <!-- ��EL����i�ٲ� -->
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
	
		<th>�Ы��s��</th>
		<th>�и�</th>
		<th>�Ыȩm�W</th>
		<th>�Ыȹq��</th>
		<th>�q��s��</th>
	</tr>
<%-- 	<%@ include file="page1_ByCompositeQuery.file"%> --%>
	<c:forEach var="roomVO" items="${listRoom_ByCompositeQuery}" >
		<tr align='center' valign='middle' ${(roomVO.room_id==param.room_id) || (roomVO.room_no==param.room_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			
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
	<b>��ܭq��s��:</b>
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
//  				 async: false, // true�w�]�O�D�P�B�A�]�N�O"���|����"; false�O�P�B�A"�|����"
//  				 success: function (data){
 				
//  					swal({
// 							icon : "success",
						
// 						});	
//  			     },
//  	            error: function(){alert("AJAX-class�o�Ϳ��~�o!")}
//  	        })
//  	        console.log("checked: "+ checked);
//  	        if(checked){
//  		        	$('#checkout-form').submit();
//  	        }
//  		});
//  	});
 </script>
