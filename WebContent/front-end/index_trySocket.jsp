<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Journey HTML CSS Template</title>
<!-- 
Journey Template 
http://www.templatemo.com/tm-511-journey
-->
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    <link rel="stylesheet" href="css/templatemo-style.css">                                   <!-- Templatemo style -->
      </head>

      <body>
        <div class="tm-main-content" id="top">
            <div class="tm-top-bar-bg"></div>    
            <div class="tm-top-bar" id="tm-top-bar">
                <div class="container">
                    <div class="row">
                        <nav class="navbar navbar-expand-lg narbar-light">
                            <a class="navbar-brand mr-auto" href="#">
                                <img src="img/USUN1.png" alt="Site logo">
                                Hotel UNUS
                            </a>
                            <button type="button" id="nav-toggle" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#mainNav" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div id="mainNav" class="collapse navbar-collapse tm-bg-white">
                                <ul class="navbar-nav ml-auto">
                                    <li class="nav-item">
                                        <a class="nav-link active" href="#top">Home <span class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-2">房型瀏覽</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-3">餐點瀏覽</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-4">餐廳預約</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-5">旅客日誌</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-6">聯絡我們</a>
                                    </li>
                                </ul> 
                            </div>                            
                        </nav>
                    </div> <!-- row -->
                </div> <!-- container -->
            </div> <!-- .tm-top-bar -->
            <div id='codingHere'  style="width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">
                
                
                
                
                
                
            </div>
            
            
            
            
            <footer  style="width:98vw;margin:0px auto;">
                <p class="mb-0">Copyright © <span class="tm-current-year">2018</span> Your Company 
                    
                . Designed by <a rel="nofollow" href="http://www.google.com/+templatemo" target="_parent">Template Mo</a></p>
            </footer>
    </div> <!-- .main-content -->
                
<!--             <div id="cusSvcIcon" style="position:fixed;width:70px;height:70px;top:83%;left:93%;"> -->
<!-- 	            <img src="img/icon-message.png"> -->
<!--             </div> -->
            
<%@ include file="/front-end/chatroom/chatroomhtml_front_end.file" %>   
    <!-- load JS files -->
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>

	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker --> 
    <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script> 
        $(function(){
        	
        	
//         	以下為客服聊天







			var myPoint = "/CusSvcChat/";
			var id = ["cus","emp","vis"];
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var webSocket;
			var randomN;
			var userId;
			var firstGetMsg = 1;
			connect();//老師註冊在body
			
// 			XXX.click(){
// 				getHistoryMsgs(chatWith);
// 			}
			
			function getHistoryMsgs(userId,chatWith){
				obj = {
						"sender":
				}
			}
			
			function connect(){
				userId = id[Math.floor(Math.random() * 3)] + Math.floor(Math.random() * 100);
				var endPointURL = "ws://" + window.location.host + webCtx + myPoint + userId;
				
				webSocket = new WebSocket(endPointURL);
				
				console.log("endPointURL = " + endPointURL);
				
				webSocket.onopen = function(event){
					
// 					getHistoryMsgs(userId,chatWith);					
				}
				
				//接收訊息
				webSocket.onmessage = function(event){
					var jsonObj = JSON.parse(event.data);
					alert("138收到訊息");
					let sender = jsonObj.sender;
					let text = jsonObj.message;
					let isPic = jsonObj.isPic;
					
					if(isPic === 1){
						text = '<img id="chatPic" src="' + text + '">';
					}
					
					if(firstGetMsg === 1){
						if(userId.includes("emp")){
							if(sender.includes("cus")){
								$("#chat_head").text("會員 " + sender);
							}else if(sender.includes("vis")){
								$("#chat_head").text("訪客" + sender);
							}
						}
						if(sender != userId)$(".receiver").text(sender);//紀錄此對話框是在跟誰對話
						firstGetMsg = 0;
					}
					
					let html = '';
					console.log("sender = " + sender);
					console.log("userId = " + userId);
					if((sender.toString() === userId) || ((sender.includes("emp"))&&(userId.includes("emp")))){
						html = '<span class="chat_msg_item chat_msg_item_user">' +
			            		text + '</span>';
					}else{
						html = '<span class="chat_msg_item chat_msg_item_admin">'+
				            '<div class="chat_avatar">'+
			                '<img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg"/>'+
			            	'</div>' + text + '</span>';
					}
					$("#chat_converse").append(html);
					$("#chat_converse").scrollTop($('#chat_converse').prop('scrollHeight'));
				}
					
				webSocket.onclose = function(event){
					console.log("離線");
					console.log(event);
				}
			}
			
			function sendMessage(message,isPic) {
				
				let receiver = $(".receiver").text();
				//只有會員(或訪客)在第一次送值的時候可能為空(員工無法主動發起對話)
				if(receiver === ''){
					receiver = "emp";
				}
				if(message === ""){
					$("#chatSend").focus();
				}else{
					let jsonObj = {
							"sender" : userId,//在send中，此人是sender
							"receiver" : receiver,
							"message" : message,
							"isPic" : isPic //判斷是否為圖片
					};
					
					console.log("jsonObj = ");
					console.log(jsonObj);
					let jsonStr = JSON.stringify(jsonObj);
					webSocket.send(jsonStr);
					$("#chatSend").val("");
					$("#chatSend").focus();
				}
			}
			
			$("body").on("click","#fab_send",function(){
				let message = $("#chatSend").val().trim();
				sendMessage(message,0);
			});
			
			$("#inputUpdatePic").change(function(){
				let input = this;
				let message = '';
				console.log(input.files);
				console.log(input.files[0]);
				
				if(input.files && input.files[0]){
alert(210);
				    var reader = new FileReader();

				    reader.onload = function (e) {

				    	message =  e.target.result;
						console.log("e.target.result = ");
						console.log(e.target.result);
						console.log("outputSrc = ");
						console.log("typeof e.target.result = ");
						console.log(typeof e.target.result);
						sendMessage(message,1);

				    }

				    reader.readAsDataURL(input.files[0]);

				  }
			});
			
			//function disconnect
			$("body").on( "unload" ,function disconnect () {
				webSocket.close();
				document.getElementById('sendMessage').disabled = true;
				document.getElementById('connect').disabled = false;
				document.getElementById('disconnect').disabled = true;
			});
			
			
			
			
			
			
// 以上為客服聊天			
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
    

    <script>
// 請在這裡coding
        









    </script>
</body>
</html>