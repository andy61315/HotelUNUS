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