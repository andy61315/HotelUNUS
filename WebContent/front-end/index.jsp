<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    <link rel="icon" href="<%=request.getContextPath()%>/front-end/img/unus_Logo-removebg.png">   
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

<!--     <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.min.css"> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/font/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
              <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/dist/photo-sphere-viewer.min.css">
	<script src="<%=request.getContextPath()%>/front-end/dist/three.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/dist/doT.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/dist/uevent.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/dist/photo-sphere-viewer.min.js"></script>
    <style type="text/css">
    
    body {
	background-color: #FFFFFF;
	margin: 0;
	height: 100%;
}

#photosphere {
	border: 2px solid black;
	width: 100%;
	height: 100px;
	cursor: url('<%=request.getContextPath()%>/front-end/img/pointers/A Red Mouse Pointer.cur'),auto;
}

.Admin {
/* 	position: relative; */
}

.demo {
	animation: move1 2s ; 
	/*2秒之內執行完這個動畫*/ 
	/*animation: move1 2s infinite;*/	
	/*加上infinite之後會一直重複操作，類似於定時器中的重複執行*/
	width: 100%;
/* 	position: absolute; */
	top: 3rem;
	color: #ff8c00;
	text-align: center;
	font-size: larger;
	font-weight: bold;
}

@keyframes move1 { 
    /*要執行的動畫*/
    /*從它到它，scale()方法，該元素增加或減少的大小，取決於寬度（X軸）和高度（Y軸）的參數，這個是css3中2D的轉換方法*/
	from { transform:scale(0.1);
	}
	to {
	  transform: scale(1);
    }
}
</style>

      </head>

      <body>
        <%@ include file="/front-end/indexFile/header.file" %>   
        

        <div class="tm-page-wrap mx-auto">
            <section class="tm-banner">
                <div class="tm-container-outer tm-banner-bg">
                    <div class="container">
<!-- <div class="Admin"> -->
<!--   <div class="demo">客房長廊:room-3 &nbsp; 客房:room-1 &nbsp; 浴室:room-2 -->
<!-- 	<div id="photosphere"  style="margin:0px auto;"></div> -->
<!--   </div> -->
<!-- </div> -->
<!--                         <div class="row tm-banner-row tm-banner-row-header"> -->
<!--                             <div class="col-xs-12"> -->
<!--                                 <div class="tm-banner-header"> -->
<!--                                     <h1 class="text-uppercase tm-banner-title">Let's begin</h1> -->
<!--                                     <img src="img/dots-3.png" alt="Dots"> -->
<!--                                     <p class="tm-banner-subtitle">We assist you to choose the best.</p> -->
<!--                                     <a href="javascript:void(0)" class="tm-down-arrow-link"><i class="fa fa-2x fa-angle-down tm-down-arrow"></i></a>        -->
<!--                                 </div>     -->
<!--                             </div>  col-xs-12                       -->
<!--                         </div> row -->
                        <div class="row tm-banner-row" id="tm-section-search">

                            <form id="form1" action="checkroomremain/tryTableandJSON.jsp"  method="get" class="tm-search-form tm-section-pad-2">
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

                        </div> <!-- row -->
                        <div class="tm-banner-overlay"></div>
                    </div>  <!-- .container -->                   
                </div>     <!-- .tm-container-outer -->                 
            </section>

            <section class="p-5 tm-container-outer tm-bg-gray">     
              
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 mx-auto tm-about-text-wrap text-center">                        
                	<div class="Admin">
  <div class="demo">
<!--   客房長廊:room-3 &nbsp; 客房:room-1 &nbsp; 浴室:room-2 -->
	<div id="photosphere"  style="margin:0px auto;"></div>
  </div>
