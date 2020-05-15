<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Restaurant: Home</title>

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
   <tr><td><h3>Restaurant: Home</h3><h4>( MVC0201 )</h4></td></tr>
</table>

<p>This is the Home page for Restaurant: Home</p>

<h3>餐廳查詢:</h3>
	
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
  <li><a href='listAllRestaurant.jsp'>List</a> all Restaurants.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="rest.do" >
        <b>輸入餐廳編號 (如REST000001):</b>
        <input type="text" name="resNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService" />
   
  <li>
     <FORM METHOD="post" ACTION="rest.do" >
       <b>選擇餐廳編號:</b>
       <select size="1" name="resNo">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.resNo}">${restVO.resNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="rest.do" >
       <b>選擇餐廳名稱:</b>
       <select size="1" name="resNo">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.resNo}">${restVO.resName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>餐廳管理</h3>

<ul>
  <li><a href='addRestaurant.jsp'>Add</a>: new a Restaurant.</li>
</ul>

</body>
</html>