<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diaryreport.model.*"%>
<%@ page import="com.diary.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>

<jsp:useBean id="diaryReportSvc"
	class="com.diaryreport.model.DiaryReportService" />
<jsp:useBean id="diarySvc" class="com.diary.model.DiaryService" />
<%
	List<DiaryReportVO> diaryReportList = diaryReportSvc.getAll();
	List<DiaryReportVO> diaryReportListStatus1 = diaryReportList.stream()
			.filter(diaryReportVO -> diaryReportVO.getDiary_report_status() == 1).collect(Collectors.toList());
	pageContext.setAttribute("diaryReportListStatus1", diaryReportListStatus1);
%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link href="" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>Insert title here</title>
<%@ include file="/back-end/homepage/head.file"%>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--網址縮圖-->
<link rel="icon"
	href="<%=request.getContextPath()%>/back-end/images/bikeSeekerIC_horizon_bordered.png">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<!--自訂CSS-->
<%-- 	<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/Generic_Manager.css"> --%>
<%-- 	<link rel="stylesheet" href="<%= request.getContextPath()%>/back-end/css/managerList.css"> --%>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/sweetalert2.js"></script>
<title>Hotel Unus Manager</title>
</head>
<body>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<%@ include file="/back-end/homepage/middle.file"%>

	<div id="lineQA" class="tableStyle">
		<h2 class="col-12 title">
			檢舉管理
			<!-- 自己的標頭名稱功能名稱 -->
		</h2>
		<div class="col-12">


			<div class="container-fluid">

				<!--首頁包全部Col-->
				<div class="row align-items-center justify-content-around">

					<%-- 		<jsp:include page="/back-end/include/NavBar.jsp" flush="true" /> --%>

					<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

						<div class="row align-items-center justify-content-center">

							<div class="col-12 text-center">
								<div class="display-3" id="Title">檢舉管理</div>
							</div>
							<div>
								<div class="col-12 text-center">
									<div class="display-5">
										<a
											href="<%=request.getContextPath()%>/back-end/diaryreport/diary_report.jsp"
											class="btn btn-secondary btn-sm" tabindex="-1" role="button"
											aria-disabled="true">未審核</a> <a
											href="<%=request.getContextPath()%>/back-end/diaryreport/diary_report_Audited.jsp"
											class="btn btn-secondary btn-sm" tabindex="-1" role="button"
											aria-disabled="true">已審核</a>
									</div>
								</div>
							</div>
							<div class="w-100">
								<hr
									style="border-color: #EAB965; border-width: 5px; width: 80em">
							</div>

							<div class="col-10 align-middle" id="ManagerListTable"
								style="width: 80em">

								<table class="table table-hover" style="word-break: break-all">
									<thead class="thead-dark">
										<tr>
											<th scope="col">日誌名稱</th>
											<th scope="col">檢舉原因</th>
											<th scope="col">審核狀態</th>
											<th scope="col">前往查看</th>
											<th scope="col">處理</th>
										</tr>
									</thead>
									<tbody>
										<%@ include file="page1.file"%>

										<c:forEach var="DiaryReportVO"
											items="${diaryReportListStatus1}" begin="<%=pageIndex%>"
											end="<%=pageIndex+rowsPerPage-1%>">

											<tr>
												<th scope="row">${diarySvc.getOneDiary(DiaryReportVO.diary_no).diary_title}</th>
												<td>${applicationScope.diaryReportProject[DiaryReportVO.report_project]}</td>
												<td>${applicationScope.diaryReportStatus[DiaryReportVO.diary_report_status]}</td>
												<td><a
													href="<%= request.getContextPath()%>/diary/diary.do?action=getOne_For_Display&diary_no=${DiaryReportVO.diary_no}"
													target="_blank">前往</a></td>
												<td>

													<form method="post"
														action="<%=request.getContextPath()%>/diaryreport/diaryreport.do"
														name="form2">
														<input type="hidden" name="action" value="update">
														<input type="hidden" name="diary_noFromReport" value="${DiaryReportVO.diary_no}"> 
															<input type="hidden" name="diary_report_no" value="${DiaryReportVO.diary_report_no}"> 
															<input type="hidden" name="diary_report_status" value="0">
														<input type="submit" value="檢舉通過" class="dropdown-item"
															type="button">
													</form>
													<form method="post"
														action="<%=request.getContextPath()%>/diaryreport/diaryreport.do"
														name="form2">
														<input type="hidden" name="action" value="updateaudited">
														<input type="hidden" name="diary_report_no"
															value="${DiaryReportVO.diary_report_no}"> <input
															type="hidden" name="diary_report_status" value="0">
														<input type="submit" value="檢舉未過" class="dropdown-item"
															type="button">
													</form>
												</td>
											</tr>

										</c:forEach>
									</tbody>
								</table>
								<%@ include file="page2.file"%>
							</div>
						</div>
					</main>

				</div>
			</div>





			<!-- Optional JavaScript -->
			<!-- jQuery first, then Popper.js, then Bootstrap JS -->
			<%-- <script src="<%= request.getContextPath()%>/js/popper.js"></script> --%>
			<script>
				feather.replace()
			</script>
		</div>
	</div>
	<%@ include file="/back-end/homepage/footer.file"%>
</body>
</html>