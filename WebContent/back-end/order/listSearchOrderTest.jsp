<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

</head>
<body>



<div class="container">
    <div class="row">
    	<div class="col-md-3">
    		<div class="card">
    			<div class="card-body">
    				<div class="row">
    					<div class="col-md-12">
    						<div class="form-group ">
    							<button type="button" class="btn btn-primary form-control" title="Add Member" id='btnAdd'>新增訂單</button>
    						</div>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                	<form action="<%=request.getContextPath()%>/order/order.do" method="post">
                    <div class="row">
		                <div class="col-md-3">
		                    <div class="form-group ">
		               	  		<input type="text" name="room_meal_order_no" placeholder="請輸入訂餐訂單編號..." id="inputState" class="form-control">
		                    </div>
		                </div>
		                <div class="col-md-3">
		                    <div class="form-group ">
		                    	<input type="text" name="b_order_no" placeholder="請輸入訂房訂單編號..." id="inputState" class="form-control">
		                    </div>
		                </div>
		                <div class="col-md-3">
		               		 <div class="form-group ">
								<select size="1" name="ro_order_status" id="inputState" class="form-control">
									<option value=""></option>
									<option value="0">備餐中</option>
									<option value="1">出餐中</option>
									<option value="2">已出餐</option>
									<option value="3">已完成</option>
									<option value="4">訂餐取消</option>
								</select>
		                	</div>
		                </div>
		                <div class="col-md-3">
		               	 	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="listOrderMasters_ByCompositeQuery">
							<input type="submit" value="搜尋" id="searchOrderMaster" class="btn btn-primary form-control">
		                </div>
            		</div>
            		</form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    <jsp:include page="listOrderMasters_ByCompositeQuery.jsp" />
    </div>
</div>




	
</body>
</html>