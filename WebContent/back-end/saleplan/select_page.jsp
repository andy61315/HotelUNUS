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

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
        <b>��J�u�f�s�� (�pSALP0001):</b>
        <input type="text" name="sapl_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="salSvc" scope="page" class="com.saleplan.model.SalePlanService" />
   
  <li>
     <FORM METHOD="post" ACTION="saleplan.do" >
       <b>����u�f�s��:</b>
       <select size="1" name="sapl_no">
         <c:forEach var="salVo" items="${salSvc.all}" > 
          <option value="${salVo.sapl_no}">${salVo.sapl_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="saleplan.do" >
       <b>��ܩЫ�:</b>
       <select size="1" name="sapl_name">
         <c:forEach var="salVo" items="${salSvc.all}" > 
          <option value="${salVo.sapl_no}">${salVo.sapl_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�q�Эq��޲z</h3>

<ul>
  <li><a href='addSal.jsp'>Add</a> a new Sal.</li>
</ul>

</body>
</html>