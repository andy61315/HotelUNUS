<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.diary.model.*"%>
<!DOCTYPE html>
<%
    DiaryService diarySvc = new DiaryService();
    List<DiaryVO> list = diarySvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Journey HTML CSS Template</title>
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
        
        
        
            
            
            
            <div id='codingHere'  style="width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">
        <div class="test">
<table id="table-1">
	<tr><td>
		 <h3>所有日誌</h3>
<!-- 		   listAlldiary.jsp -->
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>日誌編號</th>
		<th>員工編號</th>
		<th>日誌日期</th>
		<th>日誌標題</th>
		<th>日誌內容</th>
		<th>日誌狀態</th>		
		<th>修改</th>
		<th>刪除</th>
<!-- 		<th>查詢日誌留言</th> -->
	</tr>
	<%@ include file="page1.file" %> 
 	<c:forEach var="diaryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${diaryVO.diary_no}</td>
			<td>${diaryVO.cus_id}</td>
			<td>${diaryVO.diary_date}</td>
			<td>${diaryVO.diary_title}</td>
			<td>${diaryVO.diary_content}</td>
			<td>${diaryStatus[diaryVO.diary_status]}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/diary.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="diary_no"  value="${diaryVO.diary_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/diary.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="diary_no"  value="${diaryVO.diary_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/diary.do" style="margin-bottom: 0px;"> --%>
<!-- 			    <input type="submit" value="送出查詢">  -->
<%-- 			    <input type="hidden" name="diary_no" value="${diaryVO.diary_no}"> --%>
<!-- 			    <input type="hidden" name="action" value="listMessage_Bydiary_no"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="page2.file" %>

                
                
           
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
</body>
</html>