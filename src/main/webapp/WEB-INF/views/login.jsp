<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/commonLib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<script>
$(document).ready(function(){
	$('#btn').on('click',function(){
		$('#frm').submit();
	})
})
</script>
<script src="${cp}/js/js.cookie-2.2.1.min.js"></script>
<link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
<link href="${cp}/css/signin.css" rel="stylesheet">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>로그인 페이지</title>
  </head>

  <body>
  	
    <div class="container">
      <form class="form-signin" action="${cp}/main/login" method="post" id="frm">
        <h2 class="form-signin-heading">로그인을 해주세요</h2>
        <label for="inputEmail" class="sr-only">아이디</label>
        <input type="email" id="inputEmail" name="user_id" class="form-control" placeholder="Email address" required autofocus value="a001">
        <label for="inputPassword" class="sr-only">비밀번호</label>
        <input type="password" id="inputPassword" name="user_pass" class="form-control" placeholder="Password" required value="a1234">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 아이디 저장
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn">로그인</button>
      </form>
    </div> <!-- /container -->

  </body>
</html>
