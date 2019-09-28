

$(function(){

	$("#delete_btn").on("click",function(){
		
		$.ajax({ 
			url: "/api/user/"+user_id, 
			type:"delete",
			contentType: "application/json",
			dataType: "json", 
			success:function(response){
				if ( response.description == "OK" ){
					alert("회원탈퇴에 성공했습니다.")
					location.href="/1";
				} else if ( response.description == "ERROR" ){
					alert("해당 계정이 존재하지 않습니다")
				}
			},
			error:function(result){
				alert('회원탈퇴 과정에서 문제 발생 - 관리자 문의 요망');
			}

		});
		
		
	})
	
	
})

function deleteRecommend(id){
	
	
}