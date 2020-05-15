<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.*"%>
<%
	RoomService roomSvc = new RoomService();
	RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");

	RoomTypeService roomTypeSve = new RoomTypeService();
	RoomTypeVO roomTypeVO = null;
	pageContext.setAttribute("roomTypeSve", roomTypeSve);
	List<RoomTypeVO> roomTypelist = roomTypeSve.getAll();
	List<RoomVO> roomlist = roomSvc.getAll();
	pageContext.setAttribute("roomTypelist", roomTypelist);
	String romm_no_by_list = roomlist.get(roomlist.size() - 1).getRoom_no();
	pageContext.setAttribute("romm_no_by_list", romm_no_by_list);
%>
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
<style>

.delOneCol{
border:2px solid black;
float:right;
	color: #fff;
	background-color:#343232;
}
#addOneCol{
border:2px solid black;
float:right;

}
select{
float:left;
}

</style>
<%@ include file="/back-end/homepage/head.file" %> 
<body>

<%@ include file="/back-end/homepage/middle.file" %> 
	<div id="lineQA" class="tableStyle col-12" >
    			<h2  class="col-12 title" >新增房間</h2>
    			
    			<div class="col-12">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red"></font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<FORM METHOD="post" ACTION="room.do" name="form1">
<table >
	
	<tr>
		<td>房型:</td>
		<td>
		
		<select size="1" name="room_type_no">
			<option value="">選擇房型
			<c:forEach var="roomtypeVO" items="${roomTypelist}">
			<option value="${roomtypeVO.room_Type_No}">${(roomtypeVO.room_Type_Name)}</option>
			</c:forEach>
		</select>
		
		
		 
		</td>
<!-- 		<td><input type="TEXT" name="room_type_no" size="45" -->
<%-- 			 value="<%= (roomVO==null)? "RT001" : roomVO.getRoom_type_no()%>" /></td> --%>
	</tr>
	
	<tr>
		<td >房號:</td>
		<td ><input type="text" pattern="[0-9]*" name="room_no" size="45" Maxlength="3"
			 value="<%= (roomVO==null)? Integer.parseInt(romm_no_by_list)+1: roomVO.getRoom_no()%>" />
			<span id="addOneCol" class="btn">新增房間</span>
		</td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>房號2:</td> -->
<!-- 		<td><input type="text" pattern="[0-9]*" name="room_no" size="45" -->
<%-- 			 value="<%= (roomVO==null)? Integer.parseInt(romm_no_by_list)+2 : roomVO.getRoom_no()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>房號3:</td> -->
<!-- 		<td><input type="text" pattern="[0-9]*" name="room_no" size="45" -->
<%-- 			 value="<%= (roomVO==null)? Integer.parseInt(romm_no_by_list)+3 : roomVO.getRoom_no()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>房號4:</td> -->
<!-- 		<td><input type="text" pattern="[0-9]*" name="room_no" size="45" -->
<%-- 			 value="<%= (roomVO==null)? Integer.parseInt(romm_no_by_list)+4: roomVO.getRoom_no()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>房號5:</td> -->
<!-- 		<td><input type="text" pattern="[0-9]*" name="room_no" size="45" -->
<%-- 			 value="<%= (roomVO==null)? Integer.parseInt(romm_no_by_list)+5 : roomVO.getRoom_no()%>" /></td> --%>
<!-- 	</tr> -->

	


</table>
	
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增"><a href='listAllRoom.jsp'>Back Room</a>
	
<br>


</FORM>

</div>

</div>

<%@ include file="/back-end/homepage/footer.file" %>
<script>
$(document).ready(function(){

	$("#addOneCol").click(function(){
		var str='';
		str +="<tr><td>房號:</td> "+"<td><input type='text' pattern='[0-9]*' name='room_no' size='45' Maxlength='3' value='"+
				"'/><span class='delOneCol btn'>刪除一列</span></td></tr>";
		$("tr:last").after(str);
	
	});
	
		
// 	$(".delOneCol.btn").click(function(){
// 			 $(this).css('display','none');
// 			alert('!!!');
// 		});
	$("body").on('click',".delOneCol.btn",function(){
		 $(this).parent().parent().remove();
			
		});
	
	
	
})
</script>
</body>

</html>