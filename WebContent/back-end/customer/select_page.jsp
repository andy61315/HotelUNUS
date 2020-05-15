<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Cus: Home</title>

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
   <tr><td><h3>IBM Cus: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Cus: Home</p>

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
  <li><a href='listAllCus.jsp'>List</a> all Cus.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" >
        <b>��J�|���s�� (�pCUS0000001):</b>
        <input type="text" name="cus_Id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" >
        <b>��J�|�������Ҧr��:</b>
        <input type="text" name="id_Num">
        <input type="hidden" name="action" value="getOneId_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  <jsp:useBean id="customerSvc" scope="page" class="com.cus.model.CustomerService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="cus_Id">
         <c:forEach var="customerVO" items="${customerSvc.all}" > 
          <option value="${customerVO.cus_Id}">${customerVO.cus_Id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer/cus.do" >
       <b>��ܷ|���m�W:</b>
       <select size="1" name="cus_Id">
         <c:forEach var="customerVO" items="${customerSvc.all}" > 
          <option value="${customerVO.cus_Id}">${customerVO.cus_Name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�|���޲z</h3>

<ul>
  <li><a href='addCus.jsp'>Add</a> a new Cus.</li>
</ul>

</body>
</html>