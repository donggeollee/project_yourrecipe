<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그대의 레시피</title>
<style>
	.form_cont {
		margin:10% 20% 10% 20%;
	}
	#registLabel{
		margin-bottom:20px;
		color:#008080;
		text-decoration:underline;  
	} 
	#idCheck{
		margin-top:5px;
	}
	
</style>
<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/bootstrapSetting.jsp"
		flush="false"></jsp:include>
<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/logoutHeader.jsp"
		flush="false"></jsp:include>
</head> 
<body>  
	<div class="form_cont">
	<h1 id="registLabel">REGIST</h1>
		<form id="registForm" onsubmit="return false;"> 
			<div class="form-group"> 
				<label for="exampleInputEmail1">Email address</label> 
				<input type="email" class="form-control" id="inputEmail"
					aria-describedby="emailHelp" placeholder="Enter email">
					<small id="emailHelp" class="form-text text-muted">&nbsp&nbsp
					 We'll never share your email with anyone else.</small>
					 <small id="idCheckResult" class="form-text"></small>
					<button type="button" class="btn btn-primary" id="idCheck">중복확인</button>
				
			</div>
			<div class="form-group">
				<label for="inputPwd">Password</label> 
				<input type="password" class="form-control" id="inputPwd" placeholder="Password">
				<small id="pwdResult" class="form-text"></small>
			</div>
			<div class="form-group">
				<label for="inputPwdCheck">Password Check</label> 
				<input type="password" class="form-control" id="inputPwdCheck" placeholder="Password Check">
				<small id="pwdCheckResult" class="form-text"></small>
			</div>
			<div class="form-group"> 
				<label for="inputNickname">Nickname</label> 
				<input type="text" class="form-control" id="inputNickname" placeholder="Nickname">
				<small id="nickResult" class="form-text"></small>
			</div>
			<div class="form-group form-radio">
				<input type="radio" class="form-radio-input" name="gender" id="radioMan" value="man">
				<label class="form-radio-label" for="radioMan">남성</label>
				<input type="radio" class="form-radio-input" name="gender" id="radioWoman" value="women">
				<label class="form-radio-label" for="radioWomen">여성</label>
			</div>
			<button type="button" id="regist_btn" class="btn btn-primary" >가입</button>
		</form>
	</div>  
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/user/registSubmit.js"></script>
</html>