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
	<link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">
	
	<!-- fontawesome.com/releases/v5.0.13 -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	
	<!-- limonte-sweetalert2/6.10.3 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />

<!-- CSS -->


<!-- JS -->

	<!-- jquery-3.5.0 -->
	<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
	
	<!-- jquery-3.4.1.slim.min -->
<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
	
	<!-- popper.js@1.16.0 -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script> -->
	
	<!-- bootstrap/4.4.1 -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
	<!-- bootstrap-table@1.16.0 -->
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
	
	<!-- limonte-sweetalert2 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	
	<!-- 裡面有 jquery 語法需要先引入 jquery -->
	<%@ include file="/back-end/homepage/head.file" %>
<!-- JS -->

</head>
<body>

<%@ include file="/back-end/homepage/middle.file" %> 

				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >餐點管理<!-- 自己的標頭名稱功能名稱 --></h2>
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
					    			<div class="col-md-12 meal-table-body">
			 							<table id="demo" class="table" data-unique-id="id" data-height="750" data-sort-name="name"
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
<script type="text/javascript">
var param;
var mode = 'View';
$(document).ready(function(){
	// bootstrap-table setting
	$('#demo').bootstrapTable({
	  columns:[ // 欄位設定
	    {field:'checkbox', title:'選取', align:'center', width:80, visible:false, checkbox:true},
	    {field:'meal_no', title:'餐點編號', align:'center', width:120, visible:true, sortable:true},
	    {field:'mealImgTag', title:'餐點圖片', align:'center', width:80, visible:true},
	    {field:'meal_name', title:'餐點名稱', align:'center', width:120, visible:true, sortable:true},
	    {field:'price', title:'餐點價格', align:'center', width:200, visible:true},
	    {field:'resName', title:'餐廳名稱', align:'center', width:200, visible:true},
	    {field:'type_name', title:'餐點類型', align:'center', width:200, visible:true},
	    {field:'meal_status_name', title:'餐點狀態', align:'center', width:100, visible:true, sortable:true},
	    {field:'meal_introduction', title:'餐點介紹', align:'center', width:100, visible:true, sortable:true},
	  ],
	  data : [],
//      formatLoadingMessage: function () {
//    	  return '<i style="color: white; margin: auto; font-size:1rem;">';
//      	  return '<i class="fa fa-spinner fa-pulse fa-2x fa-fw" style="color: darkorange; margin: 4px 0 0 8px; font-size:4.5rem;">';
//      },
	});
	$('.fixed-table-header').css('background-color', '#E2943B');
	
	// step 2. 開始從後端撈資料（第一次）
	param = {action: 'getMealData', pageNo: '1', pageSize: '10', orderBy: 'meal_no', orderType: 'asc', like: ''};
	loadData(param);
	
	// step 3. 其他設定
	$('#demo').on('sort.bs.table', function(e, meal_no, order) {
		param.orderBy = meal_no;
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
	}).on('click-row.bs.table', function (e, row, element, field) {
			console.log(mode);
    		if(mode === 'Edit'){
			swal({
	    			title: '編輯餐點',
	    			html:
		    			'<form enctype="multipart/form-data" method="POST" name="mealForm">' +
		    			  '<div class="form-group">' +
		    			    '<label for="meal_picture" class="pull-left">' +
		    			    	'餐點相片：' + 
		    			    '</label>' +
		    			    '<input type="file" class="form-control" id="meal_picture" accept="image/*">' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="meal_name" class="pull-left">餐點名稱：</label>' +
		    			    '<input type="text" class="form-control" id="meal_name" placeholder="姓名" value="'+ row.meal_name +'">' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="price" class="pull-left">價格：</label>' +
		    			    '<input type="text" class="form-control" id="price" placeholder="100" value="'+ row.price +'">' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="res_no" class="pull-left">餐廳名稱：</label>' +
		    			    '<select id="res_no" class="form-control">' +
			    				'<option value="REST000001" "' +  ((row.res_no === 'REST000001')?'" selected>':'">') +'中式餐廳</option>' +
			    				'<option value="REST000002" "' +  ((row.res_no === 'REST000002')?'" selected>':'">') +'日式餐廳</option>' +
			    				'<option value="REST000003" "' +  ((row.res_no === 'REST000003')?'" selected>':'">') +'西式餐廳</option>' +
	    					'</select>' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="meal_type_no" class="pull-left">餐點類別：</label>' +
		    			    '<select id="meal_type_no" class="form-control">' +
			    				'<option value="MT001" "' +  ((row.meal_type_no === 'MT001')?'" selected>':'">') +'主食</option>' +
			    				'<option value="MT002" "' +  ((row.meal_type_no === 'MT002')?'" selected>':'">') +'鍋物</option>' +
	    					'</select>' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="meal_introduction" class="pull-left">餐點介紹：</label>' +
		    			    '<input type="text" class="form-control" id="meal_introduction" placeholder="輸入介紹" value="'+ row.meal_introduction +'">' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="meal_status" class="pull-left">狀態：</label>' +
		    			    '<select id="meal_status" class="form-control">' +
			    				'<option value="0" "' +  ((row.meal_status === 0)?'" selected>':'">') +'下架</option>' +
			    				'<option value="1" "' +  ((row.meal_status === 1)?'" selected>':'">') +'上架</option>' +
		    				'</select>' +
		    			  '</div>' +
		    			'</form>',
	    			type: "warning",
	    			showCancelButton: true,
	    			preConfirm: function () {
                    return new Promise(function (resolve, reject) {
                    		debugger;

                		let mealForm = document.forms.namedItem('mealForm');
                    	let meal_picture = $('#meal_picture')[0].files[0];
                    	let res_no = $('#res_no').val();
                    	let meal_type_no = $('#meal_type_no').val();
                    	let meal_name = $('#meal_name').val();
                    	let meal_status = $('#meal_status').val();
                    	let meal_introduction = $('#meal_introduction').val();
                    	let price = $('#price').val();
                    	let meal_date = row.meal_date;
                    	let meal_no = row.meal_no;

                    	let data = new FormData(mealForm);
                    	data.append('action', 'update');
                    	data.append('res_no', res_no);
                    	data.append('meal_type_no', meal_type_no);
                    	data.append('meal_name', meal_name);
                    	data.append('meal_status', meal_status);
                    	data.append('meal_introduction', meal_introduction);
                    	data.append('meal_picture', meal_picture);
                    	data.append('meal_date', meal_date);
                    	data.append('meal_no', meal_no);
                    	data.append('price', price);
                    	
                    	console.log("action : "+data.get('action'));
                    	console.log("res_no : "+data.get('res_no'));
                    	console.log("meal_type_no : "+data.get('meal_type_no'));
                    	console.log("meal_name : "+data.get('meal_name'));
                    	console.log("meal_status : "+data.get('meal_status'));
                    	console.log("meal_introduction : "+data.get('meal_introduction'));
                    	console.log("meal_date : "+data.get('meal_date'));
                    	console.log("price : "+data.get('price'));
                    	console.log("meal_no : "+meal_no);
                    	console.log("meal_date : "+meal_date);

                    	
                    	
                    		
//	                        var data = {};
//	                        data.action = 'update';
//	                        data.id = row.id;
//	                        data.name = $('#name').val().trim();
//	                        data.email = $('#email').val().trim();
//	                        data.description = $('#description').val().trim();
                		if (!res_no) {
                			$('#res_no').focus();
                			reject('請選餐廳！');
                		} else if (!meal_name) {
                			$('#meal_name').focus();
                			reject('請輸入名稱！');
                		} else if (!meal_type_no) {
                			$('#meal_type_no').focus();
                			reject('請選類別！');
                		} else if (!meal_status) {
                			$('#meal_status').focus();
                			reject('請選擇狀態！');
                		} else if (!price) {
                			$('#price').focus();
                			reject('請輸入金額！');
                		} else resolve(data);
                    })
                },
                onOpen: function () {
                    $('#meal_name').focus();
                },
	    		}).then(function (data) {
	    			if (data) {
	    		    		updateData(data, function(result){
	    		    			let resultJSON = JSON.parse(result);
	    		    			if(resultJSON.success === 'Y')
			    		    		loadData(param);
	    		    				swal({
				    		    		type: 'success',
				    		    		title: '修改成功',
				    		   		html: 
				    		   			'<b>修改完成</b>',
				    		    }).catch(swal.noop);
	    		    		}, function(result){
	    		    			swal({
			    		    		type: 'error',
			    		    		title: '修改失敗',
			    		   		html: '<b>失敗訊息是：</b> AJAX error!!!'
			    		    }).catch(swal.noop);
	    		    		});
	    			}
	    		}).catch(swal.noop);
        	}
    });
	
	$('#btnAdd').on('click', function(){
  		swal({
    			title: '新增餐點',
    			html:
    				'<form enctype="multipart/form-data" method="POST" name="mealForm">' +
	    			  '<div class="form-group">' +
	    			    '<label for="meal_picture" class="pull-left">' +
	    			    	'餐點相片：' + 
	    			    '</label>' +
	    			    '<input type="file" class="form-control" id="meal_picture" accept="image/*">' +
	    			  '</div>' +
	    			  '<div class="form-group">' +
	    			    '<label for="meal_name" class="pull-left">餐點名稱：</label>' +
	    			    '<input type="text" class="form-control" id="meal_name" placeholder="餐點名稱" value="雙頭牛肉">' +
	    			  '</div>' +
	    			  '<div class="form-group">' +
	    			    '<label for="price" class="pull-left">價格：</label>' +
	    			    '<input type="text" class="form-control" id="price" placeholder="100" value="280">' +
	    			  '</div>' +
	    			  '<div class="form-group">' +
	    			    '<label for="res_no" class="pull-left">餐廳名稱：</label>' +
	    			    '<select id="res_no" class="form-control">' +
		    				'<option value="REST000001">中式餐廳</option>' +
		    				'<option value="REST000002">日式餐廳</option>' +
		    				'<option value="REST000003">西式餐廳</option>' +
  					'</select>' +
	    			  '</div>' +
	    			  '<div class="form-group">' +
	    			    '<label for="meal_type_no" class="pull-left">餐點類別：</label>' +
	    			    '<select id="meal_type_no" class="form-control">' +
		    				'<option value="MT001">主食</option>' +
		    				'<option value="MT002">鍋物</option>' +
  					'</select>' +
	    			  '</div>' +
	    			  '<div class="form-group">' +
	    			    '<label for="meal_introduction" class="pull-left">餐點介紹：</label>' +
	    			    '<input type="text" class="form-control" id="meal_introduction" placeholder="輸入介紹" value="廢土中的唯一選擇">' +
	    			  '</div>' +
	    			  '<div class="form-group">' +
	    			    '<label for="meal_status" class="pull-left">狀態：</label>' +
	    			    '<select id="meal_status" class="form-control">' +
		    				'<option value="0">下架</option>' +
		    				'<option value="1">上架</option>' +
	    				'</select>' +
	    			  '</div>' +
	    			'</form>',	
    			type: "warning",
    			showCancelButton: true,
    			preConfirm: function () {
                return new Promise(function (resolve, reject) {
                		debugger;
                		
                		let empForm	= document.forms.namedItem('mealForm');
                    	let meal_picture = $('#meal_picture')[0].files[0];
                    	let res_no = $('#res_no').val();
                    	let meal_type_no = $('#meal_type_no').val();
                    	let meal_name = $('#meal_name').val();
                    	let meal_status = $('#meal_status').val();
                    	let meal_introduction = $('#meal_introduction').val();
                    	let price = $('#price').val();

                    	let data = new FormData(empForm);
                    	data.append('action', 'insert');
                    	data.append('res_no', res_no);
                    	data.append('meal_type_no', meal_type_no);
                    	data.append('meal_name', meal_name);
                    	data.append('meal_status', meal_status);
                    	data.append('meal_introduction', meal_introduction);
                    	data.append('meal_picture', meal_picture);
                    	data.append('price', price);
                    	
                    	console.log("action : "+data.action);
                    	console.log("res_no : "+data.res_no);
                    	console.log("meal_type_no : "+data.meal_type_no);
                    	console.log("meal_name : "+data.meal_name);
                    	console.log("meal_status : "+data.meal_status);
                    	console.log("meal_introduction : "+data.meal_introduction);
                    	console.log("meal_picture: "+data.meal_picture);
                    	console.log("price : "+data.price);

//                     var data = {};
//                     data.action = 'insert';
//                     data.dep_no = $('#dep_no').val();
//                     data.emp_name = $('#emp_name').val();
//                     data.emp_phone = $('#emp_phone').val();
//                     data.emp_email = $('#emp_email').val();
//                     data.emp_status = $('#emp_status').val();
//                     data.emp_picture = formData;
	                    if (!res_no) {
	            			$('#res_no').focus();
	            			reject('請選餐廳！');
	            		} else if (!meal_name) {
	            			$('#meal_name').focus();
	            			reject('請輸入名稱！');
	            		} else if (!meal_type_no) {
	            			$('#meal_type_no').focus();
	            			reject('請選類別！');
	            		} else if (!meal_status) {
	            			$('#meal_status').focus();
	            			reject('請選擇狀態！');
	            		} else if (!price) {
	            			$('#price').focus();
	            			reject('請輸入金額！');
	            		} else resolve(data);
                })
            },
            onOpen: function () {
                $('#meal_name').focus();
            },
    		}).then(function (data) {
    			console.log(data);		    			
    			if (data) {
    		    		insertData(data, function(result){
    		    			if(result.success === 'Y')
		    		    		loadData(param);
    		    				swal({
			    		    		type: 'success',
			    		    		title: '儲存成功',
			    		   		html: 
			    		   			'<b>新增成功</b>',
			    		    }).catch(swal.noop);
    		    		}, function(result){
    		    			swal({
		    		    		type: 'error',
		    		    		title: '儲存失敗',
		    		   		html: '<b>失敗訊息是：</b> AJAX error!!!'
		    		    }).catch(swal.noop);
    		    		});
    			}
    		}).catch(swal.noop);
    });
	
	$('#btnEdit').on('click', function(){
		mode = 'Edit';
        $(this).hide();
        $('#btnView').show();
        $('#demo').bootstrapTable('showColumn', 'checkbox');
        $('#btnDelete').attr('disabled', false);
	});
	
	$('#btnView').on('click', function(){
		mode = 'View';
        $(this).hide();
        $('#btnEdit').show();
        $('#demo').bootstrapTable('hideColumn', 'checkbox');
        $('#btnDelete').attr('disabled', true);
	});
	
//	$('#btnDelete').on('click', function(){
//		var data = {};
//		var delArray = [];
//		var oRows = $('#demo').bootstrapTable('getSelections');
//        oRows.forEach(function(item, index){
//        		delArray.push(item.meal_no);
//        });
//	    		data.action = 'delete';
//        data.delArray = delArray;
//        console.log(data);
//		if(oRows.length === 0) {
//			swal("錯誤", "請至少選擇一筆", "error");
//		} else {
//			swal({
//	            title: "確定刪除？",
//	            html: "按下確定後資料會永久刪除",
//	            type: "question", // type can be "success", "error", "warning", "info", "question"
//	            showCancelButton: true,
//	        		showCloseButton: true,
//	        }).then(function (result) {
//                if (result) {
//                    deleteData(data, function(data){
//	                		swal("完成!", "資料已經刪除", "success");
//	                		loadData(param);
//                    });
//                }
//            }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
//            		swal("取消", "資料未被刪除", "info");
//	        }).catch(swal.noop);
//		}
//	});
});
//function deleteData(data, callback){
//	$.ajax({
//		 type: "POST",
//		 url: "../../meal/mealJson.do",
//		 data: data,
//		 dataType: "json",
//		 success: callback,
//         error: function(){ alert('AJAX錯誤！')}
//     });
//}

function insertData(data, succ_callback, fail_callback){
	$.ajax({
		 type: "POST",
		 enctype: 'multipart/form-data',
		 url: "../../meal/mealJson.do",
		 data: data,
		 processData: false,
		 contentType: false,
//			 dataType: "json",
		 success: succ_callback,
         error: fail_callback
     });
}

function updateData(data, succ_callback, fail_callback){
	$.ajax({
		 type: "POST",
		 enctype: 'multipart/form-data',
		 url: "../../meal/mealJson.do",
		 data: data,
		 processData: false,
		 contentType: false,
// 		 dataType: "json",
		 success: succ_callback,
         error: fail_callback
     });
}

function loadData(param){
	$('#demo').bootstrapTable('showLoading');
	$.ajax({
		 type: "POST",
		 url: "../../meal/mealJson.do",
		 data: param,
		 dataType: "json",
		 success: function (data){
			$('#demo').bootstrapTable("load", data);
			$('#demo').bootstrapTable("refresh", { silent: true });
			$('#demo').bootstrapTable('hideLoading');
	     },
         error: function(){alert("AJAX-grade發生錯誤囉!")}
     });
};

</script>
</body>
</html>