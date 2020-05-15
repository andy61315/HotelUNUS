<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap-table (from Backend)</title>
	<link rel="stylesheet" href="css/bootstrap-3.3.7.min.css">
	<link rel="stylesheet" href="css/bootstrap-table-1.11.0.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/bootstrap-3.3.7.min.js"></script>
	<script src="js/bootstrap-table-1.11.0.min.js"></script>
</head>
<body>
	<!-- step 1. 多這個屬性設定 data-side-pagination="server" -->
	<table id="demo" class="table" data-unique-id="id" data-height="450" data-sort-name="name"
		data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-pagination-switch="true"
		data-show-refresh="true" data-search="true" data-page-size="10" data-page-list="[ 5, 10, 20, 50, 100]"
		data-side-pagination="server">
	</table>
	
	<script type="text/javascript">
		var param;
		$(document).ready(function(){
			// bootstrap-table setting
			$('#demo').bootstrapTable({
			  columns:[ // 欄位設定
			    {field:'checkbox', title:'選取', align:'center', width:80, visible:true, checkbox:true},
			    {field:'emp_id', title:'emp_id', align:'left', visible:true, sortable:true},
			    {field:'emp_name', title:'emp_name', align:'left', width:200, visible:true},
			    {field:'emp_phone', title:'emp_phone', align:'left', width:200, visible:true},
			    {field:'emp_email', title:'emp_email', align:'left', width:200, visible:true},
			    {field:'emp_password', title:'emp_password', align:'left', width:200, visible:true},
			    {field:'emp_status', title:'emp_status', align:'left', width:200, visible:true},
			  ],
			  data : [],
	          formatLoadingMessage: function () {
	          	  return '<i class="fa fa-spinner fa-pulse fa-2x fa-fw" style="color: darkorange; margin: 4px 0 0 8px; font-size:4.5rem;">';
	          },
			});
			$('.fixed-table-header').css('background-color', 'lightblue');
			
			// step 2. 開始從後端撈資料（第一次）
			param = {action: 'getData', pageNo: '1', pageSize: '10', orderBy: 'emp_id', orderType: 'asc', like: ''};
			loadData(param);
			
			// step 3. 其他設定
			$('#demo').on('sort.bs.table', function(e, name, order) {
				param.orderBy = name;
				param.orderType = order;
				loadData(param);
			}).on('page-change.bs.table', function(e, number, size) {
				var like = $('#demo').parents('.bootstrap-table').find('.search input').val();
				if(!like && !like.trim()){ // bug: 避免search會進來
					param.pageNo = number;
					param.pageSize = size;
					loadData(param);
				}
			}).on('search.bs.table', function(e, text){
				param.like = text.trim();
				param.pageNo = '1';
				loadData(param);
			});
		});
		
		function loadData(param){
			$('#demo').bootstrapTable('showLoading');
			$.ajax({
				 type: "GET",
				 url: "../../employee/emp.do",
				 data: param,
				 dataType: "json",
				 success: function (data){
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