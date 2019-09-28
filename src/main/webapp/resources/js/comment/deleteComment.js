
function deleteComment(commentId){
	
	$.ajax({ 
		url: "/api/comment/"+commentId, 
		type:"delete",
		contentType: "application/json",
		dataType: "json", 
		success:function(response){
			alert("해당 댓글이 삭제되었습니다.")
			location.reload();
		},
		error:function(result){
			alert('댓글 등록 과정에서 문제 발생 - 관리자 문의 요망');
		}
	});
	
}
 
 