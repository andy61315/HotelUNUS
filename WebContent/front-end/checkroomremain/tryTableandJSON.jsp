<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.checkroomremain.*"%>

<%-- <% --%>
<!--  	List<Combination> list = (List<Combination>)session.getAttribute("list") ; -->
<!--  	int combinationIdx = 0; -->
<%-- %>	 --%>
<html>
<head>
<meta charset="BIG5">
<title>選擇訂房組合</title>
<!-- 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"> -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/slick/slick.css"/>
	
	
<!-- 	以下為首頁轉過來的link -->
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/css/bootstrap.min.css"> --%>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">         
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">  
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/css/templatemo-style.css">                                        <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   
<style>
*{
	box-sizing:border-box;
}

.inSecond-container{
	margin:auto  !important;
}
.showCom{
	margin-left:20px;
}


.detailCom{
	display:none;
}

.expander{
	font-size:1.1rem;
	border:2px solid black;
/* 	width:70%; */
	margin:0px auto 20px;
	
	border-radius: 25px;
	transition: all 0.5s linear;
}

.expander:hover{
	background-color:#a3abad;
	transition: all 0.5s linear;
}

.mainCom{
	padding: 0.2rem;
	box-sizing:border-box;
}
.RTcolumn{
	display:inline-block;
	vertical-align :middle;
}
.comInf{
	display:inline-block;
	margin:auto 20px;
}
.oneOfCom{
	display:inline-block;
	margin-right:20px;
}
.bookingSelect {
 	margin-left:0.7rem; 
 	width: 5.5rem; 
	height:1.4rem;
/*     border-radius: 0.3rem; */
}

.form-control{
/* 	width:150px; */
     border-radius: 0.3rem; 

height:2rem !important;
}

#btnSubmit{
	height: 2rem;
	border-radius:1rem;
    line-height: 0.6rem;
}
.singleRT{
	margin:10px auto;
}

.roomTypePrice{
	width:50px;
	margin-left:5px;
}

.roomTypePriceTxt{
/* 	margin-left:100px; */
	
}
/* .mainPart{ */
/* /* 	display:inline-block; */ 
/* /* 	border:2px solid black; */ 
/* 	height:750px; */
/* } */
.second-container{
	padding:0px;
	min-width:100%;
	display:flex;
}
.row{
min-width:100%;
}

#form1{
/* 	margin:10px auto; */
	margin-bottom:0rem !important;
	width: 90%;
  	border-radius: 1rem;
}

/* .tm-banner-row{ */
/* 	margin-top:20px !important; */
/* } */
.left-side-box{
/* min-width:50%; */
/* 	border:2px solid black; */
}

.middle-side-box{
/* width:50%; */
/* 	border:2px solid red; */
}

.right-side-box{
/* width:50%; */
 	
}


.right-side-inside-box{
	border:2px solid black; 
 	border-radius:1rem;
    height: auto;
    min-height:8rem;
    width: 90%;
    position: fixed;
    width: 16rem;
}

.mainPart{
/* 	margin:0.5%; */
overflow:auto;
}

.sumOfThisCom{
	float:right;
}
.mainPart::-webkit-scrollbar {display:none}
.shoppingCart{
	width:80%;
	margin:5px auto;
/* 	border:2px solid green; */
	text-align:center;
	line-height:40px;
}

.tm-search-form{
	background-color:transparent !important;
	border:2px solid black;
	border-radius:1rem;
}
.selectedInf{
	margin-left:0.8rem;
	display:inline-block;
}
.changeSelectedColor{
	box-shadow: 8px 8px 5px rgba(0, 0, 0, 0.5);
}

.selectAddPeople{
	display:inline-block;
}

.hideCom{
 	animation: hide 1s;  
/* opacity:0; */
}
@keyframes hide {
 	0% { opacity: 1;}
  	50% { opacity: 0.5;}
 	100% { opacity:0;}
}

.showCom {
	animation:showup 1s;
}

@keyframes showup {
 	0% { opacity: 0;}
  	50% { opacity: 0.5;}
 	100% { opacity:1;}
}

/*  信用卡表單的CSS ---------------------------------------  */
.padding {
    padding: 5rem !important;
    margin-left: 300px
}

.card {
    margin-bottom: 1.5rem
}

.card {
    position: relative;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-direction: column;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid #c8ced3;
    border-radius: .25rem
}

.card-header:first-child {
    border-radius: calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0
}

.card-header {
    padding: .75rem 1.25rem;
    margin-bottom: 0;
    background-color: #f0f3f5;
    border-bottom: 1px solid #c8ced3
}

.card-body {
    flex: 1 1 auto;
    padding: 1.25rem
}

