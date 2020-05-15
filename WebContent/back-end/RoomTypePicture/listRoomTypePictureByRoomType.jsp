<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtypepicture.model.*"%>
<%@ page import="com.roomtype.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	RoomTypePictureService rtpSvc = new RoomTypePictureService();
	String room_Type_No = (String) request.getParameter("room_Type_No");//取得form傳來的房型

	if (room_Type_No == null) {//若沒有from的資訊，回傳放進session.Attribute的房型
		room_Type_No = (String) session.getAttribute("room_Type_No");
	}
	Map<String, String[]> map = new HashMap<>();//準備做SELECT查詢

	map.put("room_Type_No", new String[] { room_Type_No });
	List<RoomTypePictureVO> list = rtpSvc.getAllBy(map);//萬用查詢(?)取得單一房間編號的所有圖片
	pageContext.setAttribute("list", list);//給下面EL用的
	session.setAttribute("room_Type_No", room_Type_No);//把房型編號放進session.Attribute
	if (session.getAttribute("rtName") == null) {//取session的版本，無法即時更新
		Map<String, String> rtName = new HashMap<>();//取得房型編號對應的房名(房型是另一個表格，取房型跟房型編號來讓房型圖片的表格查詢房名)
		RoomTypeService rtSvc = new RoomTypeService();
		for (RoomTypeVO vo : rtSvc.getAll()) {
			rtName.put(vo.getRoom_Type_No(), vo.getRoom_Type_Name());
		}
		session.setAttribute("rtName", rtName);
	}
%>

<html>
<head>
<title>房型圖片 -listOneRoomTypePicture.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/slick/slick.css"/>
	
	
<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/sweetalert2-6.10.3.min.css"> 
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" /> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/bootstrap.min.css"> 
<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/bootstrap-table.min.css"> 
<!-- <link href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" href="../css/photoswipe.css">  -->
<!-- <link rel="stylesheet" href="../css/default-skin.scss">  -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/fluidbox.min.css"> 
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fluidbox/2.0.5/css/fluidbox.min.css">  -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/fluidbox.min.css.map"> 
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fluidbox/2.0.5/css/fluidbox.min.css.map">  -->


	<script  src="<%=request.getContextPath()%>/back-end/js/jquery-3.4.1.min.js"></script>
	<script  src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<!-- 	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script> -->
	<script  src="<%=request.getContextPath()%>/back-end/js/bootstrap-table.min.js"></script>
	<script  src="<%=request.getContextPath()%>/back-end/js/sweetalert.min.js"></script>
<!-- 	<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script> -->
<!-- 	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
	
<!-- 	<script src="../js/photoswipe.min.js"></script>  -->
<!-- 	<script src="../js/photoswipe-ui-default.min.js"></script>  -->
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js"></script>

<!-- 	<script src="../js/jquery.fluidbox.min.js"></script>  -->
	<script  src="<%=request.getContextPath()%>/back-end/js/jquery.fluidbox.min.js"></script>
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/fluidbox/2.0.5/js/jquery.fluidbox.min.js"></script>  -->
	
	
	
	<%@ include file="/back-end/homepage/head.file" %>
<style>


h4 {
	color: blue;
	display: inline;
}
</style>

<style>
.pic {
	max-width: 400px;
	max-height: 300px;
}

 #chooseRT{ 
 display:inline-block;
 margin-left:200px; 
 

</style>

</head>
<body bgcolor='white'>

<%@ include file="/back-end/homepage/middle.file" %> 
	<c:set var="rtNo" value="<%= room_Type_No%>" /> 
				<!-- 以下功能名稱分類:id請照<a>標籤命名去掉btn ex:lineQAbtn改成 lineQA-->
				<!-- class="tableStyle" 表格和h2的樣式 請不要拿掉  -->
			<div id="lineQA" class="tableStyle">
    			<h2 id="showRoomTypeName" class="col-12 title" >房型圖片 - -  ${rtName[rtNo]}<!-- 自己的標頭名稱功能名稱 --></h2>
   			<div class="col-12">
    			
 			<button type="button" id='addBtn' class="btn btn-primary" data-toggle="modal" data-target="#addPage">
			  新增房型圖片
			</button>

<!-- Modal -->


<!-- 這行不能刪!!!下面的ajax會用到 -->
	<jsp:useBean id="rtSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
	<div id="chooseRT">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp">
			<select size="1" id="selectRoomTypeToShowRTP" class="custom-select" name="room_Type_No">
					<option value="all" selected>所有房型
				<c:forEach var="rtVO" items="${rtSvc.all}">
					<option value="${rtVO.room_Type_No}">${rtVO.room_Type_Name}
				</c:forEach>
			</select> 
