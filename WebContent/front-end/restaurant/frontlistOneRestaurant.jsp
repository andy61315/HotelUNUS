<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.restaurant.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
    RestaurantVO restVO = (RestaurantVO) request.getAttribute("restVO"); 
    //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>list one</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">


<style type="text/css">

    	h1{
          width:800px;
          height:auto;
          text-align: center;/*��r���e�m��*/
          color: brown;
          margin:50px auto;
          background-color: gray;
       }
       p{
       	    width: 800px;
       	    margin:20px auto;
       	    color: #666;/*0,3,6,9,c,f�w����|�b�˸m�W���`���*/
       	    text-indent: 40px;/*���椺�Y*/
       	    letter-spacing: 1px;
       	    line-height: 24px;
       	    text-align: justify;/*���O���k���*/
            border:solid;
            padding: 20px;
            box-sizing:border-box;/*���~�غ����@�ˤj�p�A�ϩ����Y*/
       }
      img#a{
       	    width: 470px;
       	    height:230px;
       	    margin:20px auto;
       	    border:2px solid purple;
       	    display: block; /*��ܦ��϶����A*/
       	    padding:5px; /*���Z*/
       	    /*box-sizing:border-box;*//*���~�غ����@�ˤj�p�A�ϩ����Y*/
       	  
       }
       body{
       	   font-family: "�L�n������",microsoft jhenHei;
           /*background-image: url("images/kman.jpg");
           background-repeat: no-repeat;
           background-position: 20% 20%;
           background-attachment: scroll;*/
       }
       

    </style>

</head>
<body>
        <%@ include file="/front-end/indexFile/header.file" %>               
            
            <div id='codingHere'  style="width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">



<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>�\�U�s��</th> -->
<!-- 		<th>�\�U�W��</th> -->
<!-- 		<th>�y��</th> -->
<!-- 		<th>�p���H</th> -->
		
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td><%=restVO.getResNo()%></td> --%>
<%-- 		<td><%=restVO.getResName()%></td> --%>
<%-- 		<td><%=restVO.getTotalSeat()%></td> --%>
<%-- 		<td><%=restVO.getResContact()%></td> --%>
	
<!-- 	</tr> -->
<!-- </table> -->

<h1><%=restVO.getResName()%></h1>
<div id="wrapper">
<div id="left">
<P>
<a href="<%=request.getContextPath()%>/front-end/resreservation/select_page.jsp">�����w���q��(�Ы���)</a>
<br>
�Ӽh�G�@��<br>
�`�y��ơG310�u<br>
�]�[�G2���A7���b�}��<u><%=restVO.getResName()%></u>�F�o��<br>
�\�U�q��M�u�G${restVO.resPhone} <br>
�� �H�W����ݥt�[�@���A�ȶO�A�H�{�����i���D�A���t��q���C<br>
�� �۳ưs���A�ȶO�G����s���ζ��ƨC�~500���A�P�s���C�~1,000��<br>
��~�ɶ�<br>
<br>
���\�G11:30 AM �V 2:30 PM<br>
�U�ȯ��G3:00 �V 5:00 PM<br>
���\�G�g�@�ܶg�|6:00 �V 9:30 PM�B�g���ܶg��6:00 �V 10:00 PM<br>
<br>
�� �̫��I�\�ɶ�:��~�ɶ������e30����<br><br>
<br>
���T�O�ĵ��w���A�аȥ���u�H�U���\�W�d�G
<br>
�C3��6���H�U�ĵ��ݦܤ֦�1�즨�H���P���\�F�C6��6���H�U�ĵ��ݦܤ֦�2�즨�H���P���\�A�H�������A�ܱz�@�P�u�@�ĵ������\�w���C<br>
</P>
</div>
	
<div id="right">
<!-- <img id="a" src="images/1.png" alt=""> -->
</div>
</div>
<%@ include file="/front-end/indexFile/footer.file" %>   
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
    <script> 
//         $(function(){
//         	    if ($.browser.msie && $.browser.version.substr(0,1)<7) {
//         	      $('li').has('ul')
//         	      .mouseover(function(){
//         	        $(this).children('ul').css('visibility','visible');
//         	      })
//         	      .mouseout(function(){
//         	        $(this).children('ul').css('visibility','hidden');
//         	      })
//         	    }
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