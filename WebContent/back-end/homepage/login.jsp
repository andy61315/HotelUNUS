<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Examples</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">
</head>
<style>

 
  
.shaped{    
height:100vh;     
width:45vw;    
float:right;   
background: black url("/DA106G1/back-end/img/back01.jpg") center top no-repeat;   
background-size:cover;  
clip-path: polygon(0 0, 100% 0, 100% 100%,30% 100%);    
shape-outside: polygon(0 0, 100% 0, 100% 100%,30% 100%);   
shape-margin:0px; 
shape-outside: url(back01.jpg);   
shape-image-threshold: 0.5;
}  
.shaped2{    
height:400px;     
width:60vw;    
float:left;
position:absolute;
left:-10px;
top:250px;
background-size:cover;  
clip-path: polygon(100% 100%, 0 0 ,0 70%, 100% 100%);    
shape-outside: polygon(100% 100%, 0 0 ,0 70%, 100% 100%);     
shape-image-threshold: 0.5;
background: black url("/DA106G1/back-end/img/back01.jpg") center left no-repeat; 
} 
 
*{
	margin: 0;
	padding: 0;
	list-style: none;
}
html,body{
	height: 100vh;
	overflow:hidden;
}
body{
	/*background-color: black;*/
	background-size: cover;
		background-position: center;
		background-attachment: fixed;
		background-image: linear-gradient(to top left,black,black,#0F2540);	
	display: flex;
	justify-content: center;
	align-items: center;
	   

}

.login{
 margin-left:200px;
  width: 500px;
  height: 400px;
  background-color:rgba(0,0,0,.5);
 border-radius:20px;
  border:1px solid rgba(0,0,0,0.3);
  box-shadow:0 0 60px #000;
  background-filter:blur(3px);
  display:flex;
  justify-content:center;
  align-items:center;
  z-index:3;
  border:5px solid #fff;
}

.form{
  width: 400px;
  color:#fff;
}

.form h2{
  margin-bottom: 20px;
  /*border-bottom: 1px solid #fff;*/
}
.form .group{
  margin-bottom: 20px;
}
.form label{
  line-height: 2;
}
.form input{
  width: 100%;
  border:1px solid #aaa;
  line-height: 3;
  border-radius:5px;
}
.form .btn-group{
  font-size: 0;
}
.form .btn{
	font-size: 20px;
	border-radius: 5px;
	border:3px solid white;
	background-color:#0F2540;
	width: 190px;
	padding: 10px 0;
	color:  white;
	font-family: 'Noto Sans TC', sans-serif;
}
.form .btn + .btn{
	margin-left: 20px;
}
h1{
	margin-top: 20px;
	color:white;
}
 h3{
 	color:white;
 }
</style>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<div class="container-fluid">   
<div class="shaped"></div>   
  

<h1><span>Hotel Unus</h1>
<h3>Passez une bonne journée</h3><br/>
<div class="row">
 <div class="login">
    <form action="" class="form">
      <h2><center>員工登入</center></h2>
      <div class="group">
      <label for="user_id">帳號</label>
      <input type="text" name="" id="user_id">
      </div>
      
    
      <div class="group">
      <label for="user_password">密碼</label>
      <input type="text" name="" id="user_password">
      </div>
      <div class="btn-group">
        <button class="btn">登入</button>
        <button class="btn">取消</button>
      </div>
    </form>

  </div>
</div>
 <div  class="shaped2"></div>
</div>   


</body>
</html>