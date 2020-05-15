<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- JAVA ZONE -->
<%
	
%>
<!-- JAVA ZONE -->



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<title>Insert title here</title>

<!-- CSS -->

	<!-- bootstrap/4.4.1 -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	<!-- bootstrap-table@1.16.0 -->
	<link href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css" rel="stylesheet">
	
	<!-- fontawesome.com/releases/v5.0.13 -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	
	<!-- limonte-sweetalert2/6.10.3 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />

<!-- CSS -->


<!-- JS -->

	<!-- jquery-3.5.0 -->
	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	
	<script src="https://unpkg.com/tableexport.jquery.plugin/tableExport.min.js"></script>
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table-locale-all.min.js"></script>
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/extensions/export/bootstrap-table-export.min.js"></script>	
	<!-- limonte-sweetalert2 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	
	<!-- 裡面有 jquery 語法需要先引入 jquery -->
	<%@ include file="/back-end/homepage/head.file" %>
<!-- JS -->
</head>
<body>

<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >雇員管理<!-- 自己的標頭名稱功能名稱 --></h2>
    			<hr>
    			</div>
    			
    			<div class="row">
    				<div class="col-md-12">
    					<div class="card">
    						<div class="card-body">
			    				<!-- NAV BAR --> 
				    			<div class="row">
				    				<div class="col-md-12">
				  						<div id="tableToolbar" class="btn-group">
										    <button type="button" class="btn btn-default" title="Add Member" id='btnAdd'>
										        <i class="fa fa-plus"></i>
										    </button>
										    <button type="button" class="btn btn-default" title="Edit Mode" id='btnEdit' >
										        <i class="fa fa-edit"></i>
										    </button>
										    <button type="button" class="btn btn-default" title="View Mode" id='btnView' style='display: none;'>
										        <i class="fas fa-binoculars"></i>
										    </button>
<!-- 										    <button type="button" class="btn btn-default" title="Delete Member" id='btnDelete' disabled> -->
<!-- 										        <i class="fa fa-trash"></i> -->
<!-- 										    </button> -->
									    </div>
			    					</div>
				    			</div>
				    			<!-- NAV BAR -->
				    			
				    			<!-- CONTENT -->
				    			<div class="row">
					    			<div class="col-md-12 emp-table-body">
			 							<table id="demo" class="table" data-unique-id="id" data-height="900" data-sort-name="name"
											data-pagination="true" data-show-columns="false" data-show-toggle="true" data-show-pagination-switch="true"
											data-show-refresh="true" data-search="true" data-page-size="10" data-page-list="[ 5, 10, 20, 50, 100]"
											data-side-pagination="server" data-toolbar="#tableToolbar">
										</table>
					    			</div>
				    			</div>
				    			<!-- CONTENT -->
	    					</div>
	    				</div>
	    			</div>
	    		</div>
	    		
				
<%@ include file="/back-end/homepage/footer.file" %>
<script src="<%= request.getContextPath() %>/back-end/empolyee/js/content.js"></script>
</body>
</html>