<!-- 			<input type="hidden" name="action"	value="get_Room_Type_For_Display">  -->
<!-- 			<input type="submit" value="送出"> -->
		</FORM>
	</div>
	
<!-- <table id="table"  data-toggle="table"  data-height="460"  data-pagination="true"  data-pagination-v-align="both"> -->
<table id="demo" class="table" data-unique-id="id" data-height="450" data-sort-name="name" data-height="450"
		data-pagination="true" data-show-columns="true" data-show-toggle="true" data-show-pagination-switch="true"
		data-show-refresh="true" data-search="true" data-page-size="5" data-page-list="[ 5, 10, 20, 50, 100]"
		>
	<thead>
		<tr>
			<th data-field="room_Type_Picture_No">房型圖片編號</th>
			<th data-field="room_Type_No" data-width="100">房型名稱</th>
			<th data-field="room_Type_Picture">房型圖片</th>
			<th data-field="delete">刪除</th>
		</tr>
	</thead>
<%-- 		<%@ include file="page1.file"%> --%>
<%-- 		<%@ include file="page2.file"%> --%>
	<tbody id="tbody">
<%-- 		<c:forEach var="rtpVO" items="${pageScope.list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		<c:forEach var="rtpVO" items="${pageScope.list}" >
			<tr>
				<td>${rtpVO.room_Type_Picture_No}</td>
				<td>
					<%-- 		${rtpVO.room_Type_No} --%> 
<%-- 					<c:set var="rtNo" value="${rtpVO.room_Type_No}" />  --%>
					<a	href='<%=request.getContextPath()%>/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp?room_Type_No=${rtpVO.room_Type_No}'>
						<c:out value="${rtName[rtNo]}" /> 
					</a>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?room_Type_Picture_No=${rtpVO.room_Type_Picture_No}" title="" class="custom-event-2">
						<img class="pic"
						src="<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?room_Type_Picture_No=${rtpVO.room_Type_Picture_No}" />
					</a>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/RoomTypePicture/roomtypepicture.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="room_Type_Picture_No" value="${rtpVO.room_Type_Picture_No}">
						<input type="hidden" name="comeFrom"
							value="../back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



				</div>
				</div>
		
<!-- 			</section> -->
<!--   		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<%@ include file="/back-end/homepage/footer.file" %>


<div class="modal fade" id="addPage" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="max-width:1100px;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">新增房型圖片</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" id="btnSubmit" class="btn btn-primary">新增</button>
      </div>
    </div>
  </div>
</div>
<%-- 	<%@ include file="page2.file"%> --%>
	

  
	<!-- UI JS file -->
<script>