</div>     
<!--                             <h2 class="text-uppercase mb-4">Your <strong>Journey</strong> is our priority</h2> -->
<!--                             <p class="mb-4">Nullam auctor, sapien sit amet lacinia euismod, lorem magna lobortis massa, in tincidunt mi metus quis lectus. Duis nec lobortis velit. Vivamus id magna vulputate, tempor ante eget, tempus augue. Maecenas ultricies neque magna.</p> -->
<!--                             <a href="#" class="text-uppercase btn-primary tm-btn">Continue explore</a>                               -->
                        </div>
                    </div>
                </div>            
            </section>
            
            <div class="tm-container-outer" id="tm-section-2">
                <section class="tm-slideshow-section">
                    <div class="tm-slideshow">
                        <img class="indexImg" src="img/room1.jpg" alt="Image">
                        <img class="indexImg" src="img/room4.jpg" alt="Image">
                        <img class="indexImg" src="img/room3.jpg" alt="Image">    
                    </div>
                    <div class="tm-slideshow-description tm-bg-primary">
                        <h2 class="">眾多房型任您所選</h2>
                        <p>尋找忙碌生活裡的桃花源  何必遠求?<br>
							在三二行館私人管家的貼心細緻服務下  為您打理一切<br>
							在這裡  您只需盡情享受放空的自在</p>
						<a href="#" class="text-uppercase tm-btn tm-btn-white tm-btn-white-primary">Continue Reading</a>
                    </div>
                </section>
            </div>        
            <div class="tm-container-outer" id="tm-section-3">
                <section class="clearfix tm-slideshow-section tm-slideshow-section-reverse">

                    <div class="tm-right tm-slideshow tm-slideshow-highlight">
                        <img src="img/food1.jpg" alt="Image">
                        <img src="img/food2.jpg" alt="Image">
                        <img src="img/food3.jpg" alt="Image">
                    </div> 

                    <div class="tm-slideshow-description tm-slideshow-description-left tm-bg-highlight">
                        <h2 class="">佳餚美饌隨您品味</h2>
                        <p>酒杯輕聲碰撞，瓷盤與刀叉相互問候和美食跳躍舌尖的感動<br>
							讓用餐記憶份外鮮活，一盤好菜 一杯好酒，就是絕美之夜	
						</p>
                        <a href="#" class="text-uppercase tm-btn tm-btn-white tm-btn-white-highlight">Continue Reading</a>
                    </div>                        

                </section>
            </div>  
            <div class="tm-container-outer" id="tm-section-4">
                <section class="tm-slideshow-section">
                    <div class="tm-slideshow">
                        <img src="img/res1.jpg" alt="Image">
                        <img src="img/res2.jpg" alt="Image">
                        <img src="img/res3.jpg" alt="Image">
                    </div>
                    <div class="tm-slideshow-description tm-bg-primary">
                        <h2 class="">四海餐廳邀您饗宴</h2>
                        <p>坐擁極佳視野景觀，讓受到庸碌生活所束縛的城市人，享受沉靜卻又明亮開闊的氣氛環抱。 <a href="#" class="text-uppercase tm-btn tm-btn-white tm-btn-white-primary">Continue Reading</a>
                    </div>
                </section>
            </div>  
            <footer class="tm-container-outer">
                <p class="mb-0">Copyright © <span class="tm-current-year">2020</span> Hotel UNUS 
                    
                . Designed by DA106_G1 <a rel="nofollow" href="http://www.google.com/+templatemo" target="_parent"></a></p>
            </footer>
        </div>
    </div> <!-- .main-content -->

    <!-- load JS files -->
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>

<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker --> 
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%@ include file="/front-end/chatroom/chatroomhtml_front_end.file" %>   
    <script> 
        $(function(){
        	
        	

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

            // Date Picker in Search form
            // var pickerCheckIn = datepicker('#inputCheckIn');
            

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

            $('.tm-current-year').text(new Date().getFullYear());  // Update year in copyright           
        });
