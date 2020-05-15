<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    RestaurantService restSvc = new RestaurantService();
    List<RestaurantVO> list = restSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="resrSvc" scope="page" class="com.resreservation.model.ResReservationService" /> --%>

<!DOCTYPE html>
<html lang="en">
<head>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css">
                         <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">                
<title>�\�U�C��</title>

<style>
  table#table-1 {
    width: 900px;
	margin-left: 182px;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 60px;
  }
  table, th, td {
    border: 1px solid green;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
<style type="text/css">
		body{
			margin:0px;/*�M���w�]�~�Z*/
/* 			background-color: #000; �I���¦� */
			font-family: microsoft jhengHei;
		}
		#wrapper{
			width: 900px;
			margin: 0px auto;/*�W�U/���k���Z*/
/* 			background-color: pink; */
            
		}
		#wrapper #content p{
			
			margin: 0px;
			letter-spacing: 1px;
			text-align: justify;/*�峹���k���*/
			text-indent: 40px;/*�����Y��*/
			color:black;
		}
		#wrapper #cf{
			text-align: center;

		}
		#wrapper #cf img{
			width: 250px;
			height: 200px;
			margin:20px;/*900-(250*3)/6=25 �̦h�~�Z�d��*/
		}
 		#wrapper #banner img{ 
 			width: 900px; 
 			margin-bottom:10px;
 			 
 		} 
		
		
	</style>
</head>
<body bgcolor='white'>
<%@ include file="/front-end/indexFile/header.file" %>




<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
		 <h3 style="text-align:center;">�����W</h3>
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div id="wrapper">
		<div id="banner"><img src="images/big.png"></div>
		<div id="content">
			<!-- p>lorem200 -->
			<p>���ַ��ε������[�A������e�L�ͬ��ҧ����������H�A�ɨ��I�R�o�S���G�}�諸��^����C
			�m�O�_�̨�L���n 2018 & 2019�n�̨�L�G�P�\�U���P�M�u�f�t�Ʋz����ζW�L20�~�ѭ����N�u�\�ҧe�{���e�C�t�����ضQ���]�[�A�����U���b�|�ݨD�B��O�@�F�B�}�|�����γ��ҡC
			  ���U������12�ϥ��}�񦡪������Ʋz�ϡC�]�t�F�԰ϡB�馡�Ʋz�ϡB���ءB��v�B�F�n�ȭ����B���A�Ʋz�������ϡB�{���M�����I&�ѥ]�ϡB�ΦU�����~�C
			 �D�p���ˡG����@�~���B���I�̯����B�B�}�\�l�Y�BĬ���p���]�B�ªo�����B���o�汲�B�w����׵��B���t�|�u���B�w�ԥֳJ�B�����ܡB�ʭ����G���B�ե��sŽ�ءB�l���خ絥�h�D�z���H�f���a�C
			 SUKHOTHAI�ë��۶ǲή���椧�f���A�`�J�ܤƤ������A���e�{�X���H���A�s�����A�A��Ź�ȹ����檺��O�L�H�C�ǻ��������겱�@�A�����������Q���C
			 �D�p���ˡG�c�ʻĲ����稧�A���F�ԡB�ܬ��G���۴����B��G����B���F�ҩ@�����v�C
			 �Y���O�����A�H��諸�馡�Ʋz�\�ҤνL�����N�e�{��o�h�ۮƲz���D�a�����A��ߥ��y����s�b���n�ɩ|�����C���A�����O�d�ǲΨʳ��������c��VIP�[�СC�~���߮�����W�A�e�����P����s�O�z�̨Ϊ���ܡC
			 ���Ť��ơA���P��������C�j�嶮�P���w�F�U�A�@�ɹU�s�׶��A��m�e�{�U�����ƭ����W�b�C�m�O�_�̨�L���n 2018�n�̨�L�J�ѱ��˳��Ť��ơB�f��D�Zĭ�t������²�����A�e�{���Ť��ƪ��u��y�O�A�@�ɦW�s�@���ѥ��A�M�~�K�ߪ�����A�ȡA�~�������������g��զX�C
			�D�p���ˡG�g���s���@���B���곻���Q�N�ز����ơB����D�e�A�����©M��30�Ѱ��������B�����ơB���N���������������³b�a���ز��B�k��ծ�έ���B�N�O</p>
		</div>
		<div id="cf">
			<img src="images/1.png" alt="">
			<img src="images/2.png" alt="">
			<img src="images/3.png" alt="">
		</div>
<table>
	<tr>
		<th>�Ա�</th>
		<th>�\�U</th>
		<th>�y���</th>
		<th>�q��q��</th>
		<th>�Y��w��</th>
		
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="restVO" items="${list}" >
		
		<tr>
			<td><a href='<%=request.getContextPath()%>/front-end/restaurant/rest.do?action=getOne_For_FrontDisplay&resNo=${restVO.resNo}'>�`�N�ƶ�</a></td>
			<td>${restVO.resName}</td>
			<td>${restVO.totalSeat}</td>
			<td>${restVO.resPhone}</td>
			<td><a href='<%=request.getContextPath()%>/front-end/resreservation/select_page.jsp'>�q��</a></td>
			
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>
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
</body>
</html>