.form-control:focus {
    color: #5c6873;
    background-color: #fff;
    border-color: #c8ced3 !important;
    outline: 0;
    box-shadow: 0 0 0 #F44336
}
/* .btn-outline-secondary{ */
/* 	font-size:0.5rem; */
/* 	font-weight:bold; */
/* } */

.cart-titile{
	text-align:center;
	margin:1rem auto;
}
.showName{
	margin:0rem 1.5rem;
  -webkit-transition: all .2s ease-in-out;
          transition: all .2s ease-in-out;
} 

.showName:hover{
	cursor: pointer;
	-webkit-transform: scale(1.2);
  -ms-transform: scale(1.2);
      transform: scale(1.2);
/*   box-shadow: 5px 4px 3px #C7C7C7; */
}

.singleSelectedRT{
	text-align:left !important;
}

label{
display:inline-block;
	margin:auto;
}


.tm-form-group-pad{
	text-align: center;
}

.custom-control{
	display:inline-block !important;
	margin-right: 0.5rem;
}

.form-group{
	margin-bottom: 0.7rem;
}
#tm-section-search, .form-check{
/* 	position:fixed; */
/* 	width:12rem; */
}

#showCreditCard{
	z-index: 10000;
}
/*  信用卡表單的CSS ---------------------------------------  */
</style>
</head>
<body>
        <%@ include file="/front-end/indexFile/header.file" %>   

			
			<div class="second-container">
				<div class="row inSecond-container">
					<div class="col left-side-box mainPart">
						<div class="row " id="tm-section-search">

                            <form id="form1" action="tryTableandJSON.jsp"  method="get" class="tm-search-form tm-section-pad-2">