//         $(document).ready(function() {
            $("#form1").submit(function(e){
            	var room = parseInt($("#inputRoom").val());//沒加parseInt就會變成字串，下面的if就無法做比對
            	var people = parseInt($("#inputAdult").val());
            	//console.log(room);
            	//console.log(people);
            	if(room > people){
            		swal({
          			  title: "房間數不可大於人數!",
  	  				  text: "請重新填寫",
  	  				  icon: "error",
  	  				  button: "我知道了",
          			});
            	//console.log(room);
            	//console.log(people);
              	  e.preventDefault(e);
            	}
            	
            });
            

            var PSV = new PhotoSphereViewer({
                panorama: "<%=request.getContextPath()%>/front-end/img/rooms/room-1.jpg",
                container: 'photosphere',
                caption: 'TibaMe <b>中壢中心</b> 3D VR',
                loading_img: "<%=request.getContextPath()%>/front-end/img/rooms/room-VR-Tour.gif",

                default_long: 1.57,//初始經度，介於0和2π之間
                default_lat: 0.12,//初始緯度，介於-π/2和π/2之間

                min_fov: 30,//最小視野（對應於最大變焦），介於1和179之間
                max_fov: 90,//最大視野（對應於最小變焦），介於1和179之間
                default_fov: 70,
                time_anim: true,
                mousewheel: true,
                touchmove_two_fingers: true,
                size: {
                  width: 800,
                  height: 400
                },
                markers: [
                    {
                        // html marker with custom style
                        id: 'tag1',
                        longitude: 1.50,
                        latitude: 0.05,
                        html: '浴室<a href="#" style="color:#fff;"><img id="myimg" src="<%=request.getContextPath()%>/front-end/img/rooms/right-arrow.gif" width="80" height="60" /></a>',
                        anchor: 'bottom right',
                        style: {
                          maxWidth: '20px',
                          color: 'white',
                          fontSize: '20px',
                          fontFamily: 'Helvetica, sans-serif',
                          textAlign: 'center'
                        },
                        tooltip: {
                          content: '(tag1)-Watch this 浴室 for more details',
                          position: 'top',
                        }
                    }
                    ,
                    {
                        // html marker with custom style
                        id: 'tag3',
                        longitude: 0.90,
                        latitude: 0.05,
                        html: '客房長廊<a href="#" style="color:#fff;"><img id="myimg" src="<%=request.getContextPath()%>/front-end/img/rooms/left-arrow.gif" width="80" height="60" /></a>',
                        anchor: 'bottom right',
                        style: {
                          maxWidth: '20px',
                          color: 'white',
                          fontSize: '20px',
                          fontFamily: 'Helvetica, sans-serif',
                          textAlign: 'center'
                        },
                        tooltip: {
                          content: '(tag3)-Watch this 客房長廊 for more details',
                          position: 'top',
                        }
                    }
                ]
            })
              
              
            PSV.on('select-marker', function (marker) {
            	  if (marker.id === 'tag1') {
            	        PSV.setPanorama('<%=request.getContextPath()%>/front-end/img/rooms/room-2.jpg', null, true);
            	        PSV.startAutorotate();
            	        if(PSV.hud.markers){
            		　　             PSV.hud.clearMarkers();
            		　　             PSV.addMarker({
            		　　    	     id: 'tag2',
            		　　    	     longitude: -1.55,
                                 latitude: 0.05,
            		             html: '客房<a href="#" style="color:#fff;"><img id="myimg" src="<%=request.getContextPath()%>/front-end/img/rooms/left-arrow.gif" width="80" height="60" /></a>',
            		             anchor: 'bottom right',
            		             style: {
            		               maxWidth: '20px',
            		               color: 'white',
            		               fontSize: '20px',
            		               fontFamily: 'Helvetica, sans-serif',
            		               textAlign: 'center'
            		             },
            		             tooltip: {
            		               content: '(tag2)-Watch this 客房 for more details',
            		               position: 'top',
            		             }
            	             });
            		}
            	   
            	  };
            	  
            	  if (marker.id === 'tag2' || marker.id === 'tag4') {
            		    PSV.setPanorama('<%=request.getContextPath()%>/front-end/img/rooms/room-1.jpg', null, true);
            		    PSV.startAutorotate();
            		    if(PSV.hud.markers){
            			　　    PSV.hud.clearMarkers();
            			　　    PSV.addMarker({
            			　　    	 id: 'tag1',
            			         longitude: 1.50,
            			         latitude: 0.05,
            			         html: '浴室<a href="#" style="color:#fff;"><img id="myimg" src="<%=request.getContextPath()%>/front-end/img/rooms/right-arrow.gif" width="80" height="60" /></a>',
            			         anchor: 'bottom right',
            			         style: {
            			           maxWidth: '20px',
            			           color: 'white',
            			           fontSize: '20px',
            			           fontFamily: 'Helvetica, sans-serif',
            			           textAlign: 'center'
            			         },
            			         tooltip: {
            			           content: '(tag1)-Watch this 浴室 for more details',
            			           position: 'top',
            			         }
            		         });
            			　　     
            			　　     PSV.addMarker({
            				　　      id: 'tag3',
                                  longitude: 0.90,
            			          latitude: 0.05,
            			          html: '客房長廊<a href="#" style="color:#fff;"><img id="myimg" src="<%=request.getContextPath()%>/front-end/img/rooms/left-arrow.gif" width="80" height="60" /></a>',
            			          anchor: 'bottom right',
            			          style: {
            			            maxWidth: '20px',
            			            color: 'white',
            			            fontSize: '20px',
            			            fontFamily: 'Helvetica, sans-serif',
            			            textAlign: 'center'
            			          },
            			          tooltip: {
            			            content: '(tag3)-Watch this 客房長廊 for more details',
            			            position: 'top',
            			          }
            			     });
            			}
            		}
            	  
            	    if (marker.id === 'tag3') {
            		      PSV.setPanorama('<%=request.getContextPath()%>/front-end/img/rooms/room-3.jpg', null, true);
            		      PSV.startAutorotate();
            		      if(PSV.hud.markers){
            			　　          PSV.hud.clearMarkers();
            			　　          PSV.addMarker({
            			　　    	   id: 'tag4',
            			           longitude: 4.69,
            			           latitude: 0.00,
            			           html: '客房<a href="#" style="color:#fff;"><img id="myimg" src="<%=request.getContextPath()%>/front-end/img/rooms/arrowkk2.gif" width="80" height="60" /></a>',
            			           anchor: 'bottom right',
            			           style: {
            			             maxWidth: '20px',
            			             color: 'white',
            			             fontSize: '20px',
            			             fontFamily: 'Helvetica, sans-serif',
            			             textAlign: 'center'
            			           },
            			           tooltip: {
            			             content: '(tag4)-Watch this 客房 for more details',
            			             position: 'top',
            			           }
            		           });
            			  }
            		}
            });
            </script>

            <script>
            		var playground = document.querySelector("#photosphere");

            		var cursorArray = [
            			    'url("<%=request.getContextPath()%>/front-end/img/pointers/hvr_move.cur"), auto',
            			    'url("<%=request.getContextPath()%>/front-end/img/pointers/hvr_resvert.cur"), auto',
            				'url("<%=request.getContextPath()%>/front-end/img/pointers/hvbbr_diag2ne_b.cur"), auto',
            				'url("<%=request.getContextPath()%>/front-end/img/pointers/move.cur"), auto',
            				'url("<%=request.getContextPath()%>/front-end/img/pointers/hvr_reshoriz.cur"), auto',
            				'url("<%=request.getContextPath()%>/front-end/img/pointers/hvbbr_diag1nw_b.cur"), auto'
            		];
            		i = 0;
            		(function cursor() {
            			playground.style.cursor = cursorArray[i];
            			i++;
            			if (i == cursorArray.length) {
            				i = 0;
            			}
            			setTimeout(cursor, 50);
            		})();
            </script>
    </script>             

</body>
</html>