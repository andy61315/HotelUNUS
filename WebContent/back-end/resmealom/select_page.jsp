<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂單: Home</title>

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
   <tr><td><h3>訂單: Home</h3><h4>( MVC0201 )</h4></td></tr>
</table>

<p>This is the Home page for 餐廳訂單</p>

<h3>訂單查詢:</h3>
	
<%-- 若錯誤表列 不為空:條件為時才執行本體 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllResMealOrderMaster.jsp'>List:</a> 訂單們.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="resmom.do" >
        <b>輸入訂單編號 (如RESM-20200323-001):</b>
        
        <input type="text" name="resMealOrderNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="resmomSvc" scope="page" class="com.resmealom.model.ResMealOrderMasterService" />
   
  <li>
     <FORM METHOD="post" ACTION="resmom.do" >
       <b>選擇訂單編號:</b>
       
       <select size="1" name="resMealOrderNo">
         <c:forEach var="resmomVO" items="${resmomSvc.all}" > 
          <option value="${resmomVO.resMealOrderNo}">${resmomVO.resMealOrderNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="resmom.do" >
       <b>選擇訂房編號:</b>
       <select size="1" name="resMealOrderNo">
         <c:forEach var="resmomVO" items="${resmomSvc.all}" > 
          <option value="${resmomVO.resMealOrderNo}">${resmomVO.bOrderNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>訂單管理</h3>

<ul>
  <li><a href='addResMealOrderMaster.jsp'>Add</a> 訂單.</li>
</ul>

</body>
</html>