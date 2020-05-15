<%@page import="com.meal.model.OrderMealVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* " %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

 <title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/resmeal/css/ShoppingCart.css">
 <!-- CSS -->

	<!-- bootstrap/4.4.1 -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
<!-- CSS -->


<!-- JS -->

	<!-- jquery-3.4.1 -->
	<script
	  src="https://code.jquery.com/jquery-3.4.1.js"
	  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	  crossorigin="anonymous">
	</script>
	
	<!-- jquery-3.4.1.slim.min -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	
	<!-- popper.js@1.16.0 -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	
	<!-- bootstrap/4.4.1 -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- 裡面有 jquery 語法需要先引入 jquery -->
	<%@ include file="/back-end/homepage/reshead.file" %>
	
<!-- JS -->
<style>
table#table1 {  
 	margin-top: 50px; 
 	margin-left: 20px;
 	opacity:0.9;  
   } 
   
   table#table2 { 
/*  	width:500px; */
    margin-left: 20px;  
 	margin-bottom: 5px;
 	opacity:0.9; 
   } 
   
   table, th, td { 
     border: 1px solid #CCCCFF; 
   } 


</style>
<script>
   $(document).ready(function(){
	  <c:if test="${'CHECKOUT'.equals(param.action)}">
	  swal("請於退房時付款", "Good job!", "success");
	  </c:if>
	});
 </script>
</head>

 
<body>
<%@ include file="/back-end/homepage/middle.file" %> 

		<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >點餐系統<!-- 自己的標頭名稱功能名稱 --></h2>
                <font size="+3"> 請於checkout時結帳</font>
<hr><p>

<table id="table1">
	<tr>
		
		<th width="100">餐點名稱</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"><h3>總價</h3></th>
	</tr></table>
	
	<table id="table2">

	<%  @SuppressWarnings("unchecked")
		Vector<OrderMealVO> buylist = (Vector<OrderMealVO>) session.getAttribute("shoppingcart");//結帳照樣要秀出購物車書本
		String amount =  (String) session.getAttribute("amount");//購買總金額
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
		   OrderMealVO order = buylist.get(i);
			
			String name = order.getMeal_name();
			Integer price = order.getPrice();
			Integer quantity = order.getQuantity();
			//跑迴圈將購物車中的書明細列出
	%>
	<tr>
		<td width="100"><%=name%>     </td>
		<td width="100"><%=price%>   </td>
		<td width="100"><%=quantity%></td>
		<td width="120"></td>
	</tr>
<!-- 	   動態生成筆數 -->
	<%
		}
	%>
	 
	
	<tr>
		<td colspan="4" style="text-align:right;"> 
		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font>
	    </td>
	</tr>
	<tr>
		<td colspan="4" style="text-align:right;"> 
		   <form method="POST" name="checkoutForm" action="<%=request.getContextPath()%>/back-end/resmeal/Shopping.html" >
              <input type="hidden" name="action"  value="pay"> 
              <input type="submit" value="確認" class="button">
          </form>
	    </td>
	</tr>
</table>
       
       <p><a href="<%=request.getContextPath()%>/back-end/resmeal/listOneShop.jsp"><font size="+1"> 回點餐畫面</font></a>
       <p><a href="<%=request.getContextPath()%>/back-end/restaurant/listAllRestaurant.jsp"><font size="+1"> 回首頁</font></a>
</div>
<%@ include file="/back-end/homepage/footer.file" %>
</body>
</html>