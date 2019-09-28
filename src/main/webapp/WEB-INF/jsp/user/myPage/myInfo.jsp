<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.myInfo_title{
		margin:2% 10% 5% 10%;
		text-align:center;
	}
	.btn-link{
		margin:5% 10% 0% 10%;
	}
	.form_cont{
		margin:0% 10% 10% 10%;
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

<h1>나의 레시피</h1>
<div class= "btn-link">
<a href="<%=request.getContextPath()%>/user/myPage/myLecipe" class="btn btn-danger">나의 레시피</a>
<a href="<%=request.getContextPath()%>/user/myPage/myRecommend" class="btn btn-warning">추천한 레시피</a>
<a href="<%=request.getContextPath()%>/user/myPage/myInfo" class="btn btn-primary">내 정보 수정</a>
</div>
<h1 class="myInfo_title">나의 회원정보</h1>
	<div class="form_cont"> 
		<div class="form-group"> 
			<label for="inputEmail">Email address</label> 
			<input type="email" class="form-control" id="inputEmail" value="${ user.account }"
				aria-describedby="emailHelp" readonly>
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
			<input type="text" class="form-control" id="inputNickname" 
			value="${ user.nickname }" placeholder="Nickname">
			<small id="nickResult" class="form-text"></small>
		</div>
		<div class="form-group form-radio">
			<label for="inputGender">Gender</label> 
			<input type="text" class="form-control" id="inputGender" 
			value="${ user.gender eq 'man' ? '남성' : '여성' }" readonly>
		</div>
		<button type="button" id="update_btn" class="btn btn-primary">수정하기</button>
		<button type="button" id="delete_btn" class="btn btn-dark">탈퇴하기</button>
	</div>  
</body>
<script type="text/javascript">
	var loginNickname = "${user.nickname}";
	var user_id = ${user.id};
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/user/updateMyInfo.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/user/deleteAccount.js"></script>
</html>