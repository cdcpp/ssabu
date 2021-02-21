<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String myctx = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>싸이월드의 부활 싸부!</title>
	<!--<meta name="viewport" content="width=device-width, initial-scale=1">
-->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript" src="<%=myctx%>/js/main.js"></script>
    <script type="text/javascript" src="<%=myctx%>/js/login.js"></script>
    
</head>
<body>
	<div id="flower1">✿</div>
    <div id="flower2">✿</div>
    <div id="flower3">✿</div>
    <div id="flower4">✿</div>
    <div id="flower5">✿</div>
    <div id="flower6">✿</div>
    <div id="flower7">✿</div>
    <div id="flower8">✿</div>
    <div id="flower9">✿</div>
    <div id="flower10">✿</div>
   
    <div id="loginBackground">
        <div id="loginCircle">
            <p id="lcLogo">SSABU</p>
            <form id="loginF" action="subLoginInfo" method="POST" onSubmit="return loginCheck()">
                <input class="text" type="text" id="idInputbox" name="email" placeholder="이메일">
                <input type="password" id="pwdInputbox" name="pwd" placeholder="비밀번호">
                <br><br> 
                <button class="btn btn-secondary" id="loginSub">
                    로그인
                </button>
            </form>
            <div id="registerSection">
                <a href="findUser">아이디/비밀번호찾기</a>
                <span>|</span>
                <a href="join">회원가입</a>
            </div>
        </div>
    </div>
</body>
</html>