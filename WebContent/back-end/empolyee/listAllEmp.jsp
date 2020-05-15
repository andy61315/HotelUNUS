<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap-table (from Backend)</title>
	<link rel="stylesheet" href="css/bootstrap-3.3.7.min.css">
	<link rel="stylesheet" href="css/bootstrap-table-1.11.0.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/bootstrap-3.3.7.min.js"></script>
	<script src="js/bootstrap-table-1.11.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
	<style>
		.pointer {cursor: pointer;}
	</style>
</head>
<body>
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
	<table id="demo" class="table" data-unique-id="id" data-height="450" data-sort-name="name"
		data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-pagination-switch="true"
		data-show-refresh="true" data-search="true" data-page-size="10" data-page-list="[ 5, 10, 20, 50, 100]"
		data-side-pagination="server" data-toolbar="#tableToolbar">
	</table>
	
	<script type="text/javascript">
		var param;
		var mode = 'View';
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
			}).on('click-row.bs.table', function (e, row, element, field) {
            		if(mode === 'Edit'){
					swal({
			    			title: '編輯員工',
			    			html:
				    			'<form>' +
				    			  '<div class="form-group">' +
				    			    '<label for="name" class="pull-left">名稱：</label>' +
				    			    '<input type="text" class="form-control" id="name" placeholder="Name" value="'+ row.name +'">' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="email" class="pull-left">E-mail：</label>' +
				    			    '<input type="email" class="form-control" id="email" placeholder="E-mail" value="'+ row.mailAddress +'">' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="description" class="pull-left">說明：</label>' +
				    			    '<input type="text" class="form-control" id="description" placeholder="Description" value="'+ row.description +'">' +
				    			  '</div>' +
				    			'</form>',	
			    			type: "warning",
			    			showCancelButton: true,
			    			preConfirm: function () {
		                    return new Promise(function (resolve, reject) {
		                    		debugger;
		                        var data = {};
		                        data.action = 'update';
		                        data.id = row.id;
		                        data.name = $('#name').val().trim();
		                        data.email = $('#email').val().trim();
		                        data.description = $('#description').val().trim();
			                    var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
		                    		if (!data.name) {
		                    			$('#name').focus();
		                    			reject('請輸入名稱！');
		                    		} else if (!data.email) {
		                    			$('#email').focus();
		                    			reject('請輸入E-mail！');
		                    		} else if (!emailRule.test(data.email)) {
		                    			$('#email').focus();
		                    			reject('E-mail格式錯誤！');
		                    		} else resolve(data);
		                    })
		                },
		                onOpen: function () {
		                    $('#name').focus();
		                },
			    		}).then(function (data) {
			    			if (data) {
			    		    		updateData(data, function(result){
			    		    			if(result.success === 'Y')
					    		    		loadData(param);
			    		    				swal({
						    		    		type: 'success',
						    		    		title: '修改成功',
						    		   		html: 
						    		   			'<b>輸入的名稱是：</b>' + data.name + '<br>' +
						    		   			'<b>輸入的e-mail是：</b>' + data.email + '<br>' +
						    		   			'<b>輸入的description是：</b>' + data.description,
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
		    			title: '新增員工',
		    			html:
			    			'<form>' +
			    			  '<div class="form-group">' +
			    			    '<label for="name" class="pull-left">名稱：</label>' +
			    			    '<input type="text" class="form-control" id="name" placeholder="Name">' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="email" class="pull-left">E-mail：</label>' +
			    			    '<input type="email" class="form-control" id="email" placeholder="E-mail">' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="description" class="pull-left">說明：</label>' +
			    			    '<input type="text" class="form-control" id="description" placeholder="Description">' +
			    			  '</div>' +
			    			'</form>',	
		    			type: "warning",
		    			showCancelButton: true,
		    			preConfirm: function () {
	                    return new Promise(function (resolve, reject) {
	                    		debugger;
	                        var data = {};
	                        data.action = 'insert';
	                        data.name = $('#name').val().trim();
	                        data.email = $('#email').val().trim();
	                        data.description = $('#description').val().trim();
		                    var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	                    		if (!data.name) {
	                    			$('#name').focus();
	                    			reject('請輸入名稱！');
	                    		} else if (!data.email) {
	                    			$('#email').focus();
	                    			reject('請輸入E-mail！');
	                    		} else if (!emailRule.test(data.email)) {
	                    			$('#email').focus();
	                    			reject('E-mail格式錯誤！');
	                    		} else resolve(data);
	                    })
	                },
	                onOpen: function () {
	                    $('#name').focus();
	                },
		    		}).then(function (data) {
		    			if (data) {
		    		    		insertData(data, function(result){
		    		    			if(result.success === 'Y')
				    		    		loadData(param);
		    		    				swal({
					    		    		type: 'success',
					    		    		title: '儲存成功',
					    		   		html: 
					    		   			'<b>輸入的名稱是：</b>' + data.name + '<br>' +
					    		   			'<b>輸入的e-mail是：</b>' + data.email + '<br>' +
					    		   			'<b>輸入的description是：</b>' + data.description,
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
			
			$('#btnDelete').on('click', function(){
				var data = {};
				var delArray = [];
				var oRows = $('#demo').bootstrapTable('getSelections');
                oRows.forEach(function(item, index){
                		delArray.push(item.id);
                });
        	    		data.action = 'delete';
                data.delArray = delArray;
                console.log(data);
				if(oRows.length === 0) {
					swal("錯誤", "請至少選擇一筆", "error");
				} else {
					swal({
			            title: "確定刪除？",
			            html: "按下確定後資料會永久刪除",
			            type: "question", // type can be "success", "error", "warning", "info", "question"
			            showCancelButton: true,
			        		showCloseButton: true,
			        }).then(function (result) {
		                if (result) {
		                    deleteData(data, function(data){
			                		swal("完成!", "資料已經刪除", "success");
			                		loadData(param);
		                    });
		                }
		            }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
		            		swal("取消", "資料未被刪除", "info");
			        }).catch(swal.noop);
				}
			});
		});
		function deleteData(data, callback){
			$.ajax({
				 type: "POST",
				 url: "../../employee/emp.do",
				 data: data,
				 dataType: "json",
				 success: callback,
	             error: function(){ alert('AJAX錯誤！')}
	         });
		}
		
		function insertData(data, succ_callback, fail_callback){
			$.ajax({
				 type: "POST",
				 url: "../../employee/emp.do",
				 data: data,
				 dataType: "json",
				 success: succ_callback,
	             error: fail_callback
	         });
		}
		
		function updateData(data, succ_callback, fail_callback){
			$.ajax({
				 type: "POST",
				 url: "../../employee/emp.do",
				 data: data,
				 dataType: "json",
				 success: succ_callback,
	             error: fail_callback
	         });
		}
		
		function loadData(param){
			$('#demo').bootstrapTable('showLoading');
			$.ajax({
				 type: "POST",
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