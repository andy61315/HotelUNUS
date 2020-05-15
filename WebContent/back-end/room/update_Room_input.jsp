<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="java.util.*"%>

<%
	RoomVO roomVo = (RoomVO) request.getAttribute("roomVo"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	RoomTypeService roomTypeSve = new RoomTypeService();
	RoomTypeVO roomTypeVO = new RoomTypeVO();
	List<RoomTypeVO> roomTypelist = roomTypeSve.getAll();
	pageContext.setAttribute("roomTypelist", roomTypelist);
	pageContext.setAttribute("roomTypeSve", roomTypeSve);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file"%>

</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<%@ include file="/back-end/homepage/middle.file"%>

	<div id="lineQA" class="tableStyle">
		<h2 class="col-12 title">
			房間修改
			<!-- 自己的標頭名稱功能名稱 -->
		</h2>
		<div class="col-12">

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<FORM METHOD="post" ACTION="room.do" name="form1">
				<table>

					<tr>
						<td>房型:</td>
						<td><select size="1" name="room_type_no">
								<option value="<%=roomVo.getRoom_type_no()%>">${roomTypeSve.findOneByNo(roomVo.room_type_no).room_Type_Name}
									<c:forEach var="roomtypeVO" items="${roomTypelist}">
										<option value="${roomtypeVO.room_Type_No}">${(roomtypeVO.room_Type_Name)}</option>
									</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>房號:</td>
						<td><%=roomVo.getRoom_no()%></td>
					</tr>




					<tr>
						<td colspan="2"><input type="hidden" name="action"
							value="update"> 
							<input type="hidden" name="room_id" value="<%=roomVo.getRoom_id()%>"> 
							<input type="hidden" name="room_no" value="<%=roomVo.getRoom_no()%>"> 
							<input type="hidden"
							name="requestURL" value="<%=request.getParameter("requestURL")%>">
							<input type="hidden" name="whichPage"
							value="<%=request.getParameter("whichPage")%>"> <input
							type="submit" value="送出修改"><a href='listAllRoom.jsp'>Back
								Room</a></td>
					</tr>
				</table>
			</FORM>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>