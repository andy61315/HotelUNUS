<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.diary.model.*"%>
<%@ page import="com.diarymessage.model.*"%>
<%@ page import="com.diaryreport.model.*"%>
<%@ page import="com.cus.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--  	EmpServlet.java(Concroller), 存入req的diaryVO物件 -->
<% 


	DiaryMessageVO diaryMessageVO = (DiaryMessageVO) request.getAttribute("diaryMessageVO");
	DiaryVO diaryVO = (DiaryVO) request.getAttribute("diaryVO");
 	DiaryMessageService diaryMessageSvc = new DiaryMessageService();
 	String diary_no = null;
 	System.out.println(diaryVO);
 	if(diaryVO != null){
 		diary_no = diaryVO.getDiary_no();
 	}else{
 		diary_no = request.getParameter("diary_no");
 	}
//   	List<DiaryMessageVO> msgList =(List<DiaryMessageVO>) request.getAttribute("msgList"); 
 	List<DiaryMessageVO> msgList = diaryMessageSvc.getAllMsg(diary_no);
	pageContext.setAttribute("msgList", msgList);
	
	CustomerService cusService = new CustomerService();
// 	if(diaryMessageVO != null){
//  		String cus_name = cusService.getOneCus(diaryVO.getCus_id()).getCus_Name();
// 	 	pageContext.setAttribute("cus_name", cus_name);
// 	}
	//給檢舉用
	DiaryReportVO diaryReportVO = (DiaryReportVO) request.getAttribute("diaryReportVO");
	DiaryReportService diaryReportSvc = new DiaryReportService();
	List<DiaryReportVO> reportList = diaryReportSvc.getAll();
	List<Integer> reportStatusList = new ArrayList<Integer>();
	//要+會員session
%>
<html lang="en">
<head>
	<script src="<%=request.getContextPath()%>/front-end/ckeditor/ckeditor.js"></script>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">                                    <!-- Templatemo style -->
      </head>
      <body>
        <%@ include file="/front-end/indexFile/header.file" %>   
<!-- co -->               
<!-- co -->      
<!-- 內容 -->
<!-- 日誌部分 -->
<!-- 第一層 -->
<!-- 登入狀態 -->
		<div class="col-2"></div>
        
            <nav aria-label="breadcrumb" style="margin-left: -15px;">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/select_page.jsp">Home</a></li>
                        <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/front-end/diary/addDiary.jsp">新增日誌</a></li>
                        
                </ol>
            </nav>
            <div class="row justify-content-center align-items-center p-4" style="margin-top: 30px;background-color: #e9ecef;">

                <div class="col-12">
                    <h1 style="color: #1c7430; font-weight:900;margin-bottom: 1rem"><%=diaryVO.getDiary_title()%></h1>
                </div>
<jsp:useBean id="customerSvc" class="com.cus.model.CustomerService" />
                <div class="col-6">   	 
           			 <span class="text-muted mr-2"><b>${customerSvc.getOneCus(diaryVO.cus_id).cus_Name}&nbsp;&nbsp;&nbsp;</b></span>
                     <span class="text-muted mr-2">
                    <fmt:formatDate type="date" value="${diaryVO.diary_date}"/> 發表&nbsp;&nbsp;&nbsp;</span>
               <span class="text-muted mr-2"><a href="<%=request.getContextPath()%>/front-end/diary/listCusDiary.jsp?cus_Id=${diaryVO.cus_id}" class="btn btn-info">查看會員日誌</a></span>     
      
                </div>

                <div class="col-6 text-right">
                        <span><button type="button" class="btn control" data-toggle="modal" data-target="#exampleModalCenter"><img src="<%= request.getContextPath()%>/front-end/img/Diary_Report_Icon.png" style="width: 20px; height: 20px;" alt="檢舉日誌">檢舉日誌</button></span>

                        

                        <form method="post" action="<%=request.getContextPath()%>/diary/diary.do" style="display: inline">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="diary_no" value="<%=diaryVO.getDiary_no()%>">
                            <button type="submit" class="btn control"><img src="<%= request.getContextPath()%>/front-end/img/Edit_Diary_Icon.png" style="width: 20px; height: 20px;" alt="編輯日誌">編輯日誌</button>
                        </form>
                </div>

                <div class="col-12">
                    <hr style="border: 0.5px solid #AAAAAA">
                </div>


                <div class="col-11" id="diary_content">
                    <%=diaryVO.getDiary_content()%>
                </div>

                <div class="col-11">
                    <hr style="border: 0.5px solid #AAAAAA">
                </div>

                <div class="col-11">
                    <c:if test="${commentList!=null}">
                        <table id = "commentTable" class="table table-sm table-striped">
                            <tbody>
                                <c:forEach var="DiaryMessageVO" items="${commentList}">
                                    <tr>
                                        <td style="vertical-align: middle; width: 110px">
                                            <div class="row justify-content-center align-items-center">
                                                <div class="col-12 text-center mt-1 mb-1">
                                                    <img src="<%= request.getContextPath()%>/customer/cus.do?action=showOthersImg&cus_Id=${DiaryMessageVO.cus_id}" style="object-fit: cover; width: 60px;height: 60px;border-radius: 100%">
                                                </div>
                                                <div class="col-12 text-center">
                                                    <a href="<%=request.getContextPath()%><c:if test="${DiaryMessageVO.cus_id eq sessionScope.customerVO.cus_Id}">/front-end/mem/member.jsp</c:if><c:if test="${DiaryMessageVO.cus_id ne sessionScope.customerVO.cus_Id}">/customer/cus.do?action=getOne_For_Display&cus_Id=${DiaryMessageVO.cus_id}</c:if>">${customerSvc.getOneCus(DiaryMessageVO.cus_id).cus_Name}</a>
                                                </div>

                                            </div>
                                        </td>
                                        <td style="vertical-align: middle">
                                            <div class="row align-items-center justify-content-center">
                                                <div class="col-12">
                                                    ${DiaryMessageVO.diary_message_content}
                                                </div>
                                             
                                                <div class="col-12 text-right">
                                                    <small><fmt:formatDate type="both" value="${DiaryMessageVO.diary_message_date}"/></small>
                                                </div>
                                            </div>
                                        </td>
                                       
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>

                       <form method="post" action="<%=request.getContextPath()%>/diarymessage/diarymessage.do" name="form1">
					<input type="hidden" name="action" value="insert"> 
					<input type="hidden" name="diary_no" value="${diaryVO.diary_no}">
					<input type="hidden" name="cus_Id" value="${sessionScope.customerVO.cus_Id}">
						<input type="text" name="content">
				
