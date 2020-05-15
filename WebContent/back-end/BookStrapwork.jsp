<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Examples</title>
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<style>
/*@media (max-width: 575.99px) { ... }
@media (min-width: 576px) and (max-width: 767.99px) { ... }
@media (min-width: 768px) and (max-width: 991.99px) { ... }
@media (min-width: 992px) and (max-width: 1199.99px) { ... }
@media (min-width: 1200px) { ... }*/
	body{
		font-family:Microsoft JhengHei,Arial Black;
	}
	
	#left{
		height:100vh;
		width: -30px;
		overflow:hidden;
		padding: 0px;
		/*padding-top:20px;
		padding-bottom:20px;*/
		
		 /*<a href="https://pngtree.com/free-backgrounds">free background photos from pngtree.com</a>*/
		/*-webkit-filter: grayscale(50%);
   		filter: grayscale(50%);*//*background-image:url("icon/Ã¢ÂÂPngtreeÃ¢ÂÂ3d abstract background_981834.png");*/
   		background-size: cover;
		background-position: center;
		background-attachment: fixed;
		background-image: linear-gradient(to top left,black,#0F2540,#0F2540,black);	
		/*background-image: url("backgroung_small_c.jpg");*/
		/*filter:contrast(-20%);
    	-webkit-filter:contrast(-20%);*/
    	
		}
	#log img{
		margin-top:20px;
		width:100px;
		margin-left:50%;
		position:relative;
		right:50px;
		background-color: white;
		border-radius:50%;
	}
	#date{
		margin-top: 20px;
		height:50px;
		text-align:center;
		color:#E2943B;
		/*top:200px;*/
		/*font-size:10%;*/
	}
	#date h5{
		top:20px;
	}
	.tableStyle h2{
		font-weight:30%;
		 background-color:#E2943B;
		
	}
	nav{

		text-align:center;
		font-size: 15px;
		position: absolute;

	}
	nav a{
		background-color: white;
		border:1px solid black;
		color:#0F2540;
	}
	nav a:hover{
		background-color:#E2943B;
		color: white;
	}
	nav a img {
		width:20px;
		margin-right:10px;
	}
	#right{
		height:100vh;
		/*width:0px;*/
		overflow-x:hidden;
		overflow-y:auto;
		text-align: center; 
		padding:0px;
		margin: 0px;
		background-size: cover;
		background-position: center;
		background-attachment: fixed;
		scroll-behavior: smooth;
		
		background-image: url("/DA106G1/back-end/img/backgroung_small_c.jpg");
		filter: contrast(-20%);
    	-webkit-filter:contrast(-20%);
    	
	}
	
	#date{
		font-family:Arial Black;
		color:#E2943B;
	}
	#right section {
		margin-top:20px;
		margin-bottom:50px;
		border:2px solid black;
		border-radius:5px;
		height:100%;
		/*background-color:#0F2540;*/
		background-color:rgba(0,0,0,0.3);
	}
	
	.title{
		margin-top:10px;
		border:1px solid black;
		background-color: white;
	}
	#righttitle~div h2.title{
		border:1px solid black;
		background-color:#E2943B;;
	}
	.tableStyle table{
		position:absolute;
		text-align: center;
		width:200px;
	}
	 .tableStyle th{
	 background-color:#E2943B;
	  border:1px solid black;
	 }
	 .tableStyle td{
	 border:1px solid black;
		background-color: white;
	 }
	


</style>
<script>
		$(document).ready(function(){
		var nowDate=new Date();
		var month=nowDate.getMonth()+1;
		var year=nowDate.getFullYear();
		var day=nowDate.getDate();

		$("#date").html("<h5>"+year+"."+month+"."+day+"</h5>");
		// append("<h6>"+month+day+"</h6>");
		
		});
</script>
</head>
<body ><!-- data-target="#nav01" data-spy="scroll" -->
 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<div  class="container-fluid">
  <div class="row">
   	<div id="left" class="col-md-3 col-12" >
 		<div id="log" class="col-lg-12"><img src="<%=request.getContextPath()%>/back-end/img/logo/sea.png" alt="hotel Unus logo"></div>
   		
			<div id="date" class="col-12"></div>
  			<nav class="nav flex-column col-lg-10 col-10 offset-1"  id="myNavbar">		
  				<!-- active fa fa-bed -->
  					
  				  					
  					<a class="nav-link" id="OnSiteBookingbtn"><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-heart-key-100.png" >現場訂房</a>

  					<a class="nav-link" id="bookRoombtn" ><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-money-box-100.png">訂房管理</a>

  					<a class="nav-link" id="cusbtn"><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-cute-pumpkin-100.png">會員管理</a>

  					<a class="nav-link" id="empbtn"><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-thanksgiving-100.png">雇員管理</a>

  					<a class="nav-link"  id="reportbtn" ><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-monster-face-100.png">檢舉管理</a>

  					<a class="nav-link"  id="diarybtn"><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-quill-with-ink-96.png">日誌管理</a>

  					<a class="nav-link" id="lineQAbtn" ><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-cute-monster-100.png">線上客服</a>

  					<a class="nav-link" id="roombtn"><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-heart-key-100.png" >房務管理</a>

  					<a class="nav-link "  id="homebtn"><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-staff-stick-100.png">首頁管理</a>


  					<a class="nav-link"  id="analysisbtn" ><img src="<%=request.getContextPath()%>/back-end/img/icon/Favorites/icons8-search-for-love-100.png">分析</a>


				</nav>	
		
			<div id="color2" class="col-lg-12"></div>
  		</div>
	
    	<div id="right" class="col-md-9 col-12">
    			
    <section class="col-10 offset-1">
    			
				
				
				<!-- 以下功能名稱分類:id請照<a>標籤命名去掉btn ex:lineQAbtn改成 lineQA-->
				<!-- class="tableStyle" 表格和h2的樣式 請不要拿掉  -->
				<div id="lineQA" class="tableStyle">
    			<h2  class="col-12 title" >線上客服<!-- 自己的標頭名稱功能名稱 --></h2>
    			<div class="col-12">
    			<!--表格部分可放自己的內容-->
    			<!--
    			表格內容放這
    			<table>
    				<tr>
    					<th>範例</th>
    					<th>範例</th>
    					<th>範例</th>
    				</tr>
    				<tr>
    				<td>範例</td>
    				<td>範例</td>
    				<td>範例</td>
    				</tr>
    			</table>
    			-->
    			</div>
				</div>

		
			</section>
  		</div>
	</div>
</div>

</body>
</html>