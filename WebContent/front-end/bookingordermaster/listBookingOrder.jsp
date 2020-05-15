<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>訂房訂單管理</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-table.min.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   
    
<style type="text/css">
 #bom thead{
/*  	display:none !important; /*把莫名多出來的一欄拿掉*/ 
 
 }
 
 body {
	font-size: 1rem ;
}

 .modal{
  	z-index:10001 !important;  
/*   	top:8rem !important;  */
 	 margin:auto;  
 }
 
 .swal2-container {
  z-index: 10002 !important;
}

.btn-secondary{
    font-size: 0.75rem !important;
    font-weight: bold !important;
    line-height: normal;
}

.bootstrap-table{
	margin:0rem 1rem;
}

#top{
	    text-align: center;
}

.btn-group{
	width: 96%;
    height: 1.6rem;
}

.pagination-detail{
	display:none;
/* 	把下面分幾頁的那些字樣拿掉 */
}
/* margin: 0rem 1rem; */
/* .fht-cell{ */
/* /* display:none; */ 
/* width: 4em !important; */
/* } */
</style>
</head>
      <body>
        <%@ include file="/front-end/indexFile/header.file" %>   
        <h4>住房訂單管理</h4>
       	<jsp:useBean id="rtSvc" scope="page" class="com.roomtype.model.RoomTypeService" />    
			<div class="btn-group btn-group-toggle" data-toggle="buttons">
			  <label id="past" class="btnBOMStatus btn btn-secondary past ">
			    <input type="radio" name="options" id="option1" value="2"> 過去訂單
			  </label>
			  <label id="inProgress"  class="btnBOMStatus btn btn-secondary inProgress active">
			    <input type="radio" name="options" id="option2" value="1" checked> 進行中
			  </label>
			  <label id="cancelled"  class="btnBOMStatus btn btn-secondary cancelled">
			    <input type="radio" name="options" id="option3" value="3"> 已取消
			  </label>
			</div>
			
         <table id="bom" class="table" data-unique-id="b_Order_No" data-height="550" data-sort-name="name"
			data-pagination="true" data-page-size="10">
		</table>    
           
          <div class="modal fade" id="showBom"  tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document" style="max-width:1100px;">
<!--          	 	<div style="position:relative;margin:auto;z-index:10001;"> -->
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">訂單資訊</h5>
				        <input type="hidden" class="rtNo" id="input_bomId" value="">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				      	<table id="modal" class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">房型名稱</th>
						      <th scope="col">訂房數量</th>
						      <th scope="col">加床人數</th>
						      <th scope="col">總價格</th>
						    </tr>
						  </thead>
						  <tbody id="modal-tbody">
						  </tbody>
						</table>
				      </div>
				      <div class="modal-footer">
			          <button type="button" id="btnClose"class="btn btn-secondary" data-dismiss="modal">關閉</button>
<!-- 		          <button type="button" id="btnDeleteBom" class="btn btn-primary">取消整張訂單</button> -->
		          </div>
		        </div>
<!-- 		      </div> -->
		    </div>
	      </div> 
    <!-- load JS files -->
<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker --> 
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap-table.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>          
<!--     <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.min.js"></script>           https://github.com/flesler/jquery.scrollTo -->

		<%@ include file="/front-end/indexFile/footer.file" %>  
