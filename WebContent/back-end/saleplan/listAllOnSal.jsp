<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.saleplan.model.*"%>
<%@ page import ="java.util.stream.Collectors" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

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
	<th style="background-color:pink">正在優惠</th>
	</tr>

	<tr>
		<th>優惠編號</th>
		<th>優惠名稱</th>
		<th>優惠內容</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>優惠價格</th>
		<th>優惠狀態</th>
		<th>修改</th>
		<th>新增</th>
		<th>推播</th>
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="sapl_no"  value="${salVo.sapl_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			     
			     
			   </FORM>
			</td>
			<td>
			<button id="addSal" >
					<a href="addSal.jsp">新增</a>
			</button>
			</td>
			<td>
			

			     <input type="submit" id="sendMessage" class="button" value="推播" onclick="sendMessage();"/>
			     
			 
			</td>
		</tr>
	</c:forEach>
</table>


