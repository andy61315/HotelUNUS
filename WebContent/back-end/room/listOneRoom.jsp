<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); 
RoomTypeService roomTypeSvc = new RoomTypeService();
List<RoomTypeVO> roomTypelist = roomTypeSvc.getAll();
pageContext.setAttribute("roomTypeSvc",roomTypeSvc);
%>

 <!-- ��EL����i�ٲ� -->
<h4>�d�ߵ��G</h4>
<table>
	<tr>
		
		<th>�Ы�</th>
		<th>�и�</th>
		<th>�ж����A</th>
		<th>�M�����A</th>
		<th>�Ыȩm�W</th>
		<th>�Ыȹq��</th>
	</tr>
	<tr>
		
		<td>${roomTypeSvc.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
		<td><%=roomVO.getRoom_no()%></td>
		<td>${roomStatus[roomVO.room_status]}</td>
		<td>${cleanStatus[roomVO.clean_status]}</td>
		<%if(roomVO.getTenant_name()==null ||roomVO.getTenant_name().length()==0){
			roomVO.setTenant_name("�L�Ы�");
		}%>
		<td><%=roomVO.getTenant_name()%></td>
		<%if(roomVO.getTenant_phone()==null ||roomVO.getTenant_phone().length()==0){
			roomVO.setTenant_phone("�L�q��");
		}%>
		<td><%=roomVO.getTenant_phone()%></td>
	</tr>
</table>
