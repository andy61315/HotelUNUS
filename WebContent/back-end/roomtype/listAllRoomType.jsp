<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<html>
<head>


<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>ListAllRoomType.jsp</title>

<!--   <link rel="stylesheet" href="http://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css" /> -->
<!--   <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap.min.css" /> -->
<!--   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" /> -->
<!--     <link rel="stylesheet" href="../css/bootstrap-table.css">            -->
<!--     <link rel="stylesheet" href="../css/bootstrap.min.css">            -->
<!-- 	<link rel="stylesheet" href="../css/bootstrap-3.3.7.min.css"> -->
<!-- <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css"> -->
<!-- 	<link rel="stylesheet" href="../css/bootstrap-table-1.11.0.min.css"> -->
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">            -->
<!-- <link href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="../css/sweetalert2.css">           
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/sweetalert2-theme-dark.scss">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

<!-- 	<script src="../js/jquery-1.12.3.min.js"></script> -->
<!-- 	<script src="../js/bootstrap-3.3.7.min.js"></script> -->
<!-- <script src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script> -->
<!-- 	<script src="../js/bootstrap-table-1.11.0.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script> -->
	<script  src="https://code.jquery.com/jquery-3.4.1.js"  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="  crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="../js/sweetalert2.js"></script>
	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script> -->
<!-- 	<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script> -->
	


	<%@ include file="/back-end/homepage/head.file" %>

<style>

.fixed-table-toolbar{
display:none;
}

td{
max-height:25px;
}
tr {
   line-height: 25px !important;
   min-height: 25px !important;
   height: 25px !important;
   max-height:25px !important;
   over-flow:hidden !important;
}

.table td, .table th {
    padding: .3rem;
}
.table100, .row, .container, .table-responsive, .table-bordered  {
    height: 100%;
}

.checkArticle:hover{
	cursor:pointer;
}
</style>
</head>
<body>


<%@ include file="/back-end/homepage/middle.file" %> 
    			
				
				
				<!-- 以下功能名稱分類:id請照<a>標籤命名去掉btn ex:lineQAbtn改成 lineQA-->
				<!-- class="tableStyle" 表格和h2的樣式 請不要拿掉  -->
				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >房型管理<!-- 自己的標頭名稱功能名稱 --></h2>
    			<div class="col-12">
    			
    			
    			
				
				<div id="tableToolbar" class="btn-group">
				    <button type="button" class="btn btn-secondary" title="Add Member" id='btnAdd'>
				    	新增房型
				<!--         <i class="glyphicon glyphicon-plus"></i> -->
				    </button>
				    <button type="button" class="btn btn-secondary" title="Edit Mode" id='btnEdit'>
				        	修改房型
				    </button>
				    <button type="button" class="btn btn-secondary" title="View Mode" id='btnView' style='display: none;'>
				    	檢視模式
				    </button>
				    <button type="button" class="btn btn-secondary" title="Delete Member" id='btnDelete' style='display: none;'>
				        	下架
				    </button>
				    
<!-- 				     <button type="button" class="btn btn-default" title="Add Member" id='btnAdd'> -->
<!-- 	        <i class="glyphicon glyphicon-plus"></i> -->
<!-- 	    </button> -->
<!-- 	    <button type="button" class="btn btn-default" title="Edit Mode" id='btnEdit'> -->
<!-- 	        <i class="fa fa-edit"></i> -->
<!-- 	    </button> -->
<!-- 	    <button type="button" class="btn btn-default" title="View Mode" id='btnView' style='display: none;'> -->
<!-- 	        <i class="fa fa-eye-open"></i> -->
<!-- 	    </button> -->
<!-- 	    <button type="button" class="btn btn-default" title="Delete Member" id='btnDelete' disabled> -->
<!-- 	        <i class="fa fa-trash"></i> -->
<!-- 	    </button> -->
				</div>
<!-- 				<div class="container"> -->
<!-- 				  <div class="row"> -->
<!-- 				    <div class="col custom-table-width"> -->
						<table id="table" class="table scrollbarStyle" data-unique-id="room_Type_No" data-show-refresh="true" 
						data-show-button-icons="true" data-search="true"
								data-pagination="true"  data-show-columns="true" data-page-size="5" data-height="700"
								data-sort-name="room_Type_No" data-sort-order="asc" data-show-toggle="true">
						</table>
<!-- 					</div> -->
<!-- 					</div> -->
<!-- 				</div> -->

				</div>
				</div>
		
