<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listRoom_ByCompositeQuery" scope="request" type="java.util.List<RoomVO>" /> <!-- 於EL此行可省略 -->

<%
RoomTypeService roomTypeSvc = new RoomTypeService();
List<RoomTypeVO> roomTypelist = roomTypeSvc.getAll();
pageContext.setAttribute("roomTypeSvc",roomTypeSvc);
%>
<h4>查詢結果</h4>
<table>
	<tr>
	
		<th>房型</th>
		<th>房號</th>
		<th>房間狀態</th>
		<th>清掃狀態</th>
		<th>房客姓名</th>
		<th>房客電話</th>
		
	</tr>
	<%@ include file="page1_ByCompositeQuery.file"%>
	<c:forEach var="roomVO" items="${listRoom_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(roomVO.room_id==param.room_id) || (roomVO.room_no==param.room_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			
			<td>${roomTypeSvc.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
			<td>${roomVO.room_no}</td>
			<td>${roomStatus[roomVO.room_status]}</td>
			<td>${cleanStatus[roomVO.clean_status]}</td>
			<td>${(roomVO.tenant_name)==null ||(roomVO.tenant_name)=="" ? '無房客': (roomVO.tenant_name)}</td>
			<td>${(roomVO.tenant_phone)==null || (roomVO.tenant_phone)=="" ? '無電話' :(roomVO.tenant_phone)}</td>		
		</tr>
	</c:forEach>		

</table>
<%@ include file="page2_ByCompositeQuery.file" %>