<%-- <%@ include file="/front-end/chatroom/chatroomhtml_front_end.file" %>    --%>
    <script> 
    	var bomStatus = '1';
        $(function(){
        	let cus_Id = "${customerVO.cus_Id}";
        	$('#bom').bootstrapTable({
  			  columns:[ // 這裡把顧客編號/訂單狀態拿掉
  			    {field:'b_Order_No', title:'訂單編號', align:'center', width:60, visible:true, sortable:true},
  			    {field:'total_Price', title:'訂單總價', align:'center', width:120, visible:true, sortable:true},
  			    {field:'start_Date', title:'入住日期', align:'center', width:120, visible:true, sortable:true},
  			    {field:'end_Date', title:'退房日期', align:'center', width:120, visible:true, sortable:true},
//   			    {field:'co_Time', title:'實際入住時間', align:'center', width:120, visible:true, sortable:true}, //如果還沒入住的話要做判斷顯示
  			    {field:'order_Date', title:'下訂日期', align:'center', width:120, visible:true, sortable:true},
  			  ],
  			  data : [],
//   	          formatLoadingMessage: function () {
//   	          	  return '<i class="fa fa-spinner fa-pulse fa-2x fa-fw" style="color: darkorange; margin: 4px 0 0 8px; font-size:2.5rem;">';
//   	          },
  			});
        	$('.fixed-table-header').css('background-color', 'gray');
        	
        	getBomData();//第一次取資料
        	
        	var rtObj = [
        		{
        			room_Type_No : 'all',
        			room_Type_Name : '所有房型'
        		},
        		<c:forEach var="rtVO" items="${rtSvc.all}" varStatus="vendorStatus">
        			{            
        				room_Type_No: '<c:out value="${rtVO.room_Type_No}"/>',
        				room_Type_Name: '<c:out value="${rtVO.room_Type_Name}"/>'
        			}
        			<c:if test="${!vendorStatus.last}">
        				,
        			</c:if>
        		</c:forEach>
        		
        	];
        	
        	
        	$(".btnBOMStatus").change(function(){
        		bomStatus = $("input[name='options']:checked").val();
//         		if(parseInt(bomStatus )=== 3){
//         			$('#bom').bootstrapTable('hideColumn', 'co_Time');
//         		}else{
//         			$('#bom').bootstrapTable('showColumn', 'co_Time');
        			
//         		}
//         		if
	        	getBomData();
        	});
        	
        	$("#bom").on('click-row.bs.table', function (e, row, element, field) {
//         		console.log(element);
//         		console.log(row);
        		let b_order_no = row.b_Order_No;
        		$("#input_bomId").val(b_order_no);
//         		console.log("b_order_no = " + b_order_no);
        		let params = {
        				"action" : "getBodByNoJSON",
        				"b_order_no" : b_order_no
        		};
        		getBodData(params);
        	});
        	
//         	如果是從新增訂單跳轉過來的，就加上這裡
        	var newBomNo = '';
        	<c:if test="${param.successfullyBooked == 'Y'}">
        		newBomNo = "${param.newBomNo}";
        		<c:set var="successfullyBooked" scope="request" value="N"/>
       			let params = {
       				"action" : "getBodByNoJSON",
       				"b_order_no" : newBomNo,
       				"newOrder" : "Y"
        		};
        		getBodData(params);
        	</c:if>
        	
        	function getBodData(params){
        		$.ajax({
        			type:"post",
        			url:"<%=request.getContextPath()%>/bod/bod.do",
        			data:params,
        			dataType:"JSON",
        			success:function(data){
        				let html = '';
        				$("#staticBackdropLabel").text( ((params.newOrder === "Y")?"新增訂單成功:":"訂單資訊 -- ")  + data[0].b_order_no);//改h5
        				$("#input_bomId").val(data[0].b_order_no);//改要給刪除資料用的訂單編號
        				$.each(data, function (index, element) {
	        				let room_type_name = rtObj.find(function(item, index, array){
	        					return item.room_Type_No === element.room_type_no;
	        				}).room_Type_Name;
        					html += 
        							'<tr>' + 
        							'<input type="hidden" class="rtNo" value="' + element.room_type_no +'">' + 
        							'<td class="room_type_name">' + room_type_name +'</td><td>' + element.quantity +
        							'</td><td>' + element.total_add_people +'</td><td>' + element.price +'</td></tr>';
                        });
//         				$("#modal-tbody").empty();
        				$("#modal-tbody").html(html);
        				if(bomStatus === '1'){//如果是在查詢
        					$("#btnDeleteBom").remove();
        					$("#btnClose").after('<button type="button" id="btnDeleteBom" class="btn btn-primary">取消整張訂單</button>');
        				}else{
        					$("#btnDeleteBom").remove();
       					}
        				$('#showBom').modal('show');
//         				alert("success");
        			},
        			error: function(){alert("AJAX-grade發生錯誤囉!")}
        		});
        	}
        	
        	$("#modal").on('dblclick','tr',function(){
        		if(bomStatus === '1'){
        			console.log('$("#modal-tbody > tr").length = ' + $("#modal-tbody > tr").length);
        			if($("#modal-tbody > tr").length === 1){
        				tryDeleteBom();
        				return ;
        			}
	        		let room_Type_No = $(this).find(".rtNo").val();
	        		let b_order_no = $("#input_bomId").val();
	        		let room_type_name = $(this).find(".room_type_name").text();
	        		console.log("room_Type_No = " + room_Type_No);
	        		console.log("b_order_no = " + b_order_no);
	        		
	        		swal({
	        			title: '確定刪除' + room_type_name + '?',
	        			text: '刪除之後無法復原',
	        			icon: 'warning',
	       			    showCancelButton: true,
	       			    confirmButtonColor: '#3085d6',
	       			    cancelButtonColor: '#434343',
	       			 	cancelButtonText:'返回',
	       			    confirmButtonText: '刪除!',
	       			 	reverseButtons: true,
	       			 	preConfirm: function () {
	       			 		return new Promise(function (resolve, reject) {
			       				var data = {
			            				"action" : "deleteByJSON",
			            				"b_order_no": b_order_no,
			            				"room_Type_No" : room_Type_No
			            		};
			       				resolve(data);
	       			 		});
		       			 },
	        		}).then(function(data){
	        			if(data){
	//         				alert(data);
	        				deleteBod(data, function(result){
	        					if(result.success === 'Y'){
	        						let params = {
	        		        				"action" : "getBodByNoJSON",
	        		        				"b_order_no" : b_order_no
	        		        		};
	        		        		getBodData(params);
	        		        		getBomData();
		        					swal({
		        						type: 'success',
				    		    		title: '成功刪除 : ' + room_type_name,
				    		    		timer: 1500,
				    		    		showConfirmButton: false
	        						}).catch(swal.noop);
	        					}
	        				},function(result){
	    		    			swal({
			    		    		type: 'error',
			    		    		title: '刪除失敗',
			    		   			html: '<b>失敗訊息是：</b> AJAX error!!!',
		    		   				timer: 1500,
			    		    		showConfirmButton: false
			    		 	   }).catch(swal.noop);
	    		    		});
	        			}
	        		}).catch(swal.noop);
        		}
        	});
        	
        	function deleteBod(data, succ_callback, fail_callback){
    			$.ajax({
    				 type: "POST",
    				 url: "<%=request.getContextPath()%>/bod/bod.do",
    				 data: data,
    				 dataType: "json",
    				 success: succ_callback,
    	             error: fail_callback
    	         });
    		}
        	
        	$("body").on('click', "#btnDeleteBom", function(){
        		tryDeleteBom();
        	});
        	
        	function tryDeleteBom(){
        		console.log("272進deleteBom");
        		
        		let b_Order_No = $("#input_bomId").val();
        		console.log("b_Order_No = " + b_Order_No);
        		swal({
        			title: '確定取消訂單' + b_Order_No + '?',
        			text: '取消之後無法復原',
        			icon: 'warning',
       			    showCancelButton: true,
       			    confirmButtonColor: '#3085d6',
       			    cancelButtonColor: '#434343',
       			 	cancelButtonText:'返回',
       			    confirmButtonText: '取消訂單!',
       			 	reverseButtons: true,
       			 	preConfirm: function () {
       			 		return new Promise(function (resolve, reject) {
		       				var data = {
		            				"action" : "deleteBomByJSON",
		            				"b_Order_No": b_Order_No,
		            		};
		       				resolve(data);
       			 		});
	       			 },
        		}).then(function(data){
        			if(data){
//         				alert(data);
        				deleteBom(data, function(result){
        					if(result.success === 'Y'){
        		        		getBomData();
        		        		$("#showBom").modal('hide');
	        					swal({
	        						type: 'success',
			    		    		title: '成功取消訂單 : ' + b_Order_No,
			    		    		timer: 1500,
			    		    		showConfirmButton: false
        						}).catch(swal.noop);
        					}
        				},function(result){
    		    			swal({
		    		    		type: 'error',
		    		    		title: '取消失敗',
		    		   			html: '<b>失敗訊息是：</b> AJAX error!!!',
	    		   				timer: 1500,
		    		    		showConfirmButton: false
		    		 	   }).catch(swal.noop);
    		    		});
        			}
        		}).catch(swal.noop);
        	}
        	
        	
        	
        	function deleteBom(data, succ_callback, fail_callback){
        		$.ajax({
   				 type: "POST",
   				 url: "<%=request.getContextPath()%>/BookingOrderMaster/bom.do",
   				 data: data,
   				 dataType: "json",
   				 success: succ_callback,
   	             error: fail_callback
   	         });
        	}
        	
        	function getBomData(){
//         		let bomStatus = $("input[name='options']:checked").val();
        		let params = {
        						"action" : "getData",
		        				"cus_Id" : cus_Id,
		        				"status" : bomStatus
        					 };
        		$.ajax({
        			type:"post",
        			url:"<%=request.getContextPath()%>/BookingOrderMaster/bom.do",
        			data: params,
       				dataType: "JSON",
       				success: function(data){
       					$('#bom').bootstrapTable("load", data);
    					$('#bom').bootstrapTable("refresh", { silent: true });
    					$('#bom').bootstrapTable('hideLoading');
       				},
        			error: function(){alert("AJAX-grade發生錯誤囉!")}
        		})
        	}
        	
        	
        	
            // Change top navbar on scroll
            $(window).on("scroll", function() {
                if($(window).scrollTop() > 100) {
                    $(".tm-top-bar").addClass("active");
                } else {                    
                 $(".tm-top-bar").removeClass("active");
                }
            });

            // Smooth scroll to search form
            $('.tm-down-arrow-link').click(function(){
                $.scrollTo('#tm-section-search', 300, {easing:'linear'});
            });


            // Update nav links on scroll
            $('#tm-top-bar').singlePageNav({
                currentClass:'active',
                offset: 60
            });

            // Close navbar after clicked
            $('.nav-link').click(function(){
                $('#mainNav').removeClass('show');
            });

            // Slick Carousel
            $('.tm-slideshow').slick({
                infinite: true,
                arrows: true,
                slidesToShow: 1,
                slidesToScroll: 1
            });
        });
    </script>             
</body>
</html>