<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料</title>
<!-- CSS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-table.min.css">                                   <!-- Templatemo style -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.min.css">                                   <!-- Templatemo style -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">   

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!-- CSS -->
</head>
<body>
	<%@ include file="/front-end/indexFile/header.file" %>   
	
	<div class="container" style="border:5px #FFAC55 solid;">
		<nav>
		  <div class="nav nav-tabs" id="nav-tab" role="tablist">
		    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#cooking" role="tab" aria-controls="nav-home" aria-selected="true">
		    	製作中...
		    </a>
		    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#shipping" role="tab" aria-controls="nav-profile" aria-selected="false">
		    	出餐中...
		    </a>
		    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#sealed" role="tab" aria-controls="nav-contact" aria-selected="false">
		   		歷史訂單
		    </a>
		  </div>
		</nav>
		<div class="container" style="border:5px #black solid;">
			<div class="tab-content" id="nav-tabContent">
			  <div class="tab-pane fade show active" id="cooking" role="tabpanel" aria-labelledby="nav-home-tab">
			  	...1
			  </div>
			  <div class="tab-pane fade" id="shipping" role="tabpanel" aria-labelledby="nav-profile-tab">
			  	...2
			  </div>
			  <div class="tab-pane fade" id="sealed" role="tabpanel" aria-labelledby="nav-contact-tab">
			  	...3
			  </div>
			</div>
		</div>		
	</div>
	
	<%@ include file="/front-end/indexFile/footer.file" %>
<!-- plugin JS -->
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
    
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
    
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
    
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap-table.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    
    <script src="<%=request.getContextPath()%>/front-end/js/sweetalert2.min.js"></script>  
<!-- plugin JS -->	
	<script>
		$(function() {

			// Change top navbar on scroll
			$(window).on("scroll", function() {
				if ($(window).scrollTop() > 100) {
					$(".tm-top-bar").addClass("active");
				} else {
					$(".tm-top-bar").removeClass("active");
				}
			});

			// Smooth scroll to search form
			$('.tm-down-arrow-link').click(function() {
				$.scrollTo('#tm-section-search', 300, {
					easing : 'linear'
				});
			});

			// Update nav links on scroll
			$('#tm-top-bar').singlePageNav({
				currentClass : 'active',
				offset : 60
			});

			// Close navbar after clicked
			$('.nav-link').click(function() {
				$('#mainNav').removeClass('show');
			});

			// Slick Carousel
			$('.tm-slideshow').slick({
				infinite : true,
				arrows : true,
				slidesToShow : 1,
				slidesToScroll : 1
			});
		});
	</script>
</body>


</html>