<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.saleplan.model.*"%>
<%@ page import ="java.util.stream.Collectors" %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    SalePlanService SalSvc = new SalePlanService();
    List<SalePlanVO> list = SalSvc.getAll();
   	List<SalePlanVO> listSal = list.stream().filter(s -> s.getStatus()==1).collect(Collectors.toList());
    pageContext.setAttribute("listSal",listSal);
%>

<style>
#onSal{
	margin-top:10%;
}

</style>

<table id="onSal" >
<tr>
	<th style="background-color:pink">���b�u�f</th>
	</tr>

	<tr>
		<th>�u�f�s��</th>
		<th>�u�f�W��</th>
		<th>�u�f���e</th>
		<th>�}�l���</th>
		<th>�������</th>
		<th>�u�f����</th>
		<th>�u�f���A</th>
		<th>�ק�</th>
		<th>�s�W</th>
		<th>����</th>
	</tr>
	<c:forEach var="salVo" items="${listSal}" >
		
		<tr${(salVo.sapl_no==param.sapl_no) ? 'color=#CCCCFF':''}>
			<td>${salVo.sapl_no}</td>
			<td>${salVo.sapl_name}</td>
			<td>${salVo.detail}</td>
			<td>${salVo.start_date}</td>
			<td>${salVo.end_date}</td> 
			<td>${salVo.sapl_discount}</td> 
			<td>${saplStatus[salVo.status]}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/saleplan/saleplan.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="sapl_no"  value="${salVo.sapl_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			     
			     
			   </FORM>
			</td>
			<td>
			<button id="addSal" >
					<a href="addSal.jsp">�s�W</a>
			</button>
			</td>
			<td>
			

			     <input type="submit" id="sendMessage" class="button" value="����" onclick="sendMessage();"/>
			     
			 
			</td>
		</tr>
	</c:forEach>
</table>


