<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); 
RoomTypeService roomTypeSvc = new RoomTypeService();
List<RoomTypeVO> roomTypelist = roomTypeSvc.getAll();
pageContext.setAttribute("roomTypeSvc",roomTypeSvc);
%>

 <!-- 於EL此行可省略 -->
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
	<tr>
		
		<td>${roomTypeSvc.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
		<td><%=roomVO.getRoom_no()%></td>
		<td>${roomStatus[roomVO.room_status]}</td>
		<td>${cleanStatus[roomVO.clean_status]}</td>
		<%if(roomVO.getTenant_name()==null ||roomVO.getTenant_name().length()==0){
			roomVO.setTenant_name("無房客");
		}%>
		<td><%=roomVO.getTenant_name()%></td>
		<%if(roomVO.getTenant_phone()==null ||roomVO.getTenant_phone().length()==0){
			roomVO.setTenant_phone("無電話");
		}%>
		<td><%=roomVO.getTenant_phone()%></td>
	</tr>
</table>