<!--                             <form id="form1" action="checkroomremain/CheckRoomRemain.do" onsubmit="return validate();" method="post" class="tm-search-form tm-section-pad-2"> -->
                                
                                <div class="form-row tm-search-form-row">

                                    <div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
                                        <label for="inputCheckIn">入住日期</label>
                                        <input name="check-in" type="text" readonly="readonly" class="form-control" id="inputCheckIn" placeholder="Check In">
                                    </div>
                                    <div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">
                                        <label for="inputCheckOut">退房日期</label>
                                        <input name="check-out" type="text" readonly="readonly" class="form-control" id="inputCheckOut" placeholder="Check Out">
                                    </div>
                                    
                                </div> 
                                <div class="tm-search-form-row">     
                                            <!-- form-row                 -->
                                       <!--  <div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
                                            <label for="inputCity">Choose Your Destination</label>
                                            <input name="destination" type="text" class="form-control" id="inputCity" placeholder="Type your destination...">
                                        </div> -->
                                    <div class="form-group tm-form-group tm-form-group-1">                                    
                                        <div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">
                                            <label for="inputRoom">房間數</label>
                                            <select name="room" class="form-control tm-select" id="inputRoom">
                                                <option value="1" selected>1 間房</option>
                                                <option value="2">2 間房</option>
                                                <option value="3">3 間房</option>
                                                <option value="4">4 間房</option>
                                                <option value="5">5 間房</option>
                                                <option value="6">6 間房</option>
                                                <option value="7">7 間房</option>
                                                <option value="8">8 間房</option>
                                                <option value="9">9 間房</option>
                                                <option value="10">10 間房</option>
                                                <option value="11">11 間房</option>
												<option value="12">12 間房</option>
												<option value="13">13 間房</option>
												<option value="14">14 間房</option>
												<option value="15">15 間房</option>
												<option value="16">16 間房</option>
												<option value="17">17 間房</option>
												<option value="18">18 間房</option>
												<option value="19">19 間房</option>
												<option value="20">20 間房</option>
												<option value="21">21 間房</option>
												<option value="22">22 間房</option>
												<option value="23">23 間房</option>
												<option value="24">24 間房</option>
												<option value="25">25 間房</option>
												<option value="26">26 間房</option>
												<option value="27">27 間房</option>
												<option value="28">28 間房</option>
												<option value="29">29 間房</option>
												<option value="30">30 間房</option>
                                            </select>                                        
                                        </div>
                                        <div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">                                       
                                            <label for="inputAdult">人數</label>     
                                            <select name="adult" class="form-control tm-select" id="inputAdult">
                                                <option value="1" selected>1</option>
                                                <option value="2">2人</option>
                                                <option value="3">3人</option>
                                                <option value="4">4人</option>
                                                <option value="5">5人</option>
                                                <option value="6">6人</option>
                                                <option value="7">7人</option>
                                                <option value="8">8人</option>
                                                <option value="9">9人</option>
                                                <option value="10">10人</option>
                                                <option value="11">11 人</option>
												<option value="12">12 人</option>
												<option value="13">13 人</option>
												<option value="14">14 人</option>
												<option value="15">15 人</option>
												<option value="16">16 人</option>
												<option value="17">17 人</option>
												<option value="18">18 人</option>
												<option value="19">19 人</option>
												<option value="20">20 人</option>
												<option value="21">21 人</option>
												<option value="22">22 人</option>
												<option value="23">23 人</option>
												<option value="24">24 人</option>
												<option value="25">25 人</option>
												<option value="26">26 人</option>
												<option value="27">27 人</option>
												<option value="28">28 人</option>
												<option value="29">29 人</option>
												<option value="30">30 人</option>
                                            </select>            
                                        </div>
                                    </div>
                                        <div class="form-group tm-form-group tm-form-group-pad tm-form-group-1 check-availability">
                                        <label style="display:none;" for="btnSubmit">&nbsp;</label>
										<input type="hidden" name="action"  value="showAllCombination">
                                        <button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit">查看空房</button>
                                    </div>
                                </div> <!-- form-row -->                             
                            </form>                             

                        </div> 
                        
						<div class="form-check row">
						<div class="custom-control custom-switch checkboxGroup">  
								<input class="custom-control-input form-check-input checkRoom" type="checkbox" name='checkRoom' value="one" id="checkRoom1" checked>
								<label class="custom-control-label form-check-label" for="checkRoom1">
								  單人房
								</label>
							</div>
							<div class="custom-control custom-switch checkboxGroup">  
								<input class="custom-control-input form-check-input checkRoom" type="checkbox" name='checkRoom' value="two" id="checkRoom2" checked>
								<label class="custom-control-label form-check-label" for="checkRoom2">
								  雙人房
								</label>
							</div>
							<div class="custom-control custom-switch checkboxGroup">  
								<input class="custom-control-input form-check-input checkRoom" type="checkbox" name='checkRoom' value="three" id="checkRoom3" checked>
								<label class="custom-control-label form-check-label" for="checkRoom3">
								  三人房
								</label>
							</div>
							<div class="custom-control custom-switch checkboxGroup">  
								<input class="custom-control-input form-check-input checkRoom" type="checkbox" name='checkRoom' value="four" id="checkRoom4" checked>
								<label class="custom-control-label form-check-label" for="checkRoom4">
								  四人房
								</label>
							</div>
							<div class="custom-control custom-switch checkboxGroup">  
								<input class="custom-control-input form-check-input checkRoom" type="checkbox" name='checkRoom' value="five" id="checkRoom5" checked>
								<label class="custom-control-label form-check-label" for="checkRoom5">
								  五人房
								</label>
							</div>
							<div class="custom-control custom-switch checkboxGroup">  
								<input class="custom-control-input form-check-input checkRoom" type="checkbox" name='checkRoom' value="six" id="checkRoom6" checked>
								<label class="custom-control-label form-check-label" for="checkRoom6">
								  六人房
								</label>
							</div>
						</div>	
					</div>
					
					<div id='allCom' class='col-6 middle-side-box mainPart'></div>
					
					<div class="col right-side-box mainPart">
						<div class="right-side-inside-box">
							<h5 class="cart-titile">購物車</h5>
							<div class="totalRooms shoppingCart">已選擇 :  <span id="roomsCount">0</span>間房</div>
							<div class="selectedRoomType shoppingCart"></div>
							<div class="totalPrice shoppingCart">
								NT$ <span id="txTTLtPrice">0</span>
							</div>
							<div class="send shoppingCart">
								<form id="booking" name="book" action="<%= request.getContextPath()%>/BookingOrderMaster/bom.do" method="POST">
									<input type="hidden" name="action"  value="bookByJSON">
									<input type="hidden" id='JSONArray' name="JSONArray"  value="">
					            	<input type="submit" value="立即訂房" class="button btn btn-outline-secondary">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		</div>
		
		<div class="modal fade" id="showCreditCard" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
	            <div class="card">
	                <div class="card-header">
	                    <strong>信用卡資訊</strong>
	                    <small>請輸入您的信用卡資訊</small>
	                </div>
	                <div class="card-body">
	                    <div class="row">
	                        <div class="col-sm-12">
	                            <div class="form-group">
	                                <label for="name">您的姓名</label>
	                                <input class="form-control" id="name" readonly type="text" placeholder="Enter your name" value="${customerVO.cus_Name}">
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-sm-12">
	                            <div class="form-group">
	                                <label for="ccnumber">信用卡卡號</label>
	                                <div class="input-group">
	                                    <input id="cardNo" class="form-control" type="text" placeholder="0000 0000 0000 0000" autocomplete="email">
	                                    <div class="input-group-append">
	                                        <span class="input-group-text">
	                                            <i class="mdi mdi-credit-card"></i>
	                                        </span>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="form-group col-sm-4">
	                            <label for="ccmonth">月份</label>
	                            <select class="form-control" id="ccmonth">
	                                <option>1</option>
	                                <option>2</option>
	                                <option>3</option>
	                                <option>4</option>
	                                <option>5</option>
	                                <option>6</option>
	                                <option>7</option>
	                                <option>8</option>
	                                <option>9</option>
	                                <option>10</option>
	                                <option>11</option>
	                                <option>12</option>
	                            </select>
	                        </div>
	                        <div class="form-group col-sm-4">
	                            <label for="ccyear">年</label>
	                            <select class="form-control" id="ccyear">
	                                <option>2014</option>
	                                <option>2015</option>
	                                <option>2016</option>
	                                <option>2017</option>
	                                <option>2018</option>
	                                <option>2019</option>
	                                <option>2020</option>
	                                <option>2021</option>
	                                <option>2022</option>
	                                <option>2023</option>
	                                <option>2024</option>
	                                <option>2025</option>
	                            </select>
	                        </div>
	                        <div class="col-sm-4">
	                            <div class="form-group">
	                                <label for="cvv">CVV/CVC</label>
	                                <input class="form-control" id="cvv" type="text" placeholder="123">
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id="btnMagic" class="btn btn-secondary" >神奇小按鈕</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">暫時不要</button>
		        <button type="button" id="btnCreditCard" class="btn btn-primary" data-dismiss="modal">確認訂房</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		
		
		
		
