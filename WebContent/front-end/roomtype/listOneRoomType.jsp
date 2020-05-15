<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.roomtypepicture.model.*"%>

<%
	String room_Type_No = request.getParameter("room_Type_No");
	RoomTypeService rtSvc = new RoomTypeService();
	RoomTypeVO thisRtVO = rtSvc.findOneByNo(room_Type_No);
	pageContext.setAttribute("thisRtVO", thisRtVO);
	
	
	//取得此房型的所有圖片
	Map<String, String[]> map = new HashMap<>();
	map.put("room_Type_No",new String[]{room_Type_No});
	
	RoomTypePictureService rtpSvc = new RoomTypePictureService();
	List<RoomTypePictureVO> list = rtpSvc.getAllBy(map);
	pageContext.setAttribute("list", list);
	int isFirst = 1;
	
	//取得和此房型的人數一樣的其他房型
	map.remove("room_Type_No");
	map.put("person_Capacity", new String[]{String.valueOf(thisRtVO.getPerson_Capacity())});
	map.put("room_Type_Status", new String[]{"1"});
	List<RoomTypeVO> similarList = rtSvc.getAllBy(map);
	pageContext.setAttribute("similarList", similarList);
	
%>

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

    <link rel="stylesheet" href="../css/jquery-ui.min.css"> <!-- themes/smoothness/jquery-ui.css -->                      <!-- Bootstrap style -->
<!--     <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">         <!--         Font Awesome -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="../slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../slick/slick-theme.css"/>
    <link rel="stylesheet" href="../css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   
<style>
.room_Name{
	height:100px;
	margin-bottom: 20px;
}

h3,h4{
	line-height: 100px;
	text-align: center;
	font-size: 50px;
}
    
figure a{
	opacity:0;
}
.room_Detail{
	width:70%;
	height:600px;
	margin: 20px auto;
}
.room_Detail_Left, .room_Detail_Right{
	margin:0px auto;
	width:50%;
	height:500px;
/* 	display:inline-block; */
/* 	border:2px solid yellow; */
}

.room_Detail_Left{
	float:left;
}

.room_Detail_Right{
	float:right;
	padding:0px 10px;
	box-sizing:border-box;
}

.room_Detail_Right p{
	font-size:20px;
/* 	padding: */
}

.room_Detail_Left div img{
	max-width:100%;
}

.room_Pic_Collection{
	max-width:60%;
	
	margin: 20px auto 70px;
}
img.room_Type_Pic{
	width:80rem;
	height:35rem;
}

/* .similar_Room_Type_Pic{ */
/*  	width:400px;  */
/* 	height:400px; */
/* } */

figure{
	width: 400px;
	height: 300px;
	overflow: hidden;
}
figure img{
	width: 100%;
	height: 100%;
	/*transition: all 0.5s;*/
}

figure:hover img{
	/*width: 120%;
	height: 100%;*/
	transition: all 1s;/*放這裡也可以唷*/
	transform: scale(1.5,1.5);
}

figure figcaption{
	position: relative;
	width: 100%;
	height: 100%;
	background-color: rgba(50,50,50,0.5);
	top: -102%;
	left: 0;
	z-index: -1;
	text-align: center;
	padding-top: 35%;
	box-sizing: border-box;
	color: #fff;
	font-size: 26px;
	transition: all 1s;
	opacity: 0;
}
figure:hover figcaption{
	z-index: 2;
	cursor: pointer;
	opacity: 3;
}

figcaption a{
	width: 100%;
	height: 100%;
}
.other_Room{
	width:80%;
	margin:auto;
}

.similar_Room_Type_Pic{
	background-color:black;
	padding:2px;
	box-sizing:border-box;
}
</style>
    
    
    
</head>

