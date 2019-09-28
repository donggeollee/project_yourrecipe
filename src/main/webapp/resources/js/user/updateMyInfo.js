/**
 * 
 */
$(function(){
	var obj = new Object();
	obj.transactionTime = " ";
	obj.resultCode = " "
	obj.description = " ";
	
	var pwCheck = false;
	var nickCheck = false;
	
	
	$("#inputPwd").on("keyup",function(){
		pwCheck = false;
		var inputPwd = $("#inputPwd").val();
		if( inputPwd.length < 8 )
			$("#pwdResult").css("color","red").html("패스워드 길이는 8글자 이상이어야 합니다.");
		else 
			$("#pwdResult").css("color","green").html("사용가능한 패스워드입니다.");
	});
	$("#inputPwdCheck").on("keyup",function(){
		pwCheck = false;
		var inputPwd = $("#inputPwd").val();
		var inputPwdCheck = $("#inputPwdCheck").val();
		if( inputPwd != inputPwdCheck ){
			$("#pwdCheckResult").css("color","red").html("위에서 입력한 패스워드와 일치해야 합니다.");	
		} else {
			$("#pwdCheckResult").css("color","green").html("패스워드가 일치합니다.");
			pwCheck = true;
		}
	});
	
	$("#inputNickname").on("keyup",function(){
		var nickName = $("#inputNickname").val().trim();
		if ( nickName.length < 1 ){
			$("#nickResult").css("color","red").html("닉네임을 작성해주세요");
			nickCheck = false; 
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
					nickCheck = true;
					$("#nickResult").css("color","green").html("사용가능한 닉네임입니다.");
					return;
				} else if ( nickName == loginNickname ){
					nickCheck = true;
					$("#nickResult").css("color","green").html("사용가능한 닉네임입니다.");
				} else {
					$("#nickResult").css("color","red").html("이미 사용중인 닉네임입니다.");
				}
			}, 
			error:function(result){
				alert('회원정보 수정에서 문제 발생 - 관리자 문의 요망');
			}
		});
		nickCheck = false;
	});
	
	
	$("#update_btn").on("click",function(){
		
		if( !pwCheck ) {
			alert("패스워드 입력을 확인해주세요");
			return;
		} else if( !nickCheck ){ 
			alert("닉네임 입력을 확인해주세요");
			return;
		}
		
		var data = new Object();
		data.id = user_id;
		data.password = $("#inputPwd").val();
		data.nickname = $("#inputNickname").val();
		
		obj.data = data;
		
		var request = JSON.stringify(obj);
		
		$.ajax({ 
			url: "/api/user", 
			type:"put",
			contentType: "application/json",
			data: request,
			dataType: "json",  
			success:function(response){
				alert(response.data.nickname+"님의 회원정보가 수정되었습니다");
				alert("다시 로그인해주세요 !");
				window.location.href = "/user/login";
			},
			error:function(result){
				alert('회원정보 수정에서 문제 발생 - 관리자 문의 요망');
			}

		});
	});
})
 
 