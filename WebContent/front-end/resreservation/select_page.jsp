<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>

<style>
 body{
 	font-size:1rem !important;
 }
  div#s1{
			width: 150px;
			text-align: center;
			height: 50px;
			line-height: 50px;
			background-color:#c39;
			color:#fff;
			margin: 20px;
			margin-left:40px;
			font-family: webfont,Arial;
			border-radius: 10px 10px 10px 10px;
		}
   table#table-1 {
	width: 600px;
    border: 2px solid black;
    text-align: center;
    margin-left:360px;
    margin-top:10px;
  }
  
  #wrapper{
			width: 1200px;
			height:550px;
			margin:10px auto 50px;
/* 			background-color: #A62121; */
		}
	#wrapper #left{
		width:250px;
		height:500px;
 		float:left; 
	}
  #wrapper #right{
			width: 400px;
 			float:left;
 			margin-left:2%;
			text-align: center;
            padding: 0px 2px;
             box-sizing: border-box;;
		}
		#wrapper #right h1{
			color: green;
			font-size: 30px;
		}
		#wrapper #right p{
			text-align: justify;
			font-family: microsoft jhenHei;
			letter-spacing: 1px;
			line-height: 30px;/*��Z*/
			text-indent: 40px;
			color:green;/*�r��զ�*/
		}
		#wrapper #right img{
			width: 70px;
			height: 50px;/*�Ϥ�����*/
			margin: 5px;
		}
		#wrapper #finalright{
/* 			width: 450px; */
			height: 370px;
			margin: 5px;
/* 			background-image: url('images/p1.jpg');/*�����k�����*/ */
			background-size: cover;/*�I���v���Y��Acover�t�X��/contain��������*/
			float: left;
		}
		#wrapper #finalright img{
			width: 370px;
			height: 400px;/*�Ϥ�����*/
			margin: 10px;
			border:2px solid purple;
       	    display: block; /*��ܦ��϶����A*/
       	    padding:5px; /*���Z*/
		}
  h4 {
    color: blue;
    display: inline;
  }
  
  h3 {
    color : blue;            /* �Ŧ� */
    font-style : italic
  }
  
  ul li{
     color : blue;            /* �Ŧ� */
     font-style : italic;
     font-size:20px;
     
  }
  
  #right, #finalright{
  	margin-left:50px !important;
  }
  input {
       background-color:#c39;
       color:#fff;
       border-radius:10px;
       cursor:pointer;}
</style>

   <script type="text/javascript">

		function showBig(){
			var picinfo=event.target.id;
			var picname=event.target.name;
			//alert(picinfo);//��K�[�� ��id�ᵹpicinfo
			var bigPic=document.getElementById('finalright').innerHTML="<img src= images/"+picinfo+".png><p>"+picname+"</p>";
			
		}
	</script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>�e�x�q��</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <!-- <link rel="stylesheet" type="text/css" href="css/datepicker.css"/> -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/slick/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/templatemo-style.css"> 
     <!-- Templatemo style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/multipleDropup.css">                                    <!-- Templatemo style -->
      </head>
      <body>
        <%@ include file="/front-end/indexFile/header.file" %>   
                
      <table id="table-1">
   		<tr><td><h3>Hotel Unus �\�U�q��A��</h3></td></tr>
   		
	  </table>

<div id="wrapper">
		<div id="left">
<ul>
<li><h3>�q��A��</h3></li>
</ul>
<a href='<%=request.getContextPath()%>/front-end/resreservation/addCusReservation.jsp'><div id="s1">�w�����\</div></a> 


<ul>
<li><h3>�w���d��:</h3></li>
	
<jsp:useBean id="resrSvc" scope="page" class="com.resreservation.model.ResReservationService" />
   
 
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/resreservation/resr.do" >
       <b>�ڪ��w�� </b>
<!--        <select size="1" name="custId"> -->
<%--          <c:forEach var="resrVO" items="${resrSvc.all}" >  --%>
<%--           <option value="${resrVO.custId}">${resrVO.custId} --%>
<%--          </c:forEach>    --%>
<!--        </select> ��εn�J���o�Ȥ�id�d�߾��v�q�����-->
       <input type="hidden" name="custId" value="${customerVO.cus_Id}">
       <input type="hidden" name="action" value="getCus_For_Display">
       <input type="submit" value="�d��">
     </FORM>
  </li>
  
</ul>
                
  </div>
		<div id="right">
		<h1>�\�U��T</h1>
			<p>12�ϥ��}�񦡪������Ʋz�ϡA���ѻ��ȧ�s�A�h���������ɨ��C
			���U������12�ϥ��}�񦡪������Ʋz�ϡC�]�t�F�԰ϡB�馡�Ʋz�ϡB���ءB��v�B�F�n�ȭ����B���A�Ʋz�������ϡB�{���M�����I&�ѥ]�ϡB�ΦU�����~�C
			*���جP�󥭤�(�g�@�ܶg�����\�P�U�ȯ�& �g�@�ܶg�|���\)��3-8�H��(�t)�P����\75���u�f�F����(�g�����\& �g���ܶg��ȱ��\�P�U�ȯ�)��3-8�H(�t)�P����\9���u�f�C
			�\�U�������\�I�l�N������ײ{�λ�l���V�n���תe���A�״I������U���հs�B�A�G�B�F�B�����ᶼ�ΦU�����B����I�����C</p>
			<img src="images/1.png" alt="" id="1" name="���N�F�Y��" onclick="showBig()">
			<img src="images/2.png" alt="" id="2" name="�T�M��" onclick="showBig()">
			<img src="images/3.png" alt="" id="3" name="���Ť���" onclick="showBig()">
			<img src="images/4.png" alt="" id="4" name="Ĭ���p���]" onclick="showBig()">
			<img src="images/5.png" alt="" id="5" name="���ªo����" onclick="showBig()">
			<img src="images/6.png" alt="" id="6" name="���Ŭ��s�B�N�O" onclick="showBig()">
			<img src="images/7.png" alt="" id="7" name="�����" onclick="showBig()">
			<img src="images/8.png" alt="" id="8" name="�k���ѥ]" onclick="showBig()">
		</div>
		
		<div id="finalright">
		    <img src="images/1.png" alt="">
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
<%-- 		<%@ include file="/front-end/indexFile/footer.file" %>    --%>
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