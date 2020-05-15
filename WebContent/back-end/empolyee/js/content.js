		var param;
		var mode = 'View';
		$(document).ready(function(){
			// bootstrap-table setting
			$('#demo').bootstrapTable({
			  columns:[ // 欄位設定
				    {field:'checkbox', title:'選取', align:'center', width:80, visible:false, checkbox:true},
				    {field:'emp_id', title:'雇員編號', align:'center', width:120, visible:true, sortable:true},
				    {field:'empImg', title:'雇員圖片', align:'center', width:80, visible:true},
				    {field:'emp_name', title:'雇員名稱', align:'center', width:120, visible:true, sortable:true},
				    {field:'emp_phone', title:'連絡電話', align:'center', width:200, visible:true},
				    {field:'emp_email', title:'聯絡信箱', align:'center', width:200, visible:true},
				    {field:'emp_password', title:'雇員密碼', align:'center', width:200, visible:true},
				    {field:'emp_status', title:'雇員狀態', align:'center', width:100, visible:true, sortable:true},
			  ],
			  data : [],
//	          formatLoadingMessage: function () {
//	        	  return '<i style="color: white; margin: auto; font-size:1rem;">';
//	          	  return '<i class="fa fa-spinner fa-pulse fa-2x fa-fw" style="color: darkorange; margin: 4px 0 0 8px; font-size:4.5rem;">';
//	          },
			});
			$('.fixed-table-header').css('background-color', '#E2943B');
			
			// step 2. 開始從後端撈資料（第一次）
			param = {action: 'getData', pageNo: '1', pageSize: '10', orderBy: 'emp_id', orderType: 'asc', like: ''};
			loadData(param);
			
			// step 3. 其他設定
			$('#demo').on('sort.bs.table', function(e, emp_id, order) {
				param.orderBy = emp_id;
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
			    			title: '編輯員工',
			    			html:
				    			'<form enctype="multipart/form-data" method="POST" name="empForm">' +
				    			  '<div class="form-group">' +
				    			    '<label for="emp_picture" class="pull-left">' +
				    			    	'相片：' + 
				    			    '</label>' +
				    			    '<input type="file" class="form-control" id="emp_picture" accept="image/*">' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="dep_no" class="pull-left">部門：</label>' +
				    			    '<select id="dep_no" class="form-control">' +
					    				'<option value="DEP01" "' +  ((row.dep_no === 'DEP01')?'" selected>':'">') +'餐飲部</option>' +
					    				'<option value="DEP02" "' +  ((row.dep_no === 'DEP02')?'" selected>':'">') +'客房部</option>' +
					    				'<option value="DEP03" "' +  ((row.dep_no === 'DEP03')?'" selected>':'">') +'休閒部</option>' +
			    					'</select>' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="emp_name" class="pull-left">名稱：</label>' +
				    			    '<input type="text" class="form-control" id="emp_name" placeholder="姓名" value="'+ row.emp_name +'">' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="emp_phone" class="pull-left">手機：</label>' +
				    			    '<input type="text" class="form-control" id="emp_phone" placeholder="0912345678" value="'+ row.emp_phone +'">' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="emp_email" class="pull-left">電子郵件信箱：</label>' +
				    			    '<input type="email" class="form-control" id="emp_email" placeholder="xxx@gmail.com" value="'+ row.emp_email +'">' +
				    			  '</div>' +
				    			  '<div class="form-group">' +
				    			    '<label for="emp_status" class="pull-left">狀態：</label>' +
				    			    '<select id="emp_status" class="form-control">' +
					    				'<option value="0" "' +  ((row.emp_status === 0)?'" selected>':'">') +'離職</option>' +
					    				'<option value="1" "' +  ((row.emp_status === 1)?'" selected>':'">') +'在職</option>' +
				    				'</select>' +
				    			  '</div>' +
				    			'</form>',
			    			type: "warning",
			    			showCancelButton: true,
			    			preConfirm: function () {
		                    return new Promise(function (resolve, reject) {
		                    		debugger;

	                    		let empForm	= document.forms.namedItem('empForm');
		                    	let emp_picture = $('#emp_picture')[0].files[0];
		                    	let dep_no = $('#dep_no').val();
		                    	let emp_name = $('#emp_name').val();
		                    	let emp_phone = $('#emp_phone').val();
		                    	let emp_email = $('#emp_email').val();
		                    	let emp_status = $('#emp_status').val();
		                    	let emp_id = row.emp_id;

		                    	let data = new FormData(empForm);
		                    	data.append('action', 'update');
		                    	data.append('dep_no', dep_no);
		                    	data.append('emp_name', emp_name);
		                    	data.append('emp_phone', emp_phone);
		                    	data.append('emp_email', emp_email);
		                    	data.append('emp_status', emp_status);
		                    	data.append('emp_picture', emp_picture);
		                    	data.append('emp_id', emp_id);
		                    	
		                    		
// 		                        var data = {};
// 		                        data.action = 'update';
// 		                        data.id = row.id;
// 		                        data.name = $('#name').val().trim();
// 		                        data.email = $('#email').val().trim();
// 		                        data.description = $('#description').val().trim();
		                        var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	                    		if (!dep_no) {
	                    			$('#dep_no').focus();
	                    			reject('請選部門！');
	                    		} else if (!emp_name) {
	                    			$('#emp_name').focus();
	                    			reject('請輸入名稱！');
	                    		} else if (!emp_phone) {
	                    			$('#emp_phone').focus();
	                    			reject('請輸入電話！');
	                    		} else if (!emp_status) {
	                    			$('#emp_status').focus();
	                    			reject('請選擇狀態！');
	                    		} else if (!emailRule.test(emp_email)) {
	                    			$('#emp_email').focus();
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
		    			title: '新增員工',
		    			html:
			    			'<form enctype="multipart/form-data" method="POST" name="empForm">' +
			    			  '<div class="form-group">' +
			    			    '<label for="emp_picture" class="pull-left">' +
			    			  		'<img src="" />' + 
			    			    	'相片：' + 
			    			    '</label>' +
			    			    '<input type="file" class="form-control" id="emp_picture" accept="image/*">' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="dep_no" class="pull-left">部門：</label>' +
			    			    '<select id="dep_no" class="form-control">' +
				    				'<option value="DEP01" selected>餐飲部</option>' +
				    				'<option value="DEP02">客房部</option>' +
				    				'<option value="DEP03">休閒部</option>' +
		    					'</select>' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="emp_name" class="pull-left">名稱：</label>' +
			    			    '<input type="text" class="form-control" id="emp_name" placeholder="姓名" value="peter">' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="emp_phone" class="pull-left">手機：</label>' +
			    			    '<input type="text" class="form-control" id="emp_phone" placeholder="0912345678" value="0912345678">' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="emp_email" class="pull-left">電子郵件信箱：</label>' +
			    			    '<input type="email" class="form-control" id="emp_email" placeholder="xxx@gmail.com" value="kancolle86web@gmail.com">' +
			    			  '</div>' +
			    			  '<div class="form-group">' +
			    			    '<label for="emp_status" class="pull-left">狀態：</label>' +
			    			    '<select id="emp_status" class="form-control">' +
				    				'<option value="0">離職</option>' +
				    				'<option value="1" selected>在職</option>' +
			    				'</select>' +
			    			  '</div>' +
			    			'</form>',	
		    			type: "warning",
		    			showCancelButton: true,
		    			preConfirm: function () {
	                    return new Promise(function (resolve, reject) {
	                    		debugger;
	                    		
                    		let empForm	= document.forms.namedItem('empForm');
	                    	let emp_picture = $('#emp_picture')[0].files[0];
	                    	let dep_no = $('#dep_no').val();
	                    	let emp_name = $('#emp_name').val();
	                    	let emp_phone = $('#emp_phone').val();
	                    	let emp_email = $('#emp_email').val();
	                    	let emp_status = $('#emp_status').val();

	                    	let data = new FormData(empForm);
	                    	data.append('action', 'insert');
	                    	data.append('dep_no', dep_no);
	                    	data.append('emp_name', emp_name);
	                    	data.append('emp_phone', emp_phone);
	                    	data.append('emp_email', emp_email);
	                    	data.append('emp_status', emp_status);
	                    	data.append('emp_picture', emp_picture);

// 	                        var data = {};
// 	                        data.action = 'insert';
// 	                        data.dep_no = $('#dep_no').val();
// 	                        data.emp_name = $('#emp_name').val();
// 	                        data.emp_phone = $('#emp_phone').val();
// 	                        data.emp_email = $('#emp_email').val();
// 	                        data.emp_status = $('#emp_status').val();
// 	                        data.emp_picture = formData;
	                        var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	                    		if (!dep_no) {
	                    			$('#dep_no').focus();
	                    			reject('請選部門！');
	                    		} else if (!emp_name) {
	                    			$('#emp_name').focus();
	                    			reject('請輸入名稱！');
	                    		} else if (!emp_phone) {
	                    			$('#emp_phone').focus();
	                    			reject('請輸入電話！');
	                    		} else if (!emp_status) {
	                    			$('#emp_status').focus();
	                    			reject('請選擇狀態！');
	                    		} else if (!emailRule.test(emp_email)) {
	                    			$('#emp_email').focus();
	                    			reject('E-mail格式錯誤！');
	                    		} else resolve(data);
	                    })
	                },
	                onOpen: function () {
	                    $('#dep_no').focus();
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
			
			$('#btnDelete').on('click', function(){
				var data = {};
				var delArray = [];
				var oRows = $('#demo').bootstrapTable('getSelections');
                oRows.forEach(function(item, index){
                		delArray.push(item.emp_id);
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
				 enctype: 'multipart/form-data',
				 url: "../../employee/emp.do",
				 data: data,
				 processData: false,
				 contentType: false,
// 				 dataType: "json",
				 success: succ_callback,
	             error: fail_callback
	         });
		}
		
		function updateData(data, succ_callback, fail_callback){
			$.ajax({
				 type: "POST",
				 enctype: 'multipart/form-data',
				 url: "../../employee/emp.do",
				 data: data,
				 processData: false,
				 contentType: false,
// 				 dataType: "json",
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
		};
