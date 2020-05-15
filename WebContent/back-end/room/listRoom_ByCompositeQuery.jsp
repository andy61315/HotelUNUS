<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listRoom_ByCompositeQuery" scope="request" type="java.util.List<RoomVO>" /> <!-- ��EL����i�ٲ� -->

<%
RoomTypeService roomTypeSvc = new RoomTypeService();
List<RoomTypeVO> roomTypelist = roomTypeSvc.getAll();
pageContext.setAttribute("roomTypeSvc",roomTypeSvc);
%>
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
	<%@ include file="page1_ByCompositeQuery.file"%>
	<c:forEach var="roomVO" items="${listRoom_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(roomVO.room_id==param.room_id) || (roomVO.room_no==param.room_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			
			<td>${roomTypeSvc.findOneByNo(roomVO.room_type_no).room_Type_Name}</td>
			<td>${roomVO.room_no}</td>
			<td>${roomStatus[roomVO.room_status]}</td>
			<td>${cleanStatus[roomVO.clean_status]}</td>
			<td>${(roomVO.tenant_name)==null ||(roomVO.tenant_name)=="" ? '�L�Ы�': (roomVO.tenant_name)}</td>
			<td>${(roomVO.tenant_phone)==null || (roomVO.tenant_phone)=="" ? '�L�q��' :(roomVO.tenant_phone)}</td>		
		</tr>
	</c:forEach>		

</table>
<%@ include file="page2_ByCompositeQuery.file" %>