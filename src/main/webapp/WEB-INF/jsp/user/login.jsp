<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style>
	.login_form_cont{
		margin:5% 30% 10% 30%;	 
	}

	#title{ 
		margin:10% 30% 0% 30%;
		text-align:center; 
		font-style:normal;
	}
	#loginLabel{
		margin-bottom:20px;
		color:#008080;  
		text-decoration:underline;
	}
</style>
</head>
<body>
	<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/bootstrapSetting.jsp"
		flush="false"></jsp:include>
	<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/logoutHeader.jsp"
		flush="false"></jsp:include>

	<div class="title_cont">
		<h1 id="title">그대의 레시피</h1>
	</div>
	<div class="login_form_cont">  
		<h1 id="loginLabel">LOGIN</h1>
		<form id="loginForm" action="<%=request.getContextPath()%>/api/user/login" method="post">
			<div class="form-group">
				<label for="inputEmail">Email address</label> 
				<input type="email" class="form-control" name="account" id="inputEmail"
					aria-describedby="emailHelp" placeholder="Enter email">
				<small id="emailHelp" class="form-text text-muted">
				아이디가 없다면 회원가입을 먼저 실시하세요.</small> 
			</div>
			<div class="form-group">
				<label for="inputPwd">Password</label> 
				<input type="password" class="form-control" name="password" id="inputPwd" placeholder="Password">
			</div>
			<!-- ${saveEmailUser.rememberAccount}; -->
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" name="rememberAccount"
				 id="saveEmail">
				<label class="form-check-label" for="saveEmail">아이디 저장</label>
			</div> 
			<button type="submit" id="login_btn"class="btn btn-primary">로그인</button>
			<span id="loginResult"></span> 
		</form>
	</div>  
</body> 
<script type="text/javascript">
var loginResult = "<c:out value="${loginResult}"></c:out>" 

if ( ${  empty loginUser } ){
	if( loginResult == "noAccount"){
		alert("이메일을 다시 입력해주세요.");
	} else if ( loginResult == "noPassword") {
		alert("패스워드를 다시 입력해주세요.");
	} 
} else if ( ${ not empty saveAccount }){
	 $("#inputEmail").html("${saveAccount}");
	 $("#saveEmail").prop('checked',true);
} else {
	window.location.href="/1";
}

</script>
</html>
