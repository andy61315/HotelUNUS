<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Sal: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Sal: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Sal: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllSal.jsp'>List</a> all Sal.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="saleplan.do" >
        <b>輸入優惠編號 (如SALP0001):</b>
        <input type="text" name="sapl_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="salSvc" scope="page" class="com.saleplan.model.SalePlanService" />
   
  <li>
     <FORM METHOD="post" ACTION="saleplan.do" >
       <b>選擇優惠編號:</b>
       <select size="1" name="sapl_no">
         <c:forEach var="salVo" items="${salSvc.all}" > 
          <option value="${salVo.sapl_no}">${salVo.sapl_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="saleplan.do" >
       <b>選擇房型:</b>
       <select size="1" name="sapl_name">
         <c:forEach var="salVo" items="${salSvc.all}" > 
          <option value="${salVo.sapl_no}">${salVo.sapl_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>訂房訂單管理</h3>

<ul>
  <li><a href='addSal.jsp'>Add</a> a new Sal.</li>
</ul>

</body>
</html>