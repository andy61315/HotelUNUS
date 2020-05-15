<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HSignin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%= request.getContextPath() %>/back-end-login/bootstrap-3.3.7.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/back-end-login/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <div class="form-signin">
      <img class="mb-4" src="<%= request.getContextPath() %>/back-end-login/UNUS.png" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">HOTEL UNUS</h1>
      <label for="inputEmail" class="sr-only"></label>
      <input type="email" id="inputEmail" class="form-control" placeholder="清輸入信箱" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" class="form-control" placeholder="請輸入密碼" required>
      <button class="btn btn-lg btn-primary btn-block signin-btn">登入</button>
      <button class="btn btn-lg btn-primary btn-block emp-magic-btn">神奇小按鈕</button>
    </div>
    
    <script src="<%= request.getContextPath() %>/back-end-login/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
	
    $(document).ready(function(){
        <c:if test="${not empty sessionScope.emp_id}">
        	signedCheck();
    	</c:if>
    });
    
    $('.signin-btn').on('click', function() {
		let param = {};
		param.action = 'empSignin';
		param.emp_email = $('#inputEmail').val();
		param.emp_password = $('#inputPassword').val();
		
		
		console.log(param.action);
		console.log(param.emp_email);
		console.log(param.emp_password);
		
		empSignin(param);
		
	});
    
    // 神奇小按鈕
    $('.emp-magic-btn').on('click', function() {
		$('#inputEmail').val("Limberger5@gmail.com");
		$('#inputPassword').val("123456");
	});
    
    // 登入
	function empSignin(param) {
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/employee/emp.do",
			 data: param,
			 dataType: "json",
			 // 引導到 template.jsp
			 success: function() {
				 		signedCheck();
			 		  },
			 // 失敗顯示錯誤訊息並把 input 裡面的東西清空
             error: function() {
		            	alert("登入失敗!  請確認帳號或是密碼是否正確!");
		            	$('#inputEmail').val("");
		            	$('#inputPassword').val("");
            	 	}
         });
	};
	
	// 登入檢測, 有登入的導向到 content.jsp
	function signedCheck() {
		window.location.href = "<%= request.getContextPath() %>/back-end/index.jsp";
	};
	
    </script>
  </body>
</html>