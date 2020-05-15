<%@page import="com.meal.model.OrderMealVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* " %>
<html>
<head>
 <title>Cart.jsp</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/resmeal/css/ShoppingCart.css">
<style>
  
   table { 
 	width: 800px; 
 	background-color: white; 
 	opacity:0.9;
 	margin-top: 30px; 
/*  	margin-bottom: 5px;  */
   } 
   
   
   table, th, td { 
     border: 1px solid #CCCCFF; 
   } 
  
   

</style>
</head>
<body><br>

   <% @SuppressWarnings("unchecked")
   Vector<OrderMealVO> buylist = (Vector<OrderMealVO>) session.getAttribute("shoppingcart");
   
     
   Integer amount=null;//���o�p���`��
   try{
		amount  = Integer.valueOf((String)session.getAttribute("amount"));
   }catch(NumberFormatException e){
	   amount = 0;
   }
   
   %>
   
<%if (buylist != null && (buylist.size() > 0)) {
//if true
%>

<font size="+3" color="orange">�I�\���e�p�U:</font>

<table>
	<tr>
		<th  width="100">�\�I�s��</th>
		<th  width="100">�W��</th>
		<th  width="100">����</th>
		<th  width="100">�ƶq</th>
		<th  width="100"><img src="<%=request.getContextPath()%>/back-end/resmeal/images/shopping-cart.png" width="45px" height="35px"></th>
		
	</tr>
	<%
	//�qsession���o��list(add�[�J�ѥ�)
	 for (int index = 0; index < buylist.size(); index++) {
		 OrderMealVO order = buylist.get(index);//�]�j����X�������\�I
	%>
	
		<tr>
			<td  width="100"><%=order.getMeal_no() %></td>
			<td  width="100"><%=order.getMeal_name() %></td>
			<td  width="100"><%=order.getPrice() %></td>
			<td  width="100"><%=order.getQuantity() %></td>		
         <td width="120">
          <form name="deleteForm" action="<%=request.getContextPath()%>/back-end/resmeal/Shopping.html" method="POST">
              <input type="hidden" name="action"  value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="�R ��" class="button">
          </form>
         </td>
	</tr>
	
	<%}%>
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">�`���B�G <h4>$<%=amount%></h4> </font>
		   
	    </td>
	</tr>
</table>
<input type="hidden" name="total" id="total" value="<%=amount %>">

<p>

<%}%>
</body>
</html>