<%-- <%@ include file="/front-end/indexFile/footer.file" %>   <!-- footer --> --%>

	
<%-- 	<script src="<%=request.getContextPath() %>/front-end/js/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> --%>
	<script src="<%=request.getContextPath() %>/front-end/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/front-end/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath() %>/front-end/js/bootstrap.min.js"></script>
<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<%-- 	<script	src="<%=request.getContextPath() %>/front-end/js/jquery-1.11.3.min.js" type="text/javascript"></script> --%>
    <script src="<%=request.getContextPath() %>/front-end/js/popper.min.js"></script>
<%-- 	<script	src="<%=request.getContextPath() %>/front-end/js/bootstrap.min.js"></script> --%>
<!-- 	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- 	<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script> -->
<!-- 	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css"></script><!-- 給信用卡用的 -->
<!-- 	以下為首頁轉過來的link -->
    <script src="<%=request.getContextPath() %>/front-end//js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath() %>/front-end//slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath() %>/front-end//js/jquery.scrollTo.min.js"></script>    
    
<%@ include file="/front-end/indexFile/footer.file" %>  
	<script>
	var nowurl = new URL(document.URL);
	var params = nowurl.searchParams;
		var clientChooseString = '';
		var roomNumCh = new Array("單人房","雙人房","三人房","四人房 ","五人房","六人房","howManyRooms","howManyPeople");
		var roomNumEng = new Array("one","two","three","four","five","six","howManyRooms","howManyPeople");
		$(function(){
			<c:if test="${not empty bookingErrorMsgs}">
				swal({
					icon: 'warning',
		    		title: '${bookingErrorMsgs}',
		   		})
// 		   		alert("訂玩了");
		   		<c:remove var="bookingErrorMsgs" scope="session"/>
			</c:if>
			//console.log("JSON = " + JSON.stringify(clientChooseString));
			getComfromDB();//第一次載入
			$('#allCom').on('click','.mainCom',function(e){//各個排列組合被點擊的時候
// 				//console.log( $(this).prop("class"));
				$(".mainCom").not(this).next().slideUp("slow");//把除了已經點選到的mainCom後面的detailCom縮起來
// 				//console.log($(this).text());
				var target = $(this).next();//找detailCom
// 				//console.log("target.prop('class') = " + target.prop("class"));
// 				//console.log("$(this).text() = " + $(this).text());
				if(!target.is(':empty')){
// 					//console.log('not empty');
					target.slideToggle("slow");
				} else {
// 					//console.log('empty');
					$.ajax({
						url: "CheckRoomRemain.do",
						type: "POST",
						data:creatQueryString(
											  $(this).find('#one').val(),$(this).find('#two').val(),
											  $(this).find('#three').val(),$(this).find('#four').val(),
											  $(this).find('#five').val(),$(this).find('#six').val(),
											  $(this).find('#howManyRooms').val(),$(this).find('#howManyPeople').val(),
											  params.get('check-in'),
											  params.get('check-out')
											 ),
						dataType: "json",
						success: function(data){
							//console.log(data);
							var wholeTag = '';
							$.each(data, function(index, element){ //$.each成功後執行的函數(respnose)
								let singleRT = $("<div class='singleRT'>");
								let name = $("<div class='showName RTcolumn'>").text(element.room_Type_Name);
								let hiddenRTNo = $("<input class='hiddenRTNo' value='" + element.room_Type_No +"' hidden>");//很醜，但下面那種不知為何無法
// 								let hiddenRTNo = $("<input class='hiddenRTNo' hidden>").val(element.room_Type_No);
// 								alert("element.room_Type_No = " + element.room_Type_No);
								let roomTypePriceTxt = $("<div class='roomTypePriceTxt RTcolumn'>").text("優惠房價 :  NT$ ");
// 								let roomTypePrice = $("<div class='roomTypePrice RTcolumn'>").text(((Math.floor(Math.random()*10))%6+1)*1100);
								let roomTypePrice = $("<div class='roomTypePrice RTcolumn'>").text(element.price);
								let addPeopleSelect = $("<select class='addPeopleSelect form-control bookingSelect RTcolumn'>");
								for(var i = 0; i <= parseInt(element.add_People)*parseInt(element.remain); i++){
									let optionTxt = $("<option>").val(i);
									optionTxt.text('加' + i + '人');
									addPeopleSelect.append(optionTxt);
								}
								let addPeoplediv = $("<div class='selectAddPeople'>").append(addPeopleSelect);
								let hiddenAaddPeopleSelectVal = $("<input class='hiddenAaddPeopleSelectVal' value='0' hidden>");//儲存目前房型被選擇到的房間數量，後續購物車作比對用
								
								//選房間數
								let selectTxt = $("<select class='selectRoomNum form-control bookingSelect RTcolumn'>");
								for(var i = 0; i <= element.remain; i++){
									let optionTxt = $("<option>").val(i);
									optionTxt.text(i + '間房');
									selectTxt.append(optionTxt);
								}
								let selectRoomDiv = $("<div class='selectRoomDiv RTcolumn'>").append(selectTxt);
// 								//console.log("selectTxt.html() = " + selectTxt.html());

								let hiddenSelectedRoomValue = $("<input class='hiddenSelectedRoomValue' value='0' hidden>");//儲存目前房型被選擇到的房間數量，後續購物車作比對用
								let singleTag = singleRT.append(hiddenRTNo).append(name).append(roomTypePriceTxt).append(roomTypePrice)
											.append(addPeoplediv).append(hiddenAaddPeopleSelectVal).append(selectRoomDiv).append(hiddenSelectedRoomValue);
								target.append("<div class='singleRT'>" + singleTag.html() + "</div>");
// 								//console.log("singleTag.html()  = " +  singleTag.html() );
						  });		
							target.slideToggle("slow");
						}
					});
				}
			});
			
			$("body").on("click",".showName",function(){
				let roomTypeNo = $(this).parent().find(".hiddenRTNo").val();
				window.location.href = "<%=request.getContextPath()%>/front-end/roomtype/listOneRoomType.jsp?room_Type_No=" + roomTypeNo;

			});
			function getComfromDB(state){//從資料庫取得房型的組合(大的)
				
				let checkIn =  params.get('check-in');
				let checkOut =  params.get('check-out');
				let room =  params.get('room');
				let adult =  params.get('adult');
				let action =  params.get('action');
				if((state != 1)&&(checkIn!=null)){
					$('#inputCheckIn').val(checkIn);
					$('#inputCheckOut').val(checkOut);
					$('#inputRoom').val(room);
					$('#inputAdult').val(adult);
				}
				var favorite = [];//checkbox的內容
	            $.each($("input[name='checkRoom']:not(:checked)"), function(){            
	                favorite.push($(this).val());
	//                 //console.log("$(this).val() = " + $(this).val());
	            });
				clientChooseString = {
						"check-in" : checkIn , 
						"check-out" : checkOut , 
						"room" : room , 
						"adult" : adult , 
						"action" : action ,
						"difRoomNumWanted": favorite.join(",")
				};
				$.ajax({
					url: "CheckRoomRemain.do",
					type: "POST",
					data: clientChooseString,
					dataType: "json",
					success: function(data){
						
						$("#allCom").empty();			
						let allCom = $("#allCom");
						$.each(data, function(index, element){//對單一種組合裡面的1~6人房做計算
							let expander = $("<div class='expander'>");
							let mainCom = $("<div class='mainCom'>");
							let indexOfCom = $("<div class='indexOfCom comInf'>").text(index + 1);
							let Coms = $("<div class='Coms comInf'>");
							for(let i = 0; i < 8; i++){//記得放input name
								if( (i < 6) && (element[roomNumEng[i]] != 0)){
									let oneOfCom = $("<div class='oneOfCom'>").text(roomNumCh[i] + " * " + element[roomNumEng[i]]);
									Coms.append(oneOfCom);
								}
								let inputHidden = $("<input>").attr({"name":roomNumEng[i],
																	"type":"hidden",
																	"id":roomNumEng[i]
																	}).val(element[roomNumEng[i]]);
								mainCom.append(inputHidden);
							}
							mainCom.append(indexOfCom).append(Coms).append("<div class='sumOfThisCom comInf'></div>");
							expander.append(mainCom).append($("<div class='detailCom'>"));
							allCom.append(expander);
						});
// 						debugger;
						if(state === 1){
// 							$("#allCom").fadeIn("slow");
							$("#allCom").addClass("showCom");
							$("#allCom").removeClass("hideCom");
						}
					},
					error: function(){
						//console.log("getComfromDB error");
					},
				});
			}
			
		$(".checkRoom").click(function(){
// 			getComfromDB();//暫時無法解決資料庫更新會比fadein慢的問題，先把它放在最前面權宜
			$("#roomsCount").text(0);
			$("#txTTLtPrice").text(0);
	    	$(".selectedRoomType").empty();
			$(".selectedRoomType").empty();
			$("#allCom").addClass("hideCom");
			$("#allCom").removeClass("showCom");
			getComfromDB(1);
		});
		
		function creatQueryString(one,two,three,four,five,six,howManyRooms,howManyPeople, checkIn, checkOut){
			var queryString={"action":"showCombinationDetail",
								"one":one,
								"two":two,
								"three":three,
								"four":four,
								"five":five,
								"six":six,
								"howManyRooms":howManyRooms,
								"howManyPeople":howManyPeople,
								"check-in": checkIn,
								"check-out":checkOut
								};

// 			//console.log("queryString = " + JSON.stringify(queryString));
			return queryString;
		}
		//準備建立訂單明細物件
		function BOD(room_Type_No,name,roomTypePrice,addPeopleSelected,addPeopleMax,roomselected,roomMax){
			this.room_Type_No = room_Type_No;
			this.name = name;
			this.roomTypePrice = roomTypePrice;
			this.addPeopleSelected = addPeopleSelected;
			this.addPeopleMax = addPeopleMax;
			this.roomselected = roomselected;
			this.roomMax = roomMax;
		}	
		
		var list = []; 
		
			$('#allCom').on('change','.form-control',function(){
				//console.log("----------------------------------------------------------------------");
				//console.log("----------------------------------------------------------------------");
				//console.log("----------------------------------------------------------------------");
				//console.log("----------------------------------------------------------------------");
				//選單被點擊時，一次把那欄的房型編號/選擇到的兩種人數都抓出(hidden input也要)
				//進入list迴圈比對是否有同房型
				//有的話先不加，把list內房型的資料拿出來做比對
				//合則加入list定更改hidden input的值，不合則不加並把被更改的值改回hidden input的值
				var singleRT = $(this).closest(".singleRT");
				let roomSelectedElement = singleRT.find(".selectRoomNum").find("option:selected");//被選擇的房間數元素
				let roomselected = parseInt(singleRT.find(".selectRoomNum").find("option:selected").val());
				
				let oldRoomselectedElement = singleRT.find(".hiddenSelectedRoomValue");//上次選擇的房間元素
				let oldRoomselectedVal = oldRoomselectedElement.val();
				let roomMax = parseInt(singleRT.find(".selectRoomNum").find("option:last").val());//可以選的最大值 
				let room_Type_No = singleRT.find(".hiddenRTNo").val();//房間編號
				let name = singleRT.find(".showName").text();//房型名稱
				let roomTypePrice = parseInt(singleRT.find(".roomTypePrice").text());//房價(整個期間的)
				let addPeopleSelectedElement = singleRT.find(".addPeopleSelect").find("option:selected");//選擇的加床人數的元素本身
				let addPeopleSelected = parseInt(singleRT.find(".addPeopleSelect").find("option:selected").val());//選擇的加床人數
				let addPeopleMax = parseInt(singleRT.find(".addPeopleSelect").find("option:last").val());
				let oldAddPeopleSelectedElement = singleRT.find(".hiddenAaddPeopleSelectVal");
				let oldAddPeopleSelectedVal = oldAddPeopleSelectedElement.val();
					//有的話先不加，把list內房型的資料拿出來做比對
				//合則加入list定更改hidden input的值，不合則不加並把被更改的值改回hidden input的值
				let isNew = 1;
				let isWrong = 0;
				
				$.each(list,function(index,bod){
					//要給這個each最後面做判斷，如果新的總房數為0，則把list內的物件拿掉，並且直接跳離each(不然拿掉物件之後list的size會少1而跳錯)
					let newRoomselected = -1;
					if(bod.room_Type_No == room_Type_No){
						//這邊的意思是"這個房間更新房/人數之後的值"
						newRoomselected = bod.roomselected + (roomselected - oldRoomselectedVal);//如果相等的話代表這個欄位可能選過了，所以要加入的新舊值的差
						newAddPeopleSelected = bod.addPeopleSelected +  (addPeopleSelected - oldAddPeopleSelectedVal);
						actualAddPeopleAvg = newAddPeopleSelected / newRoomselected;
						tolerantAddPeopleAvg = bod.addPeopleMax / bod.roomMax;
						if(newRoomselected == 0){
							list.splice(index,1);
						}
						//錯誤驗證
						if(newRoomselected> bod.roomMax){
							swal({
 	          			  	    title: bod.name + " 總數不能超過 " + bod.roomMax + "間",
	 	  	  			  	    text: "請重新填寫",
	 	  	  				    icon: "error",
	 	  	  				    button: "我知道了",
	 	          			});
							isWrong = 1;
							//console.log("494目前list = ");
							//console.log(list);
// 							return false;
						}else if(actualAddPeopleAvg > tolerantAddPeopleAvg){
							swal({
 	          			  	    title: bod.name + " 的平均加床人數不能超過 " + tolerantAddPeopleAvg + "人",
	 	  	  			  	    text: "請重新填寫",
	 	  	  				    icon: "error",
	 	  	  				    button: "我知道了",
	 	          			});
							isWrong = 1;
							//console.log("505目前list = ");
							//console.log(list);
// 							return false;
						}else if(newAddPeopleSelected > bod.addPeopleMax){//其實這個條件會被上面的擋掉
							swal({
 	          			  	    title: bod.name + " 的總加床人數不能超過 " + bod.addPeopleMax + "人",
	 	  	  			  	    text: "請重新填寫",
	 	  	  				    icon: "error",
	 	  	  				    button: "我知道了",
	 	          			});
							isWrong = 1;
// 							return false;// = break;
						}else{
							isNew = 0;//錯誤驗證都對的話，就可以加入了
							bod.roomselected = newRoomselected;//新值
							bod.addPeopleSelected = newAddPeopleSelected;
							singleRT.find(".hiddenSelectedRoomValue").val(roomselected);//舊元素內放新值
							singleRT.find(".hiddenAaddPeopleSelectVal").val(newAddPeopleSelected);
							if(newRoomselected === 0)return false;
						};
						
						if(isWrong == 1){//如果錯了就要把option改回舊值
							singleRT.find(".selectRoomNum").val(oldRoomselectedElement.val());
							singleRT.find(".addPeopleSelect").val(oldAddPeopleSelectedElement.val());
							//console.log("roomSelectedElement.val() = " + roomSelectedElement.val());
							//console.log("addPeopleSelectedElement.val() = " + addPeopleSelectedElement.val());
							//console.log("531 錯誤訊息 list: ");
							//console.log(list);
							return false;// = break;
						}
// 						//console.log("478 changeData= " + changeData);
					}
					
				});
				if(isWrong == 1){
					//console.log("537出錯，跳離");
					return;
				}
				if(isNew == 1){//如果選擇的房間是新的
					if((addPeopleSelected / roomselected) > (addPeopleMax / roomMax)){
						swal({
          			  	    title: name + " 的平均加床人數不能超過 " + (addPeopleMax / roomMax) + "人",
	  	  			  	    text: "請重新填寫",
	  	  				    icon: "error",
	  	  				    button: "我知道了",
          				});
						//console.log("roomSelectedElement.val() = " + roomSelectedElement.val());//新元素內放舊值
						singleRT.find(".selectRoomNum").val(oldRoomselectedElement.val());
						singleRT.find(".addPeopleSelect").val(oldAddPeopleSelectedElement.val());
						//console.log("552 新房型出錯");
						return ;
					};
					let newBOD = new BOD(room_Type_No,name,roomTypePrice,addPeopleSelected,addPeopleMax,roomselected,roomMax);
					list.push(newBOD);
					oldRoomselectedElement.val(roomselected);//舊元素內放新值
					oldAddPeopleSelectedElement.val(addPeopleSelected);
					//console.log("544 這是新房 list: ");
					//console.log(list);
// 					return;
				}
			    changeSelectedColor();//改變有選中的區塊的顏色
			    console.log("785 list = ");
				console.log(list);
				//console.log("570目前list = ");
				//console.log(list);
			    
				//重新顯示購物車品項(打掉重生)
		    	let ttlPrice = $("#txTTLtPrice");//總價
		    	ttlPrice.text(0);
		    	$(".selectedRoomType").empty();//先把組合清掉
		    	let roomSum = 0;
			    $.each(list, function(i,element){//把list印到購物車
			    	roomSum += element.roomselected;
					let singleSelectedRT = $("<div class='singleSelectedRT'>");//宣告要動態生成的element
					let selectedRTName = $("<div class='selectedRTName selectedInf'>").text(element.name);
					let cross = $("<div class='cross selectedInf'>").text("X");
					let selectQTY = $("<div class='selectQTY selectedInf'>").text(element.roomselected);
					singleSelectedRT.append(selectedRTName).append(cross).append(selectQTY);
					$(".selectedRoomType").append(singleSelectedRT);
					let singleSumPrice = element.roomTypePrice * element.roomselected + element.addPeopleSelected * 500;
					ttlPrice.text(parseInt(ttlPrice.text()) + singleSumPrice);
				});
			    $("#roomsCount").text(roomSum);
			    countSelectedSum();
			});
			
			
			function countSelectedSum(){
					//alert(938);
				$(".expander").each(function(){
					let sum = 0;
					$(this).find(".selectRoomNum").find("option:selected").each(function(){
						sum += parseInt($(this).val());
					});
					if(sum > 0){
						$(this).find(".sumOfThisCom").html("已選擇<b> " + sum + " </b>間房");
					}else{
						$(this).find(".sumOfThisCom").text("");
					}
				});
			}
			
			
			function changeSelectedColor(){
				//用expander來抓，下層所有的房型.each，如果有任何一個的選擇數量>1則變化class2，都無則remove
				$(".expander").each(function(){
					//this = ".expander"
					$(this).find(".singleRT").each(function(){
						if(parseInt($(this).find(".selectRoomNum").find("option:selected").val()) != 0){
							$(this).parent().parent().addClass("changeSelectedColor");
							return false;
						}
						$(this).parent().parent().removeClass("changeSelectedColor");
	 					
					});
				});
			}
			
			$("#booking").submit(function(event){
				event.preventDefault();
				//console.log("list = " + list.length);
				if(list.length==0){
					swal({
      			  	    title: "未選擇房間~",
  	  			  	    text: "請重新填寫",
  	  				    icon: "error",
  	  				    button: "我知道了",
      				});
					return false;
				}
				let jsonStr_1 = '';//準備接BOM
				let jsonStr_2 = list;//準備接BOD
				
				let nowurl = new URL(document.URL);
				let params = nowurl.searchParams;
				let checkIn =  params.get('check-in');
				let checkOut =  params.get('check-out');
				jsonStr_1={
						"cus_Id":"${customerVO.cus_Id}",
						"start_Date":checkIn,
						"end_Date":checkOut,
						"status":0,
						"bookingLocation":nowurl
				};
				let outputJSON ={"BOM":jsonStr_1,"BOD":jsonStr_2};
				outputJSON = JSON.stringify(outputJSON);
				$("#JSONArray").val(outputJSON);//結論是json只要轉一次就好
				$('#showCreditCard').modal('show');
// 				return false;
	// 			//console.log(outputJSON);
			});
		});
		
		$("#btnCreditCard").click(function(){
			$("#booking")[0].submit();
		});
		
		$("#btnMagic").click(function(){
			let cardNo = '';
			for(let i = 0; i < 4; i++){
				cardNo += String(Math.floor(Math.random()*10000));
				if(i < 3) cardNo += ' - ';
			}
			$("#cardNo").val(cardNo);
			
			let mon = (Math.floor(Math.random()*12 + 1));
			$("#ccmonth").val(mon);
			
			let year = (Math.floor(Math.random()*5 +2021));
			$("#ccyear").val(year);
			
			let cvv = (Math.floor(Math.random()*900 +100));
			$("#cvv").val(cvv);
		});
		
		
		
		
		//首頁的
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
            
            $("#inputCheckIn").datepicker({ 
                dateFormat: "yy-mm-dd",
                defaultDate : "+0d",
                minDate : "0d",
                maxDate : "+6m",
                numberOfMonths : [ 1, 2 ],
                onSelect: function(date){            
                	
                        var date1 = $('#inputCheckIn').datepicker('getDate');           
                        var date = new Date( Date.parse( date1 ) ); 
                        date.setDate( date.getDate() + 1 );        
                        var newDate = date.toDateString(); 
                        newDate = new Date( Date.parse( newDate ) );                      
                        $('#inputCheckOut').datepicker("option","minDate",newDate);            
                    },
             onClose: function() {
                $("#inputCheckOut").datepicker("show");
            }
            });
            $('#inputCheckIn').datepicker('setDate', 'today');
            // var pickerCheckOut = datepicker('#inputCheckOut');
            // $("#inputCheckIn").datepicker({ minDate : "0d"});
            $( "#inputCheckOut" ).datepicker({
                dateFormat: "yy-mm-dd",
                defaultDate : "+1d",
                minDate :"+1d",
                maxDate : "+6m+1d",
                numberOfMonths : [ 1, 2 ]
            });
            $('#inputCheckOut').datepicker('setDate', 'today+1d');

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

            $('.tm-current-year').text(new Date().getFullYear()); 
	</script>
</body>
</html>