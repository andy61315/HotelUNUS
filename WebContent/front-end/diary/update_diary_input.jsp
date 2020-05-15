<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.diary.model.*"%>
<%
  DiaryVO diaryVO = (DiaryVO) request.getAttribute("diaryVO"); //EmpServlet.java (Concroller) 存入req的diaryVO物件 (包括幫忙取出的diaryVO, 也包括輸入資料錯誤時的diaryVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="<%=request.getContextPath()%>/front-end/ckeditor/ckeditor.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Journey HTML CSS Template</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
    <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
    <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">
    <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">
    <!-- Templatemo style -->

    <style>
        .card-img-top {
            object-fit: cover;
            max-width: 100%;
            height: 350px;
        }
    </style>
    <title>日誌修改 </title>
</head>

<body>
    <%@ include file="/front-end/indexFile/header.file" %>


    <div id='codingHere' style="width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">
        <!-- coco             -->
        <!-- 內容 -->
        <!-- 日誌部分 -->
        <div class="container-fluid" style="margin-top: 10px">
            <!-- 第一層 -->
            <div class="row">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/select_page.jsp">Home</a></li>
                        
                    </ol>
            </div>

            <div class="row" style="min-height: 75%">
                <div class="col-7 p-3" id="textBox">
                    <div class="row">
                        <div class="col-6">
                            <h2 class="mb-3" style="color: #191919;">編輯日誌</h2>
                        </div>
                        <div class="col-3">
                            <c:if test="${not empty errorMsgs }">
                                <font style="color: #b50000">請修正以下錯誤：</font>
                                <ul>
                                    <c:forEach var="message" items="${errorMsgs}">
                                        <li style="color: #b50000">${message}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                        <div class="col-12">
                         <form METHOD="post" ACTION="diary.do" name="form1"
                                style="display: inline-block; width: 800px">
                                <div class="form-group">
                                    <input type="text" name="diary_title" class="form-control"
                                        value="<%=diaryVO.getDiary_title()%>" placeholder="請輸入日誌標題" />
                                </div>

                                <textarea name="diary_content" id="diary_content" rows="10"
                                    cols="80"><%=diaryVO.getDiary_content()%></textarea>
                                <script>
                                    CKEDITOR.replace('diary_content', { height: ['500px'] });
                                </script>
                            
                        </div>

                        <input type="hidden" name="diary_no" value="<%=diaryVO.getDiary_no()%>">
                        <input type="hidden" name="action" value="getOne_For_Update">
                        <input type="submit" class="btn btn-primary" id="sendBtn" value="送出">

                        </form>
                    </div>

                </div>
            </div>

        </div>
        <!-- coco        -->

        <c:if test="${not empty errorMsgs}">
            <font style="color:red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li style="color:red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>

    </div>



    <!-- load JS files -->
    <!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

    <script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script> <!-- https://popper.js.org/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>
    <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>
    <!-- http://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>
    <%@ include file="/front-end/indexFile/footer.file" %>
    <!-- https://github.com/flesler/jquery.scrollTo -->

    <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

    <% 
  java.sql.Date diary_date = null;
  try {
	  diary_date = diaryVO.getDiary_date();
   } catch (Exception e) {
	   diary_date = new java.sql.Date(System.currentTimeMillis());
// 	   String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new java.sql.Timestamp(System.currentTimeMillis()));
   }
%>

    <style>
        .xdsoft_datetimepicker .xdsoft_datepicker {
            width: 300px;
            /* width:  300px; */
        }

        .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
            height: 151px;
            /* height:  151px; */
        }
    </style>
    <script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
            theme: '',              //theme: 'dark',
            timepicker: false,       //timepicker:true,
            step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
            format: 'Y-m-d',         //format:'Y-m-d H:i:s',
            value: <%=diary_date%> // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
    </script>

    <script>
        $(function () {
            if ($.browser.msie && $.browser.version.substr(0, 1) < 7) {
                $('li').has('ul')
                    .mouseover(function () {
                        $(this).children('ul').css('visibility', 'visible');
                    })
                    .mouseout(function () {
                        $(this).children('ul').css('visibility', 'hidden');
                    })
            }
            // Change top navbar on scroll
            $(window).on("scroll", function () {
                if ($(window).scrollTop() > 100) {
                    $(".tm-top-bar").addClass("active");
                } else {
                    $(".tm-top-bar").removeClass("active");
                }
            });

            // Smooth scroll to search form
            $('.tm-down-arrow-link').click(function () {
                $.scrollTo('#tm-section-search', 300, { easing: 'linear' });
            });


            // Update nav links on scroll
            $('#tm-top-bar').singlePageNav({
                currentClass: 'active',
                offset: 60
            });

            // Close navbar after clicked
            $('.nav-link').click(function () {
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
    <script src="<%=request.getContextPath()%>/js/NavBarEvent.js"></script>
    <script src="<%=request.getContextPath()%>/js/popper.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-4.3.1.js"></script>
</body>

</html>