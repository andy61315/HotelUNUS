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

<!-- 	<!-- bootstrap/4.4.1 --> -->
<!-- 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
	
<!-- 	<!-- sweetalert2 --> -->
<%-- 	<link rel="stylesheet" href="<%= request.getContextPath() %>/back-end/css/sweetalert2.css">  --%>
	
<!-- 	<!-- bootstrap/4.4.1 --> -->
<!-- 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
	
<!-- 	<!-- bootstrap-table --> -->
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap-table.min.css"> --%>
    
<!-- 	<!-- sweetalert2-theme-dark --> -->
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/sweetalert2-theme-dark.scss"> --%>
    <link rel="stylesheet" href="../css/sweetalert2.css">           
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/sweetalert2-theme-dark.scss">
<!-- CSS -->


<!-- JS -->

<!-- 	<!-- jquery-3.4.1 --> -->
<!-- 	<script -->
<!-- 	  src="https://code.jquery.com/jquery-3.4.1.js" -->
<!-- 	  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" -->
<!-- 	  crossorigin="anonymous"> -->
<!-- 	</script> -->
	
<!-- 	<!-- jquery-3.4.1.slim.min --> -->
<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
	
<!-- 	<!-- popper.js@1.16.0 --> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script> -->
	
<!-- 	<!-- bootstrap/4.4.1 --> -->
<!-- 	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
	
<!-- 	<!-- sweetalert2 --> -->
<%-- 	<script src="<%=request.getContextPath()%>/back-end/js/sweetalert2.js"></script> --%>
	
<!-- 	<!-- bootstrap-table --> -->
<!-- 	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script> -->
	<script  src="https://code.jquery.com/jquery-3.4.1.js"  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="  crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="../js/sweetalert2.js"></script>
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
	<!-- 裡面有 jquery 語法需要先引入 jquery -->
	<%@ include file="/back-end/homepage/head.file" %>
	
<!-- JS -->

</head>
<body>

<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >餐點管理<!-- 自己的標頭名稱功能名稱 --></h2>
    			<hr>
    				<!-- NAV BAR --> 
	    			<div class="row">
	    			<div class="col-md-12">
	    				<div class="card">
	    					<div class="card-body">
	    						
	    					</div>
	    				</div>
    				</div>
	    			</div>
	    			<!-- NAV BAR -->
	    			
	    			<hr>
	    			
	    			<!-- CONTENT -->
	    			<div class="row">
	    				<div class="col-md-12">
							<table id="meal-table" class="table" data-unique-id="id" data-height="450" data-sort-name="name"
								data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-pagination-switch="true"
								data-show-refresh="true" data-search="true" data-page-size="10" data-page-list="[ 5, 10, 20 ]"
								data-side-pagination="server" data-toolbar="#tableToolbar">
							</table>
	    				</div>
	    			</div>
	    			<!-- CONTENT -->
	    			
				</div>
<%@ include file="/back-end/homepage/footer.file" %>

<script type="text/javascript">
	var param;
	var mode = 'View';
	
	$(document).ready(function() {
		// bootstrap-table setting
		$('#meal-table').bootstrapTable({
		  columns:[ // 欄位設定
		    {field:'checkbox', title:'選取', align:'center', width:80, visible:false, checkbox:true},
		    {field:'meal_no', title:'代號', align:'center', width:120, visible:true, sortable:true},
		    {field:'mealImgTag', title:'名稱', align:'left', visible:true, sortable:true, sortable:true},
		    {field:'meal_name', title:'Email', align:'left', width:100, visible:true, sortable:true},
		    {field:'res_no', title:'說明', align:'left', width:100, visible:true, sortable:true},
		    {field:'meal_type_no', title:'說明', align:'left', width:100, visible:true, sortable:true},
		    {field:'price', title:'說明', align:'left', width:100, visible:true, sortable:true},
		    {field:'meal_date', title:'說明', align:'left', width:100, visible:true, sortable:true},
		    {field:'meal_status', title:'說明', align:'left', width:100, visible:true, sortable:true},
		    {field:'meal_introduction', title:'說明', align:'left', width:100, visible:true, sortable:true},
		  ],
		  data : [],
          formatLoadingMessage: function () {
        	  return '<i style="color: white; margin: auto; font-size:1rem;">';
          },
		});
		$('.fixed-table-header').css('background-color', '#E2943B');
		
		// step 2. 開始從後端撈資料（第一次）
		param = {action: 'getMealData', pageNo: '1', pageSize: '10', orderBy: 'name', orderType: 'asc', like: ''};
		loadData(param);
		// step 3. 其他設定
		
		
		
	});
	
	
	// 載入資料
	function loadData(param){
// 		$('#meal-table').bootstrapTable('showLoading');
		$.ajax({
			 type: "POST",
			 url: "<%= request.getContextPath()%>/meal/mealJson.do",
			 data: param,
			 dataType: "json",
			 success: function (data){
				alert("成功")
				$('#demo').bootstrapTable("load", data);
				$('#demo').bootstrapTable("refresh", { silent: true });
				$('#demo').bootstrapTable('hideLoading');
		     },
             error: function(){alert("AJAX-grade發生錯誤囉!")}
         });
	}
	
	
	
</script>

</body>
</html>