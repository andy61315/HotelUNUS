<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>�q��: Home</title>

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
   <tr><td><h3>�q��: Home</h3><h4>( MVC0201 )</h4></td></tr>
</table>

<p>This is the Home page for �\�U�q��</p>

<h3>�q��d��:</h3>
	
<%-- �Y���~��C ������:���󬰮ɤ~���楻�� --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllResMealOrderMaster.jsp'>List:</a> �q���.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="resmom.do" >
        <b>��J�q��s�� (�pRESM-20200323-001):</b>
        
        <input type="text" name="resMealOrderNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="resmomSvc" scope="page" class="com.resmealom.model.ResMealOrderMasterService" />
   
  <li>
     <FORM METHOD="post" ACTION="resmom.do" >
       <b>��ܭq��s��:</b>
       
       <select size="1" name="resMealOrderNo">
         <c:forEach var="resmomVO" items="${resmomSvc.all}" > 
          <option value="${resmomVO.resMealOrderNo}">${resmomVO.resMealOrderNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="resmom.do" >
       <b>��ܭq�нs��:</b>
       <select size="1" name="resMealOrderNo">
         <c:forEach var="resmomVO" items="${resmomSvc.all}" > 
          <option value="${resmomVO.resMealOrderNo}">${resmomVO.bOrderNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�q��޲z</h3>

<ul>
  <li><a href='addResMealOrderMaster.jsp'>Add</a> �q��.</li>
</ul>

</body>
</html>