<body>
        <%@ include file="/front-end/indexFile/header.file" %>   
      
      	  <h3 class='room_Name'>${thisRtVO.room_Type_Name}</h3>
          <div class='room_Detail'>
          	<div class='room_Detail_Left'>
          		<div><img src='<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?action=getOneForDisplay&room_Type_No=${thisRtVO.room_Type_No}'></div>
          		<div class='bookingData'></div>
          	</div>
          	
          	<div class='room_Detail_Right'>
          		<p class='article'>${thisRtVO.article}</p>
          		<hr>
          		<div class='hotel_Service'>
          			<p><strong>衛浴設備｜</strong>分離式淋浴、吹風機、盥洗用品、浴缸</p>
          			<p><strong>娛樂｜</strong> Wi-Fi、液晶電視、有線頻道、國際直撥電話服務</p>
          			<p><strong>舒適設備｜</strong>Morning call、空調、室內拖鞋</p>
          			<p><strong>餐飲服務｜</strong>冰箱、即溶咖啡、茶包、瓶裝水、快煮壺</p>
          			<p><strong>衣物與洗衣設備｜</strong>衣櫃衣架、洗衣乾洗服務</p>
          			<p><strong>安全特色｜</strong>晶片式安全感應門鎖</p>
          		</div>
          	</div>
          	
          </div>



<h4>房間美景</h4>

          <div class='room_Pic_Collection'>
          	
          	
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
	<c:forEach var="i" begin="0" end="<%=list.size() %>">
	    <li data-target="#carouselExampleIndicators" data-slide-to="${i}" ${(i==0)?"class='active'":""}></li>
    </c:forEach>
  </ol>
  <div class="carousel-inner">
	  <c:forEach var='rtpVO' items='${list}' varStatus="userStatus">
	  	<c:if test="${!userStatus.first}">
		    <div class="carousel-item <%= ((isFirst == 1)?"active":"") %><% isFirst = 0;%>">
		      <img class='room_Type_Pic' src="<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?room_Type_Picture_No=${rtpVO.room_Type_Picture_No}" class="d-block w-100" alt="...">
		    </div>
	    </c:if>
	  </c:forEach>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
          	
          	
          </div>
          
          <h4>其他相似房型</h4>
          
          <div class='other_Room'>
          	<c:forEach var='rtVO' items='${similarList}'>
          		<c:if test="${rtVO.room_Type_No != thisRtVO.room_Type_No}">
	<%-- 	    <div class="carousel-item <%= ((isFirst == 1)?"active":"") %><% isFirst = 0;%>"> --%>
				<div class="similar_Room_Type_Pic" ">
					
						<a href="<%=request.getContextPath()%>/front-end/roomtype/listOneRoomType.jsp?room_Type_No=${rtVO.room_Type_No}">
					<figure>
			      		<img class='similar_Room_Type_Pic' src="<%=request.getContextPath()%>/RoomTypePicture/GetRoomTypePicture?action=getOneForDisplay&room_Type_No=${rtVO.room_Type_No}">
						<figcaption>${rtVO.room_Type_Name}</figcaption>
					</figure>
		<!-- 	    </div> --></a>
				</div>
				</c:if>
	 		 </c:forEach>
          </div>
          
          
      

    <!-- load JS files -->
<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
    <script src="../js/jquery-1.11.3.min.js"></script>   <!-- 1.11.4 -->                 <!-- https://popper.js.org/ -->       

    <script src="../js/jquery-ui.min.js"></script>   <!-- 1.11.4 -->                 <!-- https://popper.js.org/ -->       
<!-- 	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
    <script src="../js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    <script src="../js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker --> 
    <script src="../js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="../slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    <script src="../js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    
	<%@ include file="/front-end/indexFile/footer.file" %>   
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
            	  centerMode:true,
                  centerPadding: '100px',
                  slidesToShow: 1,
                  slidesToScroll: 1,
                  autoplaySpeed: 2000,  
                  dots: true,
                  arrows: true,
              });

            $('.other_Room').slick({
                centerMode:true,
                centerPadding: '10px',
                slidesToShow: 2,
                slidesToScroll: 2,
                autoplaySpeed: 2000,  
                dots: true,
                arrows: true,
                variableWidth: true,//讓圖片之間沒有間隔
                
            });
        });

    </script>             
    

    <script>
// 請在這裡coding
        









    </script>
</body>
</html>