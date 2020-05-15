<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MealService mealSvc = new MealService();
    List<MealVO> list = mealSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��\�I��� - listAll.jsp</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/resmeal/css/ShoppingCart.css">
<style>
  

/*   table#table-1 h4 { */
/*     color: red; */
/*     display: block; */
/*     margin-bottom: 1px; */
/*   } */
   h4 { 
     color: blue;
     display: inline; 
  } 
</style>

<style>
/*   table { */
/* 	width: 1000px; */
/* 	background-color: white; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
/*   } */
/*   table, th, td { */
/*     border: 1px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 5px; */
/*     text-align: center; */
/*   } */
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��\�I - listAll.jsp</h3>
		 <h4>shop����</h4>
	</td></tr>
<form name="checkoutForm" id="checkoutForm" action="<%=request.getContextPath()%>/back-end/resmeal/Shopping.html" method="POST">
	<h3>�п�J: </h3>
	<h4>�ู <input type="text" name="table_no" value=""placeholder="����"></h4>
	<h4>�и� <input type="text" name="room_no" value=""></h4>
	<h4>�S��n�D <input type="text" name="require" value=""></h4>
	<input type="hidden" name="total" value="" id="hiddenTotal">
	<input type="hidden" name="action"  value="CHECKOUT2 "> 
	
</form>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">��J��Ʀ��~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th  width="100">�\�I�s��</th>
		<th  width="100">�W��</th>
		<th  width="100">����</th>
		<th  width="100">�Ϥ�</th>
		<th  width="100">�ƶq</th>
		<th  width="100"><img src="images/shopping-cart.png" width="45px" height="35px"></th>
		
	</tr>
</table>

<c:forEach var="mealVO" items="${list}" >
<form name="shoppingForm" action="<%=request.getContextPath()%>/back-end/resmeal/Shopping.html" method="POST">	
<table>
	
		<tr>
			<td  width="100">${mealVO.meal_no}</td>
			<td  width="100">${mealVO.meal_name}</td>
			<td  width="100">${mealVO.price}</td>
			<td  width="100">${mealVO.meal_picture}</td>
			<td  width="100"><div align="center">�ƶq�G<select name="quantity">
			                                              <option value=1>1</option>
			                                              <option value=2>2</option>
			                                              <option value=3>3</option></select></td>
			<td  width="100"><div align="center"> <input type="submit" class="button" value="�[�J�\�I"> </div></td>
		</tr>	

</table>
      <input type="hidden" name="meal_no" value="${mealVO.meal_no}">
      <input type="hidden" name="meal_name" value="${mealVO.meal_name}">
      <input type="hidden" name="price" value="${mealVO.price}">
      <input type="hidden" name="meal_picture" value="${mealVO.meal_picture}">
      <input type="hidden" name="action" value="ADD">
</form>
</c:forEach>



<c:if test="${shoppingcart!=null}">
<jsp:include page="/back-end/resmeal/Cart.jsp" flush="true" />
</c:if>

<input type="submit" value="�I�ڵ��b" class="button" id="checkOut" onclick="checkOut()">
</body>
<script>

function checkOut(){
	var total = document.getElementById("total").value;
	document.getElementById("hiddenTotal").value=total;
	document.getElementById("checkoutForm").submit();
}
</script>
</html>