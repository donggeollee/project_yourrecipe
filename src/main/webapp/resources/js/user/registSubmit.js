/**
 * 
 */
$(function(){
	var obj = new Object();
	obj.transactionTime = " ";
	obj.resultCode = " "
	obj.description = " ";
	
	var registCheck = new Object();
	registCheck.idCheck = false;
	registCheck.pwCheck = false;
	registCheck.nickCheck = false;
	registCheck.genderCheck = false;
	
	$("#idCheck").on("keyup",function(){
		registCheck.idCheck = false;
	})
	// id 중복검사
	$("#idCheck").on("click",function(){
		var account = $("#inputEmail").val(); 
		if( account.indexOf("@") == -1 ){
			$("#idCheckResult").css("color","red").html("Email 형식에 맞춰 작성해주십시오.");
			return;
		} else {
			if ( !account.endsWith(".com") &&
					!account.endsWith(".net") &&
					!account.endsWith(".co.kr")){
				$("#idCheckResult").css("color","red").html("Email 형식에 맞춰 작성해주십시오.");
				return;
			}
		}
		$.ajax({ 
			
			url: "/api/user/idCheck", 
			type:"post",
			contentType: "application/json",
			data: account,
			dataType: "json",
			success:function(response){
				// true면 사용가능
				if( response.data == "true" ){
					registCheck.idCheck = true; 
					$("#idCheckResult").css("color","green").html("사용가능한 Email입니다.");
					return;
				} else {
					$("#idCheckResult").css("color","red").html("이미 사용중인 Email입니다.");
				}
				
			},
			error:function(result){
				alert('회원 가입 과정에서 문제 발생 - 관리자 문의 요망');
			}
		});
		registCheck.idCheck = false;
	});
	
	$("#inputPwd").on("keyup",function(){
		registCheck.pwCheck = false;
		var inputPwd = $("#inputPwd").val();
		if( inputPwd.length < 8 )
			$("#pwdResult").css("color","red").html("패스워드 길이는 8글자 이상이어야 합니다.");
		else 
			$("#pwdResult").css("color","green").html("사용가능한 패스워드입니다.");
	});
	$("#inputPwdCheck").on("keyup",function(){
		registCheck.pwCheck = false;
		var inputPwd = $("#inputPwd").val();
		var inputPwdCheck = $("#inputPwdCheck").val();
		if( inputPwd != inputPwdCheck ){
			$("#pwdCheckResult").css("color","red").html("위에서 입력한 패스워드와 일치해야 합니다.");	
		} else {
			$("#pwdCheckResult").css("color","green").html("패스워드가 일치합니다.");
			registCheck.pwCheck = true;
		}
	});
	
	$("#inputNickname").on("keyup",function(){
		var nickName = $("#inputNickname").val();
		if ( nickName.length < 1 ){
			$("#nickResult").css("color","green").html("닉네임을 작성해주세요");
			registCheck.nickCheck = false; 
			return;
		}
		 
		$.ajax({ 
			url: "/api/user/nickCheck", 
			type:"post",
			contentType: "application/json",
			data: nickName,
			dataType: "json",
			success:function(response){
				// true면 사용가능
				if( response.data == "true" ){ 
					registCheck.nickCheck = true;
					$("#nickResult").css("color","green").html("사용가능한 닉네임입니다.");
					return;
				} else {
					$("#nickResult").css("color","red").html("이미 사용중인 닉네임입니다.");
				}
			}, 
			error:function(result){
				alert('회원 가입 과정에서 문제 발생 - 관리자 문의 요망');
			}
		});
		registCheck.nickCheck = false;
	});
	
	
	$("#regist_btn").on("click",function(){
		
		if ( !registCheck.idCheck ){
			alert("이메일 입력을 확인해주세요.");
			return;
		} else if( !registCheck.pwCheck ) {
			alert("패스워드 입력을 확인해주세요");
			return;
		} else if( !registCheck.nickCheck ){
			alert("닉네임 입력을 확인해주세요");
			return;
		} else if( !$("input[name=gender]:checked").val() ){
			alert("성별을 선택해주세요");
			return;
		}
		
		var data = new Object();
		data.account = $("#inputEmail").val();
		data.password = $("#inputPwd").val();
		data.nickname = $("#inputNickname").val();
		data.gender = $("input[name=gender]:checked").val();
		
		obj.data = data;
		
		var request = JSON.stringify(obj);
		
		$.ajax({ 
			 
			url: "/api/user", 
			type:"post",
			contentType: "application/json",
			data: request,
			dataType: "json", 
			success:function(response){
				alert(response.data.nickname+"님 가입을 축하합니다.");
				window.location.href = "/1"; 
			},
			
			error:function(result){
				alert('회원 가입 과정에서 문제 발생 - 관리자 문의 요망');
			}

		});
	});
})
 
 