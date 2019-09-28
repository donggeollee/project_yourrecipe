/**
 * 
 */
$(function(){
	
	var obj = new Object();
	obj.transactionTime = " ";
	obj.resultCode = " "
	obj.description = " ";
		
	$("#btn-create-comment").on("click",function(){
		
		if ( $("#comment-write").val().trim().length==0 ){
			alert("내용을 입력하세요.");
			return; 
		}
		
		if ( userId == "" ){
			alert("댓글 작성은 로그인 후 이용할 수 있습니다.");
			return;
		}
		var data = new Object();
		data.content = $("#comment-write").val();
		data.article_id = articleId;
		data.user_id = userId;
		 
		obj.data = data;
		
		var request = JSON.stringify(obj);
		
		$.ajax({ 
			url: "/api/comment", 
			type:"post",
			contentType: "application/json",
			data: request,
			dataType: "json", 
			success:function(response){
				location.reload();
			},
			error:function(result){
				alert('댓글 등록 과정에서 문제 발생 - 관리자 문의 요망');
			}
		});
	});
})
 
 