<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	#nicknameCheck{
		margin-top:5px;
	}
</style>
</head>
<body> 
<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/bootstrapSetting.jsp"
		flush="false"></jsp:include>
<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/loginHeader.jsp"
		flush="false">
		<jsp:param  name="nickname" value="${ loginUser.nickname }"/>	
</jsp:include>
<div class="form_cont">
	<h1 id="registLabel">회원정보 수정</h1>
		<form id="registForm" onsubmit="return false;"> 
			<div class="form-group"> 
				<label for="inputEmail">Email address</label> 
				<input type="email" class="form-control" id="inputEmail"
					aria-describedby="emailHelp" value="${ loginUser.account }" readonly>
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
				<input type="text" class="form-control" id="inputNickname"  value="${ loginUser.nickname }" placeholder="Nickname">
				<small id="nickResult" class="form-text"></small>
			</div>
			<div class="form-group"> 
				<label for="registDate">Regist Date</label> 
				<input type="text" class="form-control" id="registDate"  value="${ loginUser.createdAt }" readonly>
			</div>
			<div class="form-group form-radio">
				<input type="radio" class="form-radio-input" name="gender" id="radioMan" value="man">
				<label class="form-radio-label" for="radioMan">남성</label>
				<input type="radio" class="form-radio-input" name="gender" id="radioWoman" value="women">
				<label class="form-radio-label" for="radioWomen">여성</label>
			</div>
			<button type="button" id="update_btn" class="btn btn-primary" >수정</button> 
		</form>
	</div>  
</body>
<script type="text/javascript">
	 var gender = "${ loginUser.gender }";
	 if (gender == "man"){
		 $("#radioMan").attr("checked",true);
	 } else { 
		 $("#radioWoman").attr("checked",true);
	 }
	 
	 var loginNickname = "${ loginUser.nickname }";
	 var user_id = "${ loginUser.id }"
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/user/updateMyInfo.js"></script>
</html>