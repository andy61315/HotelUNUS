<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.diary.model.*"%>

<%
//     DiaryService diarySvc = new DiaryService();
// 	String diary_key_name = (String) session.getAttribute("diary_key_name");
    List<DiaryVO> list = (List<DiaryVO>)session.getAttribute("diaryList");
//     request.setAttribute("list",(List<DiaryVO>)session.getAttribute("list"));
%>

<!DOCTYPE html>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">  
                                <!-- Templatemo style -->
      </head>
      <body>
        <%@ include file="/front-end/indexFile/header.file" %>   
                
        <!-- 內容 -->
<!-- 日誌部分 -->
<!-- <div class="container-fluid" style="margin-top: 100px;"> -->
    <!-- 第一層 -->
    <div class="row" style="min-height: 74vh">
        <div class="col-2"></div>
        <div class="col-6">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/select_page.jsp">Home</a></li>
                    
                </ol>
            </nav>
            <h5 class="text-right">
                <%-- 關鍵字查詢 --%>
                <FORM METHOD="post" <%=request.getContextPath()%>/diary/diary.do">
                    <input type="text" class="form-control ml-auto" name="diary_key_name" id="KeywordBox" placeholder="輸入關鍵字">
                    <input type="hidden" name="action" value="getSome_For_Display">
                    <input type="submit" class="btn btn-primary" id="KeywordSearch" value="搜尋">
                </FORM>
                <%-- 錯誤表列 --%>
                <c:if test="${not empty errorMsgs}">
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <a style="color: #1c7430">${message}</a>
                        </c:forEach>
                    </ul>
                </c:if>
            </h5>
            <h1 style="color:#1c7430;">搜尋結果</h1>
            <%@ include file="page1.file" %>
            <div style="margin-top: 30px; background-color: rgba(255, 255, 255, 0.8)" id="textBox" class="rounded">
                
                <table>
                    <c:forEach var="DiaryVO" items="${diaryList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

                        <tr>
                            <td>
                                <div class="card mb-3" style="max-width: 100%;border-radius: 35px;overflow: hidden">
                                    <div class="row no-gutters">
                                        
                                        <div class="col-md-8" style="height: 223px">
                                            <div class="card-body h-100">
                                                <div class="row h-100">
													<jsp:useBean id="memberVO" class="com.cus.model.CustomerService"></jsp:useBean>
                                                    <div class="col-12 title">
                                                        <a href="<%=request.getContextPath()%>/diary/diary.do?action=getOne_For_Display&diary_no=${DiaryVO.diary_no}">
                                                         <div class="card-title"><h2>${DiaryVO.diary_title}</h2></a></div>
                                                    </div>
                                                    
                                                    <div class="col-12 text-right my-auto" style="font-size: 14px">
                                                        <span class="text-muted ml-2">日誌作者: ${memberVO.getOneCus(DiaryVO.cus_id).cus_Name}&nbsp;&nbsp;&nbsp;</span>
                                                        <span class="text-muted ml-2">發表於<fmt:formatDate type="date" value="${DiaryVO.diary_date}"/></span>
                                                        
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <%@ include file="page2.file" %>
            </div>
        </div>
        <div class="col-4">
        </div>
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