$(function () {
	
	
	let rtNo = '${rtNo}';
let backFromDelete = '${param.room_Type_No}';
	if(backFromDelete === '')backFromDelete = 'all';
	
	getPicFromDB(backFromDelete);
		
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
	
	$("#selectRoomTypeToShowRTP").change(function(){
		let room_Type_No = $(this).find("option:selected").val();
		getPicFromDB(room_Type_No);
	});
	
	
	//從資料庫撈資料
	function getPicFromDB(room_Type_No){
		let infString = {
				"action" : "getData",
				"room_Type_No" : room_Type_No
		}
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/RoomTypePicture/roomtypepicture.do",
			 data: infString,
			 dataType: "json",
			 success: function (data){
				 let tbody = $("#tbody");
				 tbody.empty();
				 let title_Room_Type_Name = '';
				 let same_Room_Type = {};
				 if(room_Type_No != 'all'){
					 same_Room_Type = rtObj.find(function(item, index, array){
						 return item.room_Type_No === room_Type_No;
					 });
					 title_Room_Type_Name = same_Room_Type.room_Type_Name;
				 }else{
					 title_Room_Type_Name = '所有房型';
				 }
				 $.each(data, function(index, element){
					 
					 //取得這個房型的房名
					 if(room_Type_No === 'all'){
						 same_Room_Type = rtObj.find(function(item, index, array){
							 return item.room_Type_No === element.room_Type_No;
						 });
						 title_Room_Type_Name = '所有房型';
			 		 }
					 
					 let html = 
						 '<tr>'+
						'<td>' + element.room_Type_Picture_No + '</td>' +
						'<td>' +
							'<a	href="<%=request.getContextPath()%>/back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp?room_Type_No=' + element.room_Type_No +'">' +
								same_Room_Type.room_Type_Name +
							'</a>'+
						'</td>'+
						'<td>'+
							'<a href="<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?room_Type_Picture_No='+ element.room_Type_Picture_No +'" title="" class="custom-event-2">'+
								'<img class="pic"'+
								'src="<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?room_Type_Picture_No='+ element.room_Type_Picture_No +'" />'+
							'</a>'+
						'</td>'+
						'<td>'+
							'<FORM METHOD="post"'+
								'ACTION="<%=request.getContextPath()%>/RoomTypePicture/roomtypepicture.do"'+
								'tyle="margin-bottom: 0px;">'+
								'<input type="submit" value="刪除"> <input type="hidden"'+
									'name="room_Type_Picture_No" value="'+ element.room_Type_Picture_No +'">'+
								'<input type="hidden" name="comeFrom"'+
									'value="../back-end/RoomTypePicture/listRoomTypePictureByRoomType.jsp">'+
								'<input type="hidden" name="room_Type_No" value="'+ room_Type_No + '">'+
								'<input type="hidden" name="action" value="delete">'+
							'</FORM>'+
						'</td>'+
					'</tr>';
					 tbody.append(html);
				 });
					 $("#selectRoomTypeToShowRTP").val(room_Type_No);
					 $("#showRoomTypeName").text("房型圖片 --  " + title_Room_Type_Name);//更改標題
					 if(data != ''){
						 $('.custom-event-2').fluidbox();//如果直接放在前面4行的tbody.append(html)後面就不行$(...).fluidbox is not a function，不知為何= =
					 }
				 //console.log("有近來");
		     },
            error: function(){alert("AJAX-grade發生錯誤囉!")}
        });
		
		
	};
	
	

    
    
    $("#addBtn").click(function(){
    	let now_Room_Type_No = $("#selectRoomTypeToShowRTP").val();
    	let room_Type_Select = '';
		for(let i = 1 ; i < rtObj.length ; i++){
			room_Type_Select += '<option value="' + rtObj[i].room_Type_No + ((rtObj[i].room_Type_No=== now_Room_Type_No)?'" selected>':'">') + rtObj[i].room_Type_Name +  '</option>';
		}
    	let html = '<form METHOD="post" ACTION="<%=request.getContextPath()%>/RoomTypePicture/roomtypepicture.do" id="form1" name="form1" enctype="multipart/form-data">' +
					  '<div class="form-group">' +
						'<label for="submit_Room_Type" class="pull-left">房型 : </label>' +  
						'<select class="form-control" id="submit_Room_Type" >' +
							room_Type_Select +
					  	'</select>' +
					  '</div>' +
					  '<div class="form-group">' +
					  '圖片 : ' + 
					  '<label for="selectImg"><img style="max-width:90.67px;" src="<%=request.getContextPath()%>/back-end/RoomTypePicture/images/upload.jpg"/></label>' +
					  '<input name="room_Type_Pic" style="display:none;" type="file" multiple="multiple" id="selectImg">' +
					  '</div>' +
					  '<div class="form-group">' +
// 						  '<input type="hidden" name="action" value="insert">' +
// 						  '<input type="submit" value="送出新增">' +
					  '</div>' +
					  
					  '<div class="form-group" id="showPic">' +
					  
					  '</div>' +
				   '</form>';
		  $('.modal-body').html(html); 
// 			console.log("html = " + html);
// 			console.log("$('.modal-body').html() = " + $('.modal-body').html());
    	return ;
    });
    

    $('body').on('change',"#selectImg" ,function(){
    	$("#showPic").html("");
     	readURL(this);

    });

    function readURL(input){//

    	if(input.files && input.files.length >= 0 ){
    		for(var i = 0; i < input.files.length; i++){
    		    var reader = new FileReader();
    		    reader.onload = function (e) {
    				var img = $("<img style='height:300px; width:auto;margin:auto;'>").attr('src', e.target.result);
    				img = $("<div style='padding:3px;width:500px;display:inline-block;'>").append(img);
    				$("#showPic").append(img);
//     				console.log(e.target.result);
    		    }
    		    reader.readAsDataURL(input.files[i]);
    		}
    	}else{
    		$("#showPic").append(noPictures);
     	}
    }
    
    $("#btnSubmit").click(function(){
    	event.preventDefault();
    	var form = $("#form1")[0];
//     	console.log("$('#form1')[0] = " );
//     	console.log(form);
    	let room_Type_No = $('#submit_Room_Type').find("option:selected").val();
    	var fd = new FormData(form);
    	fd.append("action","insert");
    	fd.append("room_Type_No",room_Type_No);
    	$.ajax({
    		url:'<%=request.getContextPath()%>/RoomTypePicture/roomtypepicture.do',
    		type:'post',
    		data:fd,
    		contentType:false,
    		processData:false,
    		success:function(data){
    			getPicFromDB(room_Type_No);
    			alert("上傳成功");
    			
    			$("#addPage").modal('hide');
    		}
    	});
    	
    	});
});
</script>
</body>
</html>