<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.diary.model.*"%>
<!DOCTYPE html>
<%
	DiaryService diarySvc = new DiaryService();
	List<DiaryVO> list = diarySvc.getAll();
	List<DiaryVO> poplist = new ArrayList();
	for (DiaryVO dVO : list) {
		if (dVO.getDiary_status() == 1) {
			poplist.add(dVO);
		}
	}
	pageContext.setAttribute("poplist", poplist);

	DiaryService diarySvcNew = new DiaryService();
	List<DiaryVO> listNew = diarySvc.getLatestDiaries();
	pageContext.setAttribute("listNew", listNew);
%>
<jsp:useBean id="diaryVO" scope="page" class="com.diary.model.DiaryService" />
<jsp:useBean id="customerSvc" class="com.cus.model.CustomerService" />
<html lang="en">

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Hotel Unus</title>
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
		#top {
			background-color: #424548;
		}
	</style>
</head>

<body>
	<%@ include file="/front-end/indexFile/header.file"%>






	<div id='codingHere' style="width: 98vw;  margin: 10px 5px; ">

		<!-- 內容 -->
		<!-- 日誌部分 -->
		<!-- <div class="container-fluid" style="margin-top: 100px;"> -->
		<!-- 第一層 -->
		<div class="row justify-content-around">
			<!-- 左邊欄位的所有日誌 -->
			<div class="col-8">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/select_page.jsp">Home</a></li>
						<li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/addDiary.jsp">新增日誌</a></li>
						<li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/listMyDiary.jsp">我的日誌</a></li>
					</ol>
				</nav>
				<h5 class="text-right">
					<%-- 關鍵字查詢 --%>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/diary.do">
						<input type="text" class="form-control ml-auto" name="diary_key_name" id="KeywordBox"
							placeholder="輸入關鍵字">
						<input type="hidden" name="action" value="getSome_For_Display">
						<input type="submit" class="btn btn-primary" id="KeywordSearch" value="搜尋">
					</FORM>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<a style="color: #1c7430;">${message}</a>
							</c:forEach>
						</ul>
					</c:if>
				</h5>

				<!-- 日誌列表 -->
				<%@ include file="page1.file"%>
				<h4 style="color: #FFFFFF;">所有日誌</h4>
				<div class="rounded" style="margin-top: 30px; background-color: rgba(255, 255, 255, 0.8)" id="textBox">
					<c:forEach var="DiaryVO" items="${poplist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<div class="card mb-3" style="max-width: 100%; border-radius: 35px; overflow: hidden">
<!-- 						<div class="card mb-3" style="max-width: 100%; border-radius: 35px; overflow: hidden"> -->
							<div class="row no-gutters">
								<div class="col-md-10" style="height: 220px">
									<div class="card-body h-100">
										<div class="row h-100">

											<div class="col-12 title" style="color: #3c3c3c; font-size: 12px;">
												<a
													href="<%=request.getContextPath()%>/diary/diary.do?action=getOne_For_Display&diary_no=${DiaryVO.diary_no}">
													<h5 class="card-title">${DiaryVO.diary_title}</h5>
												</a>
											</div>
											<div class="col-12 card-text JQellipsis" style="font-size:16px">
												<p class="card-text">
													${DiaryVO.diary_content}</p>
											</div>
											<div class="col-12 text-right my-auto" style="font-size: 12px">
												<span class="text-muted ml-2">日誌作者: 
												<a href="<%=request.getContextPath()%>/front-end/diary/listCusDiary.jsp?cus_Id=${DiaryVO.cus_id}">${customerSvc.getOneCus(DiaryVO.cus_id).cus_Name}</a></span>
												<span class="text-muted ml-2">發表於
													<fmt:formatDate type="date" value="${DiaryVO.diary_date}" />
												</span>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<%@ include file="page2.file"%>
			</div>

			<!-- 右邊欄位的最新日誌 -->
			<div class="col-2">
				<ul class="list-group" style="margin-top: 21.7vh; position: sticky; top: 100px;">
					<li class="list-group-item active text-center"
						style="background-color: #8fd19e; border-color: #8fd19e; font-size: 18px; border-top-left-radius: 30px; border-top-right-radius: 30px">
						最新日誌</li>
					<c:forEach var="DiaryVO" items="${listNew}" begin="0" end="4">
						<li class="list-group-item"><a
								href="<%=request.getContextPath()%>/diary/diary.do?action=getOne_For_Display&diary_no=${DiaryVO.diary_no}">
								<h6 class="newTitle">${DiaryVO.diary_title}</h6>
							</a> 
							<small class="text-muted" style="font-size:12px">日誌作者:
							
							<span class="text-muted ml-2">
							<a href="<%=request.getContextPath()%>/front-end/diary/listCusDiary.jsp?cus_Id=${DiaryVO.cus_id}">${customerSvc.getOneCus(DiaryVO.cus_id).cus_Name}</a></span>
							 
							</small>
							<FORM METHOD="get" class="text-right m-0"
								ACTION="<%=request.getContextPath()%>/diary/diary.do">
								<input type="hidden" name="action" value="getOne_For_Display" />
								<input type="hidden" name="diary_no" value="${DiaryVO.diary_no}" />
								<input type="submit" value="前往" class="btn btn-primary goBtn">
							</FORM>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>



	<!-- load JS files -->
	<!--     <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->

	<script src="<%=request.getContextPath()%>/front-end/js/jquery-1.11.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
	<!-- https://popper.js.org/ -->
	<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
	<!-- <script src="js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker -->
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.singlePageNav.min.js"></script>
	<!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
	<script src="<%=request.getContextPath()%>/front-end/slick/slick.min.js"></script>
	<!-- http://kenwheeler.github.io/slick/ -->
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.scrollTo.min.js"></script>
	<%@ include file="/front-end/indexFile/footer.file"%>
	<!-- https://github.com/flesler/jquery.scrollTo -->
	<script>
		$(function () {
			var len = 50; // 超過50個字以"..."取代
			$(".JQellipsis").each(
				function (i) {
					if ($(this).text().length > len) {
						$(this).attr("title", $(this).text());
						var text = $(this).text().substring(0, len - 1)
							+ "...";
						$(this).text(text);
					}
				});
		});
	</script>

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
				$.scrollTo('#tm-section-search', 300, {
					easing: 'linear'
				});
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