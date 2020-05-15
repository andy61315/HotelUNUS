<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cus.model.*"%>

<%
  CustomerVO customerVO = (CustomerVO) request.getAttribute("customerVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>login</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<!-- jquery-3.5.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- bootstrap/4.5.0 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


<style type="text/css">
body{
	margin:0;
	color:#666666;
	background:rgba(255,255,255,.2);
	font:600 16px/18px 'Open Sans',sans-serif;
}
*,:after,:before{box-sizing:border-box}
.clearfix:after,.clearfix:before{content:'';display:table}
.clearfix:after{clear:both;display:block}
a{color:inherit;text-decoration:none}

.login-wrap{
	width:100%;
	margin:auto;
	max-width:525px;
	min-height:670px;
	position:relative;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.100),0 17px 50px 0 rgba(0,0,0,.19);
}
.login-html{
	width:100%;
	height:100%;
	position:absolute;
	padding:10%;
	background:rgba(0,0,0,.7);
}
.login-html .sign-in-htm,
.login-html .sign-up-htm{
	top:0;
	left:0;
	right:0;
	bottom:0;
	position:absolute;
	transform:rotateY(180deg);
	backface-visibility:hidden;
	transition:all .4s linear;
}
.login-html .sign-in,
.login-html .sign-up,
.login-form .group .check{
	display:none;
}
.login-html .tab,
.login-form .group .label,
.login-form .group .button{
	text-transform:uppercase;
}
.login-html .tab{
	font-size:22px;
	margin-right:15px;
	padding-bottom:21px;
	margin:0 15px 30px 0;
	display:inline-block;
	border-bottom:2px solid transparent;
}
.login-html .sign-in:checked + .tab,
.login-html .sign-up:checked + .tab{
	color:#fff;
	border-color:#1161ee;
}
.login-form{
	min-height:345px;
	position:relative;
	perspective:1000px;
	transform-style:preserve-3d;
}
.login-form .group{
	margin-bottom:15px;
}
.login-form .group .label,
.login-form .group .input,
.login-form .group .button{
	width:100%;
	color:#fff;
	display:block;
}
.login-form .group .input,
.login-form .group .button{
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:rgba(255,255,255,.5);
}
.login-form .group input[data-type="password"]{
	text-security:circle;
	-webkit-text-security:circle;
}
.login-form .errorMsg{
	color:rgba(230,0,0,.8);
	font-size:15px;
}
.login-form .group .button{
	background:#1161ee;
}
.login-form .group label .icon{
	width:15px;
	height:15px;
	border-radius:2px;
	position:relative;
	display:inline-block;
	background:rgba(255,255,255,.1);
}
.login-form .group label .icon:before,
.login-form .group label .icon:after{
	content:'';
	width:10px;
	height:2px;
	background:#fff;
	position:absolute;
	transition:all .2s ease-in-out 0s;
}
.login-form .group label .icon:before{
	left:3px;
	width:5px;
	bottom:6px;
	transform:scale(0) rotate(0);
}
.login-form .group label .icon:after{
	top:6px;
	right:0;
	transform:scale(0) rotate(0);
}
.login-form .group .check:checked + label{
	color:#fff;
}
.login-form .group .check:checked + label .icon{
	background:#1161ee;
}
.login-form .group .check:checked + label .icon:before{
	transform:scale(1) rotate(45deg);
}
.login-form .group .check:checked + label .icon:after{
	transform:scale(1) rotate(-45deg);
}
.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
	transform:rotate(0);
}
.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
	transform:rotate(0);
}

.hr{
	height:2px;
	margin:40px 0 30px 0;
	
}
.foot-lnk{
	text-align:center;
}

#form{
/* 	background:url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg) no-repeat center; */
	background:url(<%=request.getContextPath()%>/front-end/homepage/back.jpg) no-repeat center;
	background-size: cover;
}
</style>
</head>
<body>

<center>
<FORM METHOD="post" id="form"ACTION="<%=request.getContextPath()%>/customer/cus.do">
<div class="login-wrap">
	
	<div class="login-html">

		<input id="tab-1" type="radio" name="tab" class="sign-in" ${(hasError == 1) ?"":"checked"}>
		<label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up" ${((hasError == 1)or (param.status == "signUp")) ?"checked":""}>
		<label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form">
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label">會員信箱</label>
					<input id="cus_Em" name ="cus_Email" type="text" class="input">
				</div>
				<div class="group">
					<label for="pass" class="label">密碼</label>
					<input id="cus_Pwd" name ="cus_PassWord" type="password" class="input" data-type="password">
				</div>
				<div class="group"><br>
					<input type="submit" class="button" value="Sign In" name="action">
				</div>
			<br>
				<div style="text-align: left ;padding-left: 30%;">   
				    <c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
		
			<div class="sign-up-htm">
				<div class="group">
					<label for="user" class="label">會員信箱</label>
					<input id="cus_Email" name ="cus_Email1" type="text" class="input" value="<%= (customerVO==null)? "" : customerVO.getCus_Email()%>">
				</div>
				<div class="group">
					<label for="Name" class="label">姓名</label>
					<input id="cus_Name" name ="cus_Name1" type="text" class="input" value="<%= (customerVO==null)? "" : customerVO.getCus_Name()%>">
				</div>
				<div class="group">
					<label for="ID" class="label">身分證字號</label>
					<input id="id_Num" name="id_Num1" type="text" class="input" value="<%= (customerVO==null)? "" : customerVO.getId_Num()%>">
				</div>
				<div class="group">
					<label for="pass" class="label">密碼</label>
					<input id="cus_Password" name ="cus_Password1" type="password" class="input" value="<%= (customerVO==null)? "" : customerVO.getCus_Password()%>">
				</div>
				<br>
				<div class="group">
					<input type="submit" class="button" value="Sign Up" name="action">
				</div>
				<div class="group">
					<button type="button" class="btn btn-primary sign-magic-btn">神奇小按鈕</button>
				</div>
				<div style="text-align: left ;padding-left: 25%;">
					<c:if test="${not empty errorMsgs2}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs2}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
   

				<div class="foot-lnk">
					<label for="tab-1">已經是會員了嗎?</label>
				</div>
				
				
			</div>
		</div>
	</div>

	</div>


	</form>
</center>

<script type="text/javascript">
	$('.sign-magic-btn').on('click', function() {
		$('#cus_Email').val("jinnchang945@gmail.com");
		$('#cus_Name').val("艾斯");
		$('#id_Num').val("A152328641");
		$('#cus_Password').val("123456");
	});
</script>
</body>
</html>