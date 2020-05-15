<%@page import="com.cus.model.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diary.model.*"%>
<%@ page import="java.util.*"%>

<%
  DiaryVO diaryVO = (DiaryVO) request.getAttribute("diaryVO");
  CustomerVO customerVO = (CustomerVO) session.getAttribute("customerVO");
%>

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
    <link rel="stylesheet" type="text/css"
        href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
    <script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>

    <style>

    </style>
</head>

<body>
    <%@ include file="/front-end/indexFile/header.file" %>






    <div id='codingHere' style="padding:30px; width:98vw; height:1000px;margin: 10px 5px; border:5px solid black">

        <table id="table-1">
            <tr>
                <td>

                    <nav aria-label="breadcrumb" style="margin-left: -15px;">
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
					<a href="<%=request.getContextPath()%>/front-end/diary/select_page.jsp">Home</a></li>
					
				</ol>
			</nav>
                </td>
            </tr>
        </table>
        <div style="background-color:#FFFFFF">
            <h5>新增日誌</h5>
        </div>

        <%-- 錯誤表列 --%>
<%--         ${diaryInsertErrorMsgs} --%>
        <c:if test="${not empty diaryInsertErrorMsgs}">
            <font style="color:red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${diaryInsertErrorMsgs}">
                    <li style="color:red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>

        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/diary.do">
            <table>
                <!-- 	<tr> -->
                <!-- 		<td>日誌編號:</td> -->
                <!-- 		<td><input type="TEXT" name="diary_no" size="45"  -->
                <%-- 			 value="<%= (diaryVO==null)? "DI000001" : diaryVO.getDiary_no()%>" /></td> --%>
                <!-- 	</tr> -->

                <tr>
                    <!-- 		會員編號: -->
                    <td>
                        <input type="hidden" name="cus_id" size="45" value="<%=customerVO.getCus_Id()%>" />
                    </td>
                </tr>
                <tr>
                    <!-- 		<td>日誌日期:</td> -->
                    <td>
                        <input type="hidden" name="diary_date" id="f_date1" type="text">
                    </td>
                </tr>
                <tr>
                    <td style="padding:10px">日誌標題</td>
                    <td><input type="TEXT" name="diary_title" size="45"
                            value="<%= (diaryVO==null)? "這是標題" : diaryVO.getDiary_title()%>" />
                    </td>
                </tr>
                <tr>
                    <td style="padding:10px">日誌內容</td>
                    <td style="width: 800px">
                        <textarea name="content" id=content rows="10" cols="80"><%= (diaryVO==null)? "" : diaryVO.getDiary_content()%></textarea>
                        <script>
                            CKEDITOR.replace('content', { height: ['500px'] }); 
                        </script>
                        <%-- 			<%=diaryVO.getDiary_content()%> --%>
                        <!-- 				<input type="TEXT" name="diary_content" size="45" -->
                        <%-- 			 value="<%= (diaryVO==null)? "輸入內容" : diaryVO.getDiary_content()%>" /> --%>
                    </td>
                </tr>
                <tr>
                    <!-- 		日誌狀態 -->
                    <td><input type="hidden" name="diary_status" size="45" value="1" />
                    </td>
                </tr>

                <%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
                <!-- 	<tr> -->
                <!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
                <!-- 		<td><select size="1" name="deptno"> -->
                <%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
                <%-- 				<option value="${deptVO.deptno}" ${(diaryVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
                <%-- 			</c:forEach> --%>
                <!-- 		</select></td> -->
                <!-- 	</tr> -->

            </table>
            <!-- 	<p> -->
            <!-- 		<select size="1" name="resvPeriod"> -->
            <%--         <c:forEach var="reserv" items="${diaryStatus}"> --%>
            <%--          <option value="${reserv.key}">${reserv.value} --%>
            <%--         </c:forEach> --%>
            <!--       </select> -->
            <!--      </p>  -->
            <br>
            <input type="hidden" name="action" value="insert">
            <input type="submit" value="送出新增">
        </FORM>
</div>



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

        <!-- load JS files -->
        <!--      <script src="http://code.jquery.com/jquery-1.10.2.js"></script>  -->

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
        <script>
            $(function () {
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
</body>

</html>