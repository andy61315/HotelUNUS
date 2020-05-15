<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.diary.model.*" %>
<%@ page import="com.cus.model.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String cus_Id = ((CustomerVO)session.getAttribute("customerVO")).getCus_Id();
    DiaryService diarySvc = new DiaryService();
    List<DiaryVO> list = diarySvc.getMyDiary(cus_Id);
    pageContext.setAttribute("diaryList", list);
%>
<jsp:useBean id="customerSvc" class="com.cus.model.CustomerService"/>
<!DOCTYPE html>
<html lang="en">
<head>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/listMyDiary.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Hotel Unus</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">                                   <!-- Templatemo style -->
    
    <style>
    
    </style>
      </head>
      <body>
        <%@ include file="/front-end/indexFile/header.file" %>   
        
        <nav aria-label="breadcrumb" style="margin-left: -15px;">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/front-end/diary/select_page.jsp">Home</a></li>
					
				</ol>
			</nav>
        
            
            
            
            <div id='codingHere'  style="width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">
       
       
       <div class="row justify-content-center align-items-center p-3" style="font-family: 'Noto Bold', sans-serif">
    <div class="col-12" id="MemBlogScroll">
    <%@ include file="page1.file"%>
        <c:forEach var="DiaryVO" items="${diaryList}">
            <div class="card mb-3" style="max-width: 100%;border-radius: 35px;overflow: hidden">
                <div class="row no-gutters">
<!--                     <div class="col-md-4"> -->
<%--                         <a href="<%=request.getContextPath()%>/front-end/diary/diary.do?action=getOne_For_Display&diary_no=${DiaryVO.diary_no}"><img src="<%= request.getContextPath()%>/front-end/diary/diary.do?action=getBlogImg&blog_no=${BlogVO.blog_no}" class="card-img-top" alt="日誌預覽圖"></a> --%>
<!--                     </div> -->
                    <div class="col-md-8" style="height: 223px">
                        <div class="card-body h-100">
                            <div class="row h-100">

                                <div class="col-12 title">
                                    <a href="<%=request.getContextPath()%>/diary/diary.do?action=getOne_For_Display&diary_no=${DiaryVO.diary_no}">
                                        <h2 class="card-title">${DiaryVO.diary_title}</h2></a>
                                </div>
                                <div class="col-12 card-text JQellipsis" style="color: #000">
                                    <p class="card-text" id="Diary-text" style="color: #3c3c3c">${DiaryVO.diary_content}</p>
                                </div>
                                <div class="col-12 text-right my-auto" style="font-size: 14px">
                                    <span class="text-muted ml-2">發表於<fmt:formatDate type="date" value="${DiaryVO.diary_date}"/></span>
                                    
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </div>
    <%@ include file="page2.file"%>
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
		<%@ include file="/front-end/indexFile/footer.file" %>   
    <script> 
        $(function(){
        	    if ($.browser.msie && $.browser.version.substr(0,1)<7) {
        	      $('li').has('ul')
        	      .mouseover(function(){
        	        $(this).children('ul').css('visibility','visible');
        	      })
        	      .mouseout(function(){
        	        $(this).children('ul').css('visibility','hidden');
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
    
    <script>
    $(function(){
        var len = 50; // 超過50個字以"..."取代
        $(".JQellipsis").each(function(i){
            if($(this).text().length>len){
                $(this).attr("title",$(this).text());
                var text=$(this).text().substring(0,len-1)+"...";
                $(this).text(text);
            }
        });
    });
</script>            
</body>
</html>