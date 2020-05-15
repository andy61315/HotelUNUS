<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.saleplan.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.sod.model.*"%>
<%@ page import="java.util.*"%>

<%
  	SalePlanVO  salVo =(SalePlanVO)request.getAttribute("salVo");
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> roomTypeList = roomTypeSvc.getAll();
	pageContext.setAttribute("roomTypeList",roomTypeList);
	SaleOrderDetailService sodSvc= new SaleOrderDetailService();
	List<SaleOrderDetailVO>  sodList = sodSvc.getSodBySalNo(salVo.getSapl_no());
	pageContext.setAttribute("sodList",sodList);
	pageContext.setAttribute("roomTypeSvc",roomTypeSvc);


%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file" %> 
<style type="text/css">
#updateSalSubmit{

top:300px;
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
<FORM METHOD="post" ACTION="saleplan.do" name="form1">
<table>
	<tr>
		<td>優惠編號:<font color=red><b>*</b></font></td>
		<td><%=salVo.getSapl_no()%></td>
	</tr>
	<tr>
		<td>優惠名稱:</td>
		<td><input type="TEXT" name="sapl_name" size="45"	value="<%=salVo.getSapl_name()%>" /></td>
	</tr>
	<tr>
		<td>優惠內容:</td>
		<td><input type="TEXT" name="detail" size="45"	value="<%=salVo.getDetail()%>" /></td>
	</tr>
	
	<tr>
		<td>開始日期:</td>
		<td><input type="hidden" name="start_date" size="45"	value="<%=salVo.getStart_date()%>" /><%=salVo.getStart_date()%></td>
	</tr>
	<tr>
		<td>結束日期:</td>
		<td><input type="hidden" name="end_date" size="45" value="<%=salVo.getEnd_date()%>" /><%=salVo.getEnd_date()%></td>
	</tr>
	<tr>
		<td>優惠折扣:</td>
		<td><input type="hidden" name="sapl_discount" size="45" value="<%=salVo.getSapl_discount()%>" /><%=salVo.getSapl_discount()%></td>
	</tr>
	<tr>
		<td>優惠狀態:</td>
		<td><select size="1" name="status" >
		<option value="${salVo.status}">${saplStatus[salVo.status]}</option>
          <option value="0">下架</option>
          <option value ="1">上架</option>
  		  <option value ="2">待審核</option>
       </select></td>
	</tr>
	<tr>
	<td>已有房型:</td>
	<td>
	<c:forEach items="${sodList}" var="sodRoomType" varStatus="status" >
	<label>${roomTypeSvc.findOneByNo(sodRoomType.room_type_no).room_Type_Name}<br></label>
	</c:forEach>
	</td>

	

</table>

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="sapl_no" value="<%=salVo.getSapl_no()%>">
<input type="submit" value="送出修改" id="updateSalSubmit"></FORM>
   			</div>
				</div>
<%@ include file="/back-end/homepage/footer.file" %>
</body>
</html>