<!-- 						<textarea name="content" id=content rows="10" cols="80"></textarea> -->
<!-- 							<script>								 -->
<!-- 							CKEDITOR.replace( 'content', {height:['200px']}); 							 -->
<!-- 							</script> -->
<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<a style="color: red">${message}</a>
							</c:forEach>
						</ul>
					</c:if>
                            <button id="insertComment" type="submit" class="btn btn-info">留言</button>

                        </form>

            </div>
        
           
        
 <!-- 頁腳 -->
    <div class="row">
        <div class="col-12 h-25" id="FooterCol">
            <div class="row align-items-center h-25" id="FooterBackground">
                <div class="col-12 col-md-2"></div>
                

                
            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">我對這篇日誌有疑慮</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form METHOD="post" ACTION="<%=request.getContextPath()%>/diaryreport/diaryreport.do">
                        <input type="hidden" name="action" value="insertReport"></input>
                        <input type="hidden" name="diary_no" value="<%=diaryVO.getDiary_no()%>"/>
                        <input type="hidden" name="cus_id" value="${sessionScope.customerVO.cus_Id}"/>
                        <c:forEach var="diaryReportProjectEntrySet" items="${diaryReportProject}">
							<input type="radio" name="report_project" size="45" id="${diaryReportProjectEntrySet.value}" ${(diaryReportProjectEntrySet.value == '與飯店無關' )?'checked="true"':''}value="${diaryReportProjectEntrySet.key}" />
							<label for="${diaryReportProjectEntrySet.value}">${diaryReportProjectEntrySet.value}</label><br>
						</c:forEach>
<!--                    <textarea name="report_project" placeholder="請輸入原因" cols="43" rows="10"></textarea> -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消	</button>
                            <input	type="submit" value="送出檢舉"  class="btn btn-primary">
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    

<%-- 	<jsp:include page="/front-end/homepage/content.jsp"/> --%>


		



		
			
				
		
		<table>
<!-- 		<tr> -->
			
<!-- 			<td style="vertical-align: middle" class="text-center"> -->
<%-- 				<form method="post" ACTION="<%=request.getContextPath()%>/diarymessage/diarymessage.do" name="form4" class="m-0"> --%>
<!-- 					<input type="hidden" name="action" value="delete">  -->
<!-- 					<input type="hidden" name="diary_no" value="">  -->
<!-- 					<input type="hidden" name="diaryCom_no" value=""> -->
<!-- 					<button type="submit" class="btn control" data-toggle="tooltip"	data-placement="right" title="檢舉留言"> -->
<%-- 						<img src="<%=request.getContextPath()%>/front-end/img/Diary_Report_Icon.png" style="width: 30px; height: 30px;" alt="檢舉留言">檢舉留言 --%>
<!-- 					</button> -->
<!-- 				</form> -->
<!-- 			</td> -->
			<!-- end of 刪除留言 -->
<!-- 		</tr> -->
		<!-- end of 新增留言 -->
		<!-- 該篇文章的留言 -->
		<!-- 該篇文章的留言 -->
		<!-- 該篇文章的留言 -->
		<!-- 該篇文章的留言 -->
		<c:forEach var="message" items="${msgList}">
		<div colspan="6" style="background-color:#FFF8D7;padding:20px;margin-bottom:5px;">
		${sessionScope.customerVO.cus_Name}&nbsp;&nbsp;&nbsp;${message.diary_message_content}
		</div>	
				 
		
		</c:forEach>
		
	</table>

    <script>
 
</script>

                
                
                
                
                
                
                
           
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

<!--     </script> -->
    <script> 
        $(function(){

        	        $('[data-toggle="tooltip"]').tooltip();
        	
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