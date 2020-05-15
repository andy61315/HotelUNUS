<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sweet Alert 2</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="js/jquery-1.12.3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<button id="btn1">Button 1</button>
	<button id="btn2">Button 2</button>
	<button id="btn3">Button 3</button>
	<button id="btn4">Button 4</button>
	<script>
	//自訂預設值
	swal.setDefaults({
	    confirmButtonText: "確定",
	    cancelButtonText: "取消"
	});
	//swal.resetDefaults();//清空自訂預設值
	
	$(function () {
	    $("#btn1").on('click', function () {
	    	debugger;
	        swal({
	            title: "確定刪除？",
	            html: "按下確定後資料會永久刪除",
	            type: "question", // type can be "success", "error", "warning", "info", "question"
	            showCancelButton: true,
     			showCloseButton: true,
	        }).then(
	        	   function (result) {
                if (result) {
                    swal("完成!", "資料已經刪除", "success");
                }
            }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
            		swal("取消", "資料未被刪除", "error");
	        }).catch(swal.noop);
	    });
	    
	    $("#btn2").on('click', function(){
	    		swal({
	    		  title: 'Please enter your password: ',
	    		  input: 'password',
	    		  type: "warning",
	    		  inputAttributes: {
	    		    'maxlength': 10,
	    		    'autocapitalize': 'off',
	    		    'autocorrect': 'off'
	    		  }
	    		}).then(function (password) {
	    		  if (password) {
	    		    swal({
	    		      type: 'success',
	    		      html: '<b>輸入的密碼是：</b>' + password
	    		    })
	    		  }
	    		}).catch(swal.noop);
	    });
	    
	    $("#btn3").on("click", function(){
	    		swal({
	    			title: '請輸入帳號與密碼 ',
	    			html:
		    			'<form>' +
		    			  '<div class="form-group">' +
		    			    '<label for="account" class="pull-left">帳號：(pikachu)</label>' +
		    			    '<input type="text" class="form-control" id="account" placeholder="Account">' +
		    			  '</div>' +
		    			  '<div class="form-group">' +
		    			    '<label for="password" class="pull-left">密碼：</label>' +
		    			    '<input type="password" class="form-control" id="password" placeholder="Password">' +
		    			  '</div>' +
		    			'</form>',	
	    			type: "warning",
	    			preConfirm: function () {
                    return new Promise(function (resolve, reject) {
                        var data = {};
                        data.action ="sweetAlert";
                        data.account = $('#account').val().trim();
                        data.password = $('#password').val().trim();
                    		if (!data.account) reject('請輸入帳號！');
                    		else if (!data.password) reject('請輸入密碼！');
                    		else {
                    			$.ajax({
                   				 type: "GET",
                   				 url: "validator.do",
                   				 data: data,
                   				 dataType: "json",
                   				 success: function (result){
                   					if("N" === result.pass){
                   						reject('此帳號已有人使用囉！');
                   					} else {
                   						resolve(data);
                   					}
                   			     },
                   	             error: function(){
                   	            	 	reject('AJAX發生錯誤囉!');
                   	            	 }
                   	        });
                    		}
                    })
                },
                onOpen: function () {
                    $('#account').focus();
                },
	    		}).then(function (data) {
	    			if (data) {
		    			swal({
		    		    		type: 'success',
		    		    		title: '儲存成功',
		    		   		html: 
		    		   			'<b>輸入的帳號是：</b>' + data.account + '<br>' +
		    		   			'<b>輸入的密碼是：</b>' + data.password,
		    		    })
	    			}
	    		}).catch(swal.noop);
	    });
	    
	    
	    $("#btn4").on('click', function(){
	        swal.setDefaults({ 
	            confirmButtonText: '下一步', 
	            cancelButtonText: '取消',
	            showCancelButton: true,
	            animation: false,
	            progressSteps: ['1', '2', '3'] //↓預設只能填一個字元，否則會破版，不然就自行客製化html
	        });

	    		var steps = [
	            {
	              title: '建立帳號',
	              text: '請輸入帳號',
	              input: 'text',
	            },
	            {
	              title: '登入密碼', 
	              text: '請輸入密碼',
	              input: 'password',
	            },
	            {
	              title: '電子信箱',
	              text: '請輸入正確格式的電子信箱',
	              input: 'email'
	            }
	         ];
	    		
	    		 swal.queue(steps).then(function (result) {
	    	         swal.resetDefaults();  //讓animation、showCancelButton、progressSteps參數回復預設
	    	         var msg = '<div>帳號: ' + result[0] +'</div>' +
	    	                   '<div>密碼: ' + result[1] +'</div>' +  
	    	                   '<div>電子信箱: ' + result[2];
	    	         swal({
	    	            title: '完成註冊!',
	    	            html: msg,
	    	            type: "success",
	    	            confirmButtonText: '確定'
	    	         });
	    	      }, function (dismiss) {
	    	          swal.resetDefaults();  //讓animation、showCancelButton、progressSteps參數回復預設   
	    	          if(dismiss==="cancel"){
	    	          }
	    	      }).catch(swal.noop);
    		});
	    
	});
	</script>

</body>
</html>