<%@ include file="/back-end/homepage/footer.file" %>

<%-- <%@ include file="/back-end/chatroom/chatroomhtml_back_end.file" %>    --%>
<!-- 			</section> -->
<!--   		</div> -->
<!-- 	</div> -->
<!-- </div> -->

	<script>
	$(function(){
		var available = '';
		var mode = 'View';
		var param;//撈資料用
		var storeRoom_Type_Name = '';
		var storeArticle = '';
		var storePerson_Capacity = -1;
		var storeAdd_People = -1;
		var storePrice = 1000;
		var storeHoliday_Price = 1000;
		var storeWorkingDay_Price = 1000;
		var storeStatus = 1;
		//房間狀態
		$('#table').bootstrapTable({
			columns:[
			    {field:'checkbox', title:'選取', align:'center', width:80, visible:false, checkbox:true},
				{field:'room_Type_No', title:'編號', align:'center',sortable:'true', width:80, visible:false},
				{field:'room_Type_Name', title:'名稱', align:'center',sortable:'true', width:120, visible:true},
				{field:'checkArticle', title:'文案', align:'center', width:80, visible:true},
				{field:'article', title:'文案', align:'center', width:80, visible:false},
				{field:'quantity', title:'房數', align:'center',sortable:'true', width:60, visible:true},
				{field:'person_Capacity', title:'人數', align:'center',sortable:'true', width:60, visible:true},
				{field:'add_People', title:'加床', align:'center',sortable:'true', width:60, visible:true},
				{field:'price', title:'定價', align:'center',sortable:'true', width:80, visible:true},
				{field:'holiday_Price', title:'假日價', align:'center',sortable:'true', width:80, visible:true},
				{field:'workingDay_Price', title:'平日價', align:'center',sortable:'true', width:80, visible:true},
				{field:'avgStars', title:'評分', align:'center',sortable:'true', width:60, visible:true},
				{field:'star_People', title:'評分人數', align:'center',sortable:'true', width:60, visible:false},
				{field:'room_Type_Status', title:'狀態', align:'center',sortable:'true', width:80, visible:true},
				{field:'roomTypePicture', title:'圖片', align:'center', width:80, visible:true},
				{field:'checkBOM', title:'訂單', align:'center', width:80, visible:true},
				
			],
		    data : [],
		    formatLoadingMessage: function () {
	          	  return '<i style="color: white; margin: auto; font-size:1rem;">';
// 	          	  return '<i class="fa fa-spinner fa-pulse fa-2x fa-fw" style="color: white; margin: auto; font-size:1rem;">';
	        }
// 		    pageSize : 10, //一頁顯示幾筆
// 		    pageList : [ 5, 10, 20, 50, 100],
// 		    height : 700//表格的高度
		});
		$('.fixed-table-header').css('background-color', 'lightblue');
		
		$("body").on("click",".checkArticle",function(){
			let article = $(this).find("span").text();
			swal({
				title:'文案內容',
				html:article
			});
// 			alert(article);
		});
		
		//第一次取資料
		param = {action: 'getData' };
		getData(param);
		
			
		
		//
		$('#table').on('dbl-click-row.bs.table', function (e, row, element, field) {
			if(mode === 'Edit'){
				let person_Capacity_Option = '';
				for(let i = 1 ; i < 7 ; i++){
					person_Capacity_Option += '<option value="' + i + ((row.person_Capacity === i)?'" selected>':'">') + i +  '人</option>';
				}
				
				let add_People_Option = '';
				for(let i = 0 ; i < 4 ; i++){
					add_People_Option += '<option value="' + i + ((row.add_People === i)?'" selected>':'">') + i +  '人</option>';
				}
				
				swal({
					title:'編輯房型',
					html:
						'<form>' +
// 						'<input type="text" class="form-control" value="' + row.room_Type_No +'" hidden readonly id="room_Type_No" placeholder="請輸入房型名稱">' +
						'<div class="form-group">' +
							'<label for="room_Type_Name" class="pull-left">房型名稱 : </label>' +  
							'<input type="text" class="form-control" value="' + row.room_Type_Name +'" readonly id="room_Type_Name" placeholder="請輸入房型名稱">' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="article" class="pull-left">房型文案 : </label>' +  
						'<textarea cols="50" class="form-control" id="article" placeholder="請輸入文案" rows="5">' +
							row.article +
						'</textarea>' + 
						'</div>' +
					
						'<div class="form-group">' +
						'<label for="person_Capacity" class="pull-left">住房人數 : </label>' +  
						'<select class="form-control" id="person_Capacity" >' +
							'<option value="-1" selected>輸入住房人數</option>' +
							person_Capacity_Option +
						'</select>' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="add_People" class="pull-left">加床數 : </label>' +  
						'<select class="form-control" id="add_People" >' +
							'<option value="-1" selected>輸入加床數</option>' +
							add_People_Option +
						'</select>' +
						'</div>' +

						'<div class="form-group">' +
						'<label for="price" class="pull-left">定價 : </label>' +  
						'<input type="number" class="form-control" id="price" value="'+ row.price +'" step="100" min="1000" max="20000" placeholder="定價">' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="price" class="pull-left">假日價 : </label>' +  
						'<input type="number" class="form-control" id="holiday_Price" value="'+ row.holiday_Price +'" step="100" min="1000" max="20000" placeholder="假日價">' +
						'</div>' +

						'<div class="form-group">' +
						'<label for="price" class="pull-left">平日價 : </label>' +  
						'<input type="number" class="form-control" id="workingDay_Price" value="'+ row.workingDay_Price +'" step="100" min="1000" max="20000" placeholder="平日價">' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="room_Type_Status" class="pull-left">房間狀態: </label>' +  
						'<select class="form-control" id="room_Type_Status" >' +
							'<option value="1"' +  ((row.room_Type_Status === 1)?'" selected>':'">') +'上架</option>' +
							'<option value="0"' +  ((row.room_Type_Status === 0)?'" selected>':'">') +'下架</option>' +
						'</select>' +
						'</div>' +
					'</form>',
					type:"warning",
					showCancelButton:true,
					preConfirm:function(){
						return new Promise(function (resolve, reject) {
							var data = {};
	    					data.action = 'update';
// 	    					data
	    					data.room_Type_No = row.room_Type_No;
	    					console.log("id = " + data.id);
	    					data.room_Type_Name = $("#room_Type_Name").val().trim();
	    					data.article = $("#article").val().trim();
	    					data.person_Capacity =  $("#person_Capacity").find("option:selected").val();
	    					console.log("data.person_Capacity = " + data.person_Capacity);
	    					data.add_People =  $("#add_People").find("option:selected").val();
	    					data.price =  $("#price").val().trim();
	    					data.holiday_Price =  $("#holiday_Price").val().trim();
	    					data.workingDay_Price =  $("#workingDay_Price").val().trim();
// 	    					console.log("data.price = " + data.price);
	    					data.room_Type_Status =  $("#room_Type_Status").find("option:selected").val();
	    					if (!data.article){
	    						$('#article').focus();
	                			reject('請輸入文案！');
	                		} else if (parseInt(data.person_Capacity) == -1){
	    						$('#person_Capacity').focus();
	                			reject('請輸入住房人數！');
	                		}  else if (parseInt(data.add_People) == -1){
	    						$('#add_People').focus();
	                			reject('請輸入加床房人數！');
	                		}  else if (!(parseInt(data.price) >= 1000 && parseInt(data.price) <= 20000)){
	    						$('#price').focus();
	                			reject('定價需介於1,000~20,000');
	                		}  else if (!(parseInt(data.holiday_Price) >= 1000 && parseInt(data.holiday_Price) <= 20000)){
	    						$('#holiday_Price').focus();
	                			reject('假日價價需介於1,000~20,000');
	                		}  else if (!(parseInt(data.workingDay_Price) >= 1000 &&  parseInt(data.workingDay_Price) <= 20000)){
	    						$('#workingDay_Price').focus();
	                			reject('平日價需介於1,000~20,000');
	                		}  else resolve(data);
						})
					},
					onOpen:function(){
						$('#room_Type_Name').focus();
	    			},
				}).then(function(data){
					if(data){//如果有資料?
						updatetData(data, function(result){
							if(result.success === 'Y'){
								getData(param);
								swal({
			    		    		type: 'success',
			    		    		title: '修改成功',
			    		   		html: 
			    		   			'<b>名稱 ： </b>' + data.room_Type_Name + '<br>' +
			    		   			'<b>文案 ： </b>' + data.article + '<br>' +
			    		   			'<b>住房人數 ： </b>' + data.person_Capacity + '<br>' +
			    		   			'<b>加床數 ： </b>' + data.add_People + '<br>' +
			    		   			'<b>定價 ： </b>' + data.price + '<br>' +
			    		   			'<b>假日價 ： </b>' + data.holiday_Price + '<br>' +
			    		   			'<b>平日價 ： </b>' + data.workingDay_Price + '<br>' +
			    		   			'<b>房間狀態 ： </b>' + data.room_Type_Status + '<br>',
			    		    }).catch(swal.noop);
							}else{
								swal({
			    		    		type: 'error',
			    		    		title: '修改失敗',
			    		   		html: 
			    		   			'<b>名稱 ： </b>' + data.room_Type_Name + '<br>' +
			    		   			'<b>失敗訊息是 ： </b>' + result.error + '<br>' ,
			    		    }).catch(swal.noop);
							}
						},function(result){
							swal({
		    		    		type: 'error',
		    		    		title: '修改失敗',
		    		   		html: '<b>失敗訊息是：</b>ajax出錯了!!!<br>'
		    		    }).catch(swal.noop);
						})
					}
				}).catch(swal.noop);
			}
		});
		
		$("#btnAdd").on('click',function(){
			let person_Capacity_Option = '';
			for(let i = 1 ; i < 7 ; i++){
				person_Capacity_Option += '<option value="' + i + ((parseInt(storePerson_Capacity) === i)?'" selected>':'">') + i +  '人</option>';
			}
			
			let add_People_Option = '';
			for(let i = 0 ; i < 4 ; i++){
				add_People_Option += '<option value="' + i + ((parseInt(storeAdd_People) === i)?'" selected>':'">') + i +  '人</option>';
			}
			
			let status_Option = '';
			
			let status = ["下架", "上架"];
			for(let i = 0 ; i < 2 ; i++){
				status_Option += '<option value="' + i + ((parseInt(storeStatus) === i)?'" selected>':'">') + status[i] +  '</option>';
			}
			
			swal({
				title:'新增房型',
				html:
					'<form>' +
						'<div class="form-group">' +
							'<label for="room_Type_Name" class=" pull-left">房型名稱 : </label>' +  
							'<input type="text" class="room_Type_Name form-control" id="room_Type_Name" placeholder="請輸入房型名稱"  value="'+ storeRoom_Type_Name +'">' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="article" class="pull-left">房型文案 : </label>' +  
						'<textarea cols="50" class="form-control" id="article" placeholder="請輸入文案" rows="5">' + storeArticle +
						'</textarea>' + 
						'</div>' +
					
						'<div class="form-group">' +
						'<label for="person_Capacity" class="pull-left">住房人數 : </label>' +  
						'<select class="form-control" id="person_Capacity" >' +
							'<option value="-1" selected>輸入住房人數</option>' +
							person_Capacity_Option +
						'</select>' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="add_People" class="pull-left">加床數 : </label>' +  
						'<select class="form-control" id="add_People" >' +
							'<option value="-1" selected>輸入加床數</option>' +
							add_People_Option +
						'</select>' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="price" class="pull-left">定價 : </label>' +  
						'<input type="number" class="form-control" id="price" step="100" min="1000" max="20000" placeholder="定價"  value="'+ storePrice +'">' +
						'</div>' +

						'<div class="form-group">' +
						'<label for="price" class="pull-left">假日價 : </label>' +  
						'<input type="number" class="form-control" id="holiday_Price"  step="100" " min="1000" max="20000" placeholder="假日價"  value="'+ storeHoliday_Price +'">' +
						'</div>' +

						'<div class="form-group">' +
						'<label for="price" class="pull-left">平日價 : </label>' +  
						'<input type="number" class="form-control" id="workingDay_Price"  step="100" " min="1000" max="20000" placeholder="平日價"  value="'+ storeWorkingDay_Price +'">' +
						'</div>' +
						
						'<div class="form-group">' +
						'<label for="room_Type_Status" class="pull-left">房間狀態: </label>' +  
						'<select class="form-control" id="room_Type_Status">' +
							status_Option +
						'</select>' +
						'</div>' +
					'</form>',

    			type: "info",
    			showCancelButton: true,
    			preConfirm: function(){
    				return new Promise(function(resolve,reject){
    					var data = {};
    					data.action = 'insert';
    					data.room_Type_Name = $("#room_Type_Name").val().trim();
    					data.article = $("#article").val().trim();
    					data.person_Capacity =  $("#person_Capacity").find("option:selected").val();
//     					console.log("data.person_Capacity = " + data.person_Capacity);
    					data.add_People =  $("#add_People").find("option:selected").val();
    					data.price =  $("#price").val().trim();
    					data.holiday_Price =  $("#holiday_Price").val().trim();
    					data.workingDay_Price =  $("#workingDay_Price").val().trim();
//	    					console.log("data.price = " + data.price);
    					data.room_Type_Status =  $("#room_Type_Status").find("option:selected").val();
    					if(!data.room_Type_Name){
    						$('#room_Type_Name').focus();
                			reject('請輸入房型名稱！');
                		} else if (!data.article){
    						$('#article').focus();
                			reject('請輸入文案！');
                		} else if (parseInt(data.person_Capacity) == -1){
    						$('#person_Capacity').focus();
                			reject('請輸入住房人數！');
                		}  else if (parseInt(data.add_People) == -1){
    						$('#add_People').focus();
                			reject('請輸入加床房人數！');
                		}    else if (!(parseInt(data.price) >= 1000 && parseInt(data.price) <= 20000)){
    						$('#price').focus();
                			reject('定價需介於1,000~20,000');
                		}  else if (!(parseInt(data.holiday_Price) >= 1000 && parseInt(data.holiday_Price) <= 20000)){
    						$('#holiday_Price').focus();
                			reject('假日價價需介於1,000~20,000');
                		}  else if (!(parseInt(data.workingDay_Price) >= 1000 &&  parseInt(data.workingDay_Price) <= 20000)){
    						$('#workingDay_Price').focus();
                			reject('平日價需介於1,000~20,000');
                		}  else if(available === 'N'){
                			reject();
                		}  else resolve(data);
    				})
    			},
    			onOpen:function(){
					$('#room_Type_Name').focus();
    			},
    			onClose:function(){
    				 storeRoom_Type_Name = $("#room_Type_Name").val().trim();
    				 storeArticle = $("#article").val().trim();
    				 storePerson_Capacity = $("#person_Capacity").find("option:selected").val();
    				 console.log("storePerson_Capacity = " + storePerson_Capacity);
    			 	 storeAdd_People = $("#add_People").find("option:selected").val();
    			 	 storePrice = $("#price").val().trim();
    				 storeHoliday_Price = $("#holiday_Price").val().trim();
    				 storeWorkingDay_Price = $("#workingDay_Price").val().trim();
    				 storeStatus = $("#room_Type_Status").find("option:selected").val();
    			}
				}).then(function(data){
					if(data){//如果有資料?
						insertData(data, function(result){
							if(result.success === 'Y'){
								getData(param);
								storeRoom_Type_Name = '';
								storeArticle = '';
								storePerson_Capacity = -1;
								storeAdd_People = -1;
								storePrice = 1000;
								storeHoliday_Price = 1000;
								storeWorkingDay_Price = 1000;
								storeStatus = 1;
								swal({
			    		    		type: 'success',
			    		    		title: '新增成功',
				    		   		html: 
				    		   			'<b>名稱 ： </b>' + data.room_Type_Name + '<br>' +
				    		   			'<b>文案 ： </b>' + data.article + '<br>' +
				    		   			'<b>住房人數 ： </b>' + data.person_Capacity + '<br>' +
				    		   			'<b>加床數 ： </b>' + data.add_People + '<br>' +
				    		   			'<b>定價 ： </b>' + data.price + '<br>' +
				    		   			'<b>假日價 ： </b>' + data.holiday_Price + '<br>' +
				    		   			'<b>平日價 ： </b>' + data.workingDay_Price + '<br>' +
				    		   			'<b>房間狀態 ： </b>' + data.room_Type_Status + '<br>',
			    		   			showCancelButton: true,
			    		   			confirmButtonText: "前往房型圖片",
			    		   						
		<%-- 		    		   				'<a href="<%=request.getContextPath()%>/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp?room_Type_No='+ result.next_Room_Type_No + '">前往房型圖片</a>', --%>
				    		    }).then(function(){
				    		    	window.location.href ='<%=request.getContextPath()%>/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp?room_Type_No='+ result.next_Room_Type_No ;
				    		    }).catch(swal.noop);
								$('#table').bootstrapTable('scrollTo', 'bottom');
							}else{
								swal({
			    		    		type: 'error',
			    		    		title: '新增失敗',
			    		   		html: 
			    		   			'<b>名稱 ： </b>' + data.room_Type_Name + '<br>' +
			    		   			'<b>失敗訊息是 ： </b>' + result.error + '<br>' ,
			    		    }).catch(swal.noop);	
							}
						},function(result){
							swal({
		    		    		type: 'error',
		    		    		title: '新增失敗',
		    		   		html: '<b>失敗訊息是：</b> AJAX error!!!'
		    		    }).catch(swal.noop);
						})
					}
				}).catch(swal.noop);
			});
		
		$("body").on('focusout','.room_Type_Name',function(){
			let room_Type_Name = $("#room_Type_Name").val().trim();
			if(room_Type_Name != ''){
				let params = { "action" : "checkIfNameExist",
						"room_Type_Name" : room_Type_Name
						};
				$.ajax({
					 type: "POST",
					 url: '<%=request.getContextPath()%>/RoomType/roomtype.do',
					 data: params,
					 dataType: "json",
					 success: function(data){
						 $("#available").remove();
						 if(data.available === "Y"){
							 available = "Y";
							 $("#room_Type_Name").after('<span style="color:#80bdff;" id="available">房型名稱可用!</span>');
						 }else{
							 available = "N";
							 $("#room_Type_Name").after('<span style="color:red;"  id="available">房型名稱重複</span>');
						 }
					 },
		             error: function(){ alert('AJAX錯誤！')}
		         });
			}
		});
		
		$('#btnDelete').on('click', function(){
			var data = {};
			var delArray = [];
			var oRows = $('#table').bootstrapTable('getSelections');
            oRows.forEach(function(item, index){
            		delArray.push(item.room_Type_No);
            });
    	    data.action = 'delete';
            data.delArray = delArray;
            console.log(data);
			if(oRows.length === 0) {
				swal("錯誤", "請至少選擇一筆", "error");
			} else {
				swal({
		            title: "確定下架？",
		            type: "question", // type can be "success", "error", "warning", "info", "question"
		            showCancelButton: true,
		        	showCloseButton: true,
		        }).then(function (result) {
	                if (result) {
	                    deleteData(data, function(data){
		                		swal("完成!", "房型已經下架", "success");
		                		getData(param);
	                    });
	                }
	            }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
	            		swal("取消", "房型未被下架", "info");
		        }).catch(swal.noop);
			}
		});
		
		$('#btnEdit').on('click', function(){
			mode = 'Edit';
            $(this).hide();
            $('#btnView').show();
            $('#btnDelete').attr('disabled',false);
            $('#table').bootstrapTable('showColumn', 'checkbox');
            $('#btnDelete').show();
		});
		
		$("#btnView").on('click', function(){
			mode = 'View';
			$(this).hide();
			$("#btnEdit").show();
			$("#table").bootstrapTable('hideColumn', 'checkbox');
			$('#btnDelete').attr('disabled',true);
			$('#btnDelete').hide();
		});
		
		function deleteData(data, callback){
			$.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/RoomType/roomtype.do",
				 data: data,
				 dataType: "json",
				 success: callback,
	             error: function(){ alert('AJAX錯誤！')}
	         });
		}
		
		function insertData(data, succ_callback, fail_callback){
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/RoomType/roomtype.do",
				data: data,
				dataType: "json",
				success: succ_callback,
	            error: fail_callback
			});
		}
		
		function updatetData(data, succ_callback, fail_callback){
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/RoomType/roomtype.do",
				data: data,
				 dataType: "json",
				 success: succ_callback,
	             error: fail_callback
			});
		}
		
		function getData(param){
			$('#table').bootstrapTable('showLoading');
			$.ajax({
				type:"POST",
				url:"<%=request.getContextPath()%>/RoomType/roomtype.do",
				data: param,
				dataType:"json",
				success:function(data){
					$("#table").bootstrapTable("load",data);
					$('#table').bootstrapTable("refresh", { silent: true });
					$('#table').bootstrapTable('hideLoading');
// console.log(data);
				},
				error: function(){alert("AJAX-grade發生錯誤囉!")}
			});
			
		}	
		$("#table").bootstrapTable('updateCell', {
	        index: 1,
	        field: 'checkArticle',
	        value: 'Updated Name'
	      });
// 		$("body").on('click',".bs-checkbox",function(){
// 			let checkbox = $(this).find("input");
// 			let status = checkbox.prop("checked");
// 			console.log("checkbox status = " + checkbox.prop("checked"));
// 			console.log("!status = " + (!status));
// 			checkbox.prop('checked',(!status));
// 			if(status == true){
// 				alert("true");
// 			}
// 		}) ;
	});
	</script>
</body>
</html>