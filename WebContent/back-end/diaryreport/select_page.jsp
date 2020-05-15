<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM DiaryReport: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM DiaryReport: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM DiaryReport: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllDiaryReport.jsp'>List</a> all DiaryReport.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="diaryreport.do" >
        <b>輸入檢舉編號 (如dire000001):</b>
        <input type="text" name="getDiary_report_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>


  <jsp:useBean id="diaryReportSvc" scope="page" class="com.diaryreport.model.DiaryReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="diaryreport.do" >
       <b>選擇檢舉編號:</b>
       <select size="1" name="diary_report_no">
         <c:forEach var="diaryReportVO" items="${diaryReportSvc.all}" > 
          <option value="${diaryReportVO.diary_report_no}">${diaryReportVO.diary_report_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="diaryreport.do" >
       <b>選擇檢舉日期:</b>
       <select size="1" name="report_date">
         <c:forEach var="diaryReportVO" items="${diaryReportSvc.all}" > 
          <option value="${diaryReportVO.diary_report_no}">${diaryReportVO.report_date}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>留言管理</h3>

<ul>
  <li><a href='addDiaryMessage.jsp'>Add</a> a new diarymessage.</li>
</ul>

</body>
</html>
