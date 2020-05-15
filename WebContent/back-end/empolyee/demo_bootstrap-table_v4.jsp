<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap-table (from Backend)</title>
<%-- 	<link rel="stylesheet" href="<%= request.getContextPath() %>/back-end/empolyee/css/bootstrap-3.3.7.min.css"> --%>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
	
	
	
	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	
	<style>
		.pointer {cursor: pointer;}
	</style>
</head>
<body>


<div class="container">

	<div id="tableToolbar" class="btn-group">
	    <button type="button" class="btn btn-default" title="Add Member" id='btnAdd'>
	        <i class="glyphicon glyphicon-plus"></i>
	    </button>
	    <button type="button" class="btn btn-default" title="Edit Mode" id='btnEdit'>
	        <i class="glyphicon glyphicon-edit"></i>
	    </button>
	    <button type="button" class="btn btn-default" title="View Mode" id='btnView' style='display: none;'>
	        <i class="glyphicon glyphicon-eye-open"></i>
	    </button>
	    <button type="button" class="btn btn-default" title="Delete Member" id='btnDelete' disabled>
	        <i class="glyphicon glyphicon-trash"></i>
	    </button>
     </div>
	<table id="demo" class="table" data-unique-id="id" data-height="530" data-sort-name="name"
		data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-pagination-switch="true"
		data-show-refresh="true" data-search="true" data-page-size="10" data-page-list="[ 5, 10, 20, 50, 100]"
		data-side-pagination="server" data-toolbar="#tableToolbar">
	</table>


</div>


	<script src="<%= request.getContextPath() %>/back-end/empolyee/js/content.js"></script>
	